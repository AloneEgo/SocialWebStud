package ru.netology.notes

object NoteCommentService : CRUDService<NoteComment>() {

    fun createComment(noteID: Int, message: String): Int {
        NoteService.getById(noteID)
        val newComment = NoteComment(id = nextID++, noteId = noteID, message = message)
        create(newComment)
        return newComment.id
    }

    fun deleteComment(id: Int): Boolean {

        return delete(id, true)
    }

    fun editComment(id: Int, message: String): Boolean {
        val newComment = items[getIndexById(id)].copy(message = message)
        return update(newComment)
    }

    fun getComments(noteID: Int): List<NoteComment> {
        return items.filter { it.noteId == noteID && !it.isDeleted }
    }

    fun restoreComment(id: Int): Boolean {
        return restore(id)
    }

    fun deleteCommentByNoteId(noteId: Int) {
        for (comment in items) {
            if (comment.noteId == noteId && !comment.isDeleted) deleteComment(comment.id)
        }
    }

//сделал заранее по аналогии с удалением
//    fun restoreCommentByNoteId(noteId: Int){
//        for (comment in items){
//            if (comment.noteId == noteId) restoreComment(comment.id)
//        }
//    }
}