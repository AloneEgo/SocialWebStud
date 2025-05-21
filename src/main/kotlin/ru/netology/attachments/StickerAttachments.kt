package ru.netology.attachments

class StickerAttachments(val sticker: Sticker) : Attachments("sticker")

data class Sticker(
    val innerType: String = "base_sticker_new",
    val stickerId: Int,
    val productId: Int,
    val isAllowed: Boolean,
    val images: List<Images>?,
    val imagesWithBackground: List<Images>?,
    val animationUrl: String?
)