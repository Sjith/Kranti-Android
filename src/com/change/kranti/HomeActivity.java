package com.change.kranti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void reportIssue(View _){
        Intent intent = new Intent(this, CaptureIssueActivity.class);
        startActivity(intent);
    }

    public void viewIssues(View _){
        Intent intent = new Intent(this, ViewIssuesActivity.class);
        startActivity(intent);
    }

}
