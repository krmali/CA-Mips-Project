package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class Assembler {

	FileInputStream fis;
	FileWriter fw;
	BufferedWriter bw;
	File input_file;
	File first_pass_file;

	public Assembler() {

//		input_file = new File("/Users/kareemali/Desktop/input.txt");
//		try {
//			fis = new FileInputStream(input_file);
//		} catch (FileNotFoundException e) {
//			// e.printStackTrace();
//			System.out.println("error in finding file");
//
//		}
//
//		first_pass_file = new File(
//				"/Users/kareemali/Desktop/first_pass_file.txt");
//		try {
//			if (first_pass_file.createNewFile()) {
//				System.out.println("File is created!");
//			} else {
//				System.out.println("File already exists.");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			fw = new FileWriter(first_pass_file.getAbsoluteFile());
//			bw = new BufferedWriter(fw);
//			bw.write("hoppa");
//			bw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public static void main(String[] args) {

		Assembler assemb = new Assembler();

		String[] temp = assemb.assemble(new File(
				"/Users/kareemali/Desktop/test2.txt"));

		assemb.printAssembledInstruction(temp);

		System.out.println(countNumberOfLines(new File(
				"/Users/kareemali/Desktop/test2.txt")));

	}

	public void first_pass() {

	}

	public String[] assemble(File file) {
		String[] result = new String[countNumberOfLines(file)];
		int instructionNumber = 0;
		try {
			FileInputStream fis = new FileInputStream(file);
			int content;
			String[] instruction = { "", "", "", "" };
			int separator = 0;
			while ((content = fis.read()) != -1) {
				// System.out.println(content);
				if (content != 10) {
					if (separator == 0 && content == 35) {
						separator++;
					}
					if (separator == 0 && content == 36) {
						separator++;
						instruction[separator] += "$";

					} else if (content == 44
							&& (separator == 1 || separator == 2)) {
						separator++;
					} else {
						instruction[separator] += Character.toChars(content)[0];
					}
				} else {
					System.out.println(instruction[0] + ":" + instruction[1]
							+ ":" + instruction[2] + ":" + instruction[3]);
					result[instructionNumber] = instructionAssemble(instruction);
					instruction[0] = "";
					instruction[1] = "";
					instruction[2] = "";
					instruction[3] = "";
					separator = 0;
					instructionNumber++;
				}
			}
		} catch (Exception e) {

			System.out.println("Error occur when trying to read from file.");
			e.printStackTrace();
		}
		return result;
	}

	public String instructionAssemble(String[] instruction) {
		String result = "";
		if (instruction[0].equals("add")) {
			String[] op = translateOp("add");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];
		}
		if (instruction[0].equals("addi")) {
			String[] op = translateOp("addi");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(instruction[3]);
		}
		if (instruction[0].equals("sub")) {
			String[] op = translateOp("sub");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];
		}
		if (instruction[0].equals("lw")) {
			String[] op = translateOp("lw");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);
		}
		if (instruction[0].equals("lh")) {
			String[] op = translateOp("lh");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);

		}
		if (instruction[0].equals("lhu")) {
			String[] op = translateOp("lhu");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);

		}
		if (instruction[0].equals("lb")) {
			String[] op = translateOp("lb");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);
		}
		if (instruction[0].equals("lbu")) {
			String[] op = translateOp("lbu");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);
		}
		if (instruction[0].equals("sw")) {
			String[] op = translateOp("sw");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);
		}
		if (instruction[0].equals("sh")) {
			String[] op = translateOp("sh");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);
		}
		if (instruction[0].equals("sb")) {
			String[] op = translateOp("sb");
			String temp = instruction[2];
			String[] tempArray = temp.split("\\(");
			temp = tempArray[tempArray.length - 1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length() - 1);
			result = op[0] + translateRegister(temp)
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(tempNum);
		}
		if (instruction[0].equals("lui")) {
			// result[0] = "001111";//after XXXXX
			String[] op = translateOp("lui");
			result = op[0] + "XXXXX" + translateRegister(instruction[1])
					+ translateIFormatNumber(instruction[2]);

		}
		if (instruction[0].equals("sll")) {
			// result[0] = "000000";
			// result[1] = "000000";
			String[] op = translateOp("sll");
			result = op[0] + "XXXXX" + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateRFormatNumber(instruction[3]) + op[1];

		}
		if (instruction[0].equals("srl")) {
			String[] op = translateOp("srl");
			result = op[0] + "XXXXX" + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateRFormatNumber(instruction[3]) + op[1];
		}
		if (instruction[0].equals("and")) {
			String[] op = translateOp("and");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];
		}
		if (instruction[0].equals("andi")) {
			String[] op = translateOp("andi");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(instruction[3]);
		}
		if (instruction[0].equals("or")) {
			String[] op = translateOp("or");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];
		}
		if (instruction[0].equals("ori")) {
			String[] op = translateOp("ori");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(instruction[3]);
		}
		if (instruction[0].equals("nor")) {
			String[] op = translateOp("nor");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];
		}
		if (instruction[0].equals("beq")) {
			String[] op = translateOp("beq");
			result = op[0] + translateRegister(instruction[1])
					+ translateRegister(instruction[2])
					+ translateIFormatNumber(instruction[3]);
		}
		if (instruction[0].equals("bne")) {
			String[] op = translateOp("bne");
			result = op[0] + translateRegister(instruction[1])
					+ translateRegister(instruction[2])
					+ translateIFormatNumber(instruction[3]);
		}
		if (instruction[0].equals("j")) {
			String[] op = translateOp("j");
			result = op[0] + translateJFormatNumber(instruction[1]);
		}
		if (instruction[0].equals("jal")) {
			String[] op = translateOp("jal");
			result = op[0] + translateJFormatNumber(instruction[1]);
		}
		if (instruction[0].equals("jr")) {
			String[] op = translateOp("jr");
			result = op[0] + translateRegister(instruction[1])
					+ "000000000000000" + op[1];
		}
		if (instruction[0].equals("slt")) {
			String[] op = translateOp("slt");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];
		}
		if (instruction[0].equals("slti")) {
			String[] op = translateOp("slti");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(instruction[3]);
		}
		if (instruction[0].equals("sltu")) {
			String[] op = translateOp("sltu");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[3])
					+ translateRegister(instruction[1]) + "00000" + op[1];

		}
		if (instruction[0].equals("sltui")) {
			String[] op = translateOp("sltui");
			result = op[0] + translateRegister(instruction[2])
					+ translateRegister(instruction[1])
					+ translateIFormatNumber(instruction[3]);

		}

		return result;

	}

	public void printAssembledInstruction(String[] instructions) {
		System.out.println("================================");
		System.out.println("Assembled file:");
		for (int i = 0; i < instructions.length; i++) {
			System.out.println(instructions[i]);
		}
		System.out.println("================================");
	}

	public String translateRegister(String instructionPart) {
		if (instructionPart.equals("$a0")) {
			return "00100";
		}
		if (instructionPart.equals("$a1")) {
			return "00101";
		}
		if (instructionPart.equals("$a2")) {
			return "00110";
		}
		if (instructionPart.equals("$a3")) {
			return "00111";
		}
		if (instructionPart.equals("$v0")) {
			return "00010";
		}
		if (instructionPart.equals("$v1")) {
			return "00011";
		}
		if (instructionPart.equals("$t0")) {
			return "01000";
		}
		if (instructionPart.equals("$t1")) {
			return "01001";
		}
		if (instructionPart.equals("$t2")) {
			return "01010";
		}
		if (instructionPart.equals("$t3")) {
			return "01011";
		}
		if (instructionPart.equals("$t4")) {
			return "01100";
		}
		if (instructionPart.equals("$t5")) {
			return "01101";
		}
		if (instructionPart.equals("$t6")) {
			return "01110";
		}
		if (instructionPart.equals("$t7")) {
			return "01111";
		}
		if (instructionPart.equals("$t8")) {
			return "11000";
		}
		if (instructionPart.equals("$t9")) {
			return "11001";
		}
		if (instructionPart.equals("$s0")) {
			return "10000";
		}
		if (instructionPart.equals("$s1")) {
			return "10001";
		}
		if (instructionPart.equals("$s2")) {
			return "10010";
		}
		if (instructionPart.equals("$s3")) {
			return "10011";
		}
		if (instructionPart.equals("$s4")) {
			return "10100";
		}
		if (instructionPart.equals("$s5")) {
			return "10101";
		}
		if (instructionPart.equals("$s6")) {
			return "10110";
		}
		if (instructionPart.equals("$s7")) {
			return "10111";
		}
		if (instructionPart.equals("$gp")) {
			return "11100";
		}
		if (instructionPart.equals("$sp")) {
			return "11101";
		}
		if (instructionPart.equals("$fp")) {
			return "11110";
		}
		if (instructionPart.equals("$ra")) {
			return "11111";
		}
		if (instructionPart.equals("$0")) {
			return "00000";
		}
		if (instructionPart.equals("$at")) {
			return "00001";
		}
		if (instructionPart.equals("$k0")) {
			return "11010";
		}
		if (instructionPart.equals("$k1")) {
			return "11011";
		}
		return "";
	}

	public String[] translateOp(String instructionPart) {
		String[] result = { "XXXXXX", "XXXXXX" };
		if (instructionPart.equals("add")) {
			result[0] = "000000";
			result[1] = "100000";

		}
		if (instructionPart.equals("addi")) {
			result[0] = "001000";
		}
		if (instructionPart.equals("sub")) {
			result[0] = "000000";
			result[1] = "100010";
		}
		if (instructionPart.equals("lw")) {
			result[0] = "100011";
		}
		if (instructionPart.equals("lh")) {
			result[0] = "100001";
		}
		if (instructionPart.equals("lhu")) {
			result[0] = "100101";
		}
		if (instructionPart.equals("lb")) {
			result[0] = "100000";
		}
		if (instructionPart.equals("lbu")) {
			result[0] = "100100";
		}
		if (instructionPart.equals("sw")) {
			result[0] = "101011";
		}
		if (instructionPart.equals("sh")) {
			result[0] = "101001";
		}
		if (instructionPart.equals("sb")) {
			result[0] = "101000";
		}
		if (instructionPart.equals("lui")) {
			result[0] = "001111";// after XXXXX
		}
		if (instructionPart.equals("sll")) {
			result[0] = "000000";
			result[1] = "000000";
		}
		if (instructionPart.equals("srl")) {
			result[0] = "000000";// after XXXXX
			result[1] = "000010";
		}
		if (instructionPart.equals("and")) {
			result[0] = "000000";
			// 00000
			result[1] = "100100";
		}
		if (instructionPart.equals("andi")) {
			result[0] = "001100";
		}
		if (instructionPart.equals("or")) {
			result[0] = "000000";
			// 00000
			result[1] = "100101";
		}
		if (instructionPart.equals("ori")) {
			result[0] = "001101";
		}
		if (instructionPart.equals("nor")) {
			result[0] = "000000";
			// 00000
			result[1] = "100111";
		}
		if (instructionPart.equals("beq")) {
			result[0] = "000100";
		}
		if (instructionPart.equals("bne")) {
			result[0] = "000101";
		}
		if (instructionPart.equals("j")) {
			result[0] = "000010";
		}
		if (instructionPart.equals("jal")) {
			result[0] = "000011";
		}
		if (instructionPart.equals("jr")) {
			result[0] = "000000";
			// 000000000000000
			result[1] = "001000";
		}
		if (instructionPart.equals("slt")) {
			result[0] = "000000";
			// 00000
			result[1] = "101010";
		}
		if (instructionPart.equals("sltu")) {
			result[0] = "000000";
			result[1] = "101011";
		}
		if (instructionPart.equals("slti")) {
			result[0] = "001010";
		}
		if (instructionPart.equals("sltui")) {
			result[0] = "001011";
		}

		return result;
	}

	public String translateRFormatNumber(String num) {
		if (Integer.parseInt(num) > 31 || Integer.parseInt(num) < 0) {
			return "00000";
		}
		String resultString = Integer.toBinaryString(Integer.parseInt(num));
		while (resultString.length() < 5) {
			resultString = "0" + resultString;
		}
		return resultString;
	}

	public String translateIFormatNumber(String num) {
		int result = Integer.parseInt(num);
		if (result > (((int) Math.pow(2.0, 15.0)) - 1)
				|| result < (-1 * ((int) Math.pow(2.0, 15.0)))) {
			return "0000000000000000";
		} else {
			String resultString = Integer.toBinaryString(result);
			if (resultString.length() > 16) {
				return resultString.substring(16);
			} else {
				while (resultString.length() < 16) {
					resultString = "0" + resultString;
				}
				return resultString;
			}
		}
	}

	public String translateJFormatNumber(String num) {

		int result = Integer.parseInt(num.substring(1));
		String resultString = Integer.toBinaryString(result);
		if (resultString.length() > 26) {
			resultString = resultString.substring(6);
		}
		while (resultString.length() < 26) {
			resultString = "0" + resultString;
		}
		return resultString;
	}

	public static int countNumberOfLines(File file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		LineNumberReader lnr = new LineNumberReader(fr);

		int linesCount = 0;
		try {
			while (lnr.readLine() != null) {
				linesCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linesCount;
	}

	public static void linker(File file) {

	}
}