package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class WallServiceTest {

    @Test
    fun reportCommentSuccess() {

        val testComment = Comment(1, 2,3465,"Test", Donut(false,"123"),
            0, 0, null, null, null)

        WallService.createComment(1, testComment)

        val result = WallService.reportComment(1, 1, 0)

        assertEquals("Комментарий добавлен верно", 1,result)
    }

    @Test(expected = TargetNotFoundException::class)
    fun reportCommentFailCommentID() {

        val testComment = Comment(1, 2,3465,"Test", Donut(false,"123"),
            0, 0, null, null, null)

        WallService.createComment(1, testComment)

        WallService.reportComment(1, -1, 0)

    }

    @Test(expected = TargetNotFoundException::class)
    fun reportCommentFailReason() {

        val testComment = Comment(1, 2,3465,"Test", Donut(false,"123"),
            0, 0, null, null, null)

        WallService.createComment(1, testComment)

        WallService.reportComment(1, 1, -1)

    }

    @Test
    fun createCommentSuccess() {

        val testComment = Comment(1, 2,3465,"Test", Donut(false,"123"),
            0, 0, null, null, null)

        val result = WallService.createComment(1, testComment)

        assertEquals("Комментарий добавлен верно", testComment,result)
    }

    @Test(expected = TargetNotFoundException::class)
    fun createCommentFail() {

        val testComment = Comment(1, 2,3465,"Test", Donut(false,"123"),
            0, 0, null, null, null)

        WallService.createComment(-1, testComment)

    }

    @Before
    fun clearBeforeTest() {
        WallService.clear()

        for (i in 1 .. 5){
            val rnd = Random.nextInt(1, 1000)
            WallService.add(Post(rnd,rnd+1,rnd+2,"Text $i", Comments(), Likes()))
        }
    }

    @Test
    fun addTest() {

        val result = WallService.add(Post(1,2,3,"Text", Comments(), Likes()))

        assertNotEquals("Добавление поста", 0, result.id)
    }

    @Test
    fun updateTestTrue() {

        val result = WallService.update(
            Post(999,999,999,"Update", Comments(), Likes(), id = 2))

        assertTrue("Обновление поста для существующего id", result)

    }

    @Test
    fun updateTestFalse() {

        val result = WallService.update(
            Post(999,999,999,"Update", Comments(), Likes(), id = -1))

        assertFalse("Обновление поста для несуществующего id", result)

    }

}