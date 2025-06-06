package ru.netology.chats

object ChatService {
    private val chats: MutableMap<Int, Chat> = mutableMapOf()
    private var nextChatId = 1
    private var nextMessageId = 1

    fun getChats(userId: Int): List<Chat> = chats.getUserChats(userId)

    fun getUnreadChatsCount(userId: Int): Int {
        return chats.getUserChats(userId)
            .asSequence()
            .filter { it.unreadMessagesID.isNotEmpty() }
            .count()
    }

    fun getLastMessages(userId: Int): List<String> {
        return chats.getUserChats(userId)
            .asSequence()
            .map { chat ->
                chat.messages.lastOrNull { !it.isDeleted }?.text ?: "нет сообщений"
            }
            .toList()
    }

    fun getMessages(userId: Int, buddyId: Int, messagesCount: Int): List<Message> {
        val chat = findChatByUsers(userId, buddyId)
            ?: throw ChatNotFoundException("Чат не найден")

        val messages = chat.messages
            .asSequence()
            .filter { !it.isDeleted }
            .toList()
            .takeLast(messagesCount)

        val messageIds = messages.map { it.id }
        markMessagesAsRead(chat, messageIds)

        return messages
    }

    fun deleteChat(chatId: Int) =
        chats[chatId]?.apply { isDeleted = true } ?: throw ChatNotFoundException("Чат не найден")

    fun sendMessage(fromUserId: Int, toUserId: Int, text: String) {
        val message = Message(generateMessageId(), fromUserId, text, setDate())

        findChatByUsers(fromUserId, toUserId) ?: generateChat(fromUserId, toUserId)
            .apply {
                messages.add(message)
                unreadMessagesID.add(message.id)
            }
    }

    fun deleteMessage(chatId: Int, messageId: Int): Boolean {
        val chat = chats.findChatById(chatId)
        val message = chat.messages.findMessageById(messageId)
        return when {
            message.isDeleted -> {
                false
            }

            else -> {
                message.apply { isDeleted = true }
                    .also { chat.unreadMessagesID.remove(messageId) }
                true
            }
        }
    }

    fun editMessage(chatId: Int, messageId: Int, text: String): Boolean {
        val message = chats.findChatById(chatId)
            .messages.findMessageById(messageId)
        return if (message.isDeleted) false else {
            message.text = text
            true
        }
    }

    private fun generateChat(fromUserId: Int, toUserId: Int): Chat =
        Chat(setOf(fromUserId, toUserId)).also { chats[generateChatId()] = it }

    private fun generateChatId(): Int = nextChatId++

    private fun generateMessageId(): Int = nextMessageId++

    private fun markMessagesAsRead(chat: Chat, messageIds: List<Int>) {
        chat.unreadMessagesID.removeAll(messageIds)
    }

    private fun setDate(): Long = System.currentTimeMillis()

    private fun Map<Int, Chat>.getUserChats(userId: Int): List<Chat> =
        this.values.asSequence()
            .filter { chat ->
                !chat.isDeleted && userId in chat.participants
            }.toList()

    private fun findChatByUsers(user1: Int, user2: Int): Chat? = chats.values.find { chat ->
        !chat.isDeleted &&
                chat.participants == setOf(user1, user2)
    }

    private fun List<Message>.findMessageById(messageId: Int) =
        this.find { it.id == messageId } ?: throw MessageNotFoundException("Сообщение не найдено")

    private fun Map<Int, Chat>.findChatById(chatId: Int) =
        this[chatId] ?: throw ChatNotFoundException("Чат не найден")

    fun clear() = this.chats.clear().also { nextChatId = 1 }.also { nextMessageId = 1 }
}