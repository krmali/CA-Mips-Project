package pack;

public class Memory {
	
	int size;
	String[] memory;
	String memRead;
	String memWrite;
	
	Memory(int size) {
		this.size = size;
		memory = new String[size];
		memRead = "0";
		memWrite = "0";
	}
	
	public void setMemRead() {
		memRead = "1";
		memWrite = "0";
	}
	
	public void setMemWrite() {
		memWrite = "1";
		memRead = "0";
	}
	
	public String readMemory(int address) {
		if (address >= 0 && address < size - 3 && memRead.equals("1")) {
			return memory[address] + memory[address + 1]
					+ memory[address + 2] + memory[address + 3];
		} else {
			return "";
		}
	}
	
	public void writeMemory(String data, int address) {
		if (address >= 0 && address < size - 3 && memWrite.equals("1")){
			memory[address] = data.substring(0, 8);
			memory[address + 1] = data.substring(8, 16);
			memory[address + 2] = data.substring(16, 24);
			memory[address + 3] = data.substring(24, 32);
		}
	}

}
