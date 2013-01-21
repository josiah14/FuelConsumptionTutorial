package idig.za.net.conscalc;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;

public class SplashScreen extends Activity {
	// Debugging Variables
	private static class Debugger {
		public static final String TAG = "SplashScreen";
		private static debugLevels debugLevel = debugLevels.ON;
		public static enum debugLevels { OFF, ON };
		
		public static debugLevels getDebugLevel() {
			return debugLevel;
		}
		
		public static void setDebugLevel(debugLevels newDebugLevel) {
			debugLevel = newDebugLevel;
		}
	}
	
	// declare a file for storing the preferences
	public static final String PREFS_NAME = "MyPrefsFile";

	// declare TimerTask variable
	private TimerTask delayTask;
	// declare TImer variable
	private Timer myTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myTimer = new Timer();
		delayTask = new TimerTask() {
			@Override
			public void run() {
				// Restore preferences
				// get a sharedPreferences object called setting
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				// extract the stored preferences and assign the values to variables
				// the 2nd parameter is value assigned if no shared preference exists
				boolean registrationIsSet = settings.getBoolean("registrationIsSet", false);
				
				// check whether the registration number has been set
				Log.d(Debugger.TAG, "is Registration set in splash screen = " + registrationIsSet );
				Log.i(Debugger.TAG, "is Registtration set in splash screen = " + registrationIsSet);
				if (registrationIsSet) {
					// cancel the timer
					myTimer.cancel();
					// create an Intent object for this activity, including its destination class
					Intent myIntent = new Intent(SplashScreen.this, CalcMenu.class);
					startActivity(myIntent);
				} else {
					// cancel the timer
					myTimer.cancel();
					// create an Intent object for this activity, including its destination class
					Intent myIntent = new Intent(SplashScreen.this, GetRegistration.class);
					startActivity(myIntent);
				}
			}
		};
		myTimer.schedule(delayTask, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
