package com.shaza.photoschallange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaza.photoschallange.model.PhotoRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@KoinViewModel
class MainViewModel(private val photoRepo: PhotoRepo) :ViewModel() {

    fun getImages(){
        viewModelScope.launch {
            photoRepo.getPhotos(1,20)
                .onStart {
                    Log.v(this@MainViewModel::class.java.simpleName, "Show Loading")
                }
                .catch {
                    Log.e(this@MainViewModel::class.java.simpleName,it.message.toString())
                }.onCompletion {
                    Log.v(this@MainViewModel::class.java.simpleName,"Hide Loading")
                }.collect{
                }
        }
    }

}