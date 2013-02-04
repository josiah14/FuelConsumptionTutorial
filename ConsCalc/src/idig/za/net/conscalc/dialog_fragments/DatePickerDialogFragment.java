/**
 * 
 */
package idig.za.net.conscalc.dialog_fragments;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author josiah
 *
 */
@SuppressLint("NewApi")
public class DatePickerDialogFragment extends DialogFragment {
	private Vector<OnDateSetListener> _listeners;
	
	int mYear, mMonth, mDay;
	DatePickerDialog datePickerDialog;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar cal = Calendar.getInstance();
		
		// get the year from the calendar
		mYear = cal.get(Calendar.YEAR);
		// get the month from the calendar
		mMonth = cal.get(Calendar.MONTH);
		// get the day from the calendar
		mDay = cal.get(Calendar.DAY_OF_MONTH);

		datePickerDialog = new DatePickerDialog(
				getActivity(), null, mYear, mMonth, mDay);
		datePickerDialog.setCancelable(true);
		datePickerDialog.setCanceledOnTouchOutside(true);
		datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Set", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				datePickerDialog.getDatePicker().clearFocus();
				fireDatePickedEvent();
			}
		});
		
		datePickerDialog.setTitle("Set Date");
		
		return datePickerDialog;
	}
	
	public void setOnDatePickedListener(OnDateSetListener listener) {
		if (_listeners == null) 
			_listeners = new Vector<OnDateSetListener>();
		
		_listeners.addElement(listener);
	}
	
	protected void fireDatePickedEvent () {
		mYear = datePickerDialog.getDatePicker().getYear();
		mMonth = datePickerDialog.getDatePicker().getMonth();
		mDay = datePickerDialog.getDatePicker().getDayOfMonth();
		
		if (_listeners != null && !_listeners.isEmpty()) {
			Enumeration<OnDateSetListener> e = _listeners.elements();
			while (e.hasMoreElements()) {
				OnDateSetListener listener = e.nextElement();
				listener.onDateSet(datePickerDialog.getDatePicker(), mYear, mMonth, mDay);
			}
		}
	}
	

}
