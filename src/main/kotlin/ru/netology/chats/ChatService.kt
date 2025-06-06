package ru.netology.chats

object ChatService {
    private val chats: MutableMap<Int, Chat> = mutableMapOf()
    private var nextChatId = 1
    private var nextMessageId = 1

    fun getChats(userId: Int): List<Chat> = chats.getUserChats(userId)

    fun getUnreadChatsCount(userId: Int): Int {
        return chats.getUserChats(userId)
            .count {
                it.unreadMessagesID.isNotEmpty()
            }
    }

    fun getLastMessages(userId: Int): List<String> {
        return chats.getUserChats(userId)
            .map { chat ->
                chat.messages.lastOrNull { !it.isDeleted }?.text ?: "нет сообщений"
            }
    }

    fun getMessages(userId: Int, buddyId: Int, messagesCount: Int): List<Message> {
        val chat = findChatByUsers(userId, buddyId)
            ?: throw ChatNotFoundException("Чат не найден")

        val messages = chat.messages
            .filter { !it.isDeleted }
            .takeLast(messagesCount)

        val messageIds = messages.map { it.id }
        markMessagesAsRead(chat, messageIds)

        return messages
    }

    fun deleteChat(chatId: Int) {
        val chat = chats[chatId] ?: throw ChatNotFoundException("Чат не найден")
        chat.isDeleted = true
    }

    fun sendMessage(fromUserId: Int, toUserId: Int, text: String) {
        val message = Message(generateMessageId(), fromUserId, text, setDate())

        val chat =
            findChatByUsers(fromUserId, toUserId) ?: generateChat(fromUserId, toUserId)

        chat.messages.add(message)
        chat.unreadMessagesID.add(message.id)
    }

    fun deleteMessage(chatId: Int, messageId: Int): Boolean {
        val chat = chats.findChatById(chatId)
        val message = chat.messages.findMessageById(messageId)
        return when {
            message.isDeleted -> {
                false
            }
            else -> {
                message.isDeleted = true
                chat.unreadMessagesID.remove(messageId)
                true
            }
        }
    }

    fun editMessage(chatId: Int, messageId: Int, text: String): Boolean {
        val chat = chats.findChatById(chatId)
        val message = chat.messages.findMessageById(messageId)
        return when {
            message.isDeleted -> {
                false
            }
            else -> {
                message.text = text
                true
            }
        }
    }

    private fun generateChat(fromUserId: Int, toUserId: Int): Chat {
        val newChat = Chat(setOf(fromUserId, toUserId))
        chats[generateChatId()] = newChat
        return newChat
    }

    private fun generateChatId(): Int = nextChatId++

    private fun generateMessageId(): Int = nextMessageId++

    private fun markMessagesAsRead(chat: Chat, messageIds: List<Int>) {
        chat.unreadMessagesID.removeAll(messageIds)
    }

    private fun setDate(): Long = System.currentTimeMillis()

    private fun Map<Int, Chat>.getUserChats(userId: Int): List<Chat> = this.values.filter { chat ->
        !chat.isDeleted && userId in chat.participants
    }

    private fun findChatByUsers(user1: Int, user2: Int): Chat? = chats.values.find { chat ->
        !chat.isDeleted &&
                chat.participants == setOf(user1, user2)
    }

    private fun List<Message>.findMessageById(messageId: Int) =
        this.find { it.id == messageId } ?: throw MessageNotFoundException("Сообщение не найдено")

    private fun Map<Int, Chat>.findChatById(chatId: Int) =
        this[chatId] ?: throw ChatNotFoundException("Чат не найден")

    fun clear(){
       chats.clear()
       nextChatId = 1
       nextMessageId = 1
    }
}