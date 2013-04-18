package pack;

import java.util.HashMap;

public class ALUControl {
	
	String ALUOp;
	String AluControlOutput;
	HashMap <String, String> possibleInputs;
	
	public ALUControl(){
		possibleInputs = new HashMap<String,String>();
	}
	
	public void ALUIControlInput(String ALUOp) {
		this.ALUOp = ALUOp;
	}
	
	public void process (){
		possibleInputs.put("0000", "and");
		possibleInputs.put("0001","or");
		possibleInputs.put("0010", "add");
		possibleInputs.put("0110","subtract");
		possibleInputs.put("0111", "slt");
		possibleInputs.put("1100","nor");	
	}
	
	public void ALUControlOutput(){
		AluControlOutput = possibleInputs.get(ALUOp);
	}

}
