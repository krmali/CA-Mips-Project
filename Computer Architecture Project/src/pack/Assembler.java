package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

public class Assembler {

	FileInputStream fis;
	FileWriter fw;
	BufferedWriter bw;
	File input_file;
	File first_pass_file;

	public Assembler() {

		input_file = new File("/Users/kareemali/Desktop/input.txt");
		try {
			fis = new FileInputStream(input_file);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println("error in finding file");

		}

		first_pass_file = new File(
				"/Users/kareemali/Desktop/first_pass_file.txt");
		try {
			if (first_pass_file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter(first_pass_file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write("hoppa");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// File input= new File("/Users/kareemali/Desktop/test.txt");
		// FileInputStream fis=null;
		// try {
		// fis=new FileInputStream(input);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// System.out.println(fis.available());
		// int content;
		// while ((content = fis.read()) != -1) {
		// // convert to char and display it
		// System.out.print(content+",");
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		Assembler assemb = new Assembler();

		String[] temp =  assemb.assemble(new File("/Users/kareemali/Desktop/test.txt"));
		assemb.printAssembledInstruction(temp);
		


		//		System.out.println(assemb.translateRFormatNumber("32"));
//		System.out
//				.println(Integer.toBinaryString(((int) Math.pow(2.0, 15.0)) - 1));
//		// System.out.println(Integer.toBinaryString(-1*(((int)Math.pow(2.0,
//		// 16.0))1)));
//		System.out.println(Integer.toBinaryString(-1
//				* ((int) Math.pow(2.0, 15.0))));
//		System.out.println("kareem".substring(0, "kareem".length()-1));
//		System.out.println("25($sp)".split("\\(")[1]);

	}

	public void first_pass() {

	}

	public String[] assemble(File file) {
		String[] result = new String[20];
		int instructionNumber = 0;
		try {
			FileInputStream fis = new FileInputStream(file);
			int content;
			String[] instruction = { "", "", "", "" };
			int separator = 0;
			while ((content = fis.read()) != -1) {
				if (content != 10) {
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
					result[instructionNumber] = instructionAssemble(instruction);
					instruction[0] = "";
					instruction[1] = "";
					instruction[2] = "";
					instruction[3] = "";
					instructionNumber++;
				}
			}
		} catch (Exception e) {
			System.out.println("Error occur when trying to read from file.");
		}

		return result;
	}

	public String instructionAssemble(String[] instruction) {
		String result = "" ;
		if (instruction[0] == "add") {
			String [] op = translateOp("add");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[3]) +
					translateRegister(instruction[1]) +
					"00000" +
					op[1];
		}
		if (instruction[0] == "addi") {
			String [] op = translateOp("addi");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[1]) +
					translateIFormatNumber(instruction[3]);
		}
		if (instruction[0] == "sub") {
			String [] op = translateOp("sub");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[3]) +
					translateRegister(instruction[1]) +
					"00000" +
					op[1];
		}
		if (instruction[0] == "lw") {
			String [] op = translateOp("lw");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
		}
		if (instruction[0] == "lh") {
			String [] op = translateOp("lh");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
			
		}
		if (instruction[0] == "lhu") {
			String [] op = translateOp("lhu");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
			
		}
		if (instruction[0] == "lb") {
			String [] op = translateOp("lb");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
		}
		if (instruction[0] == "lbu") {
			String [] op = translateOp("lbu");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
		}
		if (instruction[0] == "sw") {
			String [] op = translateOp("lb");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
		}
		if (instruction[0] == "sh") {
			String [] op = translateOp("sh");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
		}
		if (instruction[0] == "sb") {
			String [] op = translateOp("sb");
			String temp = instruction[2];
			String[] tempArray = temp.split("(");
			temp = tempArray[tempArray.length-1];
			String tempNum = tempArray[0];
			temp = temp.substring(0, temp.length()-1); 
			result = op[0] + 
					translateRegister(temp) +
					translateRegister(instruction[1])+
					translateIFormatNumber(tempNum);
		}
		if (instruction[0] == "lui") {
			//result[0] = "001111";//after XXXXX
			String [] op = translateOp("lui");
			result = op[0] + 
					"XXXXX" +
					translateRegister(instruction[1])+
					translateIFormatNumber(instruction[2]);
			
		}
		if (instruction[0] == "sll") {
			//result[0] = "000000";
			//result[1] = "000000";
			String [] op = translateOp("sll");
			result = op[0] + 
					"XXXXX" +
					translateRegister(instruction[2]) +
					translateRegister(instruction[1]) +
					translateRFormatNumber(instruction[3]) +
					op[1];
			
		}
		if (instruction[0] == "srl") {
			String [] op = translateOp("srl");
			result = op[0] + 
					"XXXXX" +
					translateRegister(instruction[2]) +
					translateRegister(instruction[1]) +
					translateRFormatNumber(instruction[3]) +
					op[1];
		}
		if (instruction[0] == "and") {
			String [] op = translateOp("and");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[3]) +
					translateRegister(instruction[1]) +
					"00000" +
					op[1];
		}
		if (instruction[0] == "andi") {
			String [] op = translateOp("andi");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[1]) +
					translateIFormatNumber(instruction[3]);
		}
		if (instruction[0] == "or") {
			String [] op = translateOp("or");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[3]) +
					translateRegister(instruction[1]) +
					"00000" +
					op[1];
		}
		if (instruction[0] == "ori") {
			String [] op = translateOp("andi");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[1]) +
					translateIFormatNumber(instruction[3]);
		}
		if (instruction[0] == "nor") {
			String [] op = translateOp("nor");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[3]) +
					translateRegister(instruction[1]) +
					"00000" +
					op[1];
		}
		if (instruction[0] == "beq") {
			String [] op = translateOp("beq");
			result = op[0] + 
					translateRegister(instruction[1]) +
					translateRegister(instruction[2]) +
					translateIFormatNumber(instruction[3]);
		}
		if (instruction[0] == "bne") {
			String [] op = translateOp("bne");
			result = op[0] + 
					translateRegister(instruction[1]) +
					translateRegister(instruction[2]) +
					translateIFormatNumber(instruction[3]);
		}
		if (instruction[0] == "j") {
			String [] op = translateOp("j");
			result = op[0] + 
					translateJFormatNumber(instruction[1]);
		}
		if (instruction[0] == "jal") {
			String [] op = translateOp("jal");
			result = op[0] + 
					translateJFormatNumber(instruction[1]);
		}
		if (instruction[0] == "jr") {
			String [] op = translateOp("jr");
			result = op[0] + 
					translateRegister(instruction[1]) +
					"000000000000000" +
					op[1];
		}
		if (instruction[0] == "slt") {
			String [] op = translateOp("slt");
			result = op[0] + 
					translateRegister(instruction[2]) +
					translateRegister(instruction[3]) +
					translateIFormatNumber(instruction[1]) +
					"00000" +
					op[1];
		}
		return result;
		
	}
	
	public void  printAssembledInstruction(String [] instructions){
		for(int i = 0; i < instructions.length;i++){
			System.out.println(instructions[i]);
		}
	}

	public String translateRegister(String instructionPart) {
		if (instructionPart == "$a0") {
			return "00100";
		}
		if (instructionPart == "$a1") {
			return "00101";
		}
		if (instructionPart == "$a2") {
			return "00110";
		}
		if (instructionPart == "$a3") {
			return "00111";
		}
		if (instructionPart == "$v0") {
			return "00010";
		}
		if (instructionPart == "$v1") {
			return "00011";
		}
		if (instructionPart == "$t0") {
			return "01000";
		}
		if (instructionPart == "$t1") {
			return "01001";
		}
		if (instructionPart == "$t2") {
			return "01010";
		}
		if (instructionPart == "$t3") {
			return "01011";
		}
		if (instructionPart == "$t4") {
			return "01100";
		}
		if (instructionPart == "$t5") {
			return "01101";
		}
		if (instructionPart == "$t6") {
			return "01110";
		}
		if (instructionPart == "$t7") {
			return "01111";
		}
		if (instructionPart == "$t8") {
			return "11000";
		}
		if (instructionPart == "$t9") {
			return "11001";
		}
		if (instructionPart == "$so") {
			return "10000";
		}
		if (instructionPart == "$s1") {
			return "10001";
		}
		if (instructionPart == "$s2") {
			return "10010";
		}
		if (instructionPart == "$s3") {
			return "10011";
		}
		if (instructionPart == "$s4") {
			return "10100";
		}
		if (instructionPart == "$s5") {
			return "10101";
		}
		if (instructionPart == "$s6") {
			return "10110";
		}
		if (instructionPart == "$s7") {
			return "10111";
		}
		if (instructionPart == "$gp") {
			return "11100";
		}
		if (instructionPart == "$sp") {
			return "11101";
		}
		if (instructionPart == "$fp") {
			return "11110";
		}
		if (instructionPart == "$ra") {
			return "11111";
		}
		if (instructionPart == "$0") {
			return "00000";
		}
		if (instructionPart == "$at") {
			return "00001";
		}
		if (instructionPart == "$k0") {
			return "11010";
		}
		if (instructionPart == "$k1") {
			return "11011";
		}
		return "";
	}

	public String[] translateOp(String instructionPart) {
		String[] result = { "XXXXXX", "XXXXXX" };
		if (instructionPart == "add") {
			result[0] = "000000";
			result[1] = "100000";

		}
		if (instructionPart == "addi") {
			result[0] = "001000";
		}
		if (instructionPart == "sub") {
			result[0] = "000000";
			result[1] = "100010";
		}
		if (instructionPart == "lw") {
			result[0] = "100011";
		}
		if (instructionPart == "lh") {
			result[0] = "100001";
		}
		if (instructionPart == "lhu") {
			result[0] = "100101";
		}
		if (instructionPart == "lb") {
			result[0] = "100000";
		}
		if (instructionPart == "lbu") {
			result[0] = "100100";
		}
		if (instructionPart == "sw") {
			result[0] = "101011";
		}
		if (instructionPart == "sh") {
			result[0] = "101001";
		}
		if (instructionPart == "sb") {
			result[0] = "101000";
		}
		if (instructionPart == "lui") {
			result[0] = "001111";//after XXXXX
		}
		if (instructionPart == "sll") {
			result[0] = "000000";
			result[1] = "000000";
		}
		if (instructionPart == "srl") {
			result[0] = "000000";//after XXXXX
			result[1] = "000010";
		}
		if (instructionPart == "and") {
			result[0] = "000000";
			//00000
			result[1] = "100100";
		}
		if (instructionPart == "andi") {
			result[0] = "001100";
		}
		if (instructionPart == "or") {
			result[0] = "000000";
			//00000
			result[1] = "100101";
		}
		if (instructionPart == "ori") {
			result[0] = "001101";
		}
		if (instructionPart == "nor") {
			result[0] = "000000";
			//00000
			result[1] = "100111";
		}
		if (instructionPart == "beq") {
			result[0] = "000100";
		}
		if (instructionPart == "bne") {
			result[0] = "000101";
		}
		if (instructionPart == "j") {
			result[0] = "000010";
		}
		if (instructionPart == "jal") {
			result[0] = "000011";
		}
		if (instructionPart == "jr") {
			result[0] = "000000";
			//000000000000000
			result[1] = "001000";
		}
		if (instructionPart == "slt") {
			result[0] = "000000";
			//00000
			result[1] = "101010";
		}
		return result;
	}

	public String translateRFormatNumber(String num) {
		if (Integer.parseInt(num) > 31) {
			return "00000";
		}
		String resultString = Integer.toBinaryString(Integer.parseInt(num));
		if (resultString.length() < 5) {
			resultString = "0" + resultString;

		}
		return resultString;
	}

	public String translateIFormatNumber(String num){
		int result = Integer.parseInt(num);
		if(result > (((int)Math.pow(2.0, 15.0))-1) || result < (-1*((int)Math.pow(2.0, 15.0))) ){
			return "0000000000000000";
		}
		else {
			String resultString = Integer.toBinaryString(result);
			if(resultString.length()>16){
				return resultString.substring(16);
				}
			else{
				while(resultString.length()<16){
					resultString = "0"+resultString;
				}
				return resultString;
			}
			}
	}
	
	public String translateJFormatNumber(String num){
		int result = Integer.parseInt(num);
		String resultString = Integer.toBinaryString(result);
		while(resultString.length()<26){
			resultString = "0"+ resultString;
		}
		return resultString;
	}
}