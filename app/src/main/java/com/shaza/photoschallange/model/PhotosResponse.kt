package com.shaza.photoschallange.model

/**
 * Created by Shaza Hassan on 30/May/2023.
 */


data class PhotosResponse(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<Photo>,
    val stat: String
)