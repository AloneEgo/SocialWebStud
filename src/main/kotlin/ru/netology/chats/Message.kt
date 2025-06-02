package ru.netology.chats

data class Message(
    val id: Int, //id сообщения
    val userID: Int, //id пользователя, который написал сообщение
    var text: String, //текст сообщения
    val date: Long, //дата и время сообщения
    var isDeleted: Boolean = false //признак удаленного сообщения
)
