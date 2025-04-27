package com.ciroiencom.gamingheaventfc.service.external;

import com.ciroiencom.gamingheaventfc.model.Juego;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class FreeToGameApiClient {

    private static final String urlGames = "https://www.freetogame.com/api/games";
    private static String urlGameWithNoID = "https://www.freetogame.com/api/game?id=";

    public static ArrayList<Juego> getJuegos() {
        ArrayList<Juego> videogames = new ArrayList<>();

        try {
            ArrayList<Integer> IDs = getIDs();

            for(Integer ID: IDs) {
                RestTemplate restTemplate = new RestTemplate();
                Juego videogame = restTemplate.getForObject(urlGameWithNoID + ID, Juego.class);

                videogames.add(videogame);
            }

            return videogames;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static ArrayList<Integer> getIDs() {
        try {
            URL urlAllGames = new URL(urlGames);
            HttpURLConnection conex = (HttpURLConnection) urlAllGames.openConnection();
            conex.setRequestMethod("GET");
            conex.connect();

            if(conex.getResponseCode() != 200) {
                throw new RuntimeException("Ha ocurrido un error con la petición GET a /games: " + conex.getResponseCode());
            }

            JSONArray json = responseToJsonArray(conex.getInputStream(), conex);

            return getIDsFromJson(json);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<Integer> getIDsFromJson(JSONArray json) {
        try {
            ArrayList<Integer> IDs = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {
                JSONObject videogame = json.getJSONObject(i);
                IDs.add(videogame.getInt("id"));
            }
            return IDs;
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static JSONArray responseToJsonArray(InputStream info, HttpURLConnection conex) {
        try {
            BufferedReader infoReader = new BufferedReader(new InputStreamReader(info));
            String infoLine;
            StringBuilder content = new StringBuilder();

            while ((infoLine = infoReader.readLine()) != null) {
                content.append(infoLine);
            }
            infoReader.close();
            conex.disconnect();

            JSONArray json = new JSONArray(content.toString());
            return json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
