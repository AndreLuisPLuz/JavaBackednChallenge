package com.challenge.api.handlers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import com.challenge.interfaces.CepApiHandler;
import com.challenge.interfaces.structure.Result;

public class ViaCepApiHandler implements CepApiHandler {
    private final String apiUrl = "viacep.com.br/ws/%s/json/";

    public Result<Boolean> consultCep(String cep) {
        var client = HttpClient.newHttpClient();
        var url = String.format(apiUrl, cep);

        var request = HttpRequest.newBuilder(URI.create(url))
            .header("accept", "application/json")
            .build();

        var response = client.sendAsync(request, new JsonBodyHandler<>(APOD.class)).get();

        return response.get().body() != null;
    }
}
