package pack;

import java.math.BigInteger;

public class BranchAdder {
	String jumpInput;

	public BranchAdder() {
		this.jumpInput = MIPSWires.jumpAdrs;
	}

	public void branch(){
		String temp = MIPSWires.toShift2.substring(2);
		temp += "00";
		String result = addBinaryString(MIPSWires.pcOut, temp);
		
		  
	}

	public String addBinaryString(String s1, String s2) {
		return Integer.toBinaryString(Integer.parseInt(((new BigInteger(s1, 2))
				.add(new BigInteger(s2, 2))).toString()));
	}

	public void addBinaryString() {

	}

	public void processJump() {
		jumpInput = MIPSWires.pcOut.substring(0, 4) + jumpInput + "00";
		MIPSWires.pcOut = MIPSWires.Jump == "0" ? MIPSWires.pcIn : jumpInput;
	}

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(Integer
				.parseInt(((new BigInteger("001001", 2)).add(new BigInteger(
						"101001", 2))).toString())));

	}

}
