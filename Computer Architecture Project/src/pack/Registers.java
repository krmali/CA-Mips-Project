package pack;

public class Registers {
	private SingleRegister[] reg;

	public Registers() {
		reg[0].setName("zero");
		reg[1].setName("at");
		reg[2].setName("v0");
		reg[3].setName("v1");
		reg[4].setName("a0");
		reg[5].setName("a1");
		reg[6].setName("a2");
		reg[7].setName("a3");
		reg[8].setName("t0");
		reg[9].setName("t1");
		reg[10].setName("t2");
		reg[11].setName("t3");
		reg[12].setName("t4");
		reg[13].setName("t5");
		reg[14].setName("t6");
		reg[15].setName("t7");
		reg[16].setName("s0");
		reg[17].setName("s1");
		reg[18].setName("s2");
		reg[19].setName("s3");
		reg[20].setName("s4");
		reg[21].setName("s5");
		reg[22].setName("s6");
		reg[23].setName("s7");
		reg[24].setName("t8");
		reg[25].setName("t9");
		reg[26].setName("k0");
		reg[27].setName("gp");
		reg[28].setName("sp");
		reg[29].setName("fp");
		reg[30].setName("ra");
		for (int i = 0; i < reg.length; i++) {
			reg[i].setContent("00000000000000000000000000000000");
		}
	}

	public static int integerValueOf(String binaryString) {
		if (binaryString.charAt(0) == '1') {
			String result = binaryString;
			result = result.replace('0', '2');
			result = result.replace('1', '0');
			result = result.replace('2', '1');
			int num = Integer.parseInt(result, 2) + 1;
			return -num;
		} else {
			return Integer.parseInt(binaryString, 2);
		}
	}

	public void writeByNumber(String regNum, String content) {
		int regNumber = integerValueOf(regNum);
		reg[regNumber].setContent(content);
	}

	public void writeByName(String regName, String content) {
		for (int i = 0; i < reg.length; i++) {
			if (reg[i].getName().equals(regName)) {
				reg[i].setContent(content);
				break;
			}
		}
	}

	public String readByNumber(String regNum) {
		int regNumber = integerValueOf(regNum);
		return reg[regNumber].getContent();
	}

	public String readByName(String regName) {
		int i;
		for (i = 0; i < reg.length; i++) {
			if (reg[i].getName().equals(regName)) {
				break;
			}
		}
		return reg[i].getContent();
	}

	public int getRegNumber(String regName) {
		int i;
		for (i = 0; i < reg.length; i++) {
			if (reg[i].getName().equals(regName)) {
				break;
			}
		}
		return i;
	}

	public String getRegName(String regNum) {
		int regNumber = integerValueOf(regNum);
		return reg[regNumber].getName();
	}
}
