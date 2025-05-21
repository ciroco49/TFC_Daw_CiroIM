package com.ciroiencom.gamingheaventfc.service.external;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class YoutubeApiClient {

    private static final String official = "official trailer";

    public static String getYoutubeVideoID(String q_title) {
        try {
            String resultSrc = doYoutubePetition(q_title);
            return resultSrc;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ""; //EN PRINCIPIO nunca debería llegar aquí por la query que realizo mediante la API
    }

    private static String doYoutubePetition(String q_title) {

        try{
            String q_title_utf8 = URLEncoder.encode(q_title, StandardCharsets.UTF_8);
            String official_utf8 = URLEncoder.encode(official, StandardCharsets.UTF_8);

            URL url = new URL("https://www.googleapis.com/youtube/v3/search" +
                    "?key=AIzaSyBWBZJ3j90Ze-fQWMmzI07sDzF53P0Hb1E" +
                    "&q=" + q_title_utf8 + official_utf8 +
                    "&part=snippet" +
                    "&order=relevance");

            HttpURLConnection conex = (HttpURLConnection) url.openConnection();
            conex.setRequestMethod("GET");
            conex.connect();

            if(conex.getResponseCode() != 200) {
                throw new RuntimeException("Ha ocurrido un error con la petición GET para obtener el video: " + conex.getResponseCode());
            }

            JSONObject json = responseToJsonObject(conex.getInputStream(), conex);

            return getFirstSrcFromResults(json);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getFirstSrcFromResults(JSONObject json) {
        try {
            JSONObject firstItem = (JSONObject) json.getJSONArray("items").get(0);
            return firstItem.getJSONObject("id").getString("videoId");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ""; //EN PRINCIPIO nunca debería llegar aquí por la query que realizo mediante la API
    }

    private static JSONObject responseToJsonObject(InputStream info, HttpURLConnection conex) {
        try {
            BufferedReader infoReader = new BufferedReader(new InputStreamReader(info));
            String infoLine;
            StringBuilder content = new StringBuilder();

            while ((infoLine = infoReader.readLine()) != null) {
                content.append(infoLine);
            }
            infoReader.close();
            conex.disconnect();

            JSONObject json = new JSONObject(content.toString());
            return json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


