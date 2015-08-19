package logic;

import java.io.Serializable;

public class Record implements Serializable {

	private static final long serialVersionUID = -8402797248970824540L;
	private AssessmentResult asses, reasses;
	public static int fieldNum = 66;
	private WorkersAssessment workersAssessment;
	// private float producePerHour;
	private String producePerHour; // Saeid
	private int id, formNumber;
	private String salonName, lineName, stationNumber, ProccesName,
			workingTask, operatorName, tool, filmCode, reviewDate,
			operatorCode, toolWeight, BM, BS, describtion, jobRotation,
			correctiveActionType, correctiveActionNumber, correctiveActionDate,
			reAssessmentDate, assessorName;

	private int A, C, D, E, F, G, H, J, K, L, M, N, P, Q, hasBar;

	public Record(String[] e) {
		try {
			try {
				this.id = Integer.parseInt(e[0]);
			} catch (NumberFormatException | NullPointerException u) {
				this.id = 0;
			}
			try {
				this.formNumber = Integer.parseInt(e[1]);
			} catch (NumberFormatException | NullPointerException u) {
				this.formNumber = 0;
			}
			if (e[1] == null)
				this.salonName = "";
			else
				this.salonName = e[2];
			if (e[2] == null)
				this.lineName = "";
			else
				this.lineName = e[3];
			// START Changed by Saeid
			if (e[4] == null)
				this.stationNumber = "";
			else
				this.stationNumber = e[4];
			// END Changed by Saeid
			if (e[5] == null)
				this.ProccesName = "";
			else
				this.ProccesName = e[5];
			if (e[6] == null)
				this.workingTask = "";
			else
				this.workingTask = e[6];
			if (e[7] == null)
				this.operatorName = "";
			else
				this.operatorName = e[7];
			if (e[8] == null)
				this.filmCode = "";
			else
				this.filmCode = e[8];
			if (e[9] == null)
				this.reviewDate = "";
			else
				this.reviewDate = e[9];
			if (e[10] == null)
				this.operatorCode = "";
			else
				this.operatorCode = e[10];
			if (e[11] == null)
				this.tool = "";
			else
				this.tool = e[11];
			if (e[12] == null)
				this.toolWeight = "";
			else
				this.toolWeight = e[12];
			// START Changed by Saeid
			if (e[13] == null)
				this.producePerHour = "";
			else
				this.producePerHour = e[13];
			// END Changed by Saeid
			try {
				this.A = new Integer(e[14]);
			} catch (NumberFormatException r) {
				this.A = 0;
			}
			if (e[15] == null)
				this.BS = "";
			else
				this.BS = e[15];
			if (e[16] == null)
				this.BM = "";
			else
				this.BM = e[16];
			try {
				this.C = new Integer(e[17]);
			} catch (NumberFormatException r) {
				this.C = 0;
			}
			try {
				this.D = new Integer(e[18]);
			} catch (NumberFormatException r) {
				this.D = 0;
			}
			try {
				this.E = new Integer(e[19]);
			} catch (NumberFormatException r) {
				this.E = 0;
			}
			try {
				this.F = new Integer(e[20]);
			} catch (NumberFormatException r) {
				this.F = 0;
			}
			try {
				this.G = new Integer(e[21]);
			} catch (NumberFormatException r) {
				this.G = 0;
			}
			try {
				this.H = new Integer(e[22]);
			} catch (NumberFormatException r) {
				this.H = 0;
			}
			try {
				this.J = new Integer(e[23]);
			} catch (NumberFormatException r) {
				this.J = 0;
			}
			try {
				this.K = new Integer(e[24]);
			} catch (NumberFormatException r) {
				this.K = 0;
			}
			try {
				this.L = new Integer(e[25]);
			} catch (NumberFormatException r) {
				this.L = 0;
			}
			try {
				this.M = new Integer(e[26]);
			} catch (NumberFormatException r) {
				this.M = 0;
			}
			try {
				this.N = new Integer(e[27]);
			} catch (NumberFormatException r) {
				this.N = 0;
			}
			try {
				this.P = new Integer(e[28]);
			} catch (NumberFormatException r) {
				this.P = 0;
			}
			try {
				this.Q = new Integer(e[29]);
			} catch (NumberFormatException r) {
				this.Q = 0;
			}
			this.asses = new AssessmentResult(e[30], e[31], e[32], e[33],
					e[34], e[35], e[36], e[37], e[46], e[47], false);
			this.workersAssessment = new WorkersAssessment(e[38], e[39], e[40],
					e[41], e[42], e[43], e[44], e[45]);
			if (e[48] == null)
				this.hasBar = 0;
			else
				try {
					this.hasBar = Integer.parseInt(e[48]);
				} catch (NumberFormatException r) {
					this.hasBar = 0;
				}
			if (e[49] == null)
				this.describtion = "";
			else
				this.describtion = e[49];
			if (e[50] == null)
				this.jobRotation = "";
			else
				this.jobRotation = e[50];
			if (e[51] == null)
				this.correctiveActionType = "";
			else
				this.correctiveActionType = e[51];
			if (e[52] == null)
				this.correctiveActionNumber = "";
			else
				this.correctiveActionNumber = e[52];
			if (e[53] == null)
				this.correctiveActionDate = "";
			else
				this.correctiveActionDate = e[53];
			this.reasses = new AssessmentResult(e[54], e[55], e[56], e[57],
					e[58], e[59], e[60], e[61], e[62], e[63], true);
			if (e[64] == null)
				this.reAssessmentDate = "";
			else
				this.reAssessmentDate = e[64];
			if (e[65] == null)
				this.assessorName = "";
			else
				this.assessorName = e[65];

		} catch (ArrayIndexOutOfBoundsException | NumberFormatException t) {
		}

	}

	public boolean equals(Record r) {
		boolean res = true;
		if (!asses.equals(r.getAsses())) {
			res = false;
		} else if (!reasses.equals(r.getReasses())) {
			res = false;
		} else if (!workersAssessment.equals(r.getWorkersAssessment())) {
			res = false;
		} else if (this.id != r.getId()) {
			res = false;
		} else if (!(formNumber == r.getFormNumber())) {
			res = false;
		} else if (!(stationNumber.equals(r.getStationNumber()))) {
			res = false;
		} else if (!(producePerHour.equals(r.getProducePerHour()))) {
			res = false;
		} else if (!(correctiveActionNumber.equals(r
				.getCorrectiveActionNumber()))) {
			res = false;
		} else if (!lineName.equals(r.lineName)) {
			res = false;
		} else if (!ProccesName.equals(r.ProccesName)) {
			res = false;
		} else if (!workingTask.equals(r.workingTask)) {
			res = false;
		} else if (!operatorName.equals(r.operatorName)) {
			res = false;
		} else if (!filmCode.equals(r.filmCode)) {
			res = false;
		} else if (!reviewDate.equals(r.reviewDate)) {
			res = false;
		} else if (!operatorCode.equals(r.operatorCode)) {
			res = false;
		} else if (!tool.equals(r.tool)) {
			res = false;
		} else if (!toolWeight.equals(r.toolWeight)) {
			res = false;
		} else if (!describtion.equals(r.describtion)) {
			res = false;
		} else if (!jobRotation.equals(r.jobRotation)) {
			res = false;
		} else if (!correctiveActionType.equals(r.correctiveActionType)) {
			res = false;
		} else if (!correctiveActionDate.equals(r.correctiveActionDate)) {
			res = false;
		} else if (!reAssessmentDate.equals(r.reAssessmentDate)) {
			res = false;
		} else if (!assessorName.equals(r.assessorName)) {
			res = false;
		} else if (!(hasBar == r.hasBar)) {
			res = false;
		}

		return res;
	}

	// @Override
	// public String toString() {
	// String res = new String();
	// res = String.format("%s\t%s\t%s\t%s", lineName, ProccesName,
	// workingTask, toolWeight);
	// return res;
	// }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public int getHasBar() {
		return hasBar;
	}

	public void setHasBar(int hasBar) {
		this.hasBar = hasBar;
	}

	public int getA() {
		return A;
	}

	public String getBM() {
		return BM;
	}

	public String getBS() {
		return BS;
	}

	public int getC() {
		return C;
	}

	public int getD() {
		return D;
	}

	public int getE() {
		return E;
	}

	public int getF() {
		return F;
	}

	public int getG() {
		return G;
	}

	public int getH() {
		return H;
	}

	public int getJ() {
		return J;
	}

	public int getK() {
		return K;
	}

	public int getL() {
		return L;
	}

	public int getM() {
		return M;
	}

	public int getN() {
		return N;
	}

	public int getP() {
		return P;
	}

	public int getQ() {
		return Q;
	}

	public void setA(int a) {
		A = a;
	}

	public void setBM(String bM) {
		BM = bM;
	}

	public void setBS(String bS) {
		BS = bS;
	}

	public void setC(int c) {
		C = c;
	}

	public void setD(int d) {
		D = d;
	}

	public void setE(int e) {
		E = e;
	}

	public void setF(int f) {
		F = f;
	}

	public void setG(int g) {
		G = g;
	}

	public void setH(int h) {
		H = h;
	}

	public void setJ(int j) {
		J = j;
	}

	public void setK(int k) {
		K = k;
	}

	public void setL(int l) {
		L = l;
	}

	public void setM(int m) {
		M = m;
	}

	public void setN(int n) {
		N = n;
	}

	public void setP(int p) {
		P = p;
	}

	public void setQ(int q) {
		Q = q;
	}

	public AssessmentResult getAsses() {
		return asses;
	}

	public AssessmentResult getReasses() {
		return reasses;
	}

	public WorkersAssessment getWorkersAssessment() {
		return workersAssessment;
	}

	public void setAsses(AssessmentResult asses) {
		this.asses = asses;
	}

	public void setReasses(AssessmentResult reasses) {
		this.reasses = reasses;
	}

	public void setWorkersAssessment(WorkersAssessment workersAssessment) {
		this.workersAssessment = workersAssessment;
	}

	public String getReAssessmentDate() {
		return reAssessmentDate;
	}

	public String getAssessorName() {
		return assessorName;
	}

	public void setReAssessmentDate(String reAssessmentDate) {
		this.reAssessmentDate = reAssessmentDate;
	}

	public void setAssessorName(String assessorName) {
		this.assessorName = assessorName;
	}

	public int getFormNumber() {
		return formNumber;
	}

	public String getStationNumber() {
		return stationNumber;
	}

	public String getProducePerHour() {
		return producePerHour;
	}

	public String getCorrectiveActionNumber() {
		return correctiveActionNumber;
	}

	public String getLineName() {
		return lineName;
	}

	public String getProccesName() {
		return ProccesName;
	}

	public String getWorkingTask() {
		return workingTask;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public String getFilmCode() {
		return filmCode;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public String getToolWeight() {
		return toolWeight;
	}

	public String getDescribtion() {
		return describtion;
	}

	public String getJobRotation() {
		return jobRotation;
	}

	public String getCorrectiveActionType() {
		return correctiveActionType;
	}

	public String getCorrectiveActionDate() {
		return correctiveActionDate;
	}

	public void setFormNumber(int formNumber) {
		this.formNumber = formNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public void setProducePerHour(String producePerHour) {
		this.producePerHour = producePerHour;
	}

	public void setCorrectiveActionNumber(String correctiveActionNumber) {
		this.correctiveActionNumber = correctiveActionNumber;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public void setProccesName(String proccesName) {
		ProccesName = proccesName;
	}

	public void setWorkingTask(String workingTask) {
		this.workingTask = workingTask;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public void setFilmCode(String filmCode) {
		this.filmCode = filmCode;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public void setJobRotation(String jobRotation) {
		this.jobRotation = jobRotation;
	}

	public void setCorrectiveActionType(String correctiveActionType) {
		this.correctiveActionType = correctiveActionType;
	}

	public void setCorrectiveActionDate(String correctiveActionDate) {
		this.correctiveActionDate = correctiveActionDate;
	}

	public String getSalonName() {
		return salonName;
	}

	// public void setProducePerHour(String producePerHour) { duplicate method,
	// cmnted by Saeid
	// this.producePerHour = producePerHour;
	// }

	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}

	public void setToolWeight(String toolWeight) {
		this.toolWeight = toolWeight;
	}

	public String toString() {
		String res = "";
		res += ("id   :" + this.id);
		res += "               ";
		res += ("tool :" + this.tool);
		res += "\r\n";
		return res;
	}

	public String[] toStringArray() {
		String[] e = new String[66];
		try {
			// res[0] = this.formNumber + "";
			// res[1] = this.salonName;
			// res[2] = this.lineName;
			// res[3] = "" + this.stationNumber;
			// res[4] = this.ProccesName;
			// res[5] = this.workingTask;
			// res[6] = this.operatorName;
			// res[7] = this.filmCode;
			// res[8] = this.reviewDate;
			// res[9] = this.operatorCode;
			// res[10] = this.toolWeight;
			// res[11] = "" + this.producePerHour;
			// res[12] = this.asses.getTotalPoint();
			// res[13] = this.asses.getQECLevel();
			// res[14] = this.asses.getBackPoint();
			// res[15] = this.asses.getBackLevel();
			// res[16] = this.asses.getShoulderPoint();
			// res[17] = this.asses.getShoulderLevel();
			// res[18] = this.asses.getWristPoint();
			// res[19] = this.asses.getWristLevel();
			// res[20] = this.asses.getNeckPoint();
			// res[21] = this.asses.getNeckLevel();
			// res[22] = this.workersAssessment.getDriving();
			// res[23] = this.workersAssessment.getDrivingLevel();
			// res[24] = this.workersAssessment.getVibration();
			// res[25] = this.workersAssessment.getVibrationLevel();
			// res[26] = this.workersAssessment.getWorkPlace();
			// res[27] = this.workersAssessment.getWorkPlaceLevel();
			// res[28] = this.workersAssessment.getStress();
			// res[29] = this.workersAssessment.getStressLevel();
			// res[30] = this.describtion;
			// res[31] = this.jobRotation;
			// res[32] = this.correctiveActionType;
			// res[33] = "" + this.correctiveActionNumber;
			// res[34] = this.correctiveActionDate;
			// res[35] = this.reasses.getTotalPoint();
			// res[36] = this.reasses.getQECLevel();
			// res[37] = this.reasses.getBackPoint();
			// res[38] = this.reasses.getBackLevel();
			// res[39] = this.reasses.getShoulderPoint();
			// res[40] = this.reasses.getShoulderLevel();
			// res[41] = this.reasses.getWristPoint();
			// res[42] = this.reasses.getWristLevel();
			// res[43] = this.reasses.getNeckPoint();
			// res[44] = this.reasses.getNeckLevel();
			// res[45] = this.reAssessmentDate;
			// res[46] = this.assessorName;
			// res[47] = A+"";
			// res[48] = BM+"";
			// res[49] = BS+"";
			// res[50] = C+"";
			// res[51] = D+"";
			// res[52] = E+"";
			// res[53] = F+"";
			// res[54] = G+"";
			// res[55] = H+"";
			// res[56] = J+"";
			// res[57] = K+"";
			// res[58] = L+"";
			// res[59] = M+"";
			// res[60] = N+"";
			// res[61] = P+"";
			// res[62] = Q+"";
			e[0] = this.id + "";
			e[1] = this.formNumber + "";
			e[2] = this.salonName;
			e[3] = this.lineName;
			e[4] = this.stationNumber + "";
			e[5] = this.ProccesName;
			e[6] = this.workingTask;
			e[7] = this.operatorName;
			e[8] = this.filmCode;
			e[9] = this.reviewDate;
			e[10] = this.operatorCode;
			e[11] = this.tool;
			e[12] = this.toolWeight;
			e[13] = this.producePerHour;
			e[14] = this.A + "";
			e[15] = this.BS + "";
			e[16] = this.BM + "";
			e[17] = this.C + "";
			e[18] = this.D + "";
			e[19] = this.E + "";
			e[20] = this.F + "";
			e[21] = this.G + "";
			e[22] = this.H + "";
			e[23] = this.J + "";
			e[24] = this.K + "";
			e[25] = this.L + "";
			e[26] = this.M + "";
			e[27] = this.N + "";
			e[28] = this.P + "";
			e[29] = this.Q + "";
			e[30] = asses.getBackPoint();
			e[31] = asses.getBackLevel();
			e[32] = asses.getShoulderPoint();
			e[33] = asses.getShoulderLevel();
			e[34] = asses.getWristPoint();
			e[35] = asses.getWristLevel();
			e[36] = asses.getNeckPoint();
			e[37] = asses.getNeckLevel();
			e[38] = workersAssessment.getDriving();
			e[39] = workersAssessment.getDrivingLevel();
			e[40] = workersAssessment.getVibration();
			e[41] = workersAssessment.getVibrationLevel();
			e[42] = workersAssessment.getWorkPlace();
			e[43] = workersAssessment.getWorkPlaceLevel();
			e[44] = workersAssessment.getStress();
			e[45] = workersAssessment.getStressLevel();
			e[46] = asses.getTotalPoint();
			e[47] = asses.getQECLevel();
			e[48] = this.hasBar + "";
			e[49] = this.describtion;
			e[50] = this.jobRotation;
			e[51] = this.correctiveActionType;
			e[52] = this.correctiveActionNumber;
			e[53] = this.correctiveActionDate;
			e[54] = reasses.getBackPoint();
			e[55] = reasses.getBackLevel();
			e[56] = reasses.getShoulderPoint();
			e[57] = reasses.getShoulderLevel();
			e[58] = reasses.getWristPoint();
			e[59] = reasses.getWristLevel();
			e[60] = reasses.getNeckPoint();
			e[61] = reasses.getNeckLevel();
			e[62] = reasses.getTotalPoint();
			e[63] = reasses.getQECLevel();
			e[64] = this.reAssessmentDate;
			e[65] = this.assessorName;

		} catch (ArrayIndexOutOfBoundsException t) {
		}
		return e;
	}

}
