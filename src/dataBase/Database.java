package dataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;
import logic.Record;
import exception.DBNotFoundException;
import exception.ExcelFormatNotSupportedException;
import exception.ExcelNotFoundException;

public class Database {
	private DBManager manager;
	private boolean connected;
	private static final int fieldsNum = 66;
	public static String columns;
	private Stack<Vector<Record>> deletedStack;
	public static String tableName = "COLORSALOON1"; // ID ezafe bara tartib
														// ---> Primary Key !!!
	public static final String DBFile = "./Files/Data.dat";

	public Database() {

		deletedStack = new Stack<>();
		try {
			manager = new DBManager();
			connected = true;
			// firstRun();
		} catch (SQLException e) {
			connected = false;
			System.err.println("Failed to connect to DBMS");
			e.printStackTrace();
		}
		String query = "";
		for (int i = 0; i < fieldsNum - 1; i++) {
			query += " " + getColumnName(i) + " , ";
		}
		query += " " + getColumnName(fieldsNum - 1);
		columns = query;
	}

	public Record getByID(int i) {
		ArrayList<String> condition = new ArrayList<>();
		Vector<Record> resultVec = new Vector<>();
		// ResultSet rs1 = manager.selectStarFromTable(tableName);
		condition.add("id = '" + i + "'");
		ResultSet rs = manager.selectFromTable(tableName, condition);
		resultVec = resultToRecord(rs);
		return resultVec.elementAt(0);
	}

	// public DataBase(String excelPath) {
	// this();
	// loadFromExcel(excelPath);
	// }
	/**
	 * 
	 * To append from .xls file into Db;
	 * 
	 * @param ExcelPath
	 */
	// /////////////////////////////////////////////
	// public Record[] loadFromExcel(String ExcelPath)
	// throws ExcelNotFoundException, ExcelFormatNotSupportedException {
	// Workbook w;
	// Vector<Record> failedRecords = new Vector<>();
	// // / Check for Format and Existence of File
	// if (ExcelPath.isEmpty()
	// || !Files.exists(Paths.get(ExcelPath),
	// LinkOption.NOFOLLOW_LINKS)) {
	// // isConnect = false;
	// throw new ExcelNotFoundException();
	// }
	// if (ExcelPath.endsWith(".dat"))
	// return readFromFile(ExcelPath);
	// else if (!ExcelPath.endsWith(".xls")) {
	// throw new ExcelFormatNotSupportedException();
	// }
	// // // Check OK !!!
	// else
	// try {
	// w = Workbook.getWorkbook(new File(ExcelPath));
	// Sheet s = w.getSheet(0);
	// for (int columnCounter = 2; columnCounter < s.getRows(); columnCounter++)
	// { // for
	// // one
	// // column
	// // !!
	// if (s.getCell(0, columnCounter).getContents().isEmpty()) {
	// break;
	// }
	// String[] recordString = new String[fieldsNum];
	// for (int rowCounter = 0; rowCounter <= fieldsNum; rowCounter++) { // for
	// // rows
	// if (rowCounter == 49) {
	// // !!!!
	// // 1
	// // until
	// // 48
	//
	// try {
	// String content = s.getCell(rowCounter,
	// columnCounter).getContents();
	// if (content == null || content.isEmpty())
	// recordString[rowCounter - 1] = null;
	// else {
	// if (content.equals("دارد"))
	// recordString[rowCounter - 1] = "1";
	// else
	// recordString[rowCounter - 1] = "0";
	// // System.out.println(content);
	// }
	// } catch (Exception e) {
	// }
	// } else
	// try {
	// String content = s.getCell(rowCounter,
	// columnCounter).getContents();
	// if (content == null || content.isEmpty())
	// recordString[rowCounter - 1] = null;
	// else
	// recordString[rowCounter - 1] = content;
	// } catch (Exception e) {
	// }
	// }
	// Record r = new Record(recordString);
	// if (insertData(r))
	// ;
	// else
	// failedRecords.add(r);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// if (failedRecords.isEmpty())
	// return new Record[0];
	// else {
	// Record[] res = new Record[failedRecords.size()];
	// for (int k = 0; k < res.length; k++)
	// res[k] = failedRecords.elementAt(k);
	// return res;
	// }
	// }

	public Record[] loadFromExcel(File file) throws ExcelNotFoundException,
			ExcelFormatNotSupportedException {
		Workbook w;
		Vector<Record> failedRecords = new Vector<>();
		// / Check for Format and Existence of File
		if (file.getAbsolutePath().isEmpty()
				|| !Files.exists(Paths.get(file.getAbsolutePath()),
						LinkOption.NOFOLLOW_LINKS)) {
			// isConnect = false;
			throw new ExcelNotFoundException();
		}
		if (file.getAbsolutePath().endsWith(".dat"))
			return readFromFile(file);
		else if (file.getAbsolutePath().endsWith(".csv")) {
			FileInputStream fis;
			byte[] data;
			String input = null;
			try {
				fis = new FileInputStream(file);
				data = new byte[(int) file.length()];
				fis.read(data);
				fis.close();
				input = new String(data, "UTF-8");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String insteadOfComma = "☺☻☺";
			input = input.replaceAll(",", insteadOfComma);
			input = input.replaceAll("~", ","); // TODO

			StringTokenizer st1 = new StringTokenizer(input, "\n\r");
			st1.nextToken();
			while (st1.hasMoreTokens()) {
				String rcdStr = st1.nextToken();
				StringTokenizer st2 = new StringTokenizer(rcdStr,
						insteadOfComma.substring(1, 2));
				String[] recordInfo = new String[66];
				for (int i = 0; i < recordInfo.length; i++) {
					recordInfo[i] = st2.nextToken();
					recordInfo[i] = recordInfo[i].replaceAll("☺", "");
				}
				recordInfo[9] = recordInfo[9].replaceAll(" شمسی", "");
				if (recordInfo[48].equals("دارد"))
					recordInfo[48] = "1";
				else
					recordInfo[48] = "0";
				Record rec = new Record(recordInfo);
				if (!insertData(rec))
					failedRecords.add(rec);
			}
			Record[] res = new Record[failedRecords.size()];
			failedRecords.toArray(res);
			return res;
		} else if (!file.getAbsolutePath().endsWith(".xls")) {
			throw new ExcelFormatNotSupportedException();
		} else
			try {
				w = Workbook.getWorkbook(file);
				Sheet e = w.getSheet(0);
				int k = e.getRows();
				System.out.println("Rows: " + k);
				for (int i = 2; i < e.getRows(); i++) {
					String[] q = new String[Record.fieldNum];
					for (int j = 0; j < Record.fieldNum; j++)
						q[j] = (e.getCell(j, i).getContents()).replace("\n",
								"~").replace("\t", "");
					if (q[48].equals("دارد"))
						q[48] = "1";
					else
						q[48] = "0";
					if (!insertData(new Record(q)))
						failedRecords.add(new Record(q));
				}
				Record[] ress = new Record[failedRecords.size()];
				failedRecords.toArray(ress);
				return ress;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new Record[0];
	}

	private String getColumnName(int code) { // TODO Data Change
		switch (code) {
		case 0:
			return "id";
		case 1:
			return "formNumber";
		case 2:
			return "salonName";
		case 3:
			return "lineName";
		case 4:
			return "stationNumber";
		case 5:
			return "ProccesName";
		case 6:
			return "workingTask";
		case 7:
			return "operatorName";
		case 8:
			return "filmCode";
		case 9:
			return "reviewDate";
		case 10:
			return "operatorCode";
		case 11:
			return "tool";
		case 12:
			return "toolWeight";
		case 13:
			return "producePerHour";
		case 14:
			return "A";
		case 15:
			return "BS";
		case 16:
			return "BM";
		case 17:
			return "C";
		case 18:
			return "D";
		case 19:
			return "E";
		case 20:
			return "F";
		case 21:
			return "G";
		case 22:
			return "H";
		case 23:
			return "J";
		case 24:
			return "K";
		case 25:
			return "L";
		case 26:
			return "M";
		case 27:
			return "N";
		case 28:
			return "P";
		case 29:
			return "Q";
		case 30:
			return "backPoint";
		case 31:
			return "backLevel";
		case 32:
			return "shoulderPoint";
		case 33:
			return "shoulderLevel";
		case 34:
			return "wristPoint";
		case 35:
			return "wristLevel";
		case 36:
			return "neckPoint";
		case 37:
			return "neckLevel";
		case 38:
			return "driving";
		case 39:
			return "drivingLevel";
		case 40:
			return "vibration";
		case 41:
			return "vibrationLevel";
		case 42:
			return "workPlace";
		case 43:
			return "workPlaceLevel";
		case 44:
			return "stress";
		case 45:
			return "stressLevel";
		case 46:
			return "totalPoint";
		case 47:
			return "qECLevel";
		case 48: // /// new new new new new new new new new new
			return "hasBar";
		case 49:
			return "describtion";
		case 50:
			return "jobRotation";
		case 51:
			return "correctiveActionType";
		case 52:
			return "correctiveActionNumber";
		case 53:
			return "correctiveActionDate";
		case 54:
			return "RbackPoint";
		case 55:
			return "RbackLevel";
		case 56:
			return "RshoulderPoint";
		case 57:
			return "RshoulderLevel";
		case 58:
			return "RwristPoint";
		case 59:
			return "RwristLevel";
		case 60:
			return "RneckPoint";
		case 61:
			return "RneckLevel";
		case 62:
			return "RtotalPoint";
		case 63:
			return "RqECLevel";
		case 64:
			return "reAssessmentDate";
		case 65:
			return "assessorName";
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param r
	 * @return false if not inserted
	 */

	public boolean insertData(Record r) { // TODO Data Change
		Vector<String> values = new Vector<>();
		Vector<String> pars = new Vector<>();
		pars.add(getColumnName(0));
		values.add(String.valueOf(r.getId()));

		pars.add(getColumnName(1));
		values.add(String.valueOf(r.getFormNumber()));

		pars.add(getColumnName(2));
		values.add(r.getSalonName());

		pars.add(getColumnName(3));
		values.add(r.getLineName());

		pars.add(getColumnName(4));
		values.add(r.getStationNumber());

		pars.add(getColumnName(5));
		values.add(r.getProccesName());

		pars.add(getColumnName(6));
		values.add(r.getWorkingTask());

		pars.add(getColumnName(7));
		values.add(r.getOperatorName());

		pars.add(getColumnName(8));
		values.add(r.getFilmCode());

		pars.add(getColumnName(9));
		values.add(r.getReviewDate());

		pars.add(getColumnName(10));
		values.add(r.getOperatorCode());

		pars.add(getColumnName(11));
		values.add(r.getTool());

		pars.add(getColumnName(12));
		values.add(r.getToolWeight());

		pars.add(getColumnName(13));
		values.add(r.getProducePerHour());

		pars.add(getColumnName(14));
		values.add(String.valueOf(r.getA()));

		pars.add(getColumnName(15));
		values.add(r.getBS());

		pars.add(getColumnName(16));
		values.add(r.getBM());

		pars.add(getColumnName(17));
		values.add(String.valueOf(r.getC()));

		pars.add(getColumnName(18));
		values.add(String.valueOf(r.getD()));

		pars.add(getColumnName(19));
		values.add(String.valueOf(r.getE()));

		pars.add(getColumnName(20));
		values.add(String.valueOf(r.getF()));

		pars.add(getColumnName(21));
		values.add(String.valueOf(r.getG()));

		pars.add(getColumnName(22));
		values.add(String.valueOf(r.getH()));

		pars.add(getColumnName(23));
		values.add(String.valueOf(r.getJ()));

		pars.add(getColumnName(24));
		values.add(String.valueOf(r.getK()));

		pars.add(getColumnName(25));
		values.add(String.valueOf(r.getL()));

		pars.add(getColumnName(26));
		values.add(String.valueOf(r.getM()));

		pars.add(getColumnName(27));
		values.add(String.valueOf(r.getN()));

		pars.add(getColumnName(28));
		values.add(String.valueOf(r.getP()));

		pars.add(getColumnName(29));
		values.add(String.valueOf(r.getQ()));

		pars.add(getColumnName(30));
		values.add(r.getAsses().getBackPoint());

		pars.add(getColumnName(31));
		values.add(r.getAsses().getBackLevel());

		pars.add(getColumnName(32));
		values.add(r.getAsses().getShoulderPoint());

		pars.add(getColumnName(33));
		values.add(r.getAsses().getShoulderLevel());

		pars.add(getColumnName(34));
		values.add(r.getAsses().getWristPoint());

		pars.add(getColumnName(35));
		values.add(r.getAsses().getWristLevel());

		pars.add(getColumnName(36));
		values.add(r.getAsses().getNeckPoint());

		pars.add(getColumnName(37));
		values.add(r.getAsses().getNeckLevel());

		pars.add(getColumnName(38));
		values.add(r.getWorkersAssessment().getDriving());

		pars.add(getColumnName(39));
		values.add(r.getWorkersAssessment().getDrivingLevel());

		pars.add(getColumnName(40));
		values.add(r.getWorkersAssessment().getVibration());

		pars.add(getColumnName(41));
		values.add(r.getWorkersAssessment().getVibrationLevel());

		pars.add(getColumnName(42));
		values.add(r.getWorkersAssessment().getWorkPlace());

		pars.add(getColumnName(43));
		values.add(r.getWorkersAssessment().getWorkPlaceLevel());

		pars.add(getColumnName(44));
		values.add(r.getWorkersAssessment().getStress());

		pars.add(getColumnName(45));
		values.add(r.getWorkersAssessment().getStressLevel());

		pars.add(getColumnName(46));
		values.add(r.getAsses().getTotalPoint());

		pars.add(getColumnName(47));
		values.add(r.getAsses().getQECLevel());

		pars.add(getColumnName(48));
		values.add(String.valueOf(r.getHasBar()));

		pars.add(getColumnName(49));
		values.add(r.getDescribtion());

		pars.add(getColumnName(50));
		values.add(r.getJobRotation());

		pars.add(getColumnName(51));
		values.add(r.getCorrectiveActionType());

		pars.add(getColumnName(52));
		values.add(String.valueOf(r.getCorrectiveActionNumber()));

		pars.add(getColumnName(53));
		values.add(r.getCorrectiveActionDate());

		pars.add(getColumnName(54));
		values.add(r.getReasses().getBackPoint());

		pars.add(getColumnName(55));
		values.add(r.getReasses().getBackLevel());

		pars.add(getColumnName(56));
		values.add(r.getReasses().getShoulderPoint());

		pars.add(getColumnName(57));
		values.add(r.getReasses().getShoulderLevel());

		pars.add(getColumnName(58));
		values.add(r.getReasses().getWristPoint());

		pars.add(getColumnName(59));
		values.add(r.getReasses().getWristLevel());

		pars.add(getColumnName(60));
		values.add(r.getReasses().getNeckPoint());

		pars.add(getColumnName(61));
		values.add(r.getReasses().getNeckLevel());

		pars.add(getColumnName(62));
		values.add(r.getReasses().getTotalPoint());

		pars.add(getColumnName(63));
		values.add(r.getReasses().getQECLevel());

		pars.add(getColumnName(64));
		values.add(r.getReAssessmentDate());

		pars.add(getColumnName(65));
		values.add(r.getAssessorName());

		try {
			manager.insertToTable(tableName, pars, values);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Vector<Record> resultToRecord(ResultSet rs) { // updated !!!!
		Vector<Record> result = new Vector<>();
		try {
			Vector<Vector<String>> data = manager.getData(rs);
			for (Vector<String> rec : data) {
				String[] tmp = new String[rec.size()];
				for (int i = 0; i < rec.size(); i++)
					tmp[i] = rec.elementAt(i);
				result.add(new Record(tmp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// try {
		// while (rs.next()) {
		// String[] record = new String[fieldsNum]; // depends on number of
		// // fields!!
		// // record[0] = String.valueOf(rs.getInt(1));
		// // record[1] = rs.getString(2);
		// // record[2] = rs.getString(3);
		// // record[3] = String.valueOf(rs.getInt(4));
		// // for (int i = 4; i <= 10; i++) {
		// // record[i] = rs.getString(i + 1);
		// // }
		// // record[11] = String.valueOf(rs.getFloat(12));
		// // for (int i = 12; i <= 27; i++) {
		// // record[i] = String.valueOf(rs.getInt(i + 1));
		// // }
		// // for (int i = 28; i <= 44; i++) {
		// // record[i] = rs.getString(i + 1);
		// // }
		// // record[45] = String.valueOf(rs.getString(46)); // new
		// // for (int i = 46; i <= 49; i++) {
		// // record[i] = rs.getString(i + 1);
		// // }
		// // record[50] = String.valueOf(rs.getInt(51));
		// for (int i = 0; i <= 63; i++) {
		// record[i] = rs.getString(i + 1);
		// }
		// result.add(new Record(record));
		// System.out.println(result.lastElement());
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		return result;
	}

	/**
	 * 
	 * 
	 * @param records
	 * @return
	 * 
	 * @return null if terminated successfully else returns records failed to
	 *         insert
	 * 
	 */
	public Record[] deleteData(ArrayList<Record> records) { // update free !
		Vector<Record> recordVec = new Vector<>();
		for (int i = 0; i < records.size(); i++)
			if (records.size() > 0)
				try {
					ArrayList<String> condition = new ArrayList<>();
					condition.add("formNumber = '"
							+ String.valueOf(records.get(i).getFormNumber())
							+ "'");
					condition.add(" reviewDate ='"
							+ String.valueOf(records.get(i).getReviewDate())
							+ "'");
					condition.add(" id ='"
							+ String.valueOf(records.get(i).getId()) + "'");
					// manager.execute("START TRANSACTION;");
					ResultSet rs = manager
							.selectFromTable(tableName, condition);
					Vector<Record> table = resultToRecord(rs);
					// System.out.println("RERCORD SIZE >> " + table.size());
					System.out.println(table.size());
					for (Record p : table) {
						recordVec.add(p);
						manager.deleteFromTable(
								tableName,
								"formNumber = "
										+ String.valueOf(records.get(i)
												.getFormNumber())
										+ " AND "
										+ "reviewDate = '"
										+ String.valueOf(records.get(i)
												.getReviewDate())
										+ "' AND"
										+ " id ='"
										+ String.valueOf(records.get(i).getId())
										+ "'");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					Record[] res = new Record[records.size() - i + 1];
					for (int j = 0; j < res.length; j++) {
						res[j] = records.get(i);
						i++;
					}
					e.printStackTrace();
					return res;
				}
		deletedStack.push(recordVec);
		return null;
	}

	public void undoDelete() throws SQLException { // update free
		if (!deletedStack.isEmpty()) {
			Vector<Record> undoRecord = deletedStack.pop();
			for (int i = undoRecord.size() - 1; i >= 0; i--) {
				undoRecord.get(undoRecord.size() - 1 - i).setId(getmaxid());
				insertData(undoRecord.get(undoRecord.size() - 1 - i));
			}
		}
	}

	public boolean removeFromDeleteds(Record record) { // by Saeid
		for (int i = 0; i < deletedStack.size(); i++)
			for (int j = 0; j < deletedStack.elementAt(i).size(); j++)
				if (record.getId() == deletedStack.elementAt(i).elementAt(j)
						.getId()) {
					deletedStack.elementAt(i).removeElementAt(j);
					if (deletedStack.elementAt(i).size() == 0) {
						deletedStack.removeElementAt(i);
						return true;
					} else
						return false;
				}
		return false;
	}

	/**
	 * @return failed records !!!!
	 * 
	 * @param path
	 * @throws DBNotFoundException
	 * @throws SQLException
	 */
	public Record[] replaceDataBsae(String path) throws DBNotFoundException,
			SQLException { // update free !!!
		Vector<Record> failedRecords = new Vector<>();
		if (path.isEmpty()
				|| !Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
			throw new DBNotFoundException();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					new File(path)));
			if (in != null) {
				while (true) {
					try {
						Record r = (Record) in.readObject();
						if (insertData(r))
							;
						else
							failedRecords.add(r);
					} catch (ClassNotFoundException | IOException e) {
						try {
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
			}
			return null;
		} catch (IOException e) {
			Record[] res = new Record[failedRecords.size()];
			for (int i = 0; i < failedRecords.size(); i++)
				res[i] = failedRecords.elementAt(i);
			return res;
		}
	}

	public Record[] selectRecords(int columnNumber, String stringValue) { // update
																			// free
																			// !!
		ArrayList<String> condition = new ArrayList<>();
		Vector<Record> resultVec = new Vector<>();
		// ResultSet rs1 = manager.selectStarFromTable(tableName);
		condition.add(getColumnName(columnNumber) + " = '" + stringValue + "'");
		ResultSet rs = manager.selectFromTable(tableName, condition);
		resultVec = resultToRecord(rs);
		Record[] res = new Record[resultVec.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = resultVec.elementAt(i);
		}
		try {
			rs.close();
			// rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * To read data from [*.dat] file.
	 * 
	 * @param path
	 * @return
	 * @throws DBNotFoundException
	 */
	public Record[] readFromFile(File file) { // update free !!
		Vector<Record> resVec = new Vector<>();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			if (in != null) {
				while (true) {
					try {
						Record r = (Record) in.readObject();
						resVec.add(r);
					} catch (ClassNotFoundException | IOException e) {
						try {
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
			}

		} catch (IOException e) {

		}

		Vector<Record> fail = new Vector<Record>();
		for (int i = 0; i < resVec.size(); i++)
			if (insertData(resVec.elementAt(i)))
				;
			else
				fail.add(resVec.elementAt(i));
		Record[] res = new Record[fail.size()];
		res = fail.toArray(res);
		return res;
	}

	public Record[] getAllrecords() { // update free !
		Vector<Record> recordsVec = new Vector<>();
		try {
			String query = "SELECT ";
			for (int i = 0; i < fieldsNum - 1; i++) {
				query += " " + getColumnName(i) + " , ";
			}
			query += " " + getColumnName(fieldsNum - 1);
			query += " FROM " + tableName + "  ORDER BY id ASC; ";
			recordsVec = resultToRecord(manager.executeQuery(query));
		} catch (SQLException e) {
			return null;
		}
		Record[] res = new Record[recordsVec.size()];
		for (int k = 0; k < recordsVec.size(); k++) {
			res[k] = recordsVec.elementAt(k);
		}
		return res;
	}

	public boolean isConnect() { // update free !
		return connected;
	}

	/**
	 * 
	 * To Copy Data in default path(mysql for now !! :D) to .dat file.
	 * 
	 * 
	 * @param path
	 * @throws DBNotFoundException
	 */
	public void saveToFile(String path) { // update free !
		Record[] records = getAllrecords();
		File f = new File(path);
		try {
			f.createNewFile();
		} catch (IOException e1) {
		}
		if (path.isEmpty()
				|| !Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
		}
		if (records == null || records.length == 0)
			return;
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(f));
			for (Record r : records) {
				out.writeObject(r);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DBManager getManager() {
		return manager;
	}

	public void update(Record records) { // update free !
		ArrayList<String> condition = new ArrayList<>();
		condition.add(" id ='" + String.valueOf(records.getId()) + "'");
		ResultSet rs = manager.selectFromTable(tableName, condition);
		Vector<Record> table = resultToRecord(rs);
		if (table.size() == 0)
			return;
		String a = "UPDATE "
				+ tableName
				+ " SET "
				+ "formNumber='"
				+ String.valueOf(records.getFormNumber())
				+ "'"
				+ ",salonName='"
				+ String.valueOf(records.getSalonName())
				+ "'"
				+ ",lineName='"
				+ String.valueOf(records.getLineName())
				+ "'"
				+ ",stationNumber='"
				+ String.valueOf(records.getStationNumber())
				+ "'"
				+ ",proccesName='"
				+ String.valueOf(records.getProccesName())
				+ "'"
				+ ",workingTask='"
				+ String.valueOf(records.getWorkingTask())
				+ "'"
				+ ",operatorName='"
				+ String.valueOf(records.getOperatorName())
				+ "'"
				+ ",filmCode='"
				+ String.valueOf(records.getFilmCode())
				+ "'"
				+ ",reviewDate='"
				+ String.valueOf(records.getReviewDate())
				+ "'"
				+ ",operatorCode='"
				+ String.valueOf(records.getOperatorCode())
				+ "'"
				+ ",tool='"
				+ String.valueOf(records.getTool())
				+ "'"
				+ ",toolWeight='"
				+ String.valueOf(records.getToolWeight())
				+ "'"
				+ ",producePerHour='"
				+ String.valueOf(records.getProducePerHour())
				+ "'"
				+ ",A='"
				+ String.valueOf(records.getA())
				+ "'"
				+ ",BS='"
				+ String.valueOf(records.getBS())
				+ "'"
				+ ",BM='"
				+ String.valueOf(records.getBM())
				+ "'"
				+ ",C='"
				+ String.valueOf(records.getC())
				+ "'"
				+ ",D='"
				+ String.valueOf(records.getD())
				+ "'"
				+ ",E='"
				+ String.valueOf(records.getE())
				+ "'"
				+ ",F='"
				+ String.valueOf(records.getF())
				+ "'"
				+ ",G='"
				+ String.valueOf(records.getG())
				+ "'"
				+ ",H='"
				+ String.valueOf(records.getH())
				+ "'"
				+ ",J='"
				+ String.valueOf(records.getJ())
				+ "'"
				+ ",K='"
				+ String.valueOf(records.getK())
				+ "'"
				+ ",L='"
				+ String.valueOf(records.getL())
				+ "'"
				+ ",M='"
				+ String.valueOf(records.getM())
				+ "'"
				+ ",N='"
				+ String.valueOf(records.getN())
				+ "'"
				+ ",P='"
				+ String.valueOf(records.getP())
				+ "'"
				+ ",Q='"
				+ String.valueOf(records.getQ())
				+ "'"
				+ ",backPoint='"
				+ String.valueOf(records.getAsses().getBackPoint())
				+ "'"
				+ ",backLevel='"
				+ String.valueOf(records.getAsses().getBackLevel())
				+ "'"
				+ ",shoulderPoint='"
				+ String.valueOf(records.getAsses().getShoulderPoint())
				+ "'"
				+ ",shoulderLevel='"
				+ String.valueOf(records.getAsses().getShoulderLevel())
				+ "'"
				+ ",wristPoint='"
				+ String.valueOf(records.getAsses().getWristPoint())
				+ "'"
				+ ",wristLevel='"
				+ String.valueOf(records.getAsses().getWristLevel())
				+ "'"
				+ ",neckPoint='"
				+ String.valueOf(records.getAsses().getNeckPoint())
				+ "'"
				+ ",neckLevel='"
				+ String.valueOf(records.getAsses().getNeckLevel())
				+ "'"
				+ ",driving='"
				+ String.valueOf(records.getWorkersAssessment().getDriving())
				+ "'"
				+ ",drivingLevel='"
				+ String.valueOf(records.getWorkersAssessment()
						.getDrivingLevel())
				+ "'"
				+ ",vibration='"
				+ String.valueOf(records.getWorkersAssessment().getVibration())
				+ "'"
				+ ",vibrationLevel='"
				+ String.valueOf(records.getWorkersAssessment()
						.getVibrationLevel())
				+ "'"
				+ ",workPlace='"
				+ String.valueOf(records.getWorkersAssessment().getWorkPlace())
				+ "'"
				+ ",workPlaceLevel='"
				+ String.valueOf(records.getWorkersAssessment()
						.getWorkPlaceLevel())
				+ "'"
				+ ",stress='"
				+ String.valueOf(records.getWorkersAssessment().getStress())
				+ "'"
				+ ",stressLevel='"
				+ String.valueOf(records.getWorkersAssessment()
						.getStressLevel()) + "'" + ",totalPoint='"
				+ String.valueOf(records.getAsses().getTotalPoint()) + "'"
				+ ",qECLevel='"
				+ String.valueOf(records.getAsses().getQECLevel()) + "'"
				+ ",hasBar='" + String.valueOf(records.getHasBar()) + "'"
				+ ",describtion='" + String.valueOf(records.getDescribtion())
				+ "'" + ",jobRotation='"
				+ String.valueOf(records.getJobRotation()) + "'"
				+ ",correctiveActionType='"
				+ String.valueOf(records.getCorrectiveActionType()) + "'"
				+ ",correctiveActionNumber='"
				+ String.valueOf(records.getCorrectiveActionNumber()) + "'"
				+ ",correctiveActionDate='"
				+ String.valueOf(records.getCorrectiveActionDate()) + "'"
				+ ",RbackPoint='"
				+ String.valueOf(records.getReasses().getBackPoint()) + "'"
				+ ",RbackLevel='"
				+ String.valueOf(records.getReasses().getBackLevel()) + "'"
				+ ",RshoulderPoint='"
				+ String.valueOf(records.getReasses().getShoulderPoint()) + "'"
				+ ",RshoulderLevel='"
				+ String.valueOf(records.getReasses().getShoulderLevel()) + "'"
				+ ",RwristPoint='"
				+ String.valueOf(records.getReasses().getWristPoint()) + "'"
				+ ",RwristLevel='"
				+ String.valueOf(records.getReasses().getWristLevel()) + "'"
				+ ",RneckPoint='"
				+ String.valueOf(records.getReasses().getNeckPoint()) + "'"
				+ ",RneckLevel='"
				+ String.valueOf(records.getReasses().getNeckLevel()) + "'"
				+ ",RtotalPoint='"
				+ String.valueOf(records.getReasses().getTotalPoint()) + "'"
				+ ",RqECLevel='"
				+ String.valueOf(records.getReasses().getQECLevel()) + "'"
				+ ",reAssessmentDate='"
				+ String.valueOf(records.getReAssessmentDate()) + "'"
				+ ",assessorName='" + String.valueOf(records.getAssessorName())
				+ "'" + " WHERE id='" + String.valueOf(records.getId()) + "'";
		// System.out.println(a);
		;
		try {
			manager.execute(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getmaxid() {
		String a = "SELECT MAX(ID) AS maxid FROM COLORSALOON1 ";
		int maxid = 1;
		try {
			ResultSet r = manager.executeQuery(a);
			r.next();
			if (r.getObject(1) == null)
				maxid = 1;
			else
				maxid = (int) r.getObject(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxid;
	}

	/*
	 * CMNTED by Saeid public static void main(String[] args) { Database a=new
	 * Database(); Record [] b=a.getAllrecords(); b[0].setA(12); a.update(b[0]);
	 * }
	 */

}
