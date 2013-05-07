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

	public void processMemory() {
		String address = MIPSWires.aluOut;
		if (MIPSWires.MemWrite.equals("1")) {
			writeMemory(MIPSWires.rd2, ALU.integerValueOf(address));
		}
		MIPSWires.regWriteData = (MIPSWires.MemtoReg.equals("0")) ? MIPSWires.aluOut
				: readMemory(Integer.parseInt(address));
		printMIPSWires();
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
