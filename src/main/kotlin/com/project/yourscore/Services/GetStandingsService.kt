package com.project.yourscore.Services

import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class GetStandingsService {
    fun getStandingsByCode(code: String): String? {
        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://api.football-data.org/v4/competitions/${code}/standings"))
            .header("X-Auth-Token", "fe382c8a16f0430ca32f601d38888099")
            .build()

        val response: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }
}