package com.shaza.photoschallange.photolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shaza.photoschallange.photolist.model.Photo
import com.shaza.photoschallange.photolist.model.repo.PhotoRepo
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PhotosViewModel(private val photoRepo: PhotoRepo) : ViewModel() {


    private val mutableSelectedItem = MutableLiveData<Photo?>()
    val selectedItem: LiveData<Photo?> get() = mutableSelectedItem

    fun selectItem(item: Photo?) {
        mutableSelectedItem.value = item
    }
    fun getPhotosLiveData(): Flow<PagingData<Photo>> =
        photoRepo.getPhotos().cachedIn(viewModelScope)

}