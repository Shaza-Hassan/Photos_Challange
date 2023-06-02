package com.shaza.photoschallange.photolist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shaza.photoschallange.R
import com.shaza.photoschallange.databinding.PhotoListItemBinding
import com.shaza.photoschallange.photolist.model.Photo

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
        val isAd = (position != 0 && (position+1) % 5 == 0)
        item?.let { holder.bindView(it, isAd) }
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

    fun bindView(photo: Photo, isAd: Boolean){
        photo.photoUrl ="https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
        photo.ad = isAd
        binding.photo = photo
        binding.executePendingBindings()

    }
}