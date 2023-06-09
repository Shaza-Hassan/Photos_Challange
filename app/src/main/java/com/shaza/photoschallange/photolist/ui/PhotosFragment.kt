package com.shaza.photoschallange.photolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.shaza.photoschallange.R
import com.shaza.photoschallange.databinding.FragmentPhotosBinding
import com.shaza.photoschallange.photofullscreen.PhotoFullScreenFragment
import com.shaza.photoschallange.photolist.model.Photo
import com.shaza.photoschallange.photolist.ui.adapter.ImageClickListener
import com.shaza.photoschallange.photolist.ui.adapter.PhotoAdapter
import com.shaza.photoschallange.shared.model.ErrorModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    private val viewModel: PhotosViewModel by activityViewModel()

    private lateinit var binding: FragmentPhotosBinding

    private lateinit var adapter: PhotoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PhotoAdapter(imageClickListener)
        binding.photosList.adapter = adapter
        listenToPaginationState()
        observeOnData()
    }

    private fun observeOnData() {
        lifecycleScope.launch {
            viewModel.getPhotosLiveData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun listenToPaginationState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                binding.loading = it.refresh is LoadState.Loading

                val errorState = when {
                    it.prepend is LoadState.Error -> it.prepend as LoadState.Error
                    it.append is LoadState.Error -> it.append as LoadState.Error
                    it.refresh is LoadState.Error -> it.refresh as LoadState.Error
                    else -> null
                }

                if (errorState?.error != null) {
                    val throwable = errorState.error
                    if (adapter.itemCount == 0) {
                        binding.error = ErrorModel(throwable.message.toString(), true)
                    }
                    else {
                        Snackbar.make(
                            requireView(),
                            throwable.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private val imageClickListener = object : ImageClickListener{
        override fun onImageClicked(photo: Photo) {
            viewModel.selectItem(photo)
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().add(R.id.main_layout, PhotoFullScreenFragment()).addToBackStack("").commit()
        }
    }

}