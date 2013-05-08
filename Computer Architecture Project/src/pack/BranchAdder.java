package pack;

import java.math.BigInteger;

public class BranchAdder {
	String jumpInput;
	String incrementedAddress;

	public BranchAdder() {
		this.jumpInput = MIPSWires.jumpAdrs;
		branch();
		printMIPSWires();
	}

	public void branch(){
		incrementedAddress = MIPSWires.pcOut;
		String temp = MIPSWires.toSignExtend;
		temp = temp.substring(2)+"00";
		String result = addBinaryString(incrementedAddress, temp);
		boolean branchAnd = (MIPSWires.zero == "1" && MIPSWires.Branch == "1")? true : false;
		MIPSWires.pcIn = branchAnd? result : incrementedAddress ;
		processJump();
	}
	
	public String shiftBranchOffset(String offset){
		String result = "";
		if(offset.charAt(0) == '1'){
			for(int i=0 ; i < 14;i++){
				result = "1"+result;
			}
			result = result + "00";
		}
		else{
			for(int j = 0;j<16;j++){
				result = "0" +result;
			}
		}
		return result;
	}

	public String addBinaryString(String s1, String s2) {
		return ALU.adding(s1, s2);
	}


	public void processJump() {
		if(MIPSWires.Jump.equals("1")){
			jumpInput = MIPSWires.pcOut.substring(0, 4) + jumpInput + "00";
			MIPSWires.pcIn = MIPSWires.Jump == "0" ? MIPSWires.pcIn : jumpInput;
		}
	}

	
	public void printMIPSWires(){
		System.out.println("===========================BRANCH and JUMP========================");
		System.out.println("jump Address: "+MIPSWires.jumpAdrs);
		System.out.println("incremented PC: "+incrementedAddress);
		System.out.println("PC for the next instruction: "+MIPSWires.pcIn);
		System.out.println("==================================================================");
	}
	
}