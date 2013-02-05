package idig.za.net.conscalc.activities;

import idig.za.net.conscalc.R;

import java.util.Calendar;
import java.util.Date;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import idig.za.net.conscalc.database.FuelRecord;
import idig.za.net.conscalc.dialog_fragments.DatePickerDialogFragment;

public class AddRecord extends Activity {
	/*************************************************************************************************************
	 * Class Members 																							 *
	 *************************************************************************************************************/
	// create variable called date of type long
	private long mDate;
	// date related members
	private TextView mDateDisplay;
	private Button mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;
	long mDatePubStamp;
	static final int DATE_DIALOG_ID = 0;
	private boolean mDateChanged = false;
	// create variable called stringDate of type String
	private String mStringDate;
	// listener for datePickerDialog in legacy Android OS's
	private DatePickerDialog.OnDateSetListener mDateSetListener;
	// declare a file for storing the preferences
	public static final String PREFS_NAME = "MyPrefsFile";
	FuelRecord mFuelRecord;
	
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
					// check if the date was changed. if not, then use the date from fuelRecord
					// else, use the date from date update
					long savedDate;
					if (mDateChanged) {
						savedDate = mDatePubStamp;
					} else {
						savedDate = mDate;
					}
					// instantiate a FuelRecord
					mFuelRecord = new FuelRecord();
					
					// put fields into record
					mFuelRecord.setDate(savedDate);
					mFuelRecord.setOdometer(odometerValue);
					mFuelRecord.setLitres(litresValue);
					mFuelRecord.setCost(costValue);
				}
			}
		});
		
		// capture the date display View element
		mDateDisplay = (TextView) findViewById(R.id.TextViewDisplayDate);
		// capture the date button View element
		mPickDate = (Button) findViewById(R.id.buttonPickDate);
		
		
		mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear;
				mDay = dayOfMonth;
				updateDisplay();
			}
		};
		
		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
					Log.i(Debugger.TAG, "Using Legacy Pre-Honeycomb datePickerDialog version");
					showDialog(DATE_DIALOG_ID);
				} else {
					Log.i(Debugger.TAG, "Using modern Honeycomb and later datePickerDialog version");
					DatePickerDialogFragment datePickerDialog = new DatePickerDialogFragment();
					datePickerDialog.setOnDatePickedListener(mDateSetListener);
					datePickerDialog.show(getFragmentManager(), Debugger.TAG);
				}
			}
		});
	}
	
	private SharedPreferences getPreferences() {
		return getSharedPreferences(PREFS_NAME, 0);
	}
	
	private String getCurDateTime() {
		final Calendar cal = Calendar.getInstance();
		
		// get the year from the calendar
		mYear = cal.get(Calendar.YEAR);
		// get the month from the calendar
		mMonth = cal.get(Calendar.MONTH);
		// get the day from the calendar
		mDay = cal.get(Calendar.DAY_OF_MONTH);
		
		// get date as long to store in database
		mDate = cal.getTimeInMillis();
		
		// for checking - convert long to string
		Date curDate = new Date(mDate);
		mStringDate = curDate.toString();
		Log.i(Debugger.TAG, "the long default curDate = " + mDate);
		Log.i(Debugger.TAG, "the curDate string representation = " + mStringDate);
		return mStringDate;
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
		mDateChanged = true;
		mDateDisplay.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(mMonth + 1).append("-").append(mDay).append("-")
		.append(mYear).append(" "));
		Log.i(Debugger.TAG, "the date string updated to the screen = " + mDateDisplay.getText());
		//*************************************************************** 
		// getting date to store in sqlite database 				    *
		// gets date from tadePicker and coverts to long 				*
		// **************************************************************
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, mYear);
		cal.set(Calendar.MONTH, mMonth);
		cal.set(Calendar.DAY_OF_MONTH, mDay);
		mDatePubStamp = cal.getTimeInMillis(); // store this date long val into the database
		Date newDate = new Date(mDatePubStamp);
		Log.i(Debugger.TAG, "the long date passed to the database = " + newDate);
		Log.i(Debugger.TAG, "the string representation of the date stored to the database = " + newDate.toString());
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if (mDateSetListener == null) {
			Log.e(Debugger.TAG, "The date set listener was null for pre-Honeycomb OS");
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			Log.e(Debugger.TAG, "Tried to use Legacy date-picker-dialog with Honeycomb or later OS");
		} else {
		switch (id) {
			case DATE_DIALOG_ID:
				return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
			}
		}
		// if erred or did not match dialog ID, return null
		Log.e(Debugger.TAG, "onCreateDialog erred, no datePickerDialog returned");
		return null;
	}
}
