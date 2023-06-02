package com.shaza.photoschallange.photolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo
    val owner: String,
    @ColumnInfo
    val secret: String,
    @ColumnInfo
    val server: String,
    @ColumnInfo
    val farm: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo(name = "is_public")
    val ispublic: Int,
    @ColumnInfo(name = "is_friend")
    val isfriend: Int,
    @ColumnInfo(name = "is_family")
    val isfamily: Int,
    @ColumnInfo
    var ad: Boolean? = null,
    var photoUrl: String? = null
)
