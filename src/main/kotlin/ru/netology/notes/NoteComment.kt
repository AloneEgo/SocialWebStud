package ru.netology.notes

data class NoteComment(
    override val id: Int, // Идентификатор комментария
    override var isDeleted: Boolean = false, //Признак удален ли комментарий
    val noteId: Int, //идентификатор заметки
    val date: Int = System.currentTimeMillis().toInt(), //Дата создания комментария в формате Unixtime
    val message: String, //Текст комментария
) : Item