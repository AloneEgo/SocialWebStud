package ru.netology.notes

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.TargetNotFoundException

class NoteServiceTest {

    @Before
    fun cleatBeforeTest(){
        NoteService.clear()
        NoteService.add("Title", "Text")
    }

    @Test
    fun addTest() {
        val id = NoteService.add("Test Title", "Test Text")
        val note = NoteService.getById(id)
        assertEquals("Test Title", note.title)
        assertEquals("Test Text", note.text)
        assertFalse(note.isDeleted)
    }

    @Test
    fun delete() {
        val id = NoteService.add("New Title", "New Text")
        val note = NoteService.getById(id)
        val result = NoteService.delete(1)
        val notes = NoteService.get()

        assertTrue(result)
        assertEquals(1, notes.size)
        assertEquals("New Title", note.title)
        assertEquals("New Text", note.text)
    }

    @Test
    fun edit() {
        val result = NoteService.edit(1, "New Title", "New Text")
        val note = NoteService.getById(1)

        assertTrue(result)
        assertEquals("New Title", note.title)
        assertEquals("New Text", note.text)
        assertFalse(note.isDeleted)
    }

    @Test
    fun get() {
        val id = NoteService.add("Test Title", "Test Text")
        NoteService.delete(id-1)
        val notes = NoteService.get()
        assertEquals(1, notes.size)
        assertEquals(id, notes[0].id)
    }

    @Test
    fun getById() {
        val id = NoteService.add("Test Title", "Test Text")
        val note = NoteService.getById(id)
        assertEquals(id, note.id)
    }

    @Test(expected = TargetNotFoundException::class)
    fun deleteDeletedTest(){
        NoteService.delete(1)
        NoteService.delete(1)
    }

    @Test(expected = TargetNotFoundException::class)
    fun getDeletedTest(){
        NoteService.delete(1)
        NoteService.getById(1)
    }

    @Test(expected = TargetNotFoundException::class)
    fun editDeletedTest(){
        NoteService.delete(1)
        NoteService.edit(1, "New Title", "New Text")
    }

}