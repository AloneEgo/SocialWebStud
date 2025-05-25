package ru.netology.notes

object NoteService : CRUDService<Note>() {

    fun add(title: String, text: String): Int {
        val newNote = Note(
            id = nextID++,
            title = title,
            text = text
        )
        create(newNote)

        return newNote.id
    }

    fun delete(noteId: Int): Boolean {
        NoteCommentService.deleteCommentByNoteId(noteId)
        return delete(noteId, false)
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        val newNote = items[getIndexById(noteId)].copy(title = title, text = text)
        return update(newNote)
    }

    fun get(): List<Note> {
        return readAll()
    }

    fun getById(noteId: Int): Note {
        return read(noteId)
    }
}