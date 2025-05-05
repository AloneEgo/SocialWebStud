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
    fun updateTest() {

        for (i in 1 .. 5){
            val rnd = Random.nextInt(1, 1000)
            WallService.add(Post(rnd,rnd+1,rnd+2,"Text $i", Comments(), Likes()))
        }

        val result = WallService.update(
            Post(999,999,999,"Update", Comments(), Likes(), id = 2))
        println(result)

        assertTrue("Обновление поста", result)

    }

}