package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId: Int = 0

    fun add(post: Post): Post {

        val postToAdd = post.copy(id = nextId, date = System.currentTimeMillis().toInt())

        posts += postToAdd
        nextId++

        return posts.last()
    }

    fun update(post: Post): Boolean {

        val targetId = post.id

        for((index, eachPost) in posts.withIndex()){
            if (eachPost.id == targetId) {
                posts[index] = post.copy()
                return true
            }
        }

        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId = 0
    }

}