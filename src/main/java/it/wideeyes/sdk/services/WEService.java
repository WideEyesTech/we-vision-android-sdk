package it.wideeyes.sdk.services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.wideeyes.sdk.models.WEResult;

/**
 * Created by lompadark on 5/21/15.
 */
class WEService extends AsyncTask<Void, Void, WEResult>{

    private final String WEURL = "http://api.wide-eyes.it/v1/";

    private WECallable callable;
    private WEQuery query;
    private int responseCode;

    public WEService(WEQuery weQuery, WECallable weCallable) {
        callable = weCallable;
        query = weQuery;
    }

    @Override
    protected WEResult doInBackground(Void... params) {
        WEResult result = null;
        try {

            HttpURLConnection connection = weUrlConnection();
            connection.connect();

            writeBody(connection);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = new WEResult(readBody(connection));
            } else {
                Log.e("WE - Service" , "HTTP error " + connection.getResponseCode());
            }
            responseCode = connection.getResponseCode();
            connection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readBody(HttpURLConnection connection) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line = io.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private void writeBody(HttpURLConnection connection) throws IOException {
        OutputStream out = connection.getOutputStream();
        out.write(query.toString().getBytes("UTF-8"));
        out.flush();
    }

    @Override
    protected void onPostExecute(WEResult weResult) {
        super.onPostExecute(weResult);
        if (weResult != null) {
            callable.success(weResult);
        } else {
            callable.error(new WEError("Error on creation, HTTP Response code " + responseCode));
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        callable.error(new WEError("canceled"));
    }

    private HttpURLConnection weUrlConnection() throws IOException {
        URL url = new URL(WEURL + query.endPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("apikey", WE.getApiKey());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Conent-Length", String.valueOf(query.toString().length()));
        return connection;
    }
}
