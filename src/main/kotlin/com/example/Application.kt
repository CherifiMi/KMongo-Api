package com.example

import com.example.model.User
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val client = KMongo.createClient().coroutine
val database = client.getDatabase("users_db")
val collection = database.getCollection<User>()

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting(collection)
        configureSerialization()
    }.start(wait = true)
}
