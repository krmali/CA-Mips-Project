package pack;

public class Registers {
	SingleRegister[] reg = new SingleRegister[31];

	public Registers() {
		for (int j = 0; j < reg.length; j++) {
			reg[j] = new SingleRegister();
		}
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
			return Integer.parseInt(binaryString, 2);
	}

	public void write() {
		if (MIPSWires.RegWrite.equals("1")) {
			int writeRegNum;
			if (MIPSWires.RegDst.equals("0")) {
				writeRegNum = integerValueOf(MIPSWires.rReg2);
			} else {
				writeRegNum = integerValueOf(MIPSWires.wReg);
			}
			reg[writeRegNum].setContent(MIPSWires.regWriteData);
		}
		printMIPSWires();
	}

	public void read() {
		MIPSWires.rd2 = reg[integerValueOf(MIPSWires.rReg2)].getContent();
		if (!MIPSWires.rReg1.equals("XXXXX")) {
			MIPSWires.aluIn1 = reg[integerValueOf(MIPSWires.rReg1)]
					.getContent();
		} else {
			MIPSWires.aluIn1 = reg[integerValueOf(MIPSWires.rReg2)]
					.getContent();
		}
		if (MIPSWires.ALUsrc.equals("0") && !(MIPSWires.toSignExtend.substring(10).equals("000000") || MIPSWires.toSignExtend
				.substring(10).equals("000010"))) {
			MIPSWires.aluIn2 = reg[integerValueOf(MIPSWires.rReg2)]
					.getContent();
		} else if ((MIPSWires.toSignExtend.substring(10).equals("000000") || MIPSWires.toSignExtend
				.substring(10).equals("000010"))
				&& MIPSWires.control.equals("000000")) {
			MIPSWires.toShift2 = MIPS.signExtend(MIPSWires.toSignExtend);
			MIPSWires.aluIn2 = MIPSWires.toShift2;
		}

		else {
			MIPSWires.toShift2 = MIPS.signExtend(MIPSWires.toSignExtend);
			MIPSWires.aluIn2 = MIPSWires.toShift2;
		}
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

	public void printMIPSWires() {
		System.out
				.println("============================RegisterFile=====================");
		System.out.println("alu input 1: " + MIPSWires.aluIn1);
		System.out.println("alu input 2: " + MIPSWires.aluIn2);
		System.out.println("memory write data: " + MIPSWires.rd2);
		System.out
				.println("=================================Registers contents after write==============");
		for (int i = 0; i < reg.length; i++) {
			System.out.print(reg[i].getName() + " : " + reg[i].getContent()
					+ "\t");
			if (i % 3 == 0) {
				System.out.println();
			}
		}
		System.out
				.println("=============================================================================");
	}

	public String getRegName(String regNum) {
		int regNumber = integerValueOf(regNum);
		return reg[regNumber].getName();
	}
}
