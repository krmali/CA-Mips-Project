package pack;

public class ALU {

	String input1;
	String input2;
	String aluControlInput;

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

	// private static String add2(String input1, String input2) {
	// int num1 = integerValueOf(input1);
	// int num2 = integerValueOf(input2);
	// String result = Integer.toBinaryString(num1 + num2);
	// while(result.length()<32){
	// result = "0"+result;
	// }
	// return result;
	// }

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

	public static void main(String[] args) {
		// System.out.println(Integer.toBinaryString(~(Integer.parseInt("1000",
		// 2)
		// | Integer.parseInt("0100", 2) )));
		//
		// String x =
		// add2("10111100001111110000111111000011","01100111111111110000111111000011");
		// System.out.println(x);
		// System.out.println(Integer.parseInt("01111111111111111111111111111111",2));
		// System.out.println(Integer.numberOfLeadingZeros(-3));
		// System.out.println(Integer.toBinaryString(-1));
		// System.out.println("01111111111111111111111111111111".length());
		// //System.out.println(Integer.getInteger("11111111111111111111111111111111",
		// 2));
		// System.out.println(Integer.toBinaryString((Integer.decode("11113"))));
		// //System.out.println(Integer.toBinaryString(~8+1));
		// System.out.println(Integer.MAX_VALUE);
		// System.out.println("01001110".replace('0', '2').replace('1',
		// '0').replace('2', '1'));
		// System.out.println(Integer.parseInt("", arg1));
		// //String z= "hoppa";
		// System.out.println(z);
		// System.out.println(.repeat((10-z.length())));
		// StringBuffer x= new StringBuffer("adxfd");

		// System.out.println(integerValueOf("01111111111111111111111111111111"));
		// System.out.println(Integer.toBinaryString(8+-1));

		ALU alu = new ALU();
		//alu.ALUInput("11101110000011111000001111100000",
			//	"00001110111010011011101001101010", "add");
		alu.process();
		System.out
				.println("11111110000011111000001111100000\n00011110111010011011101001101010\n-----------");
		//System.out.println(alu.aluOutput + "\n" + alu.zeroOutput);

		System.out.println(integerValueOf("11101110000011111000001111100000")
				+ "\n" + integerValueOf("00001110111010011011101001101010")
				+ "\n" + integerValueOf("11111100111110010011111001001010"));
		// System.out.println("111110000011111000001111100000".length());

	}

}
