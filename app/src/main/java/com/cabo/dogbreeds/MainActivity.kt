package com.cabo.dogbreeds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
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

    private val breedAdapter = BreedAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        (application as MainApplication).appComponent.inject(this)
        breedViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedViewModel::class.java)

        breedAdapter.imageLoadListener = breedViewModel.breedRepository


        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            //layoutManager = GridLayoutManager(context, 1)
            adapter = breedAdapter
            addOnItemTouchListener(RecyclerItemClickListener(this@MainActivity) { parentView, childView, position ->
                val breedEntity = breedAdapter.get(position)
                this@MainActivity.startActivity(
                    Intent(this@MainActivity, ImageListActivity::class.java).putExtra("BreedEntity", breedEntity)
                )
            })
        }

        breedViewModel.breedLiveData.observe(
            this, Observer<PagedList<BreedEntity>> {
                breedAdapter.submitList(it)
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
        if (item.itemId == R.id.search) {
            startActivityForResult(Intent(this, FilterListActivity::class.java), 100)
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val breedEntity = data?.getParcelableExtra<BreedEntity>("BreedEntity")
        if (requestCode == 100 && breedEntity != null) {
            this@MainActivity.startActivity(
                Intent(this@MainActivity, ImageListActivity::class.java)
                    .putExtra("BreedEntity", breedEntity)
            )
        }
    }
}