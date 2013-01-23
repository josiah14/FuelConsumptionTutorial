package idig.za.net.conscalc;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class AddRecord extends Activity {
	/*************************************************************************************************************
	 * Class Members 																																														 *
	 *************************************************************************************************************/
	// create variable called date of type long
	private long date;
	// create variable called stringDate of type String
	private String stringDate;
	
	/*************************************************************************************************************
	 * Nested Classes																																														 *
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
	 * Methods          																																														 *
	 *************************************************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_record);
		final Calendar cal = Calendar.getInstance();
	}

}
