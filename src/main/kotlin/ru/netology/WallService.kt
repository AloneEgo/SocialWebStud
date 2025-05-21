package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId: Int = 1
    private var comments = emptyArray<Comment>()

    fun createComment(postId: Int, comment: Comment): Comment {

        var success = false

        for (post in posts){
            if (post.id == postId){
                comments += comment
                success = true
            }
        }

        if (success)  return comments.last()

        throw PostNotFoundException("Запись не найдена")
    }

    fun add(post: Post): Post {

        val postToAdd = post.copy(id = nextId, date = System.currentTimeMillis().toInt())

        posts += postToAdd
        nextId++

        return posts.last()
    }

    fun update(post: Post): Boolean {

        val targetId = post.id

        for ((index, eachPost) in posts.withIndex()) {
            if (eachPost.id == targetId) {
                posts[index] = post.copy()
                return true
            }
        }

        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId = 1
    }

}