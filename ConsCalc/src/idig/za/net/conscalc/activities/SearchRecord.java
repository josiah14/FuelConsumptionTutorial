/**
 * Includes
 */
package idig.za.net.conscalc.activities;

import idig.za.net.conscalc.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * @author josiah
 *
 */
public class SearchRecord extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
