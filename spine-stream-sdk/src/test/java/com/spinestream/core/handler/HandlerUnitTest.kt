package com.spinestream.core.handler

import io.mockk.coVerify
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HandlerUnitTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    data class TestEvent1(val name: String)
    data class TestEvent2(val name: String)

    val listenerOnEvent1 = spyk(::onEvent1)
    val listenerOnEvent2 = spyk(::onEvent2)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `publishing to nonexistent channels`() {
        val handler = Handler()

        runBlocking {
            handler.publish(TestEvent1("testEvent"))
            handler.publish(TestEvent2("testEvent"))
        }

        assertEquals(2, handler.channels.size)
    }

    @Test
    fun `subscribing and publishing from mainThread`() {
        val handler = Handler()
        val event = TestEvent1("testEvent")

        runBlocking {
            handler.subscribe(TestEvent1::class, listenerOnEvent1)
            launch(Dispatchers.Main) {
                handler.publish(event)
            }
        }

        assertEquals(1, handler.channels.size)
        coVerify { listenerOnEvent1(event) }
    }

    @Test
    fun `publishing before subscribing - shouldn't receive the event`() {
        val handler = Handler()
        val event = TestEvent1("testEvent")
        
        runBlocking {
            handler.publish(event)
            handler.subscribe(TestEvent1::class, listenerOnEvent1)
        }

        assertEquals(1, handler.channels.size)
        coVerify(exactly = 0) { listenerOnEvent1(event) }
    }

    private suspend fun onEvent1(event: TestEvent1) {}
    private suspend fun onEvent2(event: TestEvent2) {}
}