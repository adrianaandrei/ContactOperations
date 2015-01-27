package com.example.contactoperations;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ContactOperations.Insert2Contacts(getApplicationContext(),
				"Yildirim Kocdag", "05321000000");
		if (ContactOperations.isTheNumberExistsinContacts(
				getApplicationContext(), "05321000000")) {
			Log.i(ContactOperations.TAG, "Exists");
		} else {
			Log.i(ContactOperations.TAG, "Not Exists");
		}
		ContactOperations.deleteContact(getApplicationContext(), "05321000000");
		if (ContactOperations.isTheNumberExistsinContacts(
				getApplicationContext(), "05321000000")) {
			Log.i(ContactOperations.TAG, "Exists");
		} else {
			Log.i(ContactOperations.TAG, "Not Exists");
		}
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
