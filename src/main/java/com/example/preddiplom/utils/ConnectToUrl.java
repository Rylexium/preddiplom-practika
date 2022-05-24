package com.example.preddiplom.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectToUrl {
    public static boolean transfer(String method, JSONObject json) throws IOException {
        try {
            URI url = new URI("https://sber-practika.herokuapp.com/api/finalize/" + method);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(url)
                    .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(json)))
                    .header("Content-type", "application/json")
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject answer = new JSONObject(response.body());
            System.out.println(answer + "\n");
            return answer.get("status").equals("ok");

        } catch (JSONException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
}
