package idig.za.net.conscalc.database;

/****************************
 * created by clive anthony *
 * www.101apps.co.za        *
 * **************************
 */

public class FuelRecord {
	private int mRowId;
	private long mDate;
	private int mOdometer;
	private String mLitres;
	private String mCost;

	public FuelRecord() {

	}

	public FuelRecord(int rowId, String registration, long date, int odometer,
			String litres, String cost) {
		this.mRowId = rowId;
		this.mDate = date;
		this.mOdometer = odometer;
		this.mLitres = litres;
		this.mCost = cost;
	}

	public void setRowId(int rowId) {
		this.mRowId = rowId;
	}

	public int getRowId() {
		return mRowId;
	}

	public void setDate(long date) {
		this.mDate = date;
	}

	public long getDate() {
		return mDate;
	}

	public void setOdometer(int odometer) {
		this.mOdometer = odometer;
	}

	public int getOdometer() {
		return mOdometer;
	}

	public void setLitres(String litres) {
		this.mLitres = litres;
	}

	public String getLitres() {
		return mLitres;
	}

	public void setCost(String cost) {
		this.mCost = cost;
	}

	public String getCost() {
		return mCost;
	}

}
