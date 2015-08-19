package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import net.ucanaccess.jdbc.UcanaccessSQLException;

public class DBManager {
	private Connection conn;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	// private static String database = "./Files/SaipaDB.mdb" ;
	private static final String user = "";
	private static final String password = "";
	private static final String DATABASE_URL = "jdbc:ucanaccess://Files/SaipaDB.mdb";

	public DBManager() throws SQLException {
		connectToDB();
	}

	public ResultSet selectStarFromTable(String tableName) {
		try {
			return executeQuery("SELECT * FROM " + tableName + " ;");
		} catch (SQLException e) {
			return null;
		}
	}

	public ResultSet selectStarFromTable(String tableName, String orderField) {
		try {
			return executeQuery("SELECT * FROM " + tableName + " ORDER BY "
					+ orderField + " ;");
		} catch (SQLException e) {
			return null;
		}
	}

	public ResultSet selectFromTable(String tableName, Vector<String> vars) {
		String order = "SELECT ";
		for (String string : vars)
			order += string + " ,";
		order = (String) order.subSequence(0, order.length() - 2);
		order += " FROM " + tableName;
		ResultSet r = null;
		try {
			r = executeQuery(order);
		} catch (SQLException e) {
		}
		return r;
	}

	public ResultSet selectFromTable(String tableName,
			ArrayList<String> conditions) {
		try {
			String order = "SELECT " + Database.columns + " FROM " + tableName
					+ " WHERE ";
			for (int i = 0; i < conditions.size() - 1; i++) {
				order += conditions.get(i);
				order += " AND ";
			}
			order += conditions.get(conditions.size() - 1);
			order += ";";
			return executeQuery(order);
		} catch (SQLException e) {
			return null;
		}
	}

	public void createNewTable(String tableName, Vector<String> variable)
			throws SQLException {
		try {
			dropTable(tableName);
		} catch (SQLException e) {
		}
		String order = "CREATE TABLE " + tableName + " (";
		for (int i = 0; i < variable.size() - 1; i++)
			order += variable.elementAt(i) + ",";
		order += variable.lastElement() + ");";
		execute(order);
	}

	public synchronized boolean execute(String order)
			throws UcanaccessSQLException, SQLException {
		System.out.println(order);
		return statement.execute(order);
	}

	public synchronized ResultSet executeQuery(String order)
			throws SQLException {
		System.out.println(order);
		return statement.executeQuery(order);
	}

	public void printResult(String tableName) throws SQLException {
		metaData = resultSet.getMetaData();
		System.out.println("Table : " + tableName);
		for (int i = 1; i <= metaData.getColumnCount(); i++)
			System.out.printf("%s\t", metaData.getColumnName(i));
		System.out.println();
		while (resultSet.next()) {
			for (int i = 1; i <= metaData.getColumnCount(); i++)
				System.out.printf("%s\t", resultSet.getObject(i));
			System.out.println();
		}
	}

	public void printResult(String tableName, JTextArea ta) throws SQLException {
		metaData = resultSet.getMetaData();
		ta.append("\nTable : " + tableName + "\n");
		for (int i = 1; i <= metaData.getColumnCount(); i++)
			ta.append("\t" + metaData.getColumnName(i));
		ta.append("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= metaData.getColumnCount(); i++)
				ta.append("\t" + resultSet.getObject(i));
			ta.append("\n");
		}
	}

	private void connectToDB() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			System.out.println("Driver loaded!");
			conn = DriverManager.getConnection(DATABASE_URL, user, password);
			statement = conn.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
			String msg = "<html>Can't connect or create statement!<br>Press OK to exit!<p>Error message is:<br>"
					+ ex.getMessage()
					+ "<br>The SQLState is:<br>"
					+ ex.getSQLState()
					+ "<br>The error code is: "
					+ ex.getErrorCode()
					+ "<br>The cause is:<br>"
					+ ex.getCause();
			while ((ex = ex.getNextException()) != null)
				msg += "<p>Error message is:<br>" + ex.getMessage()
						+ "<br>The SQLState is:<br>" + ex.getSQLState()
						+ "<br>The error code is: " + ex.getErrorCode()
						+ "<br>The cause is:<br>" + ex.getCause();
			JOptionPane.showMessageDialog(null, msg, "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
		}
	}

	public void printResult(String tableName, ResultSet resultSet)
			throws SQLException {
		metaData = resultSet.getMetaData();
		System.out.println("Table : " + tableName);
		for (int i = 1; i <= metaData.getColumnCount(); i++)
			System.out.printf("%s\t", metaData.getColumnName(i));
		System.out.println();
		while (resultSet.next()) {
			for (int i = 1; i <= metaData.getColumnCount(); i++)
				System.out.printf("%s\t", resultSet.getObject(i));
			System.out.println();
		}
	}

	public void printResult(String tableName, JTextArea ta, ResultSet resultSet)
			throws SQLException {
		metaData = resultSet.getMetaData();
		ta.append("\nTable : " + tableName + "\n");
		for (int i = 1; i <= metaData.getColumnCount(); i++)
			ta.append("\t" + metaData.getColumnName(i));
		ta.append("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= metaData.getColumnCount(); i++)
				ta.append("\t" + resultSet.getObject(i));
			ta.append("\n");
		}
	}

	public void deleteFromTable(String tableName, String where)
			throws SQLException {
		execute("DELETE FROM " + tableName + " WHERE " + where);
	}

	// public String getColumnName(int columnNum){
	// try {
	// metaData = resultSet.getMetaData() ;
	// return metaData.getColumnName(columnNum);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
	/**
	 * 
	 * @param tableName
	 * @param pars
	 * @param values
	 * @throws SQLException
	 * 
	 *             use it for inserting special fields into a table !! :D
	 * 
	 */
	public void insertToTable(String tableName, Vector<String> pars,
			Vector<String> values) throws UcanaccessSQLException, SQLException {
		String order = "INSERT INTO " + tableName + " (";
		for (int i = 0; i < pars.size() - 1; i++)
			order += "" + pars.elementAt(i) + "" + ",";
		order += "" + pars.lastElement() + ") VALUES (";
		for (int i = 0; i < values.size() - 1; i++)
			order += "'" + values.elementAt(i) + "'" + ",";
		order += "'" + values.lastElement() + "');";
		execute(order);
	}

	/**
	 * 
	 * 
	 * @param tableName
	 * @param values
	 * @throws SQLException
	 * 
	 *             insert YERKHI !!!!
	 */
	public void insertToTable(String tableName, Vector<String> values)
			throws SQLException {
		String order = "INSERT INTO " + tableName + " VALUES (";
		for (int i = 0; i < values.size() - 1; i++)
			order += "'" + values.elementAt(i) + "'" + ",";
		order += "'" + values.lastElement() + "');";
		execute(order);
	}

	public void insertToTable(String tableName, String value)
			throws SQLException {
		String order = "INSERT INTO " + tableName + " VALUES ('" + value
				+ "');";
		execute(order);
	}

	// public static void main(String[] args)
	// {
	// try
	// {
	// DBManager db = new DBManager(false) ;
	// }
	// catch (SQLException e)
	// {
	// System.out.println("SQLException ...\nCAN'T RUN THE PROGRAM....");
	// }
	// }
	public void dropTable(String tableName) throws SQLException {
		String order = "DROP TABLE IF EXISTS " + tableName + " ;";
		execute(order);
	}

	public void updateRow(String tableName, String set, String where)
			throws SQLException {
		execute("UPDATE " + tableName + " SET " + set + " WHERE " + where + ";");
	}

	public void updateRow(String tableName, Vector<String> set, String where)
			throws SQLException {
		for (int i = 0; i < set.size(); i++)
			updateRow(tableName, set.elementAt(i), where);
	}

	public void addColumn(String tableName, String newCol) throws SQLException {
		execute("ALTER TABLE " + tableName + " ADD " + newCol + " ;");
	}

	public void changeColumnName(String tableName, String oldCol, String newCol)
			throws SQLException {
		execute("ALTER TABLE " + tableName + " CHANGE " + oldCol + " " + newCol
				+ ";");
	}

	// public static String changeName(String str)
	// {
	// byte[] b = str.getBytes() ;
	// for(int i = 0 ; i < b.length ; i++)
	// {
	// if(b[i] == 32)
	// b[i] = '_' ;
	// else if(b[i] == '(')
	// b[i] = '-' ;
	// else if(b[i] == ')')
	// b[i] = '.' ;
	// }
	// String newString = new String(b) ;
	// return newString ;
	// }
	@Override
	public void finalize() {
		try {
			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<Vector<String>> getData(ResultSet rs) throws SQLException {
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		int cols = rs.getMetaData().getColumnCount();
		int size = 0;
		while (rs.next()) {
			data.add(new Vector<String>());
			data.elementAt(size).add( ((int)(rs.getInt(1)))+"");
			for (int i = 2; i <= cols; i++)
				data.elementAt(size).add((String) rs.getObject(i));
			size++;
		}
		return data;
	}

	public ResultSet describeTable(String string) {
		try {
			return executeQuery("DESCRIBE " + string + " ;");
		} catch (SQLException e) {
			return null;
		}
	}

	// public static void main(String[] args) {
	// try {
	// new DBManager();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
}
