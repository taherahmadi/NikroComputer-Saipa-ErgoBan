package logic;

import java.io.Serializable;

public class AssessmentResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private String backPoint, backLevel, shoulderPoint, shoulderLevel,
			wristPoint, wristLevel, neckPoint, neckLevel, totalPoint, QECLevel;
	private boolean isReAssessment;

	public AssessmentResult(String backPoint, String backLevel,
			String shoulderPoint, String shoulderLevel, String wristPoint,
			String wristLevel, String neckPoint, String neckLevel,
			String totalPoint, String qECLevel, boolean isReAssessment) {
		if (backPoint == null)
			this.backPoint = "";
		else
			this.backPoint = backPoint;
		if (backLevel == null)
			this.backLevel = "";
		else
			this.backLevel = backLevel;
		if (shoulderPoint == null)
			this.shoulderPoint = "";
		else
			this.shoulderPoint = shoulderPoint;
		if (shoulderLevel == null)
			this.shoulderLevel = "";
		else
			this.shoulderLevel = shoulderLevel;
		if (wristPoint == null)
			this.wristPoint = "";
		else
			this.wristPoint = wristPoint;
		if (wristLevel == null)
			this.wristLevel = "";
		else
			this.wristLevel = wristLevel;
		if (neckPoint == null)
			this.neckPoint = "";
		else
			this.neckPoint = neckPoint;
		if (neckLevel == null)
			this.neckLevel = "";
		else
			this.neckLevel = neckLevel;
		if (totalPoint == null)
			this.totalPoint = "";
		else
			this.totalPoint = totalPoint;
		if (qECLevel == null)
			this.QECLevel = "";
		else
			this.QECLevel = qECLevel;
		this.isReAssessment = isReAssessment;
	}

	public String getNeckPoint() {
		return neckPoint;
	}

	public String getNeckLevel() {
		return neckLevel;
	}

	public void setNeckPoint(String neckPoint) {
		this.neckPoint = neckPoint;
	}

	public void setNeckLevel(String neckLevel) {
		this.neckLevel = neckLevel;
	}

	public boolean equals(AssessmentResult r) {
		boolean res = true;
		if (!totalPoint.equals(r.totalPoint))
			res = false;
		if (!QECLevel.equals(r.QECLevel))
			res = false;
		if (!backPoint.equals(r.backPoint))
			res = false;
		if (!backLevel.equals(r.backLevel))
			res = false;
		if (!shoulderPoint.equals(r.shoulderPoint))
			res = false;
		if (!shoulderLevel.equals(r.shoulderLevel))
			res = false;
		if (!wristPoint.equals(r.wristPoint))
			res = false;
		if (!wristLevel.equals(r.wristLevel))
			res = false;
		if (!neckPoint.equals(r.neckPoint))
			res = false;
		if (!neckLevel.equals(r.neckLevel))
			res = false;
		if (isReAssessment != r.isReAssessment)
			res = false;
		return res;
	}

	public String getTotalPoint() {
		return totalPoint;
	}

	public String getQECLevel() {
		return QECLevel;
	}

	public String getBackPoint() {
		return backPoint;
	}

	public String getBackLevel() {
		return backLevel;
	}

	public String getShoulderPoint() {
		return shoulderPoint;
	}

	public String getShoulderLevel() {
		return shoulderLevel;
	}

	public String getWristPoint() {
		return wristPoint;
	}

	public String getWristLevel() {
		return wristLevel;
	}

	public boolean isReAssessment() {
		return isReAssessment;
	}

	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}

	public void setQECLevel(String qECLevel) {
		QECLevel = qECLevel;
	}

	public void setBackPoint(String backPoint) {
		this.backPoint = backPoint;
	}

	public void setBackLevel(String backLevel) {
		this.backLevel = backLevel;
	}

	public void setShoulderPoint(String shoulderPoint) {
		this.shoulderPoint = shoulderPoint;
	}

	public void setShoulderLevel(String shoulderLevel) {
		this.shoulderLevel = shoulderLevel;
	}

	public void setWristPoint(String wristPoint) {
		this.wristPoint = wristPoint;
	}

	public void setWristLevel(String wristLevel) {
		this.wristLevel = wristLevel;
	}

	public void setReAssessment(boolean isReAssessment) {
		this.isReAssessment = isReAssessment;
	}
}
