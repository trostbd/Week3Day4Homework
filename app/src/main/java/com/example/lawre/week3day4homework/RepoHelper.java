package com.example.lawre.week3day4homework;

import android.util.Log;

import com.example.lawre.week3day4homework.user.Repository;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RepoHelper
{
    public void asyncOkHttpApiCall(String url)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("TAG_", "onFailure: ",e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if(response.body() == null)
                {
                    return;
                }
                Log.d("TAG_", "onResponse: ");
                String json = "{\"items\": ";
                json = json + response.body().string();
                json = json + "}";
                Log.d("TAG_", "onResponse: received json\n"+json);
                Gson gson = new Gson();
                Repository repoList = gson.fromJson(json,Repository.class);
                Log.d("TAG_", "onResponse: made repo " + repoList);
                EventBus.getDefault().post(repoList);
            }
        });
    }
}
