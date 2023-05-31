package com.shaza.photoschallange.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.shaza.photoschallange.R
import com.shaza.photoschallange.databinding.PhotoListItemBinding
import com.shaza.photoschallange.model.Photo

/**
 * Created by Shaza Hassan on 31/May/2023.
 */
class PhotoAdapter :  PagingDataAdapter<Photo, PhotoUserHolder>(Comparator) {
    object Comparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PhotoUserHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindView(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoUserHolder {
        val binding = inflate<PhotoListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.photo_list_item,
            parent,
            false
        )

        return PhotoUserHolder(binding)
    }
}

class PhotoUserHolder(private val binding: PhotoListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bindView(photo: Photo){
        if (photo.ad == true){
            binding.photoCardView.visibility = GONE
            binding.adView.visibility = VISIBLE
            val adRequest =  AdRequest.Builder().build()
            binding.adView.loadAd(adRequest)
        }else{
            binding.photoCardView.visibility = VISIBLE
            binding.adView.visibility = GONE
            val imageUrl = "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            binding.imageUrl = imageUrl
        }
    }
}