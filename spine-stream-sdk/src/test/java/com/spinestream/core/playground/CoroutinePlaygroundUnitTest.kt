package com.spinestream.core.playground

import kotlinx.coroutines.*
import org.junit.Test

class CoroutinePlaygroundUnitTest {

    @Test
    fun launch() {
        /*
        launch coroutine creates a background routine without blocking
        the thread that launched it. inherited the context of where it
        was called if is not defined.
         */

        GlobalScope.launch {
            println("Running launch coroutine")
        }
    }

    @Test
    fun async() {
        /*
        with async you can do a barrier to return the results until the
        end of the execution.
         */
        runBlocking {
            val asyncResult = fetchResult().await()
            println(asyncResult)
        }
    }

    @Test
    fun withContext() {
        /*
        withContext coroutine let you set a specific context to associate
        the coroutine.
         */

        runBlocking {
            // Associate with main thread
//            GlobalScope.launch(context = Dispatchers.Main) {
//                println("Main")
//            }

            // Associate with IO operations thread
            GlobalScope.launch(context = Dispatchers.IO) {
                println("IO")
            }

            // Associate with background thread
            GlobalScope.launch(context = Dispatchers.Default) {
                println("Background")
            }

            // When don't need to execute in a specific thread
            GlobalScope.launch(context = Dispatchers.Unconfined) {
                println("Unconfined")
            }
        }
    }

    // auxiliary function
    suspend fun fetchResult() = GlobalScope.async {
        delay(2000)
        "The end"
    }
}