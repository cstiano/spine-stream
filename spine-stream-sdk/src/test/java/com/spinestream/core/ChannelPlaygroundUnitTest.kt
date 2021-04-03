package com.spinestream.core

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ChannelPlaygroundUnitTest {

    data class UserInfo(val name: String, val email: String)

    @ExperimentalCoroutinesApi
    @Test
    fun simpleChannel() {
        /*
        The Channel broadcast only to the first that receives the
        event. When the event was consumed the channel stops.
         */

        val user = UserInfo("Cris", "cris@email.com")

        val kotlinChannel = Channel<Any>()

        runBlocking {
            GlobalScope.launch {
                UserInfo::class.simpleName?.let { kotlinChannel.send(it) }
            }

            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 1: ${value}")
                }
            }

            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 2: ${value}")
                }
            }
        }

        println("Finish")

        kotlinChannel.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun broadcastChannel_simpleImplementation() {
        /*
        The BroadcastChannel sends the events to all the receivers that
        subscribed the channel.
         */

        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes", "Strawberry")

        val kotlinChannel = BroadcastChannel<String>(3)

        runBlocking {
            kotlinChannel.apply {
                send(fruitArray[0])
                send(fruitArray[1])
                send(fruitArray[2])
            }

            GlobalScope.launch {
                kotlinChannel.openSubscription().let { channel ->
                    for (value in channel) {
                        println("Consumer 1: $value")
                    }
                }
            }

            GlobalScope.launch {
                kotlinChannel.openSubscription().let { channel ->
                    for (value in channel) {
                        println("Consumer 2: $value")
                    }
                }
            }

            kotlinChannel.apply {
                send(fruitArray[3])
                send(fruitArray[4])
            }

            println("Finish")

            kotlinChannel.close()
        }
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun broadcastChannel_withoutSubscription() {
        /*
        Using consumeEach with BroadcastChannel, there's no need to
        call the subscription, the method already call inside.
         */
        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes", "Strawberry")

        val kotlinChannel = BroadcastChannel<String>(3)

        runBlocking {
            kotlinChannel.apply {
                send(fruitArray[0])
                send(fruitArray[1])
                send(fruitArray[2])
            }

            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 1: $value")
                }
            }

            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 2: $value")
                }
            }

            kotlinChannel.apply {
                send(fruitArray[3])
                send(fruitArray[4])
            }

            println("Finish")

            kotlinChannel.close()
        }
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun conflatedBroadcastChannel() {
        /*
        The ConflatedBroadcastChannel enables many subscribers as
        BroadcastChannel, but only emits the most recently sent event
        The older events are lost.
         */
        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes", "Strawberry")

        val kotlinChannel = ConflatedBroadcastChannel<String>()

        runBlocking {
            kotlinChannel.apply {
                send(fruitArray[0])
                send(fruitArray[1])
                send(fruitArray[2])
            }

            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 1: $value")
                }
            }

            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 2: $value")
                }
            }

            kotlinChannel.apply {
                send(fruitArray[3])
                send(fruitArray[4])
            }

            println("Finish")
//            readLine()

            kotlinChannel.close()
        }
    }
}