package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addTest() {

        for (i in 1 .. 5){
            val rnd = Random.nextInt(1, 1000)
            WallService.add(Post(rnd,rnd+1,rnd+2,"Text $i", Comments(), Likes()))
        }

        val result = WallService.add(Post(1,2,3,"Text", Comments(), Likes()))

        assertNotEquals("Добавление поста", 0, result.id)
    }

    @Test
    fun updateTestTrue() {

        for (i in 1 .. 5){
            val rnd = Random.nextInt(1, 1000)
            WallService.add(Post(rnd,rnd+1,rnd+2,"Text $i", Comments(), Likes()))
        }

        val result = WallService.update(
            Post(999,999,999,"Update", Comments(), Likes(), id = 2))

        assertTrue("Обновление поста для существующего id", result)

    }

    @Test
    fun updateTestFalse() {

        for (i in 1 .. 5){
            val rnd = Random.nextInt(1, 1000)
            WallService.add(Post(rnd,rnd+1,rnd+2,"Text $i", Comments(), Likes()))
        }

        val result = WallService.update(
            Post(999,999,999,"Update", Comments(), Likes(), id = -1))

        assertFalse("Обновление поста для несуществующего id", result)

    }

}