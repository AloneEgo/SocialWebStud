package ru.netology

import ru.netology.attachments.Attachment

data class Post(
    val replyPostId: Int,
    val ownerId: Int,
    val fromId: Int,
    val text: String?,
    val comments: Comments,
    val likes: Likes?,
    val postType: String = "post",
    val friendOnly: Boolean = false,
    val isFavorite: Boolean = false,
    val isPinned: Boolean = false,
    val attachment: List<Attachment>? = null,
    val id: Int? = 0,
    val date: Int? = 0
)