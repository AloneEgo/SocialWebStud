package ru.netology

fun main() {


    val post = Post(1,2,3,"Сообщение №1", Comments(), Likes())

    WallService.add(post)

    println(post)

}