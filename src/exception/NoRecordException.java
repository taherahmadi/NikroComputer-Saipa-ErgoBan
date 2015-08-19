package exception;

import java.util.ArrayList;

import logic.Record;

public class NoRecordException extends DatabaseException {

	private static final long serialVersionUID = 1L;
	private ArrayList<Record> f;

	public NoRecordException(ArrayList<Record> f) {
		this.f = f;
	}

	public ArrayList<Record> getRecord() {

		return f;
	}

}
