package pack;

public class Control {
	String RegDist = "0";
	String Branch = "0";
	String MemRead = "0";
	String MemtoReg = "0";
	String ALUOp1 = "0";
	String ALUOp0 = "0";
	String MemWrite = "0";
	String ALUsrc = "0";
	String RegWrite = "0";
	String Jump = "0";

	public Control(String opcode) {
		// R-type instructions
		if (opcode.equals("100000")// add opcode
				|| opcode.equals("100010")// sub opcode
				|| opcode.equals("100100") // and opcode
				|| opcode.equals("100101")// or opcode
				|| opcode.equals("100111") // nor opcode
				|| opcode.equals("000000") // sll opcode
				|| opcode.equals("000010") // srl opcode
				|| opcode.equals("101010"))// slt opcode
		{
			RegDist = "1";
			RegWrite = "1";
			ALUOp1 = "1";
		}
		// load instructions
		else if (opcode.equals("100011") // lw opcode
				|| opcode.equals("100001") // lh opcode
				|| opcode.equals("100101") // lhu opcode
				|| opcode.equals("100000") // lb opcode
				|| opcode.equals("100100") // lbu opcode
				|| opcode.equals("001111")) // lui opcode
		{
			MemRead = "1";
			MemtoReg = "1";
			RegWrite = "1";
			ALUsrc = "1";
		}
		// store instructions
		else if (opcode.equals("101011") // sw opcode
				|| opcode.equals("101001") // sh opcode
				|| opcode.equals("101000")) // sb opcode
		{
			MemWrite = "1";
			ALUsrc = "1";
		}
		// branch instructions
		else if (opcode.equals("000100") // beq opcode
				|| opcode.equals("000101")) // bne opcode
		{
			Branch = "1";
			ALUOp0 = "1";
			// I-type instructions
		} else if (opcode.equals("001000") // addi opcode
				|| opcode.equals("001100") // andi opcode
				|| opcode.equals("001101")) // ori opcode
		{
			RegWrite = "1";
			ALUsrc = "1";
		}
		// jump instructions
		else if (opcode.equals("000010") // j opcode
				|| opcode.equals("000011") // jal opcode
				|| opcode.equals("001000")) // jr opcode
		{
			Jump = "1";
		}
	}

	public String RegDist() {
		return RegDist;
	}

	public String Branch() {
		return Branch;
	}

	public String MemRead() {
		return MemRead;
	}

	public String MemtoReg() {
		return MemtoReg;
	}

	public String ALUOp1() {
		return ALUOp1;
	}

	public String ALUOp0() {
		return ALUOp0;
	}

	public String MemWrite() {
		return MemWrite;
	}

	public String ALUsrc() {
		return ALUsrc;
	}

	public String RegWrite() {
		return RegWrite;
	}

	public String Jump() {
		return Jump;
	}

}
