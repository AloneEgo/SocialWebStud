package ru.netology.attachments

class VideoAttachments(val video: Video) : Attachments("video")

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: List<CoverImages>,
    val firstFrame: List<Images>,
    val date: Int,
    val addingDate: Int,
    val views: Int,
    val localViews: Int,
    val comments: Int?,
    val player: String,
    val platform: String,
    val canAdd: Boolean,
    val isPrivate: Boolean? = null,
    val accessKey: String,
    val processing: Boolean? = null,
    val isFavorite: Boolean,
    val canComment: Boolean,
    val canEdit: Boolean,
    val canRepost: Boolean,
    val canSubscribe: Boolean,
    val canAddToFaves: Boolean,
    val canAttachLink: Boolean,
    val width: Int,
    val height: Int,
    val userId: Int,
    val converting: Boolean,
    val added: Boolean,
    val isSubscribed: Boolean,
    val repeat: Boolean? = null,
    val type: String,
    val balance: Int,
    val live: Boolean? = null,
    val liveStartTime: Int,
    val liveStatus: String,
    val upcoming: Boolean,
    val spectators: Int,
    val likes: Likes,
    val reposts: Reposts
)

data class CoverImages(
    val height: Int = 0,
    val url: String,
    val width: Int = 0,
    val withPadding: Boolean? = null
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
)

data class Reposts(
    val count: Int,
    val wallCount: Int,
    val mailCount: Int,
    val userReposted: Boolean
)