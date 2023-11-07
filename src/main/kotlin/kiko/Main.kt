package kiko

import com.petersamokhin.vksdk.core.api.VkRequest
import com.petersamokhin.vksdk.core.api.botslongpoll.VkBotsLongPollApi
import com.petersamokhin.vksdk.core.client.VkApiClient
import com.petersamokhin.vksdk.core.model.VkSettings
import com.petersamokhin.vksdk.http.VkOkHttpClient
import kotlinx.coroutines.*

// Объявление функции C++
external fun nativeAdd(a: Int, b: Int): Int

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    withContext(Dispatchers.IO) {
        launch {
            // From here: https://vk.com/club151083290 take the ID
            val groupId = 223313852

// Read more: https://vk.com/dev/access_token
            val accessToken =
                "vk1.a.3PKrKVuKIGXqlEk0R_AUYOi3HM2k6QjVswY5hwjIuIz9HoEqWLyr_d3P1e55GhS22_EKeAkNPZp1Oh3om_-9wMmE6Yyjh24i9AUu8rkqFrMW4ZXpokonnYwQ038jdKWDxYfFCRC5DOsqzjSGUPJoJZStkX4YO_PDDvkkrh7om567XPaUNBbpS3rzNy92fIkflJ2kv0SDb6Ah1HkAn3ogWg"

            val vkHttpClient = VkOkHttpClient()

            val client = VkApiClient(
                groupId, accessToken, VkApiClient.Type.Community,
                VkSettings(vkHttpClient)
            )

            println("Start long polling")

            client.onMessage { messageEvent ->
                println("[MSG]: Got message ${messageEvent.message}")
            }
            client.onEachEvent { messageEvent ->
                println("[EVENT]: Got message ${messageEvent}")
            }

            runBlocking { client.startLongPolling(settings = VkBotsLongPollApi.Settings(maxFails = 5)) }
            println("Connected")
        }
    }
}