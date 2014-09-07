package com.example.shakira;

/* This songs activity allows users to play two songs from the database
 * and to stop playing when they would like to. */

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class SongsActivity extends Activity implements Callback {

	MediaPlayer mediaPlayer;
	Button song1Button;
	Button song2Button;
	Button resetButton;
	SurfaceView surfaceview;
	SurfaceHolder surfaceHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_songs);

		song1Button = (Button) findViewById(R.id.song1Button);
		song2Button = (Button) findViewById(R.id.song2Button);
		resetButton = (Button) findViewById(R.id.resetButton);

		getWindow().setFormat(PixelFormat.UNKNOWN);

		surfaceview = (SurfaceView) findViewById(R.id.surfaceview);
		surfaceHolder = surfaceview.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setFixedSize(160, 160);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	// play the audio file with the given filename
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

		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
			}
		});

		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDisplay(surfaceHolder);

		song1Button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				play("lovehim.mp3");
			}
		});

		song2Button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				play("lapared.mp3");
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