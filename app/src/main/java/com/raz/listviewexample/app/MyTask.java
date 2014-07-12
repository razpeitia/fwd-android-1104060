package com.raz.listviewexample.app;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

/**
 * Created by raz on 11/07/14.
 */
public class MyTask extends AsyncTask<Void, Void, CategoryResponse> {

    WeakReference<RequestCallback> callbackWeakReference;

    public MyTask(RequestCallback callback) {
        callbackWeakReference = new WeakReference<RequestCallback>(callback);
    }

    public static InputStream getInputStreamFromUrl(String url) {
        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            content = response.getEntity().getContent();
        } catch (Exception e) {
            Log.e("[GET REQUEST]", "Network exception", e);
        }
        return content;
    }

    @Override
    protected CategoryResponse doInBackground(Void... voids) {
        String url = "http://www.foreverjunior.com/wslive/Service1.svc/123";
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStreamFromUrl(url)));
        return gson.fromJson(reader, CategoryResponse.class);
    }

    @Override
    protected void onPostExecute(CategoryResponse categoryResponse) {
        super.onPostExecute(categoryResponse);
        if(callbackWeakReference != null) {
            RequestCallback requestCallback = callbackWeakReference.get();
            if(requestCallback != null) {
                requestCallback.onResults(categoryResponse.getCategories());
            }
        }
    }
}
