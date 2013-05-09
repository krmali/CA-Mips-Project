package pack;

public class MIPS {

	public String mux(String in1, String in2, String selector) {
		return (selector == "0") ? in1 : in2;
	}

	public String shiftLeftTwo(String bin) {
		return bin.substring(2) + "00";
	}

	public static String signExtend(String bin) {
		return (bin.charAt(0) == '0') ? "0000000000000000" + bin
				: "1111111111111111" + bin;
	}

	public static void intializeRegisterValues(Registers rg) {
		String temp = "00000000000000000000000000000000";
		for (int i = 0; i < rg.reg.length; i++) {
			rg.reg[i].setContent(temp);
			temp = ALU.adding(temp, "01");
		}
		rg.reg[29].setContent("00000000000000000000000000000100");
	}

	public static void intializeMemoryValues(Memory memory) {
		String temp = "00000000";
		for (int i = 0; i < memory.memory.length; i++) {
			if (temp.length() == 32)
				temp = temp.substring(24);
			memory.memory[i] = temp;
			temp = ALU.adding(temp, "01");
		}
	}

	public static void startSimulation() throws NumberFormatException,
			Exception {
		InstructionMemory im = new InstructionMemory(32);
		MIPSWires.pcIn = "00000000000000000000";
		im.write_inst_mem("00010101011011000000000000001010", 0);
		im.processInstructionMemory();
		Control c = new Control();
		Registers rg = new Registers();
		intializeRegisterValues(rg);
		rg.read();
		ALUControl aluc = new ALUControl();
		ALU alu = new ALU();
		Memory m = new Memory(32);
		BranchAdder branch = new BranchAdder();
		intializeMemoryValues(m);
		m.processMemory();
		rg.write();
	}

	public static void startBulkSimulation(String[] instructions)
			throws NumberFormatException, Exception {
		InstructionMemory instMemory = new InstructionMemory(32);
		instMemory.write_inst_mem_in_order(instructions);
		//instMemory.printInstructionMemoryContents();
		MIPSWires.reset();
		Memory memory = new Memory(32);
		intializeMemoryValues(memory);
		Registers regs = new Registers();
		intializeRegisterValues(regs);
		int clk = 0;
		int x = 0;
		while (!instMemory.isEnd()) {
			x++;
			System.out.println("########################### " + "clock cycle: "
					+ clk + " ###########################");
			instMemory.processInstructionMemory();
			Control control = new Control();
			regs.read();
			ALUControl aluc = new ALUControl();
			ALU alu = new ALU();
			memory.processMemory();
			regs.write();
			BranchAdder branchAdder = new BranchAdder();
			clk++;
			//if(x>4)break;
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			Exception {
		System.out
				.println("*************************************************MIPS SIMULATION*************************************************");
		String[] instructions = {
				"00100000000010000000000000010000",
				"00000001000010010100100000100000",
				"00000001001010000101000000100010"};
		startBulkSimulation(instructions);
		
		//startSimulation();
	}

}
