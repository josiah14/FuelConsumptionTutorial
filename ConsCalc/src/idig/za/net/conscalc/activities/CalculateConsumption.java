package idig.za.net.conscalc.activities;

import idig.za.net.conscalc.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CalculateConsumption extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_consumption);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculate_consumption, menu);
		return true;
	}

}
