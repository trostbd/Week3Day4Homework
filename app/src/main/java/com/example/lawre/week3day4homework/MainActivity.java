package com.example.lawre.week3day4homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lawre.week3day4homework.user.User;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.lawre.week3day4homework.Constants.USER_URL;

public class MainActivity extends AppCompatActivity
{
    ImageView myPicture;
    TextView myName, myUserName, myBio, myLocation;
    Button btNextAct;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPicture = findViewById(R.id.imgPicture);
        myName = findViewById(R.id.tvName);
        myUserName = findViewById(R.id.tvUserName);
        myBio = findViewById(R.id.tvBio);
        myLocation = findViewById(R.id.tvLocation);
        btNextAct = findViewById(R.id.btNextScreen);
        btNextAct.setText("Go to Repositories");
        EventBus.getDefault().register(this);
        OkHttpHelper httpHelper = new OkHttpHelper();
        httpHelper.asyncOkHttpApiCall(USER_URL);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEvent(User myUser)
    {
        Glide.with(getApplicationContext()).load(myUser.getAvatarUrl()).into(myPicture);
        myName.setText(myUser.getName());
        myUserName.setText(myUser.getLogin());
        myBio.setText(myUser.getBio());
        myLocation.setText(myUser.getLocation());
    }

    @Override
    protected void onStop() {
        super.onStop();
       // EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        //EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
       EventBus.getDefault().register(this);
        super.onResume();
    }

    public void onClick(View view)
    {
        if(view.getId() == R.id.btNextScreen)
        {
            Intent intent = new Intent(this,SecondScreen.class);
            EventBus.getDefault().unregister(this);
            startActivity(intent);
        }
    }
}

/*
new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG_", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {

            }
        }
 */
