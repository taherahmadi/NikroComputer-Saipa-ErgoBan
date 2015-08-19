package graphicUI.chart;

import java.sql.SQLException;

import logic.Logic;
import logic.Record;

public class DataMaker {

	public static Logic logic;
	public static int LOW = 0;
	public static int MODERATE = 1;
	public static int HIGH = 2;
	public static int VERY_HIGH = 3;

	public static void setLogic(Logic logic) {
		DataMaker.logic = logic;
	}

	public static int[][] qecLevelValues(boolean reassessment) {
		Record[] records = logic.report();
		int[][] res = new int[4][1];
		res[VERY_HIGH][0] = 0;
		res[HIGH][0] = 0;
		res[MODERATE][0] = 0;
		res[LOW][0] = 0;
		for (Record r : records)
			if (reassessment) {
				if ("Very High".equals(r.getReasses().getQECLevel()))
					res[VERY_HIGH][0]++;
				else if ("High".equals(r.getReasses().getQECLevel()))
					res[HIGH][0]++;
				else if ("Moderate".equals(r.getReasses().getQECLevel()))
					res[MODERATE][0]++;
				else if ("Low".equals(r.getReasses().getQECLevel()))
					res[LOW][0]++;
			} else {
				if ("Very High".equals(r.getAsses().getQECLevel()))
					res[VERY_HIGH][0]++;
				else if ("High".equals(r.getAsses().getQECLevel()))
					res[HIGH][0]++;
				else if ("Moderate".equals(r.getAsses().getQECLevel()))
					res[MODERATE][0]++;
				else if ("Low".equals(r.getAsses().getQECLevel()))
					res[LOW][0]++;
			}
		return res;
	}

	public static int[][] qecLevelValuesBySalons(String[] salonNames,
			boolean reassesment) {
		int[][] res = new int[4][salonNames.length];
		Record[] records = logic.report();
		int index = 0;
		for (int i = 0; i < salonNames.length; i++)
			for (int j = 0; j < 3; j++)
				res[j][i] = 0;
		for (int i = 0; i < records.length; i++)
			if (reassesment) {
				index = findIndex(salonNames, records[i].getSalonName());
				if ("Very High".equals(records[i].getReasses().getQECLevel()))
					res[VERY_HIGH][index]++;
				else if ("High".equals(records[i].getReasses().getQECLevel()))
					res[HIGH][index]++;
				else if ("Moderate".equals(records[i].getReasses()
						.getQECLevel()))
					res[MODERATE][index]++;
				else if ("Low".equals(records[i].getReasses().getQECLevel()))
					res[LOW][index]++;
			} else {
				index = findIndex(salonNames, records[i].getSalonName());
				if ("Very High".equals(records[i].getAsses().getQECLevel()))
					res[VERY_HIGH][index]++;
				else if ("High".equals(records[i].getAsses().getQECLevel()))
					res[HIGH][index]++;
				else if ("Moderate".equals(records[i].getAsses().getQECLevel()))
					res[MODERATE][index]++;
				else if ("Low".equals(records[i].getAsses().getQECLevel()))
					res[LOW][index]++;
			}
		return res;
	}

	private static int findIndex(String[] array, String target) {
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(target))
				return i;
		return -1;
	}

	public static int[][] backLevelValuesBySalons(String[] salonNames,
			boolean reassesment) {
		int[][] res = new int[1][salonNames.length];
		Record[] records = logic.report();
		int index = 0;
		for (int i = 0; i < salonNames.length; i++)
			res[0][i] = 0;
		for (int i = 0; i < records.length; i++)
			if (reassesment) {
				index = findIndex(salonNames, records[i].getSalonName());
				if ("High".equals(records[i].getReasses().getBackLevel()))
					res[0][index]++;
			} else {
				index = findIndex(salonNames, records[i].getSalonName());
				if ("High".equals(records[i].getAsses().getBackLevel()))
					res[0][index]++;
			}
		return res;
	}

	public static int[][] shoulderLevelValuesBySalons(String[] salonNames,
			boolean reassesment) {
		// int[][] res = new int[4][salonNames.length];
		int[][] res = new int[1][salonNames.length];
		Record[] records = logic.report();
		int index = 0;
		// for (int i = 0; i < salonNames.length; i++)
		// for (int j = 0; j < 3; j++)
		// res[j][i] = 0;
		for (int i = 0; i < salonNames.length; i++)
			res[0][i] = 0;
		for (int i = 0; i < records.length; i++)
			if (reassesment) {
				index = findIndex(salonNames, records[i].getSalonName());
				// if ("Very High".equals(records[i].getReasses()
				// .getShoulderLevel()))
				// res[VERY_HIGH][index]++;
				// else if ("High".equals(records[i].getReasses()
				// .getShoulderLevel()))
				// res[HIGH][index]++;
				// else if ("Moderate".equals(records[i].getReasses()
				// .getShoulderLevel()))
				// res[MODERATE][index]++;
				// else if ("Low".equals(records[i].getReasses()
				// .getShoulderLevel()))
				// res[LOW][index]++;
				if ("High".equals(records[i].getReasses().getShoulderLevel()))
					res[0][index]++;
			} else {
				index = findIndex(salonNames, records[i].getSalonName());
				// if ("Very High"
				// .equals(records[i].getAsses().getShoulderLevel()))
				// res[VERY_HIGH][index]++;
				// else if ("High"
				// .equals(records[i].getAsses().getShoulderLevel()))
				// res[HIGH][index]++;
				// else if ("Moderate".equals(records[i].getAsses()
				// .getShoulderLevel()))
				// res[MODERATE][index]++;
				// else if
				// ("Low".equals(records[i].getAsses().getShoulderLevel()))
				// res[LOW][index]++;
				if ("High".equals(records[i].getAsses().getShoulderLevel()))
					res[0][index]++;
			}
		return res;
	}

	public static int[][] neckLevelValuesBySalons(String[] salonNames,
			boolean reassesment) {
		// int[][] res = new int[4][salonNames.length];
		int[][] res = new int[1][salonNames.length];
		Record[] records = logic.report();
		int index = 0;
		// for (int i = 0; i < salonNames.length; i++)
		// for (int j = 0; j < 3; j++)
		// res[j][i] = 0;
		for (int i = 0; i < salonNames.length; i++)
			res[0][i] = 0;
		for (int i = 0; i < records.length; i++)
			if (reassesment) {
				index = findIndex(salonNames, records[i].getSalonName());
				// if
				// ("Very High".equals(records[i].getReasses().getNeckLevel()))
				// res[VERY_HIGH][index]++;
				// else if
				// ("High".equals(records[i].getReasses().getNeckLevel()))
				// res[HIGH][index]++;
				// else if ("Moderate".equals(records[i].getReasses()
				// .getNeckLevel()))
				// res[MODERATE][index]++;
				// else if
				// ("Low".equals(records[i].getReasses().getNeckLevel()))
				// res[LOW][index]++;
				if ("High".equals(records[i].getReasses().getNeckLevel()))
					res[0][index]++;
			} else {
				index = findIndex(salonNames, records[i].getSalonName());
				// if ("Very High".equals(records[i].getAsses().getNeckLevel()))
				// res[VERY_HIGH][index]++;
				// else if ("High".equals(records[i].getAsses().getNeckLevel()))
				// res[HIGH][index]++;
				// else if ("Moderate"
				// .equals(records[i].getAsses().getNeckLevel()))
				// res[MODERATE][index]++;
				// else if ("Low".equals(records[i].getAsses().getNeckLevel()))
				// res[LOW][index]++;
				if ("High".equals(records[i].getAsses().getNeckLevel()))
					res[0][index]++;
			}
		return res;
	}

	public static int[][] wristLevelValuesBySalons(String[] salonNames,
			boolean reassesment) {
		// int[][] res = new int[4][salonNames.length];
		int[][] res = new int[1][salonNames.length];
		Record[] records = logic.report();
		int index = 0;
		// for (int i = 0; i < salonNames.length; i++)
		// for (int j = 0; j < 3; j++)
		// res[j][i] = 0;
		for (int i = 0; i < salonNames.length; i++)
			res[0][i] = 0;
		for (int i = 0; i < records.length; i++)
			if (reassesment) {
				index = findIndex(salonNames, records[i].getSalonName());
				// if
				// ("Very High".equals(records[i].getReasses().getWristLevel()))
				// res[VERY_HIGH][index]++;
				// else if
				// ("High".equals(records[i].getReasses().getWristLevel()))
				// res[HIGH][index]++;
				// else if ("Moderate".equals(records[i].getReasses()
				// .getWristLevel()))
				// res[MODERATE][index]++;
				// else if
				// ("Low".equals(records[i].getReasses().getWristLevel()))
				// res[LOW][index]++;
				if ("High".equals(records[i].getReasses().getWristLevel()))
					res[0][index]++;
			} else {
				index = findIndex(salonNames, records[i].getSalonName());
				// if
				// ("Very High".equals(records[i].getAsses().getWristLevel()))
				// res[VERY_HIGH][index]++;
				// else if
				// ("High".equals(records[i].getAsses().getWristLevel()))
				// res[HIGH][index]++;
				// else if ("Moderate".equals(records[i].getAsses()
				// .getWristLevel()))
				// res[MODERATE][index]++;
				// else if ("Low".equals(records[i].getAsses().getWristLevel()))
				// res[LOW][index]++;
				if ("High".equals(records[i].getAsses().getWristLevel()))
					res[0][index]++;
			}
		return res;
	}

	public static int[][] qecLevelValuesByKindOfSalons(String[] salonNames,
			boolean reassesment) {
		int[][] res = new int[1][salonNames.length];
		for (int i = 0; i < salonNames.length; i++)
			try {
				res[0][i] = logic.report(3, salonNames[i], reassesment).length;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return res;
	}

	public static int[][] numberOfWorksWithHasBar(String[] salonNames,
			boolean reassesment) {
		int[][] res = new int[1][salonNames.length];
		for (int i = 0; i < salonNames.length; i++)
			try {
				res[0][i] = logic.report(14,
						"همه" + ";" + salonNames[i] + " ;", reassesment).length;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return res;
	}

	public static int[][] numberOfHighQECWorksWithHasBarByKindOfSalons(
			String[] salonNames, boolean reassesment) {
		int[][] res = new int[1][salonNames.length];
		for (int i = 0; i < salonNames.length; i++)
			try {
				res[0][i] = logic.report(15, salonNames[i], reassesment).length;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return res;
	}

	public static int[][] numberOfHighQECWorksByJobRotation(boolean reassesment) {
		String[] jobRotations = logic.getJobRotations();
		int[][] res = new int[1][jobRotations.length];
		try {
			for (int i = 0; i < jobRotations.length; i++)
				res[0][i] = logic.report(17, jobRotations[i], reassesment).length;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
