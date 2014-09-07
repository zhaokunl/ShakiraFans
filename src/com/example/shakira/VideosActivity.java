package com.example.shakira;

/* This songs activity allows users to play two videos from the database
 * and to stop playing when they would like to. */

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.widget.Button;

public class VideosActivity extends Activity implements Callback{
	MediaPlayer mediaPlayer;
	Button video1Button;
	Button video2Button;
	Button resetButton;
	SurfaceView surfaceview;
	SurfaceHolder surfaceholder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videos);

		video1Button = (Button) findViewById(R.id.video1Button);
		video2Button = (Button) findViewById(R.id.video2Button);
	    resetButton = (Button) findViewById(R.id.resetVideoButton);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceview = (SurfaceView) findViewById(R.id.surfaceVideoview);
		surfaceholder = surfaceview.getHolder();
		surfaceholder.addCallback(this);
		surfaceholder.setFixedSize(160, 160);
		surfaceholder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	// play the video file with the given filename
	private void play(String filename) {
		if (mediaPlayer.isPlaying()) {
	        mediaPlayer.reset();
	    }

		try {
			AssetFileDescriptor descriptor = getAssets().openFd(filename);
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
			mediaPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mediaPlayer.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mediaPlayer = new MediaPlayer();

		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDisplay(surfaceholder);

		video1Button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				play("loca.3gp");
			}
		});

		video2Button.setOnClickListener(new Button.OnClickListener() {	
			@Override
			public void onClick(View v) {
				play("rabiosa.3gp");
			}
		});

		resetButton.setOnClickListener(new Button.OnClickListener() {	
			@Override
			public void onClick(View v) {
				if (mediaPlayer.isPlaying()) {
			        mediaPlayer.reset();
			    }
			}
		});
		
		if(mediaPlayer == null)
			mediaPlayer = new MediaPlayer();
		mediaPlayer .setDisplay(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.reset();
		}
	}
}
