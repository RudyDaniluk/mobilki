package com.example.jsononline;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        newsAdapter = new NewsAdapter(newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        // Wywołanie metody do pobierania danych
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonData = downloadData("http://json.itmargen.com/5TP");
                    if (jsonData != null) {
                        Log.d("MainActivity", "Dane JSON: " + jsonData); // Logowanie danych JSON
                        parseJSON(jsonData);
                    } else {
                        Log.e("MainActivity", "Brak danych z serwera");
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", "Błąd pobierania danych: " + e.getMessage());
                }
            }
        }).start();
    }

    public String downloadData(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
            return result.toString();
        } finally {
            urlConnection.disconnect();
        }
    }

    private void parseJSON(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String date = jsonObject.getString("date");
                String author = jsonObject.getString("author");
                String content = jsonObject.getString("content");

                News news = new News(title, description, date, author, content);
                newsList.add(news);
            }
            // Aktualizacja adaptera
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    newsAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            Log.e("Parse Error", e.toString());
        }
    }
}

