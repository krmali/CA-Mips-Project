package pack;

public class Memory {

	int size;
	String[] memory;

	Memory(int size) {
		this.size = size;
		memory = new String[size];
	}

	public void processMemory() {
		String address = MIPSWires.aluOut;
		if (MIPSWires.MemWrite.equals("1")) {
			writeMemory(MIPSWires.rd2, Integer.parseInt(address));
		}
		MIPSWires.regWriteData = (MIPSWires.MemtoReg.equals("0")) ? MIPSWires.aluOut
				: readMemory(Integer.parseInt(address));
	}

	public String readMemory(int address) {
		if (address >= 0 && address < size - 3) {
			return memory[address] + memory[address + 1] + memory[address + 2]
					+ memory[address + 3];
		} else {
			return "";
		}
	}

	public void writeMemory(String data, int address) {
		if (address >= 0 && address < size - 3) {
			memory[address] = data.substring(0, 8);
			memory[address + 1] = data.substring(8, 16);
			memory[address + 2] = data.substring(16, 24);
			memory[address + 3] = data.substring(24, 32);
		}
	}

}
