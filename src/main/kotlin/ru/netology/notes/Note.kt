package ru.netology.notes

data class Note(
    override val id: Int, //Идентификатор заметки
    override var isDeleted: Boolean = false, //Признак удалена ли заметка
    val title: String, //Заголовок заметки
    val text: String, //Текст заметки
    val date: Int = System.currentTimeMillis().toInt(), //Дата создания заметки в формате Unixtime
) : Item