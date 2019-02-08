package wbroome14.nessiehackathonexample.Providers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiCaller extends AsyncTask<Void, Void, String> {
    private final String API_KEY = "c2731de4c82120fecaa45bc319772751";
    private final String API_URL = "http://api.reimaginebanking.com/";

    private String endpoint;
    private String payload;
    private ApiResponse callback;
    private Method method;

    public ApiCaller(String endpoint, String payload, Method method, ApiResponse callback) {
        Log.d("REQUEST-ENDPOINT", endpoint);
        this.endpoint = endpoint;
        this.payload = payload;
        this.method = method;
        this.callback = callback;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Void... voids) {
        switch (method) {
            case GET:
                return getCall();
            case POST:
                return postCall();
            case DELETE:
                return deleteCall();
            default:
                return getCall();
        }
    }

    private String postCall() {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.connect();
            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    private String getCall() {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    private String deleteCall() {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded" );
            urlConnection.setRequestMethod("DELETE");
            urlConnection.connect();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        callback.apiResponseCallback(response);
    }

    public interface ApiResponse {
        void apiResponseCallback(String response);
    }
}
