/**
 * Includes
 */
package idig.za.net.conscalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author josiah
 *
 */
public class GetRegistration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_registration);
        final Button saveRegistrationButton = (Button) findViewById(R.id.buttonSaveRegistration);
        saveRegistrationButton.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				EditText editTextRegistration = (EditText) findViewById(R.id.editTextRegistration);
				String registrationNumber = editTextRegistration.getText().toString();
				
				return false;
			}
        	
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
