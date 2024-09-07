package br.com.services

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ApiConsumption {
    fun json(id: String): String? {
        val adress = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(adress))
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()

        return  json
    }
}