package ru.netology.notes

import ru.netology.Item

data class NoteComment(
    override val id: Int, // Идентификатор комментария
    override var isDeleted: Boolean = false, //Признак удален ли комментарий
    override val ownerId: Int, //Идентификатор владельца заметки
    val noteId: Int, //идентификатор заметки
    val date: Int = System.currentTimeMillis().toInt(), //Дата создания комментария в формате Unixtime
    val message: String, //Текст комментария
    val replyTo: Int, //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий
) : Item