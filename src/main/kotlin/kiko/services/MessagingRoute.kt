package kiko.services

import com.kotlinizer.injection.Injector
import com.petersamokhin.vksdk.core.client.VkApiClient
import com.petersamokhin.vksdk.core.model.objects.Message
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kiko.constants.ApplicationConstant

fun Route.messaging(injector: Injector) {
    post("message/send/text") {
        val text = call.receive<String>()
        val vkApiClient = injector.resolve(type = VkApiClient::class.java, identifier = "vkClient")

        vkApiClient?.sendMessage{
            peerId = ApplicationConstant.vkGroupPeerUid
            message = text
        }?.execute()

        call.respond("Sended")
    }
}