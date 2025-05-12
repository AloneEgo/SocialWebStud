package ru.netology.attachments

class LinkAttachment(val link: Link) : Attachment ("link")

data class Link(
    val url: String,
    val title: String,
    val caption: String?,
    val description: String,
    val photo: Photo?,
    val product: Product?,
    val button: Button?,
    val previewPage: String,
    val previewUrl: String
)

data class Product(
    val price: Price
)

data class Price(
    val amount: Int,
    val currency: Currency,
    val text: String
)

data class Currency(
    val id: Int,
    val name: String
)

data class Button(
    val title: String,
    val action: Action
)

data class Action(
    val type: String,
    val url: String
)