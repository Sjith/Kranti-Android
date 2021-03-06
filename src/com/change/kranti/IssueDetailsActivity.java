package com.change.kranti;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class IssueDetailsActivity extends Activity{
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(com.change.kranti.R.layout.issue_details);
    
    Bundle extras = getIntent().getExtras();
    String title = extras.getString(getString(com.change.kranti.R.string.issue_title));
    String description = extras.getString(getString(com.change.kranti.R.string.issue_description));
    String location = extras.getString(getString(com.change.kranti.R.string.issue_location));
    String imageUrl = extras.getString(getString(com.change.kranti.R.string.issue_image_url));
    TextView imageTitle = (TextView) findViewById(com.change.kranti.R.id.issue_title);
    imageTitle.setText(title);
    File issueImageFile = new File(imageUrl);
    Bitmap issueImageBitmap = BitmapFactory.decodeFile(issueImageFile.getAbsolutePath());
    ImageView issueImageView = (ImageView) findViewById(com.change.kranti.R.id.issue_image);
    issueImageView.setImageBitmap(issueImageBitmap);
    TextView issueDescription = (TextView) findViewById(com.change.kranti.R.id.issue_description);
    issueDescription.setText(description);
  }
}
