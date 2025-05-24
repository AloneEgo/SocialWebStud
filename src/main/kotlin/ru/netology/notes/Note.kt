package ru.netology.notes

import ru.netology.Item

data class Note(
    override val id: Int, //Идентификатор заметки
    override var isDeleted: Boolean = false, //Признак удалена ли заметка
    override val ownerId:Int = 0, //Идентификатор владельца заметки
    val title: String, //Заголовок заметки
    val text: String, //Текст заметки
    val date: Int = System.currentTimeMillis().toInt(), //Дата создания заметки в формате Unixtime
    val comments: Int = 0, //Количество комментариев
    val readComments: Int = 0, //Количество прочитанных комментариев (только при запросе информации о заметке текущего пользователя)
    val viewURL: String = "", //URL страницы для отображения заметки
    val privacyView: String, //Настройки приватности комментирования заметки
    val canComment: Int, //Есть ли возможность оставлять комментарии
    val textWiki: String = "", //Тэги ссылок на wiki
) : Item