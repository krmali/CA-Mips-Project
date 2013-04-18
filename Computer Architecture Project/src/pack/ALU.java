package pack;

public class ALU {

	String input1;
	String input2;
	String aluControlInput;
	String aluOutput;
	String zeroOutput;

	public void ALUInput(String input1, String input2, String aluControlInput) {

	}

	public void process() {
		if (aluControlInput == "and") {
			and(input1, input2);
		} else if (aluControlInput == "or") {
			or(input1, input2);
		} else if (aluControlInput == "add") {
			add(input1, input2);
		} else if (aluControlInput == "subtract") {
			subtract(input1, input2);
		} else if (aluControlInput == "slt") {
			setOnLessThan(input1, input2);
		} else if (aluControlInput == "nor") {
			nor(input1, input2);
		}
	}

	private void and(String input1, String input2) {
		int num1 = Integer.parseInt(input1, 2);
		int num2 = Integer.parseInt(input2, 2);
		String result = Integer.toBinaryString(num1 & num2);
		while(result.length()<32){
			result = "0"+result;
		}
		aluOutput = result;
	}

	private void or(String input1, String input2) {
		int num1 = Integer.parseInt(input1, 2);
		int num2 = Integer.parseInt(input2, 2);
		String result = Integer.toBinaryString(num1 | num2);
		while(result.length()<32){
			result = "0"+result;
		}
		aluOutput = result;
	}
	
	private void add(String input1, String input2) {
		int num1 = Integer.parseInt(input1, 2);
		int num2 = Integer.parseInt(input2, 2);
		String result = Integer.toBinaryString(num1 + num2);
		while(result.length()<32){
			result = "0"+result;
		}
		aluOutput = result;
	}
	
	private void subtract(String input1, String input2) {
		int num1 = Integer.parseInt(input1, 2);
		int num2 = Integer.parseInt(input2, 2);
		String result = Integer.toBinaryString(num1 - num2);
		while(result.length()<32){
			result = "0"+result;
		}
		aluOutput = result;
	}
	
	private void setOnLessThan(String input1, String input2) {
		

	}

	private void nor(String input1, String input2) {
		

	}



	public static void main(String[] args) {
//		System.out.println(Integer.toBinaryString(~(Integer.parseInt("1000", 2)
//				| Integer.parseInt("0100", 2) )));
//		
		System.out.println(Integer.parseInt("011111111"));
		//String z= "hoppa";
		//System.out.println(z);
		//System.out.println(.repeat((10-z.length())));
		//StringBuffer x= new StringBuffer("adxfd"); 
	}

}
