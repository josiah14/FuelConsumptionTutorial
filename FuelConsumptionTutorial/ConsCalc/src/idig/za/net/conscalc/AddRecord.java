package idig.za.net.conscalc;

import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class AddRecord extends Activity {
	/*************************************************************************************************************
	 * Class Members 																							 *
	 *************************************************************************************************************/
	// create variable called date of type long
	private long date;
	// date related members
	private TextView mDateDisplay;
	private Button mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;
	long datePubStamp;
	static final int DATE_DIALOG_ID = 0;
	private boolean dateChanged = false;
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
		
		createListeners();
	}
	
	private void createListeners() {
		final Button saveRecordButton = (Button) findViewById(R.id.buttonSaveRecord);
		saveRecordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// create EditText objects for each of the EditText views
				final EditText editTextOdometer = (EditText) findViewById(R.id.editTextOdometer);
				final EditText editTextLitres = (EditText) findViewById(R.id.editTextLitres);
				final EditText editTextCost = (EditText) findViewById(R.id.editTextCost);
				
				// verify values
				if ((editTextOdometer.getText().toString().trim().length() == 0) 
						|| (editTextLitres.getText().toString().trim().length() == 0)
						|| (editTextCost.getText().toString().trim().length() == 0)) {
					// create a toast to warn that nothing was entered
					Toast.makeText(AddRecord.this, "Don't be an idiot! No data. No text found in the edit text", Toast.LENGTH_LONG).show();
				} else {
					// get the values from the EditText objects
					int odometerValue = Integer.parseInt(editTextOdometer.getText().toString());
					String litresValue = editTextLitres.getText().toString();
					String costValue = editTextCost.getText().toString();
				}
			}
		});
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
	
	// updates the date in the TextView
	private void updateDisplay() {
		
		// a new date has been selected other than one displayed so dateChanged is True
		dateChanged = true;
		mDateDisplay.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(mMonth + 1).append("-").append(mDay).append("-")
		.append(mYear).append(" "));
	}
}
