package com.example.daan.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements PortalObjectAdapter.PortalClicketListener{

    private RecyclerView mPortalRecyclerView;
    private List<PortalObject> mPortalObjects;
    private PortalObjectAdapter mAdapter;

    public static final int REQUESTCODE = 1234;
    public static final String EXTRA_PORTAL = "Portal";
    public static final String EXTRA_URL = "Url";
    public static final String EXTRA_TITLE = "Title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initiate components
        mPortalRecyclerView = findViewById(R.id.recycleView);
        mPortalObjects = new ArrayList<>();

        //Fill with default (provided) data
        mPortalObjects.add(new PortalObject("VLO", "https://vlo.informatica.hva.nl"));
        mPortalObjects.add(new PortalObject("Roosters", "https://rooster.hva.nl/"));
        mPortalObjects.add(new PortalObject("Resultaten", "https://sis.hva.nl"));
        mPortalObjects.add(new PortalObject("Google", "https://www.google.com"));

        //Set layoutmanager with span of 3
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3,
                LinearLayoutManager.VERTICAL);

        mPortalRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PortalObjectAdapter(mPortalObjects,this);
        mPortalRecyclerView.setAdapter(mAdapter);

        //Floating Action Button onclick
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add an portal activity
                Intent intent = new Intent(MainActivity.this, AddPortalActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void PortalOnClick(int position) {
        PortalObject portalObject = mPortalObjects.get(position);
        Intent intent = new Intent(this, AccessPortalActivity.class);
        intent.putExtra(MainActivity.EXTRA_URL, portalObject.getUrl());
        intent.putExtra(MainActivity.EXTRA_TITLE, portalObject.getTitle());
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                PortalObject newPortalObject = data.getParcelableExtra(MainActivity.EXTRA_PORTAL);
                mPortalObjects.add(newPortalObject);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
