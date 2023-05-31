package com.shaza.photoschallange.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.insertSeparators
import androidx.paging.map
import com.shaza.photoschallange.R
import com.shaza.photoschallange.databinding.FragmentPhotosBinding
import com.shaza.photoschallange.model.Photo
import com.shaza.photoschallange.ui.adapter.PhotoAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    private val viewModel: PhotosViewModel by viewModel()

    private lateinit var binding: FragmentPhotosBinding

    private lateinit var adapter: PhotoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PhotoAdapter()
        binding.photosList.adapter = adapter
        observeOnData()
    }

    fun observeOnData(){
        viewModel.getPhotosLiveData().observe(viewLifecycleOwner) {
            Log.v(this::class.java.simpleName,it.toString())
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

}