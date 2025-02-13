package dev.redfox.timepassdoggo.network

import dev.redfox.timepassdoggo.data.models.DogsDataResponse
import retrofit2.http.GET

interface ServerInterface {
    @GET("/api/breeds/image/random")
    suspend fun getRandomDogImage(): DogsDataResponse
}