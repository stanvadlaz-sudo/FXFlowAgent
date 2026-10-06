package com.fxflowagent;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CbrService {

    public static String getRates() {

        try {
            URL url = new URL(
                    "https://www.cbr-xml-daily.ru/daily_json.js"
            );

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()
                            )
                    );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JSONObject json =
                    new JSONObject(response.toString());

            JSONObject valute =
                    json.getJSONObject("Valute");

            double usd =
                    valute.getJSONObject("USD")
                            .getDouble("Value");

            double cny =
                    valute.getJSONObject("CNY")
                            .getDouble("Value");

            return "USD/RUB: " + usd +
                    "\nCNY/RUB: " + cny;

        } catch (Exception e) {

            return "Ошибка получения данных ЦБ: "
                    + e.getMessage();
        }
    }
public static String getBrent() {

    try {

        URL url = new URL(
                "https://biquote.io/api/UKOIL"
        );

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()
                        )
                );

        StringBuilder response =
                new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONObject json =
                new JSONObject(response.toString());

        double price =
                json.getDouble("mid");

        double change =
                json.getDouble("dayDiffPercent");

        return "BRENT: " + price +
                "\nCHANGE: " + change;

    } catch (Exception e) {

        return "ERROR: " + e.getMessage();
    }
}
