package table;

public interface DataHandler {

	public boolean getDeleting();
	public boolean isEditable(int row, int col);
	public void dataEdited();

}
