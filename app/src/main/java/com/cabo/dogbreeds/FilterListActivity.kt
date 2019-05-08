package com.cabo.dogbreeds

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.cabo.dogbreeds.adapter.BreedFilterAdapter
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.FilterListActivityBinding
import com.cabo.dogbreeds.di.ViewModelFactory
import com.cabo.dogbreeds.viewmodel.BreedViewModel
import javax.inject.Inject

class FilterListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var breedViewModel: BreedViewModel

    lateinit var binding: FilterListActivityBinding

    lateinit var breedAdapter: BreedFilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.filter_list_activity)
        (application as MainApplication).appComponent.inject(this)
        breedViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedViewModel::class.java)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_SHOW_TITLE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        breedAdapter = BreedFilterAdapter()

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            //layoutManager = GridLayoutManager(context, 1)
            adapter = breedAdapter
            addOnItemTouchListener(RecyclerItemClickListener(this@FilterListActivity) { parentView, childView, position ->
                val breedEntity = breedAdapter.get(position)
                setResult(100, Intent().putExtra("BreedEntity", breedEntity))
                finish()
            })
        }

        breedViewModel.breedLiveData.observe(
            this, Observer<PagedList<BreedEntity>> {
                breedAdapter.submitList(it)
            })

        breedViewModel.filter.observe(this, Observer { t -> })

        breedViewModel.filter.value = null
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchMenuItem = menu.findItem(R.id.search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        breedViewModel.filter.value = if (newText.isNullOrEmpty()) null else newText
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        breedViewModel.filter.value = if (query.isNullOrEmpty()) null else query
        return true
    }

}


