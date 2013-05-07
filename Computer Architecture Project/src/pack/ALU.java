package pack;

public class ALU {

	String input1;
	String input2;
	String aluControlInput;
	
	public ALU(){
		ALUInput();
		process();
		printMIPSWires();
	}

	public void ALUInput() {
		this.input1 = MIPSWires.aluIn1;
		this.input2 = MIPSWires.aluIn2; 
		this.aluControlInput = MIPSWires.AluControlOutput;
	}

	public void process() {
		if (aluControlInput.equals("and")) {
			and(input1, input2);
		} else if (aluControlInput.equals("or")) {
			or(input1, input2);
		} else if (aluControlInput.equals("add")) {
			add(input1, input2);
		} else if (aluControlInput.equals("subtract")) {
			subtract(input1, input2);
		} else if (aluControlInput.equals("slt")) {
			setOnLessThan(input1, input2);
		} else if (aluControlInput.equals("nor")) {
			nor(input1, input2);
		}
	}

	private void and(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		String result = Integer.toBinaryString(num1 & num2);
		while (result.length() < 32) {
			result = "0" + result;
		}
		MIPSWires.aluOut = result;
	}

	private void or(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		String result = Integer.toBinaryString(num1 | num2);
		while (result.length() < 32) {
			result = "0" + result;
		}
		MIPSWires.aluOut = result;
	}

	private void add(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		String result = Integer.toBinaryString(num1 + num2);
		while (result.length() < 32) {
			result = "0" + result;
		}
		MIPSWires.aluOut = result;
	}
	
	public static String adding(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		String result = Integer.toBinaryString(num1 + num2);
		while (result.length() < 32) {
			result = "0" + result;
		}
		return result;
	}


	private void subtract(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		String result = Integer.toBinaryString(num1 - num2);
		while (result.length() < 32) {
			result = "0" + result;
		}
		MIPSWires.aluOut = result;
	}

	private void setOnLessThan(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		if (num1 < num2) {
			MIPSWires.zero = "1";
		} else {
			MIPSWires.zero = "0";

		}
	}

	private void nor(String input1, String input2) {
		int num1 = integerValueOf(input1);
		int num2 = integerValueOf(input2);
		String result = Integer.toBinaryString(~(num1 | num2));
		while (result.length() < 32) {
			result = "0" + result;
		}
		MIPSWires.aluOut = result;
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
	
	public void printMIPSWires(){
		System.out.println("================================ALU==============================");
		System.out.println("ALU input1 : "+ MIPSWires.aluIn1);
		System.out.println("ALU input2 : "+ MIPSWires.aluIn2);
		System.out.println("ALU control signal : "+ MIPSWires.AluControlOutput);
		System.out.println("ALU zero output : "+ MIPSWires.zero);
		System.out.println("ALU output : "+ MIPSWires.aluOut);
		System.out.println("====================================================================\n");
		
	}


}
