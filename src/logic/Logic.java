package logic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import dataBase.DBManager;
import dataBase.Database;
import exception.ExcelFormatNotSupportedException;
import exception.ExcelNotFoundException;

public class Logic {
	private Database db;
	private DBManager dbm;

	public Logic() {
		this.db = new Database();
		dbm = db.getManager();
	}

	public Record[] insert(Record[] record) {
		System.out.println();
		Vector<Record> buffer = new Vector<Record>();
		for (int i = 0; i < record.length; i++) {
			record[i].setId(getmaxid());
			if (db.isConnect() && db.insertData(record[i]))
				;
			else
				buffer.add(record[i]);
		}
		Record[] res = new Record[buffer.size()];
		res = buffer.toArray(res);
		return res;
	}

	public void undoDelete() {
		try {
			db.undoDelete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean removeFromDeleted(Record record) { // by Saeid
		return db.removeFromDeleteds(record);
	}

	public Record[] searchByNumber(int col, String value) {
		return db.selectRecords(col, value);
	}

	public void delete(Record[] records) {
		ArrayList<Record> buf = new ArrayList<Record>();
		for (int i = 0; i < records.length; i++)
			buf.add(records[i]);
		db.deleteData(buf);
	}

	public Record[] report() {
		Record[] a = db.getAllrecords();
		return a;
	}

	public Record getByID(int i) {
		return db.getByID(i);
	}

	// edited by Saeid
	public void csvoutput(String fileName, Record[] a) { // TODO Data Change
		String insteadOfComma = "~";
		String res = '\ufeff' + "id,  شماره فرم,نام سالن, نام خط,شماره (كد) ايستگاه,عنوان فرايند(ايستگاه),وظیفه کاری,نام اپراتور,كد (شماره) فيلم, تاريخ ارزيابي,كد اپراتور (چپ يا راست بودن ),ابزار یا قطعه مورد استفاده,Kg وزن ابزار يا قطعه مورد استفاده,نرخ توليد  در ساعت,A,BS,BM,C,D,E,F,G,H,J,K,L,M,N,P,Q,امتياز كمر, LEVEL,امتياز شانه,LEVEL,امتياز مچ,LEVEL,امتياز گردن,LEVEL,Driving,LEVEL,Vibration,LEVEL,Work Pace,LEVEL,Stress,LEVEL,امتياز كلي,QEC LEVEL,حمل بار,توضيحات / ملاحضات,نحوه گردش شغلي,نوع پيشنهادات /  اقدام اصلاحي,شماره اقدام اصلاحي,تاريخ اجراي اقدام اصلاحي,امتياز كمر,LEVEL,امتياز شانه,LEVEL,امتياز مچ,LEVEL,امتياز گردن,LEVEL,امتياز كلي,QEC LEVEL,تاريخ ارزيابي مجدد,نام كارشناس ارزياب";
		res += "\r\n";
		res = res.replace(",", insteadOfComma);
		File b = new File(fileName);
		try {
			b.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter r = null;
		try {
			r = new PrintWriter(fileName, "UTF-8");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Record rec : a) {
			res += rec.getId(); // 0
			res += insteadOfComma;
			res += rec.getFormNumber();
			res += insteadOfComma;
			res += rec.getSalonName();
			res += insteadOfComma;
			res += rec.getLineName();
			res += insteadOfComma;
			res += rec.getStationNumber();
			res += insteadOfComma;
			res += rec.getProccesName(); // 5
			res += insteadOfComma;
			res += rec.getWorkingTask();
			res += insteadOfComma;
			res += rec.getOperatorName();
			res += insteadOfComma;
			res += rec.getFilmCode();
			res += insteadOfComma;
			res += rec.getReviewDate() + " شمسی";
			res += insteadOfComma;
			res += rec.getOperatorCode(); // 10
			res += insteadOfComma;
			res += rec.getTool();
			res += insteadOfComma;
			res += rec.getToolWeight();
			res += insteadOfComma;
			res += rec.getProducePerHour();
			res += insteadOfComma;
			res += rec.getA();
			res += insteadOfComma;
			res += rec.getBS(); // 15
			res += insteadOfComma;
			res += rec.getBM();
			res += insteadOfComma;
			res += rec.getC();
			res += insteadOfComma;
			res += rec.getD();
			res += insteadOfComma;
			res += rec.getE();
			res += insteadOfComma;
			res += rec.getF(); // 20
			res += insteadOfComma;
			res += rec.getG();
			res += insteadOfComma;
			res += rec.getH();
			res += insteadOfComma;
			res += rec.getJ();
			res += insteadOfComma;
			res += rec.getK();
			res += insteadOfComma;
			res += rec.getL(); // 25
			res += insteadOfComma;
			res += rec.getM();
			res += insteadOfComma;
			res += rec.getN();
			res += insteadOfComma;
			res += rec.getP();
			res += insteadOfComma;
			res += rec.getQ();
			res += insteadOfComma;
			res += rec.getAsses().getBackPoint(); // 30
			res += insteadOfComma;
			res += rec.getAsses().getBackLevel();
			res += insteadOfComma;
			res += rec.getAsses().getShoulderPoint();
			res += insteadOfComma;
			res += rec.getAsses().getShoulderLevel();
			res += insteadOfComma;
			res += rec.getAsses().getWristPoint();
			res += insteadOfComma;
			res += rec.getAsses().getWristLevel(); // 35
			res += insteadOfComma;
			res += rec.getAsses().getNeckPoint();
			res += insteadOfComma;
			res += rec.getAsses().getNeckLevel();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getDriving();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getDrivingLevel();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getVibration(); // 40
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getVibrationLevel();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getWorkPlace();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getWorkPlaceLevel();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getStress();
			res += insteadOfComma;
			res += rec.getWorkersAssessment().getStressLevel(); // 45
			res += insteadOfComma;
			res += rec.getAsses().getTotalPoint();
			res += insteadOfComma;
			res += rec.getAsses().getQECLevel();
			res += insteadOfComma;
			if (rec.getHasBar() == 0)
				res += "ندارد";
			else
				res += "دارد";
			res += insteadOfComma;
			res += rec.getDescribtion();
			res += insteadOfComma;
			res += rec.getJobRotation(); // 50
			res += insteadOfComma;
			res += rec.getCorrectiveActionType();
			res += insteadOfComma;
			res += rec.getCorrectiveActionNumber();
			res += insteadOfComma;
			res += rec.getCorrectiveActionDate();
			res += insteadOfComma;
			res += rec.getReasses().getBackPoint();
			res += insteadOfComma;
			res += rec.getReasses().getBackLevel(); // 55
			res += insteadOfComma;
			res += rec.getReasses().getShoulderPoint();
			res += insteadOfComma;
			res += rec.getReasses().getShoulderLevel();
			res += insteadOfComma;
			res += rec.getReasses().getWristPoint();
			res += insteadOfComma;
			res += rec.getReasses().getWristLevel();
			res += insteadOfComma;
			res += rec.getReasses().getNeckPoint(); // 60
			res += insteadOfComma;
			res += rec.getReasses().getNeckLevel();
			res += insteadOfComma;
			res += rec.getReasses().getTotalPoint();
			res += insteadOfComma;
			res += rec.getReasses().getQECLevel();
			res += insteadOfComma;
			res += rec.getReAssessmentDate();
			res += insteadOfComma;
			res += rec.getAssessorName(); // 65
			res += insteadOfComma;
			res += "\r\n";

		}
		res = res.replaceAll(",", "|");
		res = res.replaceAll(insteadOfComma, ",");
		System.out.println(res);
		// r.write('\ufeff');
		r.write(res);
		r.flush();
		r.close();
	}

	public void replaceDataBase(String p) {
		try {
			db.replaceDataBsae(p);
		} catch (Exception e) {
		}
	}

	public Record[] loadFromFile(File file) {
		Record[] res = null;
		try {
			res = db.loadFromExcel(file);
		} catch (ExcelNotFoundException e) {
			e.printStackTrace();
		} catch (ExcelFormatNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(res.length);
		return res;
	}

	public int getsize() {

		return db.getAllrecords().length;
	}

	public void saveToFile(String absolutePath) {
		db.saveToFile(absolutePath);
	}

	public String[] getSalonNames() {
		Vector<String> buf = new Vector<>();
		for (Record d : db.getAllrecords())
			if (!buf.contains(d.getSalonName()))
				buf.add(d.getSalonName());
		String[] res = new String[buf.size()];
		res = buf.toArray(res);
		return res;
	}

	public String[] getJobRotations() {
		Vector<String> buf = new Vector<>();
		for (Record d : db.getAllrecords())
			if (!buf.contains(d.getJobRotation()))
				buf.add(d.getJobRotation());
		String[] res = new String[buf.size()];
		res = buf.toArray(res);
		return res;
	}

	public Record[] report(int id, String value, boolean isReAssess)
			throws SQLException {
		switch (id) {
		case 1:
			return report1(value, isReAssess);
		case 2:
			return report2(value, isReAssess);
		case 3:
			return report3(value, isReAssess);
		case 4:
			return report4(value, isReAssess);
		case 5:
			return report5(value, isReAssess);
		case 6:
			return report6(value, isReAssess);
		case 7:
			return report7(value, isReAssess);
		case 8:
			return report8(value, isReAssess);
		case 9:
			return report9(value, isReAssess);
		case 10:
			return report10(value, isReAssess);
		case 11:
			return report11(value, isReAssess);
		case 12:
			return report12(value, isReAssess);
		case 13:
			return report13(value, isReAssess);
		case 14:
			return report14(value, isReAssess);
		case 15:
			return report15(value, isReAssess);
		case 16:
			return report16(value, isReAssess);
		case 17:
			return report17(value, isReAssess);
		default:
			return new Record[0];
		}
	}

	private Record[] report17(String value, boolean isReAssess)
			throws SQLException {
		String level;
		if (!isReAssess)
			level = "qECLevel";
		else
			level = "RqECLevel";
		String q = "select " + Database.columns
				+ " from colorsaloon1 where jobRotation like '%" + value
				+ "%' and " + level + "='High' ;";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report16(String value, boolean isReAssess)
			throws SQLException {
		String level;
		if (!isReAssess)
			level = "qECLevel";
		else
			level = "RqECLevel";
		String q = "select " + Database.columns
				+ " from colorsaloon1 where correctiveActionType='' and "
				+ level + "='High' ;";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report15(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "qECLevel";
		else
			level = "RqECLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value
				+ "%' and hasBar =1 ;";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report14(String value, boolean isReAssess)
			throws SQLException { // value "QEC;salonName;"
		StringTokenizer s = new StringTokenizer(value, ";");
		String a1 = s.nextToken();
		String a2 = s.nextToken();
		if (a1.equals("خیلی بالا"))
			a1 = "Very High";
		else if (a1.equals("بالا"))
			a1 = "High";
		else if (a1.equals("متوسط"))
			a1 = "Moderate";
		else if (a1.equals("پایین"))
			a1 = "Low";
		String level;
		if (!isReAssess)
			level = "qECLevel";
		else
			level = "RqECLevel";
		String q;
		q = "select "
				+ Database.columns
				+ " from colorsaloon1 where hasBar=1"
				+ (a1.equals("همه") ? "" : " and " + level + "='" + a1 + "'")
				+ (a2.equals("همه") ? "" : " and salonName='" + a2.trim() + "'")
				+ ";";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report13(String value, boolean isReAssess)
			throws SQLException {
		String q = "select " + Database.columns
				+ " from colorsaloon1 where correctiveActionType<>'';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report12(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "wristLevel";
		else
			level = "RwristLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report11(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "neckLevel";
		else
			level = "RneckLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report10(String value, boolean isReAssess)
			throws SQLException {

		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "shoulderLevel";
		else
			level = "RshoulderLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;

	}

	private Record[] report9(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "backPoint";
		else
			level = "RbackPoint";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report8(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "qECLevel";
		else
			level = "RqECLevel";
		// edited by Saeid
		String q = "select "
				+ Database.columns
				+ " from colorsaloon1 where "
				+ level
				+ "='High'"
				+ (value.equals("همه") ? "" : " and salonName like '%" + value
						+ "%'") + ";";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report6(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "neckLevel";
		else
			level = "RneckLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report7(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "wristLevel";
		else
			level = "RwristLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report5(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "shoulderLevel";
		else
			level = "RshoulderLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report4(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "backLevel";
		else
			level = "RbackLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report3(String value, boolean isReAssess)
			throws SQLException {
		value = value.replace("سالن های", "").trim();
		String level;
		if (!isReAssess)
			level = "qECLevel";
		else
			level = "RqECLevel";
		String q = "select " + Database.columns + " from colorsaloon1 where "
				+ level + "='High' and salonName like '%" + value + "%';";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report2(String value, boolean isReAssess)
			throws SQLException {
		StringTokenizer s = new StringTokenizer(value, ";");
		String a1 = s.nextToken();
		String a2 = s.nextToken();
		String q = "";
		if (a2.equals("خیلی بالا"))
			a2 = "Very High";
		else if (a2.equals("بالا"))
			a2 = "High";
		else if (a2.equals("متوسط"))
			a2 = "Moderate";
		else if (a2.equals("پایین"))
			a2 = "Low";
		// edited by Saeid
		if (!isReAssess)
			q = "select " + Database.columns
					+ " from colorsaloon1 where qECLevel= '" + a2 + "'"
					+ (a1.equals("همه") ? "" : "and salonName='" + a1 + "'")
					+ ";";
		else
			q = "select " + Database.columns
					+ " from colorsaloon1 where RqECLevel= '" + a2 + "'"
					+ (a1.equals("همه") ? "" : "and salonName='" + a1 + "'")
					+ ";";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	private Record[] report1(String value, boolean isReAssess)
			throws SQLException {
		String q = "";
		if (value.equals("خیلی بالا"))
			value = "Very High";
		else if (value.equals("بالا"))
			value = "High";
		else if (value.equals("متوسط"))
			value = "Moderate";
		else if (value.equals("پایین"))
			value = "Low";
		if (!isReAssess)
			q = "select " + Database.columns
					+ " from colorsaloon1 where qECLevel= '" + value + "' ;";
		else
			q = "select " + Database.columns
					+ " from colorsaloon1 where RqECLevel= '" + value + "' ;";
		ResultSet rs = dbm.executeQuery(q);
		Vector<Record> v = new Vector<Record>();
		v = db.resultToRecord(rs);
		Record[] res = new Record[v.size()];
		res = v.toArray(res);
		return res;
	}

	public void update(Record a) {
		db.update(a);
	}

	public void test() {
		Record a = db.getAllrecords()[0];
		System.out.println(a.getTool());
	}

	public int getmaxid() {
		String a = "SELECT MAX(ID) AS maxid FROM COLORSALOON1 ";
		int maxid = 1;
		try {
			ResultSet r = dbm.executeQuery(a);
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
	 * // CMNTD by Saeid public static void main(String[] args) { //Logic l=new
	 * Logic(); // l.loadFromFile(new File("e:\\form.xls"));
	 * 
	 * }
	 */

}
