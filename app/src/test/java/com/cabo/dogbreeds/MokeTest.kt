package com.cabo.dogbreeds

import com.cabo.dogbreeds.data.local.remote.BreedApiService
import com.cabo.dogbreeds.data.local.remote.BreedImageResponse
import com.cabo.dogbreeds.di.RetrofitModule
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

class MokeTest {
    @Inject
    lateinit var api: BreedApiService


    val assets = getTestContext().assets
    lateinit var component: TestComponent1

    @Before
    fun setUp() {
        component = DaggerTestComponent1.builder()
            .retrofitModule(RetrofitModule("https://dog.ceo"))
            .build()
        component.poke(this)
    }

    @Test
    fun fetchDogImagesTest() {

//       val file = File("/image-response.json")
//        print(file.absolutePath)
//        assert(file.exists())

        val s = GsonBuilder().create().fromJson<BreedImageResponse>(
            "{\"status\":\"success\",\"message\":\"https:\\/\\/images.dog.ceo\\/breeds\\/setter-english\\/n02100735_8736.jpg\"}",
            BreedImageResponse::class.java
        )
        assert(!s.message.isNullOrBlank())
        // checkNotNull(retrofit.provideGson())
        //checkNotNull(assets)
        // print( readFromFile("image-response.json"))


        // javaClass.getResourceAsStream()

//        val call = api.fetchBreedImage("setter")
//        val response = call.execute()
//        print(response.body())
//        Assert.assertNotNull("response is null", response.body())
//        Assert.assertNotNull("response is null", response.message().isNullOrEmpty())
//        Assert.assertTrue("Image url null", response.message().isNullOrEmpty())
//        Assert.assertTrue(response.body()?.status == "success")
//        Assert.assertEquals("success", response.body()?.status)
//        Assert.assertNotNull("message is null", response.body()?.message)
    }

    @Throws(IOException::class)
    fun readFromFile(filename: String): String {
        val streamReader = javaClass.classLoader?.getResourceAsStream(filename)
        if (streamReader == null) {
            Exception("Error in file reading")
        }
        val stringBuilder = StringBuilder()

        val b = ByteArray(4096)
        var i: Int = streamReader!!.read(b)
        while (i != -1) {
            stringBuilder.append(String(b, 0, i))
            i = streamReader.read(b)
        }
        return stringBuilder.toString()
    }
}