package com.cabo.dogbreeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
import com.cabo.dogbreeds.data.local.BreedsDatabase
import com.cabo.dogbreeds.data.local.dao.BreedDao
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class DatabaseTest {
    private var breedDao: BreedDao? = null

    @Before
    fun setup() {
        BreedsDatabase.TEST_MODE = true
        breedDao = BreedsDatabase.getInstance(InstrumentationRegistry.getInstrumentation().context)?.breedDao()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun insertFoodItem() {
        val breed = BreedEntity("affenpinscher")
        breedDao?.insertBreedEntity(breed)
        print(breedDao?.getBreedCount())
        assert(breedDao?.getBreedCount() == 1)
        //val breedTest = getValue(breedDao?.getBreedEntityByBreed("Test"))
        //Assert.assertEquals(breed.breed, breedTest?.breed)
    }

    @Test
    fun should_Flush_All_Data() {
        breedDao?.flushBreedData()
        Assert.assertEquals(breedDao?.getBreedCount(), 0)
    }

    // Copied from stackoverflow
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>?): T? {
        if (liveData == null) {
            return null
        }
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}