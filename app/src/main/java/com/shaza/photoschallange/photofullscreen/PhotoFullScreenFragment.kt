package com.shaza.photoschallange.photofullscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shaza.photoschallange.databinding.FragmentPhotoFullScreenBinding
import com.shaza.photoschallange.photolist.ui.PhotosViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class PhotoFullScreenFragment : Fragment() {

    private val viewModel: PhotosViewModel by activityViewModel()

    lateinit var binding: FragmentPhotoFullScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoFullScreenBinding.inflate(inflater,container,false)
        observeOnData()
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun observeOnData() {
        viewModel.selectedItem.observe(viewLifecycleOwner) {
            binding.photo = it
            binding.toolbar.title = it?.title
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.selectItem(null)
    }

}