package ru.netology.attachments

class PhotoAttachments(val photo: Photo) : Attachments("photo")

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val thumbHash: String,
    val hasTags: Boolean,
    val sizes: List<Sizes>,
    val width: Int = 0,
    val height: Int = 0
)

data class Sizes(
    val type: String,
    val url: String,
    val width: Int = 0,
    val height: Int = 0
)