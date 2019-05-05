package com.cabo.dogbreeds

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var breedViewModel: BreedViewModel
    val breedAdapter = BreedAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        breedViewModel = ViewModelProviders.of(this).get(BreedViewModel::class.java)

        with(recycler_view) {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 1) as RecyclerView.LayoutManager
            adapter = breedAdapter
        }

        breedViewModel.allBreeds.observe(this,
            Observer<List<BreedEntity>> {
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
