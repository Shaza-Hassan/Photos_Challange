package com.shaza.photoschallange.photolist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.insertSeparators
import androidx.paging.map
import com.google.android.material.snackbar.Snackbar
import com.shaza.photoschallange.databinding.FragmentPhotosBinding
import com.shaza.photoschallange.photolist.model.Photo
import com.shaza.photoschallange.photolist.ui.adapter.PhotoAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    private val viewModel: PhotosViewModel by viewModel()

    private lateinit var binding: FragmentPhotosBinding

    private lateinit var adapter: PhotoAdapter

    private var loading: Boolean? = true
    private var error: Boolean? = false
    private var dataLoaded: Boolean? = false

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
        listenToPaginationState()
        observeOnData()
    }

    private fun observeOnData(){
        lifecycleScope.launch {
            viewModel.getPhotosLiveData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun listenToPaginationState(){
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest{
                loading = it.refresh is LoadState.Loading
                error = it.refresh is LoadState.Error

                val errorState = when {
                    it.prepend is LoadState.Error -> it.prepend as LoadState.Error
                    it.append is LoadState.Error -> it.append as LoadState.Error
                    it.refresh is LoadState.Error -> it.refresh as LoadState.Error
                    else -> null
                }

                if (errorState?.error != null) {
                    val throwable = errorState?.error
                    Snackbar.make(requireView(),throwable?.message.toString(),Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}