package com.example.daan.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity {

    private Button mAddButton;
    private EditText mUrlText;
    private EditText mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);
        setTitle("Add Portal");

        //Initiate components
        mAddButton = findViewById(R.id.addButton);
        mUrlText = findViewById(R.id.urlText);
        mTitleText = findViewById(R.id.titleText);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mUrlText.getText().toString();
                String titleText = mTitleText.getText().toString();

                if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(titleText)) {
                    PortalObject newPortalObject = new PortalObject(titleText, url);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.EXTRA_PORTAL, (Parcelable) newPortalObject);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Snackbar.make(v, "Fill in both fields", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
