package com.example.shakira;

/* The main activity contains all the basic information and takes care of 
 * the navigation among different features for users. */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    Button websiteButton;
    Button fbButton;
    Button songsButton;
    Button videosButton;
    Button wallpaperButton;
    Button mailinglistButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		websiteButton = (Button)findViewById(R.id.websiteButton);
		fbButton = (Button)findViewById(R.id.fbButton);
		songsButton = (Button)findViewById(R.id.songsButton);
		videosButton = (Button)findViewById(R.id.videosButton);
		wallpaperButton = (Button)findViewById(R.id.wallpaperButton);
		mailinglistButton = (Button)findViewById(R.id.mailinglistButton);
		
		/* When click on the official website button, redirect to the official
		 *  website of the vocal artist. */
		websiteButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, 
					    Uri.parse("http://www.shakira.com/")));
			}
		});
		
		/* When click on the follow shakira on facebook button, go to the facebook
		 *  page of shakira. */
		fbButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, 
						Uri.parse("https://www.facebook.com/shakira")));
			}
		});
		
		/* When click on the songs button, provide the users two songs of shakira
		 *  to play. */
		songsButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SongsActivity.class);
				startActivity(intent);
			}
		});
		
		/* When click on the videos button, provide the users two videos of shakira
		 * to play. */
		videosButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VideosActivity.class);
				startActivity(intent);
			}
		});
		
		/* When click on the wallpaper button, display wallpapers of shakira. */
		wallpaperButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, WallpaperActivity.class);
				startActivity(intent);
			}
		});
		
		/* When click on the mailinglist button, give the user an option to subscribe
		 *  to news of shakira. */
		mailinglistButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MailinglistActivity.class);
				startActivity(intent);
			}
		});	
	}
}
