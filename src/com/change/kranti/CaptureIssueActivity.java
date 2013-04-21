package com.change.kranti;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.kranti.location.LocationChangeTrigger;
import repository.ImageRepository;
import repository.IssueRepository;

public class CaptureIssueActivity extends Activity {


    private static final int IMAGE_CAPTURE = 0;

    private IssueRepository issueRepository;
    private ImageRepository imageRepository;

    private LocationChangeTrigger locationTracker;
    private Bitmap image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        issueRepository = new IssueRepository(getApplicationContext());
        imageRepository = new ImageRepository();
        setContentView(R.layout.main);
        locationTracker = new LocationChangeTrigger(this);
        locationTracker.fetchLatestLocation();
    }

    public void captureIssueImage(View _) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, IMAGE_CAPTURE);
    }

    public void returnToHomeScreen(View _) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void submit(View _) {
        if(storeIssueDetails())
            startActivity(new Intent(this, HomeActivity.class));
        else
            Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show();
    }

    private boolean storeIssueDetails() {
        String imagePath = imageRepository.storeImage(image);
        String title = ((EditText) findViewById(R.id.titleText)).getText().toString();
        String description = ((EditText) findViewById(R.id.descriptionText)).getText().toString();
        return issueRepository.createIssue(title, description, locationTracker.getLocation(), imagePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (isImageCaptureResult(requestCode, resultCode, intent))
            image = (Bitmap) intent.getExtras().get("data");
        else
            Toast.makeText(this, "I couldn't take a picture #fail", Toast.LENGTH_LONG).show();
    }

    private boolean isImageCaptureResult(int requestCode, int resultCode, Intent intent) {
        return requestCode == IMAGE_CAPTURE && resultCode == Activity.RESULT_OK && intent != null;
    }
}
