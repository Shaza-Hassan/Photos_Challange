package com.shaza.photoschallange

import androidx.lifecycle.ViewModel
import com.shaza.photoschallange.photolist.model.repo.PhotoRepo
import org.koin.android.annotation.KoinViewModel

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@KoinViewModel
class MainViewModel(private val photoRepo: PhotoRepo) : ViewModel() {


}