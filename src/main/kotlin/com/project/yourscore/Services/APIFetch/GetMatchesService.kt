package com.project.yourscore.Services.APIFetch

import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers

@Service
class GetMatchesService {
    fun getTodayMatches(): String? {
        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://api.football-data.org/v4/matches"))
            .header("X-Auth-Token", "fe382c8a16f0430ca32f601d38888099")
            .build()

        val response: HttpResponse<String> = client.send(request, BodyHandlers.ofString())

        return response.body()
    }
}