package ru.netology

import ru.netology.attachments.Attachments
import java.lang.Thread

data class Comment(
    val id: Int, // Идентификатор комментария
    val ownerId: Int, //Идентификатор автора комментария
    val date: Int, //Дата создания комментария в формате Unixtime
    val text: String, //Текст комментария
    val donut: Donut, //Информация о VK ru.netology.Donut
    val replyToUser: Int, //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий
    val replyToComment: Int, //Идентификатор комментария, в ответ на который оставлен текущий
    val attachments: Attachments?, //Медиавложения комментария
    val parentsStack: List<Int>?, //Массив идентификаторов родительских комментариев
    val thread: Thread?, //Информация о вложенной ветке комментариев
    )
