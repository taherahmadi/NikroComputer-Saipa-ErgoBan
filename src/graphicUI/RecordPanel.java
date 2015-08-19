package graphicUI;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Record;

public class RecordPanel extends JPanel {

	// Components ID: START <<DATA CHANGE | COMPONENT CHANGE>>
	private static final int FORM_NUMBER = 0; // Number Field: 0
	private static final int SALON_NAME = 1; // Text Field: 0
	private static final int LINE_NAME = 2; // Text Field: 1
	private static final int STATION_NUMBER = 3; // Text Field: 2
	private static final int PROCESS_NAME = 4; // Text Field: 3
	private static final int WORKING_TASK = 5; // Text Field: 4
	private static final int OPERATOR_NAME = 6; // Text Field: 5
	private static final int FILM_CODE = 7; // Text Field: 6
	private static final int REVIEW_DATE = 8; // Text Field: 7
	private static final int OPERATOR_CODE = 9; // Text Field: 8
	private static final int TOOL = 10; // Text Field: 9
	private static final int TOOL_WEIGHT = 11; // Text Field: 10
	private static final int PRODUCE_PER_HOUR = 12; // Text Field: 11
	private static final int ASS_HAS_LOAD = 13; // Combo Box: 0
	private static final int ASS_A = 14; // Combo Box: 1
	private static final int ASS_BS = 15; // Combo Box: 2
	private static final int ASS_BM = 16; // Combo Box: 3
	private static final int ASS_C = 17; // Combo Box: 4
	private static final int ASS_D = 18; // Combo Box: 5
	private static final int ASS_E = 19; // Combo Box: 6
	private static final int ASS_F = 20; // Combo Box: 7
	private static final int ASS_G = 21; // Combo Box: 8
	private static final int ASS_H = 22; // Combo Box: 9
	private static final int ASS_J = 23; // Combo Box: 10
	private static final int ASS_K = 24; // Combo Box: 11
	private static final int ASS_L = 25; // Combo Box: 12
	private static final int ASS_M = 26; // Combo Box: 13
	private static final int ASS_N = 27; // Combo Box: 14
	private static final int ASS_P = 28; // Combo Box: 15
	private static final int ASS_Q = 29; // Combo Box: 16
	private static final int ASS_BACK_POINT = 30; // Number Field: 1
	private static final int ASS_BACK_LEVEL = 31; // Text Field: 12
	private static final int ASS_SHOULDER_POINT = 32; // Number Field: 2
	private static final int ASS_SHOULDER_LEVEL = 33; // Text Field: 13
	private static final int ASS_WRIST_POINT = 34; // Number Field: 3
	private static final int ASS_WRIST_LEVEL = 35; // Text Field: 14
	private static final int ASS_NECK_POINT = 36; // Number Field: 4
	private static final int ASS_NECK_LEVEL = 37; // Text Field: 15
	private static final int DRIVING_POINT = 38; // Number Field: 5
	private static final int DRIVING_LEVEL = 39; // Combo Box: 17
	private static final int VIBRATION_POINT = 40; // Number Field: 6
	private static final int VIBRATION_LEVEL = 41; // Combo Box: 18
	private static final int WORK_PACE_POINT = 42; // Number Field: 7
	private static final int WORK_PACE_LEVEL = 43; // Combo Box: 19
	private static final int STRESS_POINT = 44; // Number Field: 8
	private static final int STRESS_LEVEL = 45; // Combo Box: 20
	private static final int ASS_TOTAL_POINT = 46; // Number Field: 9
	private static final int ASS_QEC_LEVEL = 47; // Text Field: 16
	private static final int DESCRIPTION = 48; // Text Field: 17
	private static final int JOB_ROTATION = 49; // Text Field: 18
	private static final int CORRECTIVE_ACTION_TYPE = 50; // Text Field: 19
	private static final int CORRECTIVE_ACTION_NUMBER = 51; // Text Field: 20
	private static final int CORRECTIVE_ACTION_DATE = 52; // Text Field: 21
	private static final int REASS_HAS_LOAD = 53; // Combo Box: 21
	private static final int REASS_A = 54; // Combo Box: 22
	private static final int REASS_BS = 55; // Combo Box: 23
	private static final int REASS_BM = 56; // Combo Box: 24
	private static final int REASS_C = 57; // Combo Box: 25
	private static final int REASS_D = 58; // Combo Box: 26
	private static final int REASS_E = 59; // Combo Box: 27
	private static final int REASS_F = 60; // Combo Box: 28
	private static final int REASS_G = 61; // Combo Box: 29
	private static final int REASS_H = 62; // Combo Box: 30
	private static final int REASS_J = 63; // Combo Box: 31
	private static final int REASS_K = 64; // Combo Box: 32
	private static final int REASS_L = 65; // Combo Box: 33
	private static final int REASS_M = 66; // Combo Box: 34
	private static final int REASS_N = 67; // Combo Box: 35
	private static final int REASS_P = 68; // Combo Box: 36
	private static final int REASS_Q = 69;// Combo Box: 37
	private static final int REASS_BACK_POINT = 70; // Number Field: 10
	private static final int REASS_BACK_LEVEL = 71; // Text Field: 22
	private static final int REASS_SHOULDER_POINT = 72; // Number Field: 11
	private static final int REASS_SHOULDER_LEVEL = 73; // Text Field: 23
	private static final int REASS_WRIST_POINT = 74; // Number Field: 12
	private static final int REASS_WRIST_LEVEL = 75; // Text Field: 24
	private static final int REASS_NECK_POINT = 76; // Number Field: 13
	private static final int REASS_NECK_LEVEL = 77; // Text Field: 25
	private static final int REASS_TOTAL_POINT = 78; // Number Field: 14
	private static final int REASS_QEC_LEVEL = 79; // Text Field: 26
	private static final int REASSESSMENT_DATE = 80; // Text Field: 27
	private static final int ASSESSOR_NAME = 81; // Text Field: 28
	// Components ID: END

	private static final long serialVersionUID = -6778946724457005905L;
	private int numberOfNumberFields;
	private int numberOfComponents;
	private int numberOfFields;
	private int numberOfCombos;
	private BaseManager manager;
	private Font font;
	private JFormattedTextField[] formattedFields;
	private Vector<JComboBox<String>> combos;
	private JTextField[] fields;
	private JLabel[] labels;
	private String[] names;
	private String[][] combosTexts;
	private boolean[] languages;

	// <<DATA CHANGE | COMPONENT CHANGE>>
	public RecordPanel(BaseManager manager, Font font) {
		this.manager = manager;
		this.font = font;
		numberOfCombos = 38;
		numberOfFields = 29;
		numberOfNumberFields = 15;
		numberOfComponents = numberOfFields + numberOfCombos
				+ numberOfNumberFields;
		makeCombosTexts();
		makeArray();
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private void makeCombosTexts() {
		combosTexts = new String[numberOfCombos][];
		// ASS_HAS_LOAD: START
		combosTexts[0] = new String[2];
		combosTexts[0][0] = "ندارد";
		combosTexts[0][1] = "دارد";
		// ASS_HAS_LOAD: END
		// ASS_A: START
		combosTexts[1] = new String[3];
		combosTexts[1][0] = "1";
		combosTexts[1][1] = "2";
		combosTexts[1][2] = "3";
		// ASS_A: END
		// ASS_Bs: START
		combosTexts[2] = new String[2];
		combosTexts[2][0] = "N";
		combosTexts[2][1] = "Y";
		// ASS_Bs: END
		// ASS_Bm: START
		combosTexts[3] = new String[3];
		combosTexts[3][0] = "1";
		combosTexts[3][1] = "2";
		combosTexts[3][2] = "3";
		// ASS_Bm: END
		// ASS_C: START
		combosTexts[4] = new String[3];
		combosTexts[4][0] = "1";
		combosTexts[4][1] = "2";
		combosTexts[4][2] = "3";
		// ASS_C: END
		// ASS_D: START
		combosTexts[5] = new String[3];
		combosTexts[5][0] = "1";
		combosTexts[5][1] = "2";
		combosTexts[5][2] = "3";
		// ASS_D: END
		// ASS_E: START
		combosTexts[6] = new String[2];
		combosTexts[6][0] = "1";
		combosTexts[6][1] = "2";
		// ASS_E: END
		// ASS_F: START
		combosTexts[7] = new String[3];
		combosTexts[7][0] = "1";
		combosTexts[7][1] = "2";
		combosTexts[7][2] = "3";
		// ASS_F: END
		// ASS_G: START
		combosTexts[8] = new String[3];
		combosTexts[8][0] = "1";
		combosTexts[8][1] = "2";
		combosTexts[8][2] = "3";
		// ASS_G: END
		// ASS_H: START
		combosTexts[9] = new String[4];
		combosTexts[9][0] = "1";
		combosTexts[9][1] = "2";
		combosTexts[9][2] = "3";
		combosTexts[9][3] = "4";
		// ASS_H: END
		// ASS_J: START
		combosTexts[10] = new String[3];
		combosTexts[10][0] = "1";
		combosTexts[10][1] = "2";
		combosTexts[10][2] = "3";
		// ASS_J: END
		// ASS_K: START
		combosTexts[11] = new String[3];
		combosTexts[11][0] = "1";
		combosTexts[11][1] = "2";
		combosTexts[11][2] = "3";
		// ASS_K: END
		// ASS_L: START
		combosTexts[12] = new String[2];
		combosTexts[12][0] = "1";
		combosTexts[12][1] = "2";
		// ASS_L: END
		// ASS_M: START
		combosTexts[13] = new String[3];
		combosTexts[13][0] = "1";
		combosTexts[13][1] = "2";
		combosTexts[13][2] = "3";
		// ASS_M: END
		// ASS_N: START
		combosTexts[14] = new String[3];
		combosTexts[14][0] = "1";
		combosTexts[14][1] = "2";
		combosTexts[14][2] = "3";
		// ASS_N: END
		// ASS_P: START
		combosTexts[15] = new String[3];
		combosTexts[15][0] = "1";
		combosTexts[15][1] = "2";
		combosTexts[15][2] = "3";
		// ASS_P: END
		// ASS_Q: START
		combosTexts[16] = new String[4];
		combosTexts[16][0] = "1";
		combosTexts[16][1] = "2";
		combosTexts[16][2] = "3";
		combosTexts[16][3] = "4";
		// ASS_Q: END
		// DRIVING_LEVEL: START
		combosTexts[17] = new String[3];
		combosTexts[17][0] = "Low";
		combosTexts[17][1] = "Moderate";
		combosTexts[17][2] = "High";
		// DRIVING_LEVEL: END
		// VIBRATION_LEVEL: START
		combosTexts[18] = new String[3];
		combosTexts[18][0] = "Low";
		combosTexts[18][1] = "Moderate";
		combosTexts[18][2] = "High";
		// VIBRATION_LEVEL: END
		// WORK_PACE_LEVEL: START
		combosTexts[19] = new String[3];
		combosTexts[19][0] = "Low";
		combosTexts[19][1] = "Moderate";
		combosTexts[19][2] = "High";
		// WORK_PACE_LEVEL: END
		// STRESS_LEVEL: START
		combosTexts[20] = new String[4];
		combosTexts[20][0] = "Low";
		combosTexts[20][1] = "Moderate";
		combosTexts[20][2] = "High";
		combosTexts[20][3] = "Very High";
		// STRESS_LEVEL: END
		// REASS_HAS_LOAD: START
		combosTexts[21] = new String[2];
		combosTexts[21][0] = "ندارد";
		combosTexts[21][1] = "دارد";
		// REASS_HAS_LOAD: END
		// REASS_A: START
		combosTexts[22] = new String[3];
		combosTexts[22][0] = "1";
		combosTexts[22][1] = "2";
		combosTexts[22][2] = "3";
		// REASS_A: END
		// REASS_Bs: START
		combosTexts[23] = new String[2];
		combosTexts[23][0] = "N";
		combosTexts[23][1] = "Y";
		// REASS_Bs: END
		// REASS_Bm: START
		combosTexts[24] = new String[3];
		combosTexts[24][0] = "1";
		combosTexts[24][1] = "2";
		combosTexts[24][2] = "3";
		// REASS_Bm: END
		// REASS_C: START
		combosTexts[25] = new String[3];
		combosTexts[25][0] = "1";
		combosTexts[25][1] = "2";
		combosTexts[25][2] = "3";
		// REASS_C: END
		// REASS_D: START
		combosTexts[26] = new String[3];
		combosTexts[26][0] = "1";
		combosTexts[26][1] = "2";
		combosTexts[26][2] = "3";
		// REASS_D: END
		// REASS_E: START
		combosTexts[27] = new String[2];
		combosTexts[27][0] = "1";
		combosTexts[27][1] = "2";
		// REASS_E: END
		// REASS_F: START
		combosTexts[28] = new String[3];
		combosTexts[28][0] = "1";
		combosTexts[28][1] = "2";
		combosTexts[28][2] = "3";
		// REASS_F: END
		// REASS_G: START
		combosTexts[29] = new String[3];
		combosTexts[29][0] = "1";
		combosTexts[29][1] = "2";
		combosTexts[29][2] = "3";
		// REASS_G: END
		// REASS_H: START
		combosTexts[30] = new String[4];
		combosTexts[30][0] = "1";
		combosTexts[30][1] = "2";
		combosTexts[30][2] = "3";
		combosTexts[30][3] = "4";
		// REASS_H: END
		// REASS_J: START
		combosTexts[31] = new String[3];
		combosTexts[31][0] = "1";
		combosTexts[31][1] = "2";
		combosTexts[31][2] = "3";
		// REASS_J: END
		// REASS_K: START
		combosTexts[32] = new String[3];
		combosTexts[32][0] = "1";
		combosTexts[32][1] = "2";
		combosTexts[32][2] = "3";
		// REASS_K: END
		// REASS_L: START
		combosTexts[33] = new String[2];
		combosTexts[33][0] = "1";
		combosTexts[33][1] = "2";
		// REASS_L: END
		// REASS_M: START
		combosTexts[34] = new String[3];
		combosTexts[34][0] = "1";
		combosTexts[34][1] = "2";
		combosTexts[34][2] = "3";
		// REASS_M: END
		// REASS_N: START
		combosTexts[35] = new String[3];
		combosTexts[35][0] = "1";
		combosTexts[35][1] = "2";
		combosTexts[35][2] = "3";
		// REASS_N: END
		// REASS_P: START
		combosTexts[36] = new String[3];
		combosTexts[36][0] = "1";
		combosTexts[36][1] = "2";
		combosTexts[36][2] = "3";
		// REASS_P: END
		// REASS_Q: START
		combosTexts[37] = new String[4];
		combosTexts[37][0] = "1";
		combosTexts[37][1] = "2";
		combosTexts[37][2] = "3";
		combosTexts[37][3] = "4";
		// REASS_Q: END
	}

	// <<DATA CHANGE>>
	private void makeArray() {
		labels = new JLabel[numberOfComponents];
		fields = new JTextField[numberOfFields];
		combos = new Vector<JComboBox<String>>();
		formattedFields = new JFormattedTextField[numberOfNumberFields];
		names = new String[numberOfComponents];
		languages = new boolean[numberOfComponents];

		names[FORM_NUMBER] = "شماره فرم :"; // 0
		languages[FORM_NUMBER] = true;
		names[SALON_NAME] = "نام سالن :";
		languages[SALON_NAME] = true;
		names[LINE_NAME] = "نام خط :";
		languages[LINE_NAME] = true;
		names[STATION_NUMBER] = "شماره (کد) ایستگاه :";
		languages[STATION_NUMBER] = true;
		names[PROCESS_NAME] = "عنوان فرایند (ایستگاه) :";
		languages[PROCESS_NAME] = true;

		names[WORKING_TASK] = "وظیفه کاری :"; // 5
		languages[WORKING_TASK] = true;
		names[OPERATOR_NAME] = "نام اپراتور :";
		languages[OPERATOR_NAME] = true;
		names[FILM_CODE] = "کد (شماره) فیلم :";
		languages[FILM_CODE] = true;
		names[REVIEW_DATE] = "تاریح ارزیابی :";
		languages[REVIEW_DATE] = true;
		names[OPERATOR_CODE] = "کد اپراتور (چپ یا راست بودن) :";
		languages[OPERATOR_CODE] = true;

		names[TOOL] = "ابزار یا قطعه :"; // 10
		languages[TOOL] = true;
		names[TOOL_WEIGHT] = "وزن ابزار :";
		languages[TOOL_WEIGHT] = true;
		names[PRODUCE_PER_HOUR] = "نرخ تولید در ساعت :";
		languages[PRODUCE_PER_HOUR] = true;
		names[ASS_HAS_LOAD] = "حمل بار";
		languages[ASS_HAS_LOAD] = true;
		names[ASS_A] = ": A";
		languages[ASS_A] = false;

		names[ASS_BS] = ": Bs"; // 15
		languages[ASS_BS] = false;
		names[ASS_BM] = ": Bm";
		languages[ASS_BM] = false;
		names[ASS_C] = ": C";
		languages[ASS_C] = false;
		names[ASS_D] = ": D";
		languages[ASS_D] = false;
		names[ASS_E] = ": E";
		languages[ASS_E] = false;

		names[ASS_F] = ": F"; // 20
		languages[ASS_F] = false;
		names[ASS_G] = ": G";
		languages[ASS_G] = false;
		names[ASS_H] = ": H";
		languages[ASS_H] = false;
		names[ASS_J] = ": J";
		languages[ASS_J] = false;
		names[ASS_K] = ": K";
		languages[ASS_K] = false;

		names[ASS_L] = ": L"; // 25
		languages[ASS_L] = false;
		names[ASS_M] = ": M";
		languages[ASS_M] = false;
		names[ASS_N] = ": N";
		languages[ASS_N] = false;
		names[ASS_P] = ": P";
		languages[ASS_P] = false;
		names[ASS_Q] = ": Q";
		languages[ASS_Q] = false;

		names[ASS_BACK_POINT] = "امتیاز کمر :"; // 30
		languages[ASS_BACK_POINT] = true;
		names[ASS_BACK_LEVEL] = ": Back Level";
		languages[ASS_BACK_LEVEL] = false;
		names[ASS_SHOULDER_POINT] = "امتیاز شانه :";
		languages[ASS_SHOULDER_POINT] = true;
		names[ASS_SHOULDER_LEVEL] = ": Shoulder Level";
		languages[ASS_SHOULDER_LEVEL] = false;
		names[ASS_WRIST_POINT] = "امتیاز مچ :";
		languages[ASS_WRIST_POINT] = true;

		names[ASS_WRIST_LEVEL] = ": Wrist Level"; // 35
		languages[ASS_WRIST_LEVEL] = false;
		names[ASS_NECK_POINT] = "امتیاز گردن :";
		languages[ASS_NECK_POINT] = true;
		names[ASS_NECK_LEVEL] = ": Neck Level";
		languages[ASS_NECK_LEVEL] = false;
		names[DRIVING_POINT] = ": Driving";
		languages[DRIVING_POINT] = false;
		names[DRIVING_LEVEL] = " : Driving Level";
		languages[DRIVING_LEVEL] = false;

		names[VIBRATION_POINT] = ": Vibration"; // 40
		languages[VIBRATION_POINT] = false;
		names[VIBRATION_LEVEL] = ": Vibration Level";
		languages[VIBRATION_LEVEL] = false;
		names[WORK_PACE_POINT] = ": Work Pace";
		languages[WORK_PACE_POINT] = false;
		names[WORK_PACE_LEVEL] = ": Work Pace Level";
		languages[WORK_PACE_LEVEL] = false;
		names[STRESS_POINT] = ": Stress";
		languages[STRESS_POINT] = false;

		names[STRESS_LEVEL] = ": Stress Level"; // 45
		languages[STRESS_LEVEL] = false;
		names[ASS_TOTAL_POINT] = "امتیاز کلی:  ";
		languages[ASS_TOTAL_POINT] = true;
		names[ASS_QEC_LEVEL] = ": QEC Level";
		languages[ASS_QEC_LEVEL] = false;
		names[DESCRIPTION] = "توضیحات / ملاحضات :";
		languages[DESCRIPTION] = true;
		names[JOB_ROTATION] = "نحوه گردش شغلی :";
		languages[JOB_ROTATION] = true;

		names[CORRECTIVE_ACTION_TYPE] = "نوع پیشنهادات / اقدام اصلاحی :"; // 50
		languages[CORRECTIVE_ACTION_TYPE] = true;
		names[CORRECTIVE_ACTION_NUMBER] = "شماره اقدام اصلاحی :";
		languages[CORRECTIVE_ACTION_NUMBER] = true;
		names[CORRECTIVE_ACTION_DATE] = "تاریخ اجرای اقدام اصلاحی :";
		languages[CORRECTIVE_ACTION_DATE] = true;
		names[REASS_HAS_LOAD] = "حمل بار";
		languages[REASS_HAS_LOAD] = true;
		names[REASS_A] = ": A";
		languages[REASS_A] = false;

		names[REASS_BS] = ": Bs"; // 55
		languages[REASS_BS] = false;
		names[REASS_BM] = ": Bm";
		languages[REASS_BM] = false;
		names[REASS_C] = ": C";
		languages[REASS_C] = false;
		names[REASS_D] = ": D";
		languages[REASS_D] = false;
		names[REASS_E] = ": E";
		languages[REASS_E] = false;

		names[REASS_F] = ": F"; // 60
		languages[REASS_F] = false;
		names[REASS_G] = ": G";
		languages[REASS_G] = false;
		names[REASS_H] = ": H";
		languages[REASS_H] = false;
		names[REASS_J] = ": J";
		languages[REASS_J] = false;
		names[REASS_K] = ": K";
		languages[REASS_K] = false;

		names[REASS_L] = ": L"; // 65
		languages[REASS_L] = false;
		names[REASS_M] = ": M";
		languages[REASS_M] = false;
		names[REASS_N] = ": N";
		languages[REASS_N] = false;
		names[REASS_P] = ": P";
		languages[REASS_P] = false;
		names[REASS_Q] = ": Q";
		languages[REASS_Q] = false;

		names[REASS_BACK_POINT] = "امتیاز کمر :"; // 70
		languages[REASS_BACK_POINT] = true;
		names[REASS_BACK_LEVEL] = ": Back Level";
		languages[REASS_BACK_LEVEL] = false;
		names[REASS_SHOULDER_POINT] = "امتیاز شانه :";
		languages[REASS_SHOULDER_POINT] = true;
		names[REASS_SHOULDER_LEVEL] = ": Shoulder Level";
		languages[REASS_SHOULDER_LEVEL] = false;
		names[REASS_WRIST_POINT] = "امتیاز مچ :";
		languages[REASS_WRIST_POINT] = true;

		names[REASS_WRIST_LEVEL] = ": Wrist Level"; // 75
		languages[REASS_WRIST_LEVEL] = false;
		names[REASS_NECK_POINT] = "امتیاز گردن :";
		languages[REASS_NECK_POINT] = true;
		names[REASS_NECK_LEVEL] = ": Neck Level";
		languages[REASS_NECK_LEVEL] = false;
		names[REASS_TOTAL_POINT] = "امتیاز کلی :";
		languages[REASS_TOTAL_POINT] = true;
		names[REASS_QEC_LEVEL] = ": QEC Level";
		languages[REASS_QEC_LEVEL] = false;

		names[REASSESSMENT_DATE] = "تاریح ارزیابی مجدد :"; // 80
		languages[REASSESSMENT_DATE] = true;
		names[ASSESSOR_NAME] = "نام کارشناس ارزیاب :";
		languages[ASSESSOR_NAME] = true;
	}

	// <<DATA CHANGE>>
	private void init() throws Exception {
		setFocusCycleRoot(true);
		setLayout(new GridBagLayout());
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for (int i = 0; i < numberOfComponents; i++) {
			int index = indexToComboArr(i);
			if (index != -1) {
				combos.addElement(addCombo(i, i / 2, i % 2 + 1, names[i],
						languages[i]));
				if ((ASS_A <= i && i <= ASS_Q)
						|| (REASS_A <= i && i <= REASS_Q)) {
					combos.lastElement().addActionListener(
							new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									calculatePoints();
								}
							});
				} else if (DRIVING_POINT <= i && i <= STRESS_LEVEL) {
					combos.lastElement().addActionListener(
							new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									calculateAssesPoints();
								}
							});
				}
			} else {
				index = indexToFieldArr(i);
				if (index != -1) {
					fields[index] = addField(i, i / 2, i % 2 + 1, names[i],
							languages[i]);
					if ((ASS_BACK_LEVEL <= i && i <= ASS_QEC_LEVEL)
							|| (REASS_BACK_LEVEL <= i && i <= REASS_QEC_LEVEL))
						fields[index].setEditable(false);
				} else {
					index = indexToFormattedFieldArr(i);
					if (index != -1) {
						formattedFields[index] = addFormattedField(i, i / 2,
								i % 2 + 1, names[i], languages[i]);
						if (i != FORM_NUMBER)
							formattedFields[index].setEditable(false);
					}
				}
			}
			labels[i].setOpaque(true);
			labels[i].setPreferredSize(new Dimension(150, 22));
		}
		combos.elementAt(indexToComboArr(ASS_HAS_LOAD)).addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (combos.elementAt(indexToComboArr(ASS_HAS_LOAD))
								.getSelectedIndex() == 0) {
							combos.elementAt(indexToComboArr(ASS_BS))
									.setEnabled(true);
							combos.elementAt(indexToComboArr(ASS_BM))
									.setEnabled(false);
						} else {
							combos.elementAt(indexToComboArr(ASS_BS))
									.setEnabled(false);
							combos.elementAt(indexToComboArr(ASS_BM))
									.setEnabled(true);
						}
						calculatePoints();
					}
				});
		combos.elementAt(indexToComboArr(REASS_HAS_LOAD)).addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (combos.elementAt(indexToComboArr(REASS_HAS_LOAD))
								.getSelectedIndex() == 0) {
							combos.elementAt(indexToComboArr(REASS_BS))
									.setEnabled(true);
							combos.elementAt(indexToComboArr(REASS_BM))
									.setEnabled(false);
						} else {
							combos.elementAt(indexToComboArr(REASS_BS))
									.setEnabled(false);
							combos.elementAt(indexToComboArr(REASS_BM))
									.setEnabled(true);
						}
						calculatePoints();
					}
				});
		combos.elementAt(indexToComboArr(ASS_BM)).setEnabled(false);
		combos.elementAt(indexToComboArr(REASS_BM)).setEnabled(false);

		if (combos.size() != numberOfCombos)
			throw new Exception("There is a problem with combos");

		JButton save = new JButton("ذخیره");
		save.setFont(font);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Record[] recs = new Record[1];
				recs[0] = new Record(makeStrings());
				Record[] result = manager.getLogic().insert(recs);
				if (result.length == 0) {
					manager.addReordsToTable(recs);
					clearFields();
					manager.action(RightPanelLabel.CMND_LOAD);
					manager.action(RightPanelLabel.CMND_SWITCH);
				} else
					JOptionPane.showMessageDialog(null, "این رکورد ذخیره نشد.",
							"اخطار", JOptionPane.WARNING_MESSAGE);
			}
		});
		GridBagConstraints miniGBC = new GridBagConstraints();
		miniGBC.ipady = 0;
		miniGBC.weightx = 0;
		miniGBC.weighty = 0;
		miniGBC.gridx = 1;
		miniGBC.gridy = numberOfComponents / 2 + 1;
		miniGBC.fill = GridBagConstraints.HORIZONTAL;
		add(save, miniGBC);

		JButton cancel = new JButton("لغو");
		cancel.setFont(font);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields();
				manager.action(RightPanelLabel.CMND_SWITCH);
			}
		});
		miniGBC.ipady = 0;
		miniGBC.weightx = 0;
		miniGBC.weighty = 0;
		miniGBC.gridx = 3;
		miniGBC.fill = GridBagConstraints.HORIZONTAL;
		add(cancel, miniGBC);
		calculatePoints();
		calculateAssesPoints();
	}

	private JTextField addField(int index, int line, int column, String text,
			boolean persian) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipady = 0;
		gbc.insets = new Insets(2, 0, 2, 0);
		gbc.weightx = 0.15;
		gbc.weighty = 0;
		if (column == 1) {
			gbc.gridx = 0;
		} else if (column == 2) {
			gbc.gridx = 3;
		}
		gbc.gridy = line;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		labels[index] = new JLabel(text);
		if (persian)
			labels[index].setFont(font);
		else
			labels[index]
					.setFont(labels[index].getFont().deriveFont(Font.BOLD));
		labels[index]
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(labels[index], gbc);
		gbc.weightx = 0.35;
		if (column == 1) {
			gbc.gridx = 1;
		} else if (column == 2) {
			gbc.gridx = 4;
		}
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(120, 25));
		field.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(field, gbc);
		if (column == 1) {
			gbc.gridx = 2;
		} else if (column == 2) {
			gbc.gridx = 5;
		}
		gbc.weightx = 0.01;
		add(new JLabel(), gbc);
		if (line % 2 == 0) {
			labels[index].setBackground(new Color(210, 210, 210));
			field.setBackground(new Color(210, 210, 210));
		} else {
			labels[index].setBackground(new Color(220, 220, 220));
			field.setBackground(new Color(220, 220, 220));
		}
		return field;
	}

	private JComboBox<String> addCombo(int index, int line, int column,
			String text, boolean persian) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipady = 0;
		gbc.insets = new Insets(2, 0, 2, 0);
		gbc.weightx = 0.15;
		gbc.weighty = 0;
		if (column == 1) {
			gbc.gridx = 0;
		} else if (column == 2) {
			gbc.gridx = 3;
		}
		gbc.gridy = line;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		labels[index] = new JLabel(text);
		if (persian)
			labels[index].setFont(font);
		else
			labels[index]
					.setFont(labels[index].getFont().deriveFont(Font.BOLD));
		labels[index]
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(labels[index], gbc);
		gbc.weightx = 0.35;
		if (column == 1) {
			gbc.gridx = 1;
		} else if (column == 2) {
			gbc.gridx = 4;
		}
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setPreferredSize(new Dimension(120, 25));
		comboBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for (int i = 0; i < combosTexts[indexToComboArr(index)].length; i++) {
			comboBox.addItem(combosTexts[indexToComboArr(index)][i]);
		}
		add(comboBox, gbc);
		if (column == 1) {
			gbc.gridx = 2;
		} else if (column == 2) {
			gbc.gridx = 5;
		}
		gbc.weightx = 0.01;
		add(new JLabel(), gbc);
		if (line % 2 == 0) {
			labels[index].setBackground(new Color(210, 210, 210));
			comboBox.setBackground(new Color(210, 210, 210));
		} else {
			labels[index].setBackground(new Color(220, 220, 220));
			comboBox.setBackground(new Color(220, 220, 220));
		}
		return comboBox;
	}

	private JFormattedTextField addFormattedField(int index, int line,
			int column, String text, boolean persian) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipady = 0;
		gbc.insets = new Insets(2, 0, 2, 0);
		gbc.weightx = 0.15;
		gbc.weighty = 0;
		if (column == 1) {
			gbc.gridx = 0;
		} else if (column == 2) {
			gbc.gridx = 3;
		}
		gbc.gridy = line;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		labels[index] = new JLabel(text);
		if (persian)
			labels[index].setFont(font);
		else
			labels[index]
					.setFont(labels[index].getFont().deriveFont(Font.BOLD));
		labels[index]
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(labels[index], gbc);
		gbc.weightx = 0.35;
		if (column == 1) {
			gbc.gridx = 1;
		} else if (column == 2) {
			gbc.gridx = 4;
		}
		JFormattedTextField field;
		if (37 <= index && index <= 43)
			field = new JFormattedTextField(NumberFormat.getIntegerInstance()) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void processEvent(AWTEvent e) {
					super.processEvent(e);
					calculateLevels();
				}
			};
		else if (53 <= index && index <= 59)
			field = new JFormattedTextField(NumberFormat.getIntegerInstance()) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void processEvent(AWTEvent e) {
					super.processEvent(e);
					calculateQEC();
					calculateLevels();
				}
			};
		else
			field = new JFormattedTextField(NumberFormat.getIntegerInstance());
		field.setPreferredSize(new Dimension(120, 25));
		field.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		field.setValue(0);
		add(field, gbc);
		if (column == 1) {
			gbc.gridx = 2;
		} else if (column == 2) {
			gbc.gridx = 5;
		}
		gbc.weightx = 0.01;
		add(new JLabel(), gbc);
		if (line % 2 == 0) {
			labels[index].setBackground(new Color(210, 210, 210));
			field.setBackground(new Color(210, 210, 210));
		} else {
			labels[index].setBackground(new Color(220, 220, 220));
			field.setBackground(new Color(220, 220, 220));
		}
		return field;
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private String[] makeStrings() {
		String[] res = new String[66];
		res[0] = "0";
		res[1] = formattedFields[indexToFormattedFieldArr(FORM_NUMBER)]
				.getText();
		res[2] = fields[indexToFieldArr(SALON_NAME)].getText();
		res[3] = fields[indexToFieldArr(LINE_NAME)].getText();
		res[4] = fields[indexToFieldArr(STATION_NUMBER)].getText();
		res[5] = fields[indexToFieldArr(PROCESS_NAME)].getText();
		res[6] = fields[indexToFieldArr(WORKING_TASK)].getText();
		res[7] = fields[indexToFieldArr(OPERATOR_NAME)].getText();
		res[8] = fields[indexToFieldArr(FILM_CODE)].getText();
		res[9] = fields[indexToFieldArr(REVIEW_DATE)].getText();
		res[10] = fields[indexToFieldArr(OPERATOR_CODE)].getText();
		res[11] = fields[indexToFieldArr(TOOL)].getText();
		res[12] = fields[indexToFieldArr(TOOL_WEIGHT)].getText();
		res[13] = fields[indexToFieldArr(PRODUCE_PER_HOUR)].getText();
		res[14] = (String) combos.elementAt(indexToComboArr(ASS_A))
				.getSelectedItem();
		res[15] = combos.elementAt(indexToComboArr(ASS_HAS_LOAD))
				.getSelectedIndex() == 0 ? (String) combos.elementAt(
				indexToComboArr(ASS_BS)).getSelectedItem() : "_";
		res[16] = combos.elementAt(indexToComboArr(ASS_HAS_LOAD))
				.getSelectedIndex() == 1 ? (String) combos.elementAt(
				indexToComboArr(ASS_BM)).getSelectedItem() : "_";
		res[17] = (String) combos.elementAt(indexToComboArr(ASS_C))
				.getSelectedItem();
		res[18] = (String) combos.elementAt(indexToComboArr(ASS_D))
				.getSelectedItem();
		res[19] = (String) combos.elementAt(indexToComboArr(ASS_E))
				.getSelectedItem();
		res[20] = (String) combos.elementAt(indexToComboArr(ASS_F))
				.getSelectedItem();
		res[21] = (String) combos.elementAt(indexToComboArr(ASS_G))
				.getSelectedItem();
		res[22] = (String) combos.elementAt(indexToComboArr(ASS_H))
				.getSelectedItem();
		res[23] = (String) combos.elementAt(indexToComboArr(ASS_J))
				.getSelectedItem();
		res[24] = (String) combos.elementAt(indexToComboArr(ASS_K))
				.getSelectedItem();
		res[25] = (String) combos.elementAt(indexToComboArr(ASS_L))
				.getSelectedItem();
		res[26] = (String) combos.elementAt(indexToComboArr(ASS_M))
				.getSelectedItem();
		res[27] = (String) combos.elementAt(indexToComboArr(ASS_N))
				.getSelectedItem();
		res[28] = (String) combos.elementAt(indexToComboArr(ASS_P))
				.getSelectedItem();
		res[29] = (String) combos.elementAt(indexToComboArr(ASS_Q))
				.getSelectedItem();
		res[30] = formattedFields[indexToFormattedFieldArr(ASS_BACK_POINT)]
				.getText();
		res[31] = fields[indexToFieldArr(ASS_BACK_LEVEL)].getText();
		res[32] = formattedFields[indexToFormattedFieldArr(ASS_SHOULDER_POINT)]
				.getText();
		res[33] = fields[indexToFieldArr(ASS_SHOULDER_LEVEL)].getText();
		res[34] = formattedFields[indexToFormattedFieldArr(ASS_WRIST_POINT)]
				.getText();
		res[35] = fields[indexToFieldArr(ASS_WRIST_LEVEL)].getText();
		res[36] = formattedFields[indexToFormattedFieldArr(ASS_NECK_POINT)]
				.getText();
		res[37] = fields[indexToFieldArr(ASS_NECK_LEVEL)].getText();
		res[38] = formattedFields[indexToFormattedFieldArr(DRIVING_POINT)]
				.getText();
		res[39] = (String) combos.elementAt(indexToComboArr(DRIVING_LEVEL))
				.getSelectedItem();
		res[40] = formattedFields[indexToFormattedFieldArr(VIBRATION_POINT)]
				.getText();
		res[41] = (String) combos.elementAt(indexToComboArr(VIBRATION_LEVEL))
				.getSelectedItem();
		res[42] = formattedFields[indexToFormattedFieldArr(WORK_PACE_POINT)]
				.getText();
		res[43] = (String) combos.elementAt(indexToComboArr(WORK_PACE_LEVEL))
				.getSelectedItem();
		res[44] = formattedFields[indexToFormattedFieldArr(STRESS_POINT)]
				.getText();
		res[45] = (String) combos.elementAt(indexToComboArr(STRESS_LEVEL))
				.getSelectedItem();
		res[46] = formattedFields[indexToFormattedFieldArr(ASS_TOTAL_POINT)]
				.getText();
		res[47] = fields[indexToFieldArr(ASS_QEC_LEVEL)].getText();
		res[48] = combos.elementAt(indexToComboArr(ASS_HAS_LOAD))
				.getSelectedIndex() + "";
		res[49] = fields[indexToFieldArr(DESCRIPTION)].getText();
		res[50] = fields[indexToFieldArr(JOB_ROTATION)].getText();
		res[51] = fields[indexToFieldArr(CORRECTIVE_ACTION_TYPE)].getText();
		res[52] = fields[indexToFieldArr(CORRECTIVE_ACTION_NUMBER)].getText();
		res[53] = fields[indexToFieldArr(CORRECTIVE_ACTION_DATE)].getText();
		res[54] = formattedFields[indexToFormattedFieldArr(REASS_BACK_POINT)]
				.getText();
		res[55] = fields[indexToFieldArr(REASS_BACK_LEVEL)].getText();
		res[56] = formattedFields[indexToFormattedFieldArr(REASS_SHOULDER_POINT)]
				.getText();
		res[57] = fields[indexToFieldArr(REASS_SHOULDER_LEVEL)].getText();
		res[58] = formattedFields[indexToFormattedFieldArr(REASS_WRIST_POINT)]
				.getText();
		res[59] = fields[indexToFieldArr(REASS_WRIST_LEVEL)].getText();
		res[60] = formattedFields[indexToFormattedFieldArr(REASS_NECK_POINT)]
				.getText();
		res[61] = fields[indexToFieldArr(REASS_NECK_LEVEL)].getText();
		res[62] = formattedFields[indexToFormattedFieldArr(REASS_TOTAL_POINT)]
				.getText();
		res[63] = fields[indexToFieldArr(REASS_QEC_LEVEL)].getText();
		res[64] = fields[indexToFieldArr(REASSESSMENT_DATE)].getText();
		res[65] = fields[indexToFieldArr(ASSESSOR_NAME)].getText();
		return res;
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private int indexToComboArr(int index) {
		switch (index) {
		case ASS_HAS_LOAD:
			return 0;
		case ASS_A:
			return 1;
		case ASS_BS:
			return 2;
		case ASS_BM:
			return 3;
		case ASS_C:
			return 4;
		case ASS_D:
			return 5;
		case ASS_E:
			return 6;
		case ASS_F:
			return 7;
		case ASS_G:
			return 8;
		case ASS_H:
			return 9;
		case ASS_J:
			return 10;
		case ASS_K:
			return 11;
		case ASS_L:
			return 12;
		case ASS_M:
			return 13;
		case ASS_N:
			return 14;
		case ASS_P:
			return 15;
		case ASS_Q:
			return 16;
		case DRIVING_LEVEL:
			return 17;
		case VIBRATION_LEVEL:
			return 18;
		case WORK_PACE_LEVEL:
			return 19;
		case STRESS_LEVEL:
			return 20;
		case REASS_HAS_LOAD:
			return 21;
		case REASS_A:
			return 22;
		case REASS_BS:
			return 23;
		case REASS_BM:
			return 24;
		case REASS_C:
			return 25;
		case REASS_D:
			return 26;
		case REASS_E:
			return 27;
		case REASS_F:
			return 28;
		case REASS_G:
			return 29;
		case REASS_H:
			return 30;
		case REASS_J:
			return 31;
		case REASS_K:
			return 32;
		case REASS_L:
			return 33;
		case REASS_M:
			return 34;
		case REASS_N:
			return 35;
		case REASS_P:
			return 36;
		case REASS_Q:
			return 37;
		default:
			return -1;
		}
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private int indexToFieldArr(int index) {
		switch (index) {
		case SALON_NAME:
			return 0;
		case LINE_NAME:
			return 1;
		case STATION_NUMBER:
			return 2;
		case PROCESS_NAME:
			return 3;
		case WORKING_TASK:
			return 4;
		case OPERATOR_NAME:
			return 5;
		case FILM_CODE:
			return 6;
		case REVIEW_DATE:
			return 7;
		case OPERATOR_CODE:
			return 8;
		case TOOL:
			return 9;
		case TOOL_WEIGHT:
			return 10;
		case PRODUCE_PER_HOUR:
			return 11;
		case ASS_BACK_LEVEL:
			return 12;
		case ASS_SHOULDER_LEVEL:
			return 13;
		case ASS_WRIST_LEVEL:
			return 14;
		case ASS_NECK_LEVEL:
			return 15;
		case ASS_QEC_LEVEL:
			return 16;
		case DESCRIPTION:
			return 17;
		case JOB_ROTATION:
			return 18;
		case CORRECTIVE_ACTION_TYPE:
			return 19;
		case CORRECTIVE_ACTION_NUMBER:
			return 20;
		case CORRECTIVE_ACTION_DATE:
			return 21;
		case REASS_BACK_LEVEL:
			return 22;
		case REASS_SHOULDER_LEVEL:
			return 23;
		case REASS_WRIST_LEVEL:
			return 24;
		case REASS_NECK_LEVEL:
			return 25;
		case REASS_QEC_LEVEL:
			return 26;
		case REASSESSMENT_DATE:
			return 27;
		case ASSESSOR_NAME:
			return 28;
		default:
			return -1;
		}
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private int indexToFormattedFieldArr(int index) {
		switch (index) {
		case FORM_NUMBER:
			return 0;
		case ASS_BACK_POINT:
			return 1;
		case ASS_SHOULDER_POINT:
			return 2;
		case ASS_WRIST_POINT:
			return 3;
		case ASS_NECK_POINT:
			return 4;
		case DRIVING_POINT:
			return 5;
		case VIBRATION_POINT:
			return 6;
		case WORK_PACE_POINT:
			return 7;
		case STRESS_POINT:
			return 8;
		case ASS_TOTAL_POINT:
			return 9;
		case REASS_BACK_POINT:
			return 10;
		case REASS_SHOULDER_POINT:
			return 11;
		case REASS_WRIST_POINT:
			return 12;
		case REASS_NECK_POINT:
			return 13;
		case REASS_TOTAL_POINT:
			return 14;
		default:
			return -1;
		}
	}

	private void clearFields() {
		for (int i = 0; i < numberOfComponents; i++) {
			if (indexToFieldArr(i) != -1) {
				fields[indexToFieldArr(i)].setText(null);
			} else if (indexToComboArr(i) != -1) {
				combos.elementAt(indexToComboArr(i)).setSelectedIndex(0);
			} else if (indexToFormattedFieldArr(i) != -1) {
				formattedFields[indexToFormattedFieldArr(i)].setValue(0);
			}
		}
		calculatePoints();
	}

	// <<COMPONENT CHANGE>>
	private void calculatePoints() {
		int point1, point2, point3, point4, point5;
		int a = combos.elementAt(indexToComboArr(ASS_A)).getSelectedIndex();
		int b;
		if (combos.elementAt(indexToComboArr(ASS_HAS_LOAD)).getSelectedIndex() == 0)
			b = combos.elementAt(indexToComboArr(ASS_BS)).getSelectedIndex();
		else
			b = combos.elementAt(indexToComboArr(ASS_BM)).getSelectedIndex();
		int c = combos.elementAt(indexToComboArr(ASS_C)).getSelectedIndex();
		int d = combos.elementAt(indexToComboArr(ASS_D)).getSelectedIndex();
		int e = combos.elementAt(indexToComboArr(ASS_E)).getSelectedIndex();
		int f = combos.elementAt(indexToComboArr(ASS_F)).getSelectedIndex();
		int g = combos.elementAt(indexToComboArr(ASS_G)).getSelectedIndex();
		int h = combos.elementAt(indexToComboArr(ASS_H)).getSelectedIndex();
		int j = combos.elementAt(indexToComboArr(ASS_J)).getSelectedIndex();
		int k = combos.elementAt(indexToComboArr(ASS_K)).getSelectedIndex();
		int l = combos.elementAt(indexToComboArr(ASS_L)).getSelectedIndex();

		// ASSESSMENT: START
		// BACK: START
		point1 = 2 + 2 * h + 2 * a;
		point2 = 2 + 2 * h + 2 * b;
		point3 = 2 + 2 * h + 2 * j;
		point4 = 2 + 2 * j + 2 * a;
		point5 = 2 + 2 * j + 2 * b;
		formattedFields[indexToFormattedFieldArr(ASS_BACK_POINT)]
				.setValue(point1 + point2 + point3 + point4 + point5);
		// BACK: END
		// SHOULDER: START
		point1 = 2 + 2 * h + 2 * c;
		point2 = 2 + 2 * h + 2 * d;
		point3 = 2 + 2 * h + 2 * j;
		point4 = 2 + 2 * j + 2 * c;
		point5 = 2 + 2 * j + 2 * d;
		formattedFields[indexToFormattedFieldArr(ASS_SHOULDER_POINT)]
				.setValue(point1 + point2 + point3 + point4 + point5);
		// SHOULDER: END
		// WRIST: START
		point1 = 2 + 2 * k + 2 * f;
		point2 = 2 + 2 * k + 2 * e;
		point3 = 2 + 2 * k + 2 * j;
		point4 = 2 + 2 * j + 2 * f;
		point5 = 2 + 2 * j + 2 * e;
		formattedFields[indexToFormattedFieldArr(ASS_WRIST_POINT)]
				.setValue(point1 + point2 + point3 + point4 + point5);
		// WRIST: END
		// NECK: START
		point1 = 2 + 2 * j + 2 * g;
		point2 = 2 + 2 * j + 2 * l;
		formattedFields[indexToFormattedFieldArr(ASS_NECK_POINT)]
				.setValue(point1 + point2);
		// NECK: END
		// ASSESSMENT: END

		a = combos.elementAt(indexToComboArr(REASS_A)).getSelectedIndex();
		if (combos.elementAt(indexToComboArr(REASS_HAS_LOAD))
				.getSelectedIndex() == 0)
			b = combos.elementAt(indexToComboArr(REASS_BS)).getSelectedIndex();
		else
			b = combos.elementAt(indexToComboArr(REASS_BM)).getSelectedIndex();
		c = combos.elementAt(indexToComboArr(REASS_C)).getSelectedIndex();
		d = combos.elementAt(indexToComboArr(REASS_D)).getSelectedIndex();
		e = combos.elementAt(indexToComboArr(REASS_E)).getSelectedIndex();
		f = combos.elementAt(indexToComboArr(REASS_F)).getSelectedIndex();
		g = combos.elementAt(indexToComboArr(REASS_G)).getSelectedIndex();
		h = combos.elementAt(indexToComboArr(REASS_H)).getSelectedIndex();
		j = combos.elementAt(indexToComboArr(REASS_J)).getSelectedIndex();
		k = combos.elementAt(indexToComboArr(REASS_K)).getSelectedIndex();
		l = combos.elementAt(indexToComboArr(REASS_L)).getSelectedIndex();

		// REASSESSMENT: START
		// BACK: START
		point1 = 2 + 2 * h + 2 * a;
		point2 = 2 + 2 * h + 2 * b;
		point3 = 2 + 2 * h + 2 * j;
		point4 = 2 + 2 * j + 2 * a;
		point5 = 2 + 2 * j + 2 * b;
		formattedFields[indexToFormattedFieldArr(REASS_BACK_POINT)]
				.setValue(point1 + point2 + point3 + point4 + point5);
		// BACK: END
		// SHOULDER: START
		point1 = 2 + 2 * h + 2 * c;
		point2 = 2 + 2 * h + 2 * d;
		point3 = 2 + 2 * h + 2 * j;
		point4 = 2 + 2 * j + 2 * c;
		point5 = 2 + 2 * j + 2 * d;
		formattedFields[indexToFormattedFieldArr(REASS_SHOULDER_POINT)]
				.setValue(point1 + point2 + point3 + point4 + point5);
		// SHOULDER: END
		// WRIST: START
		point1 = 2 + 2 * k + 2 * f;
		point2 = 2 + 2 * k + 2 * e;
		point3 = 2 + 2 * k + 2 * j;
		point4 = 2 + 2 * j + 2 * f;
		point5 = 2 + 2 * j + 2 * e;
		formattedFields[indexToFormattedFieldArr(REASS_WRIST_POINT)]
				.setValue(point1 + point2 + point3 + point4 + point5);
		// WRIST: END
		// NECK: START
		point1 = 2 + 2 * j + 2 * g;
		point2 = 2 + 2 * j + 2 * l;
		formattedFields[indexToFormattedFieldArr(REASS_NECK_POINT)]
				.setValue(point1 + point2);
		// NECK: END
		// REASSESSMENT: END
		calculateQEC();
		calculateLevels();
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private void calculateQEC() {
		int res;
		res = (int) (formattedFields[indexToFormattedFieldArr(ASS_BACK_POINT)]
				.getValue());
		res += (int) (formattedFields[indexToFormattedFieldArr(ASS_SHOULDER_POINT)]
				.getValue());
		res += (int) (formattedFields[indexToFormattedFieldArr(ASS_WRIST_POINT)]
				.getValue());
		res += (int) (formattedFields[indexToFormattedFieldArr(ASS_NECK_POINT)]
				.getValue());
		if (combos.elementAt(indexToComboArr(ASS_HAS_LOAD)).getSelectedIndex() == 0)
			formattedFields[indexToFormattedFieldArr(ASS_TOTAL_POINT)]
					.setValue((int) (100 * res / 162));
		else if (combos.elementAt(indexToComboArr(ASS_HAS_LOAD))
				.getSelectedIndex() == 1)
			formattedFields[indexToFormattedFieldArr(ASS_TOTAL_POINT)]
					.setValue((int) (100 * res / 176));

		res = (int) (formattedFields[indexToFormattedFieldArr(REASS_BACK_POINT)]
				.getValue());
		res += (int) (formattedFields[indexToFormattedFieldArr(REASS_SHOULDER_POINT)]
				.getValue());
		res += (int) (formattedFields[indexToFormattedFieldArr(REASS_WRIST_POINT)]
				.getValue());
		res += (int) (formattedFields[indexToFormattedFieldArr(REASS_NECK_POINT)]
				.getValue());
		if (combos.elementAt(indexToComboArr(REASS_HAS_LOAD))
				.getSelectedIndex() == 0)
			formattedFields[indexToFormattedFieldArr(REASS_TOTAL_POINT)]
					.setValue((int) (100 * res / 162));
		else if (combos.elementAt(indexToComboArr(REASS_HAS_LOAD))
				.getSelectedIndex() == 1)
			formattedFields[indexToFormattedFieldArr(REASS_TOTAL_POINT)]
					.setValue((int) (100 * res / 176));
	}

	// <<DATA CHANGE>>
	private void calculateLevels() {
		backShoulderWristLevel(ASS_BACK_POINT, true);
		backShoulderWristLevel(ASS_SHOULDER_POINT, false);
		backShoulderWristLevel(ASS_WRIST_POINT, false);
		neckLevel(ASS_NECK_POINT);
		qecLevel(ASS_TOTAL_POINT);
		backShoulderWristLevel(REASS_BACK_POINT, true);
		backShoulderWristLevel(REASS_SHOULDER_POINT, false);
		backShoulderWristLevel(REASS_WRIST_POINT, false);
		neckLevel(REASS_NECK_POINT);
		qecLevel(REASS_TOTAL_POINT);
	}

	// <<DATA CHANGE>>
	private void calculateAssesPoints() {
		drivingVibrationWorkPaceLevel(DRIVING_POINT);
		drivingVibrationWorkPaceLevel(VIBRATION_POINT);
		drivingVibrationWorkPaceLevel(WORK_PACE_POINT);
		stressLevel(STRESS_POINT);
	}

	// <<COMPONENT CHANGE>>
	private void backShoulderWristLevel(int index, boolean back) {
		int value = (int) (formattedFields[indexToFormattedFieldArr(index)]
				.getValue());
		if (((back && 8 <= value) || (!back && 10 <= value)) && value <= 20)
			fields[indexToFieldArr(index + 1)].setText("Low");
		else if (21 <= value && value <= 30)
			fields[indexToFieldArr(index + 1)].setText("Moderate");
		else if (31 <= value && value <= 40)
			fields[indexToFieldArr(index + 1)].setText("High");
		else if (41 <= value && value <= 56)
			fields[indexToFieldArr(index + 1)].setText("Very High");
		else
			fields[indexToFieldArr(index + 1)].setText("!!!Not in Ranges!!!");
	}

	// <<COMPONENT CHANGE>>
	private void neckLevel(int index) {
		int value = (int) (formattedFields[indexToFormattedFieldArr(index)]
				.getValue());
		if (4 <= value && value <= 6)
			fields[indexToFieldArr(index + 1)].setText("Low");
		else if (8 <= value && value <= 10)
			fields[indexToFieldArr(index + 1)].setText("Moderate");
		else if (12 <= value && value <= 14)
			fields[indexToFieldArr(index + 1)].setText("High");
		else if (16 <= value && value <= 18)
			fields[indexToFieldArr(index + 1)].setText("Very High");
		else
			fields[indexToFieldArr(index + 1)].setText("!!!Not in Ranges!!!");
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private void drivingVibrationWorkPaceLevel(int index) {
		switch (combos.elementAt(indexToComboArr(index + 1)).getSelectedIndex()) {
		case 0:
			formattedFields[indexToFormattedFieldArr(index)].setValue(1);
			break;
		case 1:
			formattedFields[indexToFormattedFieldArr(index)].setValue(4);
			break;
		case 2:
			formattedFields[indexToFormattedFieldArr(index)].setValue(9);
			break;
		default:
			formattedFields[indexToFormattedFieldArr(index)].setValue(0);
		}
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private void stressLevel(int index) {
		switch (combos.elementAt(indexToComboArr(index + 1)).getSelectedIndex()) {
		case 0:
			formattedFields[indexToFormattedFieldArr(index)].setValue(1);
			break;
		case 1:
			formattedFields[indexToFormattedFieldArr(index)].setValue(4);
			break;
		case 2:
			formattedFields[indexToFormattedFieldArr(index)].setValue(9);
			break;
		case 3:
			formattedFields[indexToFormattedFieldArr(index)].setValue(16);
			break;
		default:
			formattedFields[indexToFormattedFieldArr(index)].setValue(0);
		}
	}

	// <<DATA CHANGE | COMPONENT CHANGE>>
	private void qecLevel(int index) {
		int value = (int) (formattedFields[indexToFormattedFieldArr(index)]
				.getValue());
		if (value <= 40)
			fields[indexToFieldArr(index + 1)].setText("Low");
		else if (41 <= value && value <= 50)
			fields[indexToFieldArr(index + 1)].setText("Moderate");
		else if (51 <= value && value <= 70)
			fields[indexToFieldArr(index + 1)].setText("High");
		else if (71 <= value)
			fields[indexToFieldArr(index + 1)].setText("Very High");
		else
			fields[indexToFieldArr(index + 1)].setText("!!!Not in Ranges!!!");
	}

}