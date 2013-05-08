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
	}

	public static void startSimulation() {
		InstructionMemory im = new InstructionMemory(32);
		MIPSWires.pcIn = "00000000000000000000";
		im.write_inst_mem("00001000000000000000000000001010", 0);
		im.processInstructionMemory();
		Control c = new Control();
		Registers rg = new Registers();
		intializeRegisterValues(rg);
		rg.read();
		ALUControl aluc = new ALUControl();
		ALU alu = new ALU();
		BranchAdder branch = new BranchAdder();
		Memory m = new Memory(32);
		m.processMemory();
		rg.write();
	}

	public static void main(String[] args) {
		System.out
				.println("*************************************************MIPS SIMULATION*************************************************");
		startSimulation();
		//System.out.println(ALU.adding("00000000000000000000000000000000","01"));
	}

}
