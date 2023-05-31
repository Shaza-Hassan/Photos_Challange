package com.shaza.photoschallange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaza.photoschallange.model.repo.PhotoRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@KoinViewModel
class MainViewModel(private val photoRepo: PhotoRepo) :ViewModel() {



}