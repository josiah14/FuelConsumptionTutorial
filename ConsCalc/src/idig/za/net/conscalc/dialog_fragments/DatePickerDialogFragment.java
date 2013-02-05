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
	protected Vector<OnDateSetListener> _listeners;
	
	int mYear, mMonth, mDay;
	DatePickerDialog mDatePickerDialog;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar cal = Calendar.getInstance();
		
		// get the year from the calendar
		mYear = cal.get(Calendar.YEAR);
		// get the month from the calendar
		mMonth = cal.get(Calendar.MONTH);
		// get the day from the calendar
		mDay = cal.get(Calendar.DAY_OF_MONTH);

		mDatePickerDialog = new DatePickerDialog(
				getActivity(), null, mYear, mMonth, mDay);
		mDatePickerDialog.setCancelable(true);
		mDatePickerDialog.setCanceledOnTouchOutside(true);
		mDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		mDatePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Set", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mDatePickerDialog.getDatePicker().clearFocus();
				fireDatePickedEvent();
			}
		});
		
		mDatePickerDialog.setTitle("Set Date");
		
		return mDatePickerDialog;
	}
	
	public void setOnDatePickedListener(OnDateSetListener listener) {
		if (_listeners == null) 
			_listeners = new Vector<OnDateSetListener>();
		
		_listeners.addElement(listener);
	}
	
	protected void fireDatePickedEvent () {
		mYear = mDatePickerDialog.getDatePicker().getYear();
		mMonth = mDatePickerDialog.getDatePicker().getMonth();
		mDay = mDatePickerDialog.getDatePicker().getDayOfMonth();
		
		if (_listeners != null && !_listeners.isEmpty()) {
			Enumeration<OnDateSetListener> e = _listeners.elements();
			while (e.hasMoreElements()) {
				OnDateSetListener listener = e.nextElement();
				listener.onDateSet(mDatePickerDialog.getDatePicker(), mYear, mMonth, mDay);
			}
		}
	}
	

}
