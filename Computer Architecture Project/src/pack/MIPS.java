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
	
	public static void intializeRegisterValues(Registers rg){
		String temp = "00000000000000000000000000000000";
		for(int i=0;i<rg.reg.length;i++){
			rg.reg[i].setContent(temp);
			temp = ALU.adding(temp, "01");
		}
		rg.reg[29].setContent("00000000000000000000000000000100");
	}
	
	public static void intializeMemoryValues(Memory memory){
		String temp = "00000000";
		for(int i=0;i<memory.memory.length;i++){
			if (temp.length() == 32)
				temp = temp.substring(24);
			memory.memory[i] = temp;
			temp = ALU.adding(temp, "01");
		}
		memory.memory[14] = "11111111";
	}

	public static void startSimulation() throws NumberFormatException, Exception {
		InstructionMemory im = new InstructionMemory(32);
		MIPSWires.pcIn = "00000000000000000000";
		im.write_inst_mem("10100001010010010000000000000100", 0);
		im.processInstructionMemory();
		Control c = new Control();
		Registers rg = new Registers();
		intializeRegisterValues(rg);
		rg.read();
		ALUControl aluc = new ALUControl();
		ALU alu = new ALU();
		Memory m = new Memory(32);
		intializeMemoryValues(m);
		m.processMemory();
		rg.write();
	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		System.out
				.println("*************************************************MIPS SIMULATION*************************************************");
		startSimulation();
		//System.out.println(ALU.adding("00000000000000000000000000000000","01"));
	}

}
