package com.example.contactoperations;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
Added some commnets for tests
**/
public class ArrayApplication extends Application {

	static final String TAG = "ArrayApplication";

	String[] data = new String[] { "Baby mic", "Bebita", "Mamitu", "Test", "Dana", "Mamaia", "Bi",
			"Adutu" };
	

	String[] dataPerLetter = new String[] { "Baby mic,B", "Bebita,B", "Mamitu,M", "Test,T", "Dana,D", "Mamaia,M", "Bi,B",
			"Adutu,A" };
	
	String[] dataPerGroup = new String[] { "Baby mic,bebite", "Bebita,bebite", "Mamitu,family", "Test,unknown", "Dana,family", "Mamaia,family", "Bi,family",
	"Adutu,eushor" };

	/** This passes our data out to the JS */
	@JavascriptInterface
	public String getDataByLetter() {
		Log.d(TAG, "getDataByLetter() called");
		return addToJsonPerCategory(dataPerLetter,"letter").toString();
	}

	/** Allow the JavaScript to pass some data in to us. */
	@JavascriptInterface
	public void setDataByLetter(String newData) throws JSONException {
		Log.d(TAG, "MainActivity.setDataByLetter()");
		JSONArray streamer = new JSONArray(newData);
		dataPerLetter = new String[streamer.length()];
		for (int i = 0; i < streamer.length(); i++) {
			String n = streamer.getString(i);
			dataPerLetter[i] = n;
		}
	}

	
	/** This passes our data out to the JS */
	@JavascriptInterface
	public String getDataPerGroup() {
		Log.d(TAG, "getDataPerGroup() called");
		return addToJsonPerCategory(dataPerGroup,"groupName").toString();
	}

	/** Allow the JavaScript to pass some data in to us. */
	@JavascriptInterface
	public void setDataPerGroup(String newData) throws JSONException {
		Log.d(TAG, "MainActivity.setDataPerGroup()");
		JSONArray streamer = new JSONArray(newData);
		dataPerGroup = new String[streamer.length()];
		for (int i = 0; i < streamer.length(); i++) {
			String n = streamer.getString(i);
			dataPerGroup[i] = n;
		}
	}

	
	/** This passes our data out to the JS */
	@JavascriptInterface
	public String getData() {
		Log.d(TAG, "getData() called");
		return a1dToJson(data).toString();
	}

	/** Allow the JavaScript to pass some data in to us. */
	@JavascriptInterface
	public void setData(String newData) throws JSONException {
		Log.d(TAG, "MainActivity.setData()");
		JSONArray streamer = new JSONArray(newData);
		data = new String[streamer.length()];
		for (int i = 0; i < streamer.length(); i++) {
			String n = streamer.getString(i);
			data[i] = n;
		}
	}

	private Activity activity;

	public Context getActivity() {
		return activity;
	}

	public void setActivity(Activity app) {
		this.activity = app;
	}

	@JavascriptInterface
	public void finish() {
		Log.d(TAG, "ArrayApplication.finish()");
		activity.finish();
	}

	/**
	 * Sorry for not using the standard org.json.JSONArray but even in Android
	 * 4.2 it lacks the JSONArray(Object[]) constructor, making it too painful
	 * to use.
	 */
	private String a1dToJson(String[] data) {
		StringBuffer sb = new StringBuffer();
		sb.append("[\"");
		for (int i = 0; i < data.length; i++) {
			String d = data[i];
			if (i > 0)
				sb.append("\",\"");
			sb.append(d);
		}
		sb.append("\"]");
		return sb.toString();
	}
	
	private String addToJsonPerCategory(String[] data, String groupName) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < data.length; i++) {
			String d = data[i];
			if (i > 0){
				sb.append(",");
			}
			String temp[] = new String[2];
			temp = d.split(",");
			sb.append("\n{name: \"" + temp[0]+"\", " + groupName + ": \""+temp[1]+"\"}");
		}
		sb.append("]");
		
		return sb.toString();
	}
}