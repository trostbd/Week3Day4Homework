package com.example.lawre.week3day4homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.util.Log;

import com.example.lawre.week3day4homework.user.Item;
import com.example.lawre.week3day4homework.user.Repository;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.ArrayList;

import static com.example.lawre.week3day4homework.Constants.REPOS_URL;

public class SecondScreen extends AppCompatActivity
{
    RecyclerView rv;
    RecyclerViewAdapter rva;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        rv = findViewById(R.id.rvView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        Log.d("TAG_", "onCreate: created rv");
        EventBus.getDefault().register(this);
        Log.d("TAG_", "onCreate: registered bus");
        RepoHelper helper = new RepoHelper();
        helper.asyncOkHttpApiCall(REPOS_URL);
        rva = new RecyclerViewAdapter(new ArrayList<Item>());
        rv.setAdapter(rva);
        //EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRepos(Repository repo)
    {
        ArrayList<Item> repoList = new ArrayList<Item>(repo.getItems());
        Log.d("TAG_", "getRepos: " + repoList);
        rva.addItems(repoList);
        //EventBus.getDefault().unregister(this);
    }
}
