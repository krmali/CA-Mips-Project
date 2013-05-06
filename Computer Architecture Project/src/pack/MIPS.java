package pack;

public class MIPS {

	// int x= 0b010011;

	public String mux(String in1, String in2, String selector) {
		return (selector == "0") ? in1 : in2;
	}

	public String shiftLeftTwo(String bin) {
		return bin.substring(2) + "00";
	}

	public String signExtend(String bin) {
		return (bin.charAt(0) == '0') ? "0000000000000000" + bin
				: "1111111111111111" + bin;
	}

	public static void main(String[] args) {
		System.out.println("sdlkfxhadn");
	}

}
