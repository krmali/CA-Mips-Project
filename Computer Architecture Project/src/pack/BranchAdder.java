package pack;

import java.math.BigInteger;

public class BranchAdder {
	String jumpInput;

	public BranchAdder() {
		this.jumpInput = MIPSWires.jumpAdrs;
	}

	public void branch(){
		processJump();
		String temp = MIPSWires.toShift2.substring(2);
		temp += "00";
		String result = addBinaryString(MIPSWires.pcOut, temp);
		boolean branchAnd = (MIPSWires.zero == "1" && MIPSWires.Branch == "1")? true : false;
		MIPSWires.pcOut = branchAnd? result : MIPSWires.pcOut ;
	}

	public String addBinaryString(String s1, String s2) {
		return Integer.toBinaryString(Integer.parseInt(((new BigInteger(s1, 2))
				.add(new BigInteger(s2, 2))).toString()));
	}


	public void processJump() {
		jumpInput = MIPSWires.pcOut.substring(0, 4) + jumpInput + "00";
		MIPSWires.pcIn = MIPSWires.Jump == "0" ? MIPSWires.pcOut : jumpInput;
	}

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(Integer
				.parseInt(((new BigInteger("001001", 2)).add(new BigInteger(
						"101001", 2))).toString())));

	}

}
