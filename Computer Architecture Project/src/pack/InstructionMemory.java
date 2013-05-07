package pack;

public class InstructionMemory {

	String[] instruction_mem;
	int program_counter;
	int size;

	InstructionMemory(int size) {
		this.size = size;
		instruction_mem = new String[size];
		program_counter = 0;
	}
	
	public String read_inst_mem(int address) {
		if (address >= 0 && address < size - 3) {
			return instruction_mem[address] + instruction_mem[address + 1]
					+ instruction_mem[address + 2] + instruction_mem[address + 3];
		} else {
			return "";
		}
	}
	
	public void write_inst_mem(String data, int address) {
		if (address >= 0 && address < size - 3){
			instruction_mem[address] = data.substring(0, 8);
			instruction_mem[address + 1] = data.substring(8, 16);
			instruction_mem[address + 2] = data.substring(16, 24);
			instruction_mem[address + 3] = data.substring(24, 32);
		}
	}
	
	public String read_current_instruction() {
		return read_inst_mem(program_counter);
	}
	
	public void inc_pc() {
		program_counter += 4;
	}
	
	public String wire1() {
		String instruction = read_inst_mem(program_counter);
		return instruction.substring(0,6);
	}
	
	public String wire2() {
		String instruction = read_inst_mem(program_counter);
		return instruction.substring(6,11);
	}
	
	public String wire3() {
		String instruction = read_inst_mem(program_counter);
		return instruction.substring(11,16);
	}
	
	public String wire4() {
		String instruction = read_inst_mem(program_counter);
		return instruction.substring(16,21);
	}
	
	public String wire5() {
		String instruction = read_inst_mem(program_counter);
		return instruction.substring(17,32);
	}

}
