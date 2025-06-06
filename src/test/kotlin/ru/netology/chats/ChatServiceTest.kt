package ru.netology.chats

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChatServiceTest {
    private val chats: MutableList<Chat> = mutableListOf()

    @Before
    fun setUp() {
        ChatService.clear()
        ChatService.sendMessage(1, 2, "Первое сообщение")
        ChatService.sendMessage(2, 1, "Ответ")
        ChatService.sendMessage(3, 1, "Привет от третьего")
        chats.addAll(ChatService.getChats(1))
    }

    @Test
    fun getChatsTest() {
        assertEquals(2, chats.size)
        assertEquals(setOf(1,2), chats[0].participants)
        assertEquals(setOf(1,3), chats[1].participants)
        assertEquals(setOf(1,2), chats[0].unreadMessagesID)
        assertEquals(emptyList(),ChatService.getChats(999))
    }


    @Test
    fun getUnreadChatsCountTest() {
        ChatService.getMessages(1, 2, 10)
        assertEquals(1, ChatService.getUnreadChatsCount(1))
    }

    @Test
    fun getLastMessagesTest() {
        val messages = listOf("нет сообщений", "Привет от третьего")
        ChatService.deleteMessage(1,1)
        ChatService.deleteMessage(1,2)
        assertEquals(messages, ChatService.getLastMessages(1))
    }

    @Test
    fun getMessagesTest() {
        val messages = ChatService.getMessages(1,2, 1)
        val message = chats[0].messages[1]
        assertEquals(message.text, messages[0].text)
        assertEquals(setOf(1), chats[0].unreadMessagesID)
    }

    @Test(expected = ChatNotFoundException::class)
    fun getMessagesNoChatTest() {
        ChatService.getMessages(999,998,1)
    }

    @Test
    fun deleteChatTest() {
        ChatService.deleteChat(1)
        val listOfChats = ChatService.getChats(1)
        assertEquals(1, listOfChats.size)
        assertEquals(2, chats.size)
    }

    @Test(expected = ChatNotFoundException::class)
    fun deleteChatNoChatTest() {
        ChatService.deleteChat(999)
    }

    @Test
    fun sendMessageTest() {
        assertEquals(1,chats[0].messages[0].id)
        assertEquals(1, chats[0].messages[0].userID)
        assertEquals("Первое сообщение", chats[0].messages[0].text)
        assertFalse(chats[0].messages[0].isDeleted)
        assertTrue(1 in chats[0].unreadMessagesID)
    }

    @Test
    fun deleteMessageTest() {
        val result = ChatService.deleteMessage(1,1)
        assertTrue(chats[0].messages[0].isDeleted)
        assertFalse(1 in chats[0].unreadMessagesID)
        assertTrue(result)
    }

    @Test(expected = MessageNotFoundException::class)
    fun deleteMessageNoMessageTest() {
        ChatService.deleteMessage(1,999)
    }

    @Test(expected = ChatNotFoundException::class)
    fun deleteMessageNoChatTest(){
        ChatService.deleteMessage(999,1)
    }

    @Test
    fun deleteMessageDeletedMessageTest() {
        ChatService.deleteMessage(1,1)
        val result = ChatService.deleteMessage(1,1)
        assertTrue(chats[0].messages[0].isDeleted)
        assertFalse(1 in chats[0].unreadMessagesID)
        assertFalse(result)
    }

    @Test
    fun editMessageTest() {
        val result = ChatService.editMessage(1,1,"Новый текст")
        assertTrue(result)
        assertEquals("Новый текст", chats[0].messages[0].text)
    }

    @Test(expected = MessageNotFoundException::class)
    fun editMessageNoMessageTest() {
        ChatService.editMessage(1,999,"Новый текст")
    }

    @Test(expected = ChatNotFoundException::class)
    fun editMessageNoChatTest() {
        ChatService.editMessage(999,1,"Новый текст")
    }

    @Test
    fun editMessageDeletedMessageTest() {
        ChatService.deleteMessage(1,1)
        val result = ChatService.editMessage(1,1,"Новый текст")
        assertFalse(result)
    }

}