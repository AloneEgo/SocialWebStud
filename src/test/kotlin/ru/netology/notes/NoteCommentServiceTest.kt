package ru.netology.notes

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.TargetNotFoundException
import kotlin.test.assertEquals

class NoteCommentServiceTest {
    @Before
    fun setUp() {
        NoteCommentService.clear()
        NoteService.add("Title1", "Text1")
        NoteService.add("Title2", "Text2")
        NoteCommentService.createComment(1, "Comment1")
    }

    @Test
    fun createCommentTest() {
        val id = NoteCommentService.createComment(1, "Comment2")
        val comments = NoteCommentService.getComments(1)

        assertEquals(2, comments.size)
        assertEquals(id, comments[1].id)
        assertEquals("Comment2", comments[1].message)
    }

    @Test
    fun deleteCommentTest() {
        NoteCommentService.deleteComment(1)
        val comments = NoteCommentService.getComments(1)

        assertEquals(0, comments.size)
    }

    @Test
    fun editCommentTest() {
        NoteCommentService.editComment(1, "New Text")
        val comments = NoteCommentService.getComments(1)
        assertEquals("New Text", comments[0].message)
    }

    @Test
    fun getCommentsTest() {
        val comment = NoteCommentService.createComment(1, "Text2")
        NoteCommentService.deleteComment(1)
        val comments = NoteCommentService.getComments(1)

        assertEquals(1, comments.size)
        assertEquals(comment, comments[0].id)
    }

    @Test
    fun restoreCommentTest() {
        val commentId = NoteCommentService.createComment(1, "Text2")
        NoteCommentService.deleteComment(commentId)
        NoteCommentService.restoreComment(commentId)
        val comments = NoteCommentService.getComments(1)
        assertEquals(commentId, comments[1].id)
        assertEquals("Text2", comments[1].message)
    }

    @Test
    fun deleteCommentByNoteIdTest() {
        NoteCommentService.deleteCommentByNoteId(1)
        val comments = NoteCommentService.getComments(1)

        assertEquals(0, comments.size)
    }

    @Test(expected = TargetNotFoundException::class)
    fun createCommentNoNoteTest(){
        NoteCommentService.createComment(999, "Error")
    }

    @Test(expected = TargetNotFoundException::class)
    fun deleteDeletedTest(){
        NoteCommentService.deleteComment(1)
        NoteCommentService.deleteComment(1)
    }

    @Test(expected = TargetNotFoundException::class)
    fun restoreNotDeletedTest(){
        NoteCommentService.deleteComment(1)
        NoteCommentService.restoreComment(1)
        NoteCommentService.restoreComment(1)
    }

    @Test(expected = TargetNotFoundException::class)
    fun editDeletedCommentTest(){
        NoteCommentService.deleteComment(1)
        NoteCommentService.editComment(1, "New Text")
    }

}