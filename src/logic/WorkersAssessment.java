package logic;

import java.io.Serializable;

public class WorkersAssessment implements Serializable {

	private static final long serialVersionUID = 1L;
	private String driving, vibration, workPlace, stress, drivingLevel,
			vibrationLevel, workPlaceLevel, stressLevel;

	public WorkersAssessment(String driving, String drivingLevel,
			String vibration, String vibrationLevel, String workPlace,
			String workPlaceLevel, String stress, String stressLevel) {
		super();
		if (driving == null)
			this.driving = "";
		else
			this.driving = driving;
		if (vibration == null)
			this.vibration = "";
		else
			this.vibration = vibration;
		if (workPlace == null)
			this.workPlace = "";
		else
			this.workPlace = workPlace;
		if (stress == null)
			this.stress = "";
		else
			this.stress = stress;
		if (drivingLevel == null)
			this.drivingLevel = "";
		else
			this.drivingLevel = drivingLevel;
		if (vibrationLevel == null)
			this.vibrationLevel = "";
		else
			this.vibrationLevel = vibrationLevel;
		if (workPlaceLevel == null)
			this.workPlaceLevel = "";
		else
			this.workPlaceLevel = workPlaceLevel;
		if (stressLevel == null)
			this.stressLevel = "";
		else
			this.stressLevel = stressLevel;
	}

	public boolean equals(WorkersAssessment r) {
		boolean res = true;
		if (!driving.equals(r.driving))
			res = false;
		else if (!vibration.equals(r.vibration))
			res = false;
		else if (!workPlace.equals(r.workPlace))
			res = false;
		else if (!workPlaceLevel.equals(r.workPlaceLevel))
			res = false;
		else if (!stress.equals(r.stress))
			res = false;
		else if (!stressLevel.equals(r.stressLevel))
			res = false;
		else if (!vibrationLevel.equals(r.vibrationLevel))
			res = false;
		else if (!drivingLevel.equals(r.drivingLevel))
			res = false;
		return res;
	}

	public String getDriving() {
		return driving;
	}

	public String getVibration() {
		return vibration;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public String getStress() {
		return stress;
	}

	public String getDrivingLevel() {
		return drivingLevel;
	}

	public String getVibrationLevel() {
		return vibrationLevel;
	}

	public String getWorkPlaceLevel() {
		return workPlaceLevel;
	}

	public String getStressLevel() {
		return stressLevel;
	}

	public void setDriving(String driving) {
		this.driving = driving;
	}

	public void setVibration(String vibration) {
		this.vibration = vibration;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public void setStress(String stress) {
		this.stress = stress;
	}

	public void setDrivingLevel(String drivingLevel) {
		this.drivingLevel = drivingLevel;
	}

	public void setVibrationLevel(String vibrationLevel) {
		this.vibrationLevel = vibrationLevel;
	}

	public void setWorkPlaceLevel(String workPlaceLevel) {
		this.workPlaceLevel = workPlaceLevel;
	}

	public void setStressLevel(String stressLevel) {
		this.stressLevel = stressLevel;
	}

}
