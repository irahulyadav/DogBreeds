package com.cabo.dogbreeds

import com.cabo.dogbreeds.data.local.remote.ApiBuilder
import com.cabo.dogbreeds.data.local.remote.BreedApiService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {


    val api = ApiBuilder.buildService(BreedApiService::class.java)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //this.mainViewModel = MainViewModel(this.userService)
    }

    @Test
    fun fetchDogBreedsTest() {
        val call = api.fetchDogBreeds()
        val response = call.execute()
        print(response.body())
        assertNotNull("response is null", response.body())
        assertTrue(response.body()?.status == "success")
        assertEquals("success", response.body()?.status)
    }

    @Test
    fun fetchDogBreedImagesTest() {
        val call = api.fetchDogBreedImages("setter")
        val response = call.execute()
        print(response.body())
        assertNotNull("response is null", response.body())
        assertTrue(response.body()?.status == "success")
        assertEquals("success", response.body()?.status)
        assertNotNull("message is null", response.body()?.message)
    }

    @Test
    fun fetchDogSubBreedsTest() {
        val call = api.fetchDogSubBreeds("setter")
        val response = call.execute()
        print(response.body())
        assertNotNull("response is null", response.body())
        assertTrue(response.body()?.status == "success")
        assertEquals("success", response.body()?.status)
        assertNotNull("message is null", response.body()?.message)
    }

    @Test
    fun fetchDogSubBreedImagesTest() {
        val call = api.fetchDogSubBreedImages("setter", "english")
        val response = call.execute()
        print(response.body())
        assertNotNull("response is null", response.body())
        assertTrue(response.body()?.status == "success")
        assertEquals("success", response.body()?.status)
        assertNotNull("message is null", response.body()?.message)
    }

    @Test
    fun fetchDogImagesTest() {
        val call = api.fetchBreedImage("setter")
        val response = call.execute()
        print(response.body())
        assertNotNull("response is null", response.body())
        assertNotNull("response is null", response.message())
        assertTrue(response.body()?.status == "success")
        assertEquals("success", response.body()?.status)
        assertNotNull("message is null", response.body()?.message)
    }

    @Test
    fun fetchSubDogImagesTest() {
        val call = api.fetchSubBreedImage("setter", "english")
        val response = call.execute()
        print(response.body())
        assertNotNull("response is null", response.body())
        assertNotNull("response is null", response.message())
        assertTrue(response.body()?.status == "success")
        assertEquals("success", response.body()?.status)
        assertNotNull("message is null", response.body()?.message)
    }
}
