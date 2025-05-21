package ru.netology

data class Report(
    val ownerId: Int, //Идентификатор пользователя или сообщества, которому принадлежит комментарий
    val commentId: Int, //Идентификатор комментария
    val reason: Int, //Причина жалобы
)
