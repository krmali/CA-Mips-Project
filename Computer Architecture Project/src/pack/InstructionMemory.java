package pack;

import java.math.BigInteger;

public class InstructionMemory {

	String[] instruction_mem;
	int program_counter;
	int size;
	String instructionRead;

	InstructionMemory(int size) {
		this.size = size;
		instruction_mem = new String[size];
		intializeInsMem();
		program_counter = 0;
		instructionRead = "XXXXXXXXXXXXXXXXXXXXXXXX";
	}

	public void processInstructionMemory() {
		program_counter = ALU.integerValueOf(MIPSWires.pcIn);
		String instruction = read_current_instruction();
		instructionRead = instruction;
		if (instruction.equals("end")) {
			MIPSWires.pcOut = ALU.adding(MIPSWires.pcOut, "0100");
		}
		MIPSWires.control = instruction.substring(0, 6);
		MIPSWires.rReg1 = instruction.substring(6, 11);
		MIPSWires.rReg2 = instruction.substring(11, 16);
		MIPSWires.wReg = instruction.substring(16, 21);
		MIPSWires.toSignExtend = instruction.substring(16, 32);
		MIPSWires.jumpAdrs = instruction.substring(6, 32);
		MIPSWires.pcOut = ALU.adding(MIPSWires.pcIn, "0100");
		printMIPSWires();
		incPC();
	}

	public void intializeInsMem() {
		for (int i = 0; i < instruction_mem.length; i++) {
			instruction_mem[i] = "00000000";
		}
	}

	private String add(String input1, String input2) {
		int num1 = ALU.integerValueOf(input1);
		int num2 = ALU.integerValueOf(input2);
		String result = Integer.toBinaryString(num1 + num2);
		while (result.length() < 32) {
			result = "0" + result;
		}
		return result;
	}

	public String read_inst_mem(int address) {
		if (address >= 0 && address < size - 3) {
			return instruction_mem[address] + instruction_mem[address + 1]
					+ instruction_mem[address + 2]
					+ instruction_mem[address + 3];
		} else {
			return "";
		}
	}

	public void write_inst_mem(String data, int address) {
		if (address >= 0 && address < size - 3) {
			instruction_mem[address] = data.substring(0, 8);
			instruction_mem[address + 1] = data.substring(8, 16);
			instruction_mem[address + 2] = data.substring(16, 24);
			instruction_mem[address + 3] = data.substring(24, 32);
		}
	}

	public void write_inst_mem_in_order(String[] data) {
		int i = 0, j = 0;
		for (; i < data.length; i++) {
			write_inst_mem(data[i], j);
			j += 4;
		}
		instruction_mem[j] = "end";
		instruction_mem[j + 1] = "";
		instruction_mem[j + 2] = "";
		instruction_mem[j + 3] = "";
	}

	public boolean isEnd() {
		return read_current_instruction().equals("end")
				&& instruction_mem[ALU.integerValueOf(MIPSWires.pcIn)].equals("end");
	}

	public void printMIPSWires() {
		System.out
				.println("\n=======================Instruction memory=====================");
		System.out.println("read instruction: " + instructionRead);
		System.out.println("Control part:" + MIPSWires.control);
		System.out.println("Register no1: " + MIPSWires.rReg1);
		System.out.println("Resgiter no2: " + MIPSWires.rReg2);
		System.out.println("Write Register: " + MIPSWires.wReg);
		System.out.println("Sign Extend: " + MIPSWires.toSignExtend);
		System.out.println("Address to jump to " + MIPSWires.jumpAdrs);
		System.out.println("PC in: " + MIPSWires.pcIn);
		System.out.println("PC out: " + MIPSWires.pcOut);
		System.out
				.println("===============================================================");
	}

	public String read_current_instruction() {
		return read_inst_mem(program_counter);
	}

	public void printInstructionMemoryContents() {
		for (int i = 0; i < instruction_mem.length; i++) {
			System.out.println(instruction_mem[i] + " " + i);
		}
	}

	public void incPC() {
		program_counter += 4;
	}

}
