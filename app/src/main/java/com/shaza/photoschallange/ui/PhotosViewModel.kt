package com.shaza.photoschallange.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shaza.photoschallange.model.Photo
import com.shaza.photoschallange.model.repo.PhotoRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PhotosViewModel(private val photoRepo: PhotoRepo) : ViewModel() {
    fun getPhotosLiveData() : LiveData<PagingData<Photo>> = photoRepo.getPhotos().cachedIn(viewModelScope)

}