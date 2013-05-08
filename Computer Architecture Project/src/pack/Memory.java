package pack;

public class Memory {

	int size;
	String[] memory;

	Memory(int size) {
		this.size = size;
		memory = new String[size];
		for(int i= 0;i<memory.length;i++){
			memory[i]="00000000";
		}
	}

	public void processMemory() throws NumberFormatException, Exception {
		String address = MIPSWires.aluOut;
		if (MIPSWires.MemWrite.equals("1")) {
			writeMemory(MIPSWires.rd2, Integer.parseInt(address, 2));
		}
		MIPSWires.regWriteData = (MIPSWires.MemtoReg.equals("0")) ? MIPSWires.aluOut
				: readMemory(Integer.parseInt(address, 2));
		printMIPSWires();
	}

	public String readMemory(int address) throws Exception {
		if (MIPSWires.control.equals("100011")) { // lw
			if (address >= 0 && address < size - 3) {
				return memory[address] + memory[address + 1] + memory[address + 2]
						+ memory[address + 3];
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("100001")) { // lh
			if (address >= 0 && address < size - 1) {
				String prefix = memory[address].charAt(0) == '1' ? "1111111111111111" : "0000000000000000";
				return prefix + memory[address] + memory[address + 1];
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("100101")) { // lhu
			if (address >= 0 && address < size - 1) {
				String prefix = "0000000000000000";
				return prefix + memory[address] + memory[address + 1];
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("100000")) { // lb
			if (address >= 0 && address < size) {
				String prefix = memory[address].charAt(0) == '1' ? "111111111111111111111111" : "000000000000000000000000";
				return prefix + memory[address];
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("100100")) { // lbu
			if (address >= 0 && address < size) {
				String prefix = "000000000000000000000000";
				return prefix + memory[address];
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("001111")) { // lui
			System.out.println("MIPSWires.toSignExtend: " + MIPSWires.toSignExtend);
			return MIPSWires.toSignExtend + "0000000000000000";
		} else {
			throw new Exception("No such opcode");
		}
	}

	public void writeMemory(String data, int address) throws Exception {
		if (MIPSWires.control.equals("101011")) { // sw
			if (address >= 0 && address < size - 3) {
				System.out.println("saved data is: " + data);
				memory[address] = data.substring(0, 8);
				memory[address + 1] = data.substring(8, 16);
				memory[address + 2] = data.substring(16, 24);
				memory[address + 3] = data.substring(24, 32);
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("101001")) { // sh
			if (address >= 0 && address < size - 1) {
				System.out.println("saved data is: " + data);
				memory[address] = data.substring(16, 24);
				memory[address + 1] = data.substring(24, 32);
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		} else if (MIPSWires.control.equals("101000")) { // sb
			if (address >= 0 && address < size) {
				System.out.println("saved data is: " + data);
				memory[address] = data.substring(24, 32);
			} else {
				throw new Exception("Trying to access an empty memory location");
			}
		}
	}
	
	public void printMemoryContent(){
		System.out.println("memory contents:");
		for(int i=0;i<this.memory.length;i++){
			System.out.print("memory["+i+"]="+this.memory[i] +"    ");
			if(i%3 == 0){
				System.out.println();
			}
		}
	}
	
	public void printMIPSWires(){
		System.out.println("=========================MEMORY=============================");
		System.out.println("Written data to memory if write: "+MIPSWires.regWriteData);
		printMemoryContent();
		System.out.println("\n=============================================================");
	}

}
