package com.cabo.dogbreeds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cabo.dogbreeds.adapter.BreedAdapter
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.di.ViewModelFactory
import com.cabo.dogbreeds.viewmodel.BreedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var breedViewModel: BreedViewModel

    lateinit var breedAdapter: BreedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        (application as MainApplication).appComponent.inject(this)
        breedViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedViewModel::class.java)
        breedAdapter = BreedAdapter(breedViewModel)

        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            //layoutManager = GridLayoutManager(context, 1)
            adapter = breedAdapter
            addOnItemTouchListener(RecyclerItemClickListener(this@MainActivity) { parentView, childView, position ->
                this@MainActivity.startActivity(
                    Intent(this@MainActivity, ImageListActivity::class.java)
                        .putExtra("breed", breedAdapter.getItemCount(position))
                )
            })
        }

        breedViewModel.breedLiveData.observe(
            this, Observer<List<BreedEntity>> {
                breedAdapter.breeds = it
            })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
