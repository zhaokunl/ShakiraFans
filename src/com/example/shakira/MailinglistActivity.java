package com.example.shakira;

/* The mailing list ativity enables users to put in their name and email
 *  to subscribe to the vocal artist and receive future email notifications
 *   about this artist. */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MailinglistActivity extends Activity {
	EditText nameEditText;
	EditText emailEditText;
	Button subscribeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mailinglist);

		nameEditText = (EditText) findViewById(R.id.nameEditText);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		subscribeButton = (Button) findViewById(R.id.subscribeButton);

		subscribeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Clear the input field
				nameEditText.setText("");
				emailEditText.setText("");

				// Subscribe message
				AlertDialog alertDialog = new AlertDialog.Builder(
						MailinglistActivity.this).create();
				alertDialog.setTitle("Message:");
				alertDialog
						.setMessage("You have successfully subscribed Shakira!");
				alertDialog.setButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertDialog.show();
			}
		});
	}
}
