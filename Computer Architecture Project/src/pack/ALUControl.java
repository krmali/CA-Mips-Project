package pack;

import java.util.HashMap;

public class ALUControl {

	String ALUOp;
	String AluControlOutput;
	HashMap<String, String> possibleInputs;

	public ALUControl() {
		ALUOp = MIPSWires.ALUOp;
		possibleInputs = new HashMap<String, String>();
		ALUIControlInput(ALUOp);
		process();
		ALUControlOutput();
	}

	public void ALUIControlInput(String ALUOp) {
		this.ALUOp = ALUOp;
	}

	public void process() {
		possibleInputs.put("0000", "and");
		possibleInputs.put("0001", "or");
		possibleInputs.put("0010", "add");
		possibleInputs.put("0110", "subtract");
		possibleInputs.put("0111", "slt");
		possibleInputs.put("1100", "nor");
	}

	public void ALUControlOutput() {
		if(ALUOp.equals("00")){
			MIPSWires.AluControlOutput = possibleInputs.get("0010");
		}
		else if (ALUOp.equals("01")){
			MIPSWires.AluControlOutput = possibleInputs.get("0110");
		}
		else if (ALUOp.equals("10")){
			String temp = MIPSWires.toSignExtend.substring(10);
			if(MIPSWires.control.equals("001100")){
				MIPSWires.AluControlOutput = "and";
				return;
			}
			if(MIPSWires.control.equals("001101")){
				MIPSWires.AluControlOutput = "or";
				return;
			}
			if(temp.equals("100000")){
				MIPSWires.AluControlOutput = possibleInputs.get("0010");
			}
			else if(temp.equals("100010")){
				MIPSWires.AluControlOutput = possibleInputs.get("0110");
			}
			else if (temp.equals("100100")) {
				MIPSWires.AluControlOutput = possibleInputs.get("0000");
			}
			else if (temp.equals("100101")) {
				MIPSWires.AluControlOutput = possibleInputs.get("0001");
			}
			else if (temp.equals("101010")) {
				MIPSWires.AluControlOutput = possibleInputs.get("0111");
			}
			else if(temp.equals("100111")){
				MIPSWires.AluControlOutput = possibleInputs.get("1100");
			}
			else if(temp.equals("000000")){
				MIPSWires.AluControlOutput = "sll";
			}
			else if (temp.equals("000010")) {
				MIPSWires.AluControlOutput = "srl";
			}
		}
		printMIPSWires();
	}
	
	public void printMIPSWires(){
		System.out.println("\n========================ALUCONTROL=============================");
		System.out.println("ALUOp: "+ this.ALUOp);
		System.out.println("Function: "+ MIPSWires.toSignExtend.substring(10));
		System.out.println("ALU control signal to ALU: "+ MIPSWires.AluControlOutput);
		System.out.println("=================================================================\n");
	}
	

}
