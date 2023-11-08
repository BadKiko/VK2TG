package kiko

import com.kotlinizer.injection.Injector
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kiko.services.messaging

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    val injector: Injector by lazy { Injector.instance }

    routing {
        messaging(injector)
    }
}