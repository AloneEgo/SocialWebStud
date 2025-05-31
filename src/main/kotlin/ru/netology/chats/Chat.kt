package ru.netology.chats

data class Chat(
    val participants: Set<Int>, // участники чата
    val messages: MutableList<Message> = mutableListOf<Message>(), //список сообщений
    val unreadMessagesID: MutableList<Int> = mutableListOf<Int>(), //список id непрочитанных сообщений
    var isDeleted: Boolean = false //признак удаленного чата
)