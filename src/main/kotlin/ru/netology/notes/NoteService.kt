package ru.netology.notes

import ru.netology.CRUDService
import kotlin.Int

object NoteService: CRUDService<Note>() {

    fun add(title: String, text: String, privacy: Int, commentPrivacy: Int,
            privacyView: String, privacyComment: String): Int{
        val newNote = Note(
            id = nextID++,
            title = title,
            text = text,
            privacyView = privacyView,
            canComment = commentPrivacy
        )
        create(newNote)

        return newNote.id
    }

    fun delete(noteId: Int): Boolean{
        return delete(noteId, false)
    }

    fun edit(noteId: Int, title: String, text: String, privacy: Int, commentPrivacy: Int,
             privacyView: String, privacyComment: String): Boolean{
        val newNote = items[getIndexById(noteId)].copy(title = title, text = text, privacyView = privacyView)
        return update(newNote)
    }

    fun get(vararg notesId: Int, userId: Int, offset: Int, count: Int, sort: Int): Note{
        for (i in 0..count){
            read()
        }
    }
}