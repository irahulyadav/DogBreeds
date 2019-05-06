package com.cabo.dogbreeds.ui.imagelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cabo.dogbreeds.R
import com.cabo.dogbreeds.viewmodel.ImageListViewModel

class ImageListFragment : Fragment() {

    companion object {
        fun newInstance() = ImageListFragment()
    }

    private lateinit var viewModel: ImageListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.image_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ImageListViewModel::class.java)
    }

}
