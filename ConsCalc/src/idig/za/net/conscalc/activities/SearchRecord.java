/**
 * Includes
 */
package idig.za.net.conscalc.activities;

import idig.za.net.conscalc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * @author josiah
 *
 */
public class SearchRecord extends Activity {
	public enum ExecutionPath {
		EDIT(1),
		DELETE(2);
		
		private int mCode;

		private ExecutionPath (int code) {
			mCode = code;
		}
		
		public int getCode() {
			return mCode;
		}
		
	}
	// The tutorial calls this selectOdometerButton, this comes from the EditRecord layout
	final Button searchButton = (Button) findViewById(R.id.buttonSearch);
	Intent sourceIntent = getIntent();
	int goTo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_record);
        extractIntentExtras();
        createListeners();
    }
    
    private void extractIntentExtras() {
    	Bundle b = sourceIntent.getExtras();
    	goTo = b.getInt("goTo"); // edit=1, delete=2
    	boolean validateGoTo = false;
    	for (ExecutionPath x : ExecutionPath.values()) {
    		if (x.getCode() == goTo) {
    			validateGoTo = true;
    			break;
    		}
    	}
    	if (validateGoTo == false) {
    		
    	}
    }

    private void createListeners() {
    	searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
