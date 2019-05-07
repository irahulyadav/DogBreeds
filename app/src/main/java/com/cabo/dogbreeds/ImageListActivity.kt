package com.cabo.dogbreeds

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cabo.dogbreeds.adapter.ImageListAdapter
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.ImageListActivityBinding
import com.cabo.dogbreeds.di.ViewModelFactory
import com.cabo.dogbreeds.viewmodel.ImageListViewModel
import java.util.stream.Collectors
import javax.inject.Inject

class ImageListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: ImageListViewModel

    lateinit var binding: ImageListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.image_list_activity)
        (application as MainApplication).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageListViewModel::class.java)
        viewModel.breedEntity = intent.getParcelableExtra("breed") as BreedEntity
        title = viewModel.breedEntity?.breed?.toUpperCase()

        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(this@ImageListActivity, 2)
            adapter = ImageListAdapter()
        }

        viewModel.images.observe(this, Observer<List<String>> {
            (binding.recyclerView.adapter as ImageListAdapter).list = it
        })

        viewModel.subBreedList.observe(this, Observer<List<String>> {
            invalidateOptionsMenu()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (viewModel.subBreedList.value.isNullOrEmpty()) {
            return false
        }
        getMenuInflater().inflate(R.menu.menu_breeds, menu);

        val spinner = MenuItemCompat.getActionView(menu?.findItem(R.id.spinner)) as? Spinner

        val list = arrayListOf("ALL")
        list.addAll(viewModel.subBreedList.value!!)
        spinner?.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            list.stream().map(String::toUpperCase).collect(Collectors.toList()).toList()
        )// create the adapter from a StringArray

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.subBreed = if (position == 0) null else viewModel.subBreedList.value?.get(position - 1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        return true
    }



}
