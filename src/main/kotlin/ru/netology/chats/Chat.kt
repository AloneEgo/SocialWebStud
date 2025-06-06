package ru.netology.chats

data class Chat(
    val participants: Set<Int>, // участники чата
    val messages: MutableList<Message> = mutableListOf<Message>(), //список сообщений
    val unreadMessagesID: MutableSet<Int> = mutableSetOf<Int>(), //список id непрочитанных сообщений
    var isDeleted: Boolean = false //признак удаленного чата
)