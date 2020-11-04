package com.urk17cs290.mymailclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button addImage = findViewById(R.id.button);
		addImage.setOnClickListener(view -> new SendMail().execute(""));
	}
	private class SendMail extends AsyncTask<String, Integer, Void> {

		private ProgressDialog progressDialog;

		TextInputLayout emailInputLayout = findViewById(R.id.emailTextField);
		TextInputLayout toAddrInputLayout = findViewById(R.id.toAddrTextField);
		String toAddr=toAddrInputLayout.getEditText().getText().toString();


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Sending mail", true, false);
			if(toAddr.isEmpty()||toAddr.equals(null))
				toAddr=toAddrInputLayout.getPlaceholderText().toString();
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			progressDialog.dismiss();
		}

		protected Void doInBackground(String... params) {
			Mail m = new Mail("rajasinghsamuelb@karunya.edu.in", "ctgpbbqrhthgyayg");

			String[] toArr = {toAddr, "rajasinghsamuelb@karunya.edu.in"};

			m.setTo(toArr);
			m.setFrom("rajasinghsamuelb@karunya.edu.in");
			m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
			m.setBody(emailInputLayout.getEditText().getText().toString());

			try {
				if(m.send()) {
					Looper.prepare();
					Toast.makeText(MainActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
				} else {
					Looper.prepare();
					Toast.makeText(MainActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
				}
			} catch(Exception e) {
				Log.e("MailApp", "Could not send email", e);
			}
			return null;
		}
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
}