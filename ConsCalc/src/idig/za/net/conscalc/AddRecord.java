package idig.za.net.conscalc;

import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class AddRecord extends Activity {
	/*************************************************************************************************************
	 * Class Members 																							 *
	 *************************************************************************************************************/
	// create variable called date of type long
	private long date;
	// create variable called stringDate of type String
	private String stringDate;
	// declare a file for storing the preferences
	public static final String PREFS_NAME = "MyPrefsFile";
	
	/*************************************************************************************************************
	 * Nested Classes																							 *
	 *************************************************************************************************************/
	// Debugging Variables
	private static class Debugger {
		public static final String TAG = "AddRecord";
		private static debugLevels debugLevel = debugLevels.ON;
		public static enum debugLevels { OFF, ON };
		
		public static debugLevels getDebugLevel() {
			return debugLevel;
		}
		
		public static void setDebugLevel(debugLevels newDebugLevel) {
			debugLevel = newDebugLevel;
		}
	}
	
	/*************************************************************************************************************
	 * Methods          																					     *
	 *************************************************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_record);
		
		// set textViews with values
		TextView tvDate = (TextView) findViewById(R.id.TextViewDisplayDate);
		tvDate.setText(getCurDateTime());
		
		TextView tvRegistrationNumber  = (TextView) findViewById(R.id.TextViewRegNumber);
		tvRegistrationNumber.setText(getRegistrationNumber());
	}
	
	private SharedPreferences getPreferences() {
		return getSharedPreferences(PREFS_NAME, 0);
	}
	
	private String getCurDateTime() {
		final Calendar cal = Calendar.getInstance();
		// get date as long to store in database
		date = cal.getTimeInMillis();
		
		// for checking - convert long to string
		Date curDate = new Date(date);
		stringDate = curDate.toString();
		Log.i(Debugger.TAG, "the long date is " + date);
		Log.i(Debugger.TAG, "the stringDate = " + stringDate);
		return stringDate;
	}
	
	private String getRegistrationNumber() {
		SharedPreferences settings = getPreferences();
		if (!settings.getBoolean("registrationIsSet", false)) {
			Log.e(Debugger.TAG, "the registration date is not set.");
			return "";
		} else {
			String registrationNumber = settings.getString("registrationNumber", null);
			if (registrationNumber == null) {
				Log.e(Debugger.TAG, "the registration number was null.");
				return "";
			} else {
				Log.i(Debugger.TAG, "the registration number retrieved was \"" + registrationNumber + "\"");
				return registrationNumber;
			}
		}
	}

}
