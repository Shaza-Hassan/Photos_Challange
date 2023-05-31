package com.shaza.photoschallange.model

/**
 * Created by Shaza Hassan on 30/May/2023.
 */
data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
    val ad: Boolean? = null
)
