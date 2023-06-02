package com.shaza.photoschallange.photofullscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shaza.photoschallange.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFullScreenFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoFullScreenFragment()
    }

    private val viewModel: PhotoFullScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photo_full_screen, container, false)
    }


}