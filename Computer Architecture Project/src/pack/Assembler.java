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
	
	public Assembler(){
		
		input_file=new File("/Users/kareemali/Desktop/input.txt");
		try {
			fis=new FileInputStream(input_file);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("error in finding file");
			
		}
		
		first_pass_file = new File("/Users/kareemali/Desktop/first_pass_file.txt");
		try {
			if (first_pass_file.createNewFile()){
			        System.out.println("File is created!");
			      }else{
			        System.out.println("File already exists.");
			      }
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		  
		try {
			fw= new FileWriter(first_pass_file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write("hoppa");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	public static void main(String []args){
//		File input= new File("/Users/kareemali/Desktop/test.txt");
//		FileInputStream fis=null;
//		try {
//			fis=new FileInputStream(input);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(fis.available());
//			int content;
//			while ((content = fis.read()) != -1) {
//				// convert to char and display it
//				System.out.print(content+",");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		Assembler assemb = new Assembler();
		
		assemb.assemble(new File("/Users/kareemali/Desktop/test.txt"));
		
	}
	
	public void first_pass(){
		
	}
	
	
	public String [] assemble(File file){
		String [] result = new String[20];
		int instructionNumber= 0;
		try {
			FileInputStream fis = new FileInputStream(file);
			int content;
			String []instruction = {"","","",""};
			int separator = 0 ;
			while((content = fis.read()) != -1){
				if(content != 10){
					if(separator == 0 && content == 36){
						separator ++;
						instruction[separator]+="$";
						
					}
					else if( content == 44 && (separator == 1 || separator == 2)){
						separator ++;
					}
					else {
						instruction[separator] += Character.toChars(content)[0];
					}
				}
				else{
					result[instructionNumber] = instructionAssemble(instruction);
					instruction[0]="";
					instruction[1]="";
					instruction[2]="";
					instruction[3]="";
					instructionNumber ++; 
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println("Error occur when trying to read from file.");
		}
		
		
		return result;
	}
	public String instructionAssemble(String [] instruction){
		return "";
	}
	
	public String translateRegister(String instructionPart){
		if(instructionPart == "$a0"){
			return "00100";
		}
		if(instructionPart == "$a1"){
			return "00101";
		}
		if(instructionPart == "$a2"){
			return "00110";
		}
		if(instructionPart == "$a3"){
			return "00111";
		}
		if(instructionPart == "$v0"){
			return "00010";
		}
		if(instructionPart == "$v1"){
			return "00011";
		}
		if(instructionPart == "$t0"){
			return "01000";
		}
		if(instructionPart == "$t1"){
			return "01001";
		}
		if(instructionPart == "$t2"){
			return "01010";
		}
		if(instructionPart == "$t3"){
			return "01011";
		}
		if(instructionPart == "$t4"){
			return "01100";
		}
		if(instructionPart == "$t5"){
			return "01101";
		}
		if(instructionPart == "$t6"){
			return "01110";
		}
		if(instructionPart == "$t7"){
			return "01111";
		}
		if(instructionPart == "$t8"){
			return "11000";
		}
		if(instructionPart == "$t9"){
			return "11001";
		}
		if(instructionPart == "$so"){
			return "10000";
		}
		if(instructionPart == "$s1"){
			return "10001";
		}
		if(instructionPart == "$s2"){
			return "10010";
		}
		if(instructionPart == "$s3"){
			return "10011";
		}
		if(instructionPart == "$s4"){
			return "10100";
		}
		if(instructionPart == "$s5"){
			return "10101";
		}
		if(instructionPart == "$s6"){
			return "10110";
		}
		if(instructionPart == "$s7"){
			return "10111";
		}
		if(instructionPart == "$gp"){
			return "11100";
		}
		if(instructionPart == "$sp"){
			return "11101";
		}
		if(instructionPart == "$fp"){
			return "11110";
		}
		if(instructionPart == "$ra"){
			return "11111";
		}
		if(instructionPart == "$0"){
			return "00000";
		}
		if(instructionPart == "$at"){
			return "00001";
		}
		if(instructionPart == "$k0"){
			return "11010";
		}
		if(instructionPart == "$k1"){
			return "11011";
		}
		return "";
	}
	
	public String[] translateOp(String instructionPart){ 
		String [] result ={"",""}; 
		if(instructionPart == "add"){
			
		}
		if(instructionPart == "addi"){
			
		}
		if(instructionPart == "sub"){
			
		}
		if(instructionPart == "lw"){
			
		}
		if(instructionPart == "lh"){
	
		}
		if(instructionPart == "lhu"){
			
		}
		if(instructionPart == "lb"){
			
		}
		if(instructionPart == "lbu"){
			
		}
		if(instructionPart == "sw"){
			
		}
		if(instructionPart == "sh"){
	
		}
		if(instructionPart == "sb"){
			
		}
		if(instructionPart == "lui"){
			
		}
		if(instructionPart == "sll"){
			
		}
		if(instructionPart == "srl"){
			
		}
		if(instructionPart == "and"){
			
		}
		if(instructionPart == "andi"){
			
		}
		if(instructionPart == "or"){
			
		}
		if(instructionPart == "ori"){
			
		}
		if(instructionPart == "nor"){
			
		}
		if(instructionPart == "beq"){
			
		}
		if(instructionPart == "bne"){
			
		}
		if(instructionPart == "j"){
			
		}
		if(instructionPart == "jal"){
			
		}
		if(instructionPart == "jr"){
	
		}
		if(instructionPart == "slt"){
			
		}
		return result ;
	}
	
	
	
}