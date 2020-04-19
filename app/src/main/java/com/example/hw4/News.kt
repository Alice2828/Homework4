package com.example.hw4

import java.io.Serializable

data class News(
    val logo: Int = 0,
    val author: String? = null,
    val image: Int = 0,
    val data: String? = null,
    val likesCnt: Int = 0,
    val comment: Int = 0,
    var hearted: Boolean = false

) : Serializable {
    var likeBtn: Int = R.drawable.heart
}