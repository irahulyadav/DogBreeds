package com.cabo.dogbreeds

import android.app.Application
import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.mockito.Mockito

/**
 * Returns a mocked Context for the unit tests.
 * @return a mocked Context for the unit tests
 */
const val STRING_UNKNOWN_ERROR = "STRING_UNKNOWN_ERROR"

fun getTestContext(): Context {
    val application: Application = Mockito.mock(Application::class.java)
//    Mockito.`when`("Test").thenReturn(STRING_UNKNOWN_ERROR)
    Mockito.`when`(application.applicationContext).thenReturn(application)
    return application
}


fun ioThread(): Scheduler {
    return Schedulers.trampoline()
}

fun androidThread(): Scheduler {
    return Schedulers.trampoline()
}