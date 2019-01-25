package com.example.lawre.week3day4homework;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.EventLog;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.lawre.week3day4homework.user.User;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper
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
                String json = response.body().string();
                Gson gson = new Gson();
                User myUser = gson.fromJson(json,User.class);
                EventBus.getDefault().post(myUser);
            }
        });
    }

    public void asyncRepositoryCall(String url)
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
                String json = response.body().string();
                Gson gson = new Gson();
                User myUser = gson.fromJson(json,User.class);
                //EventBus.getDefault().post(myUser);
            }
        });
    }
}
