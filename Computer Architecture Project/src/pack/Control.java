package pack;

public class Control {
	private boolean RegDist;
	private boolean Branch;
	private boolean MemRead;
	private boolean MemtoReg;
	private boolean ALUOp1;
	private boolean ALUOp0;
	private boolean MemWrite;
	private boolean ALUsrc;
	private boolean RegWrite;
	private boolean Jump;

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
			RegDist = true;
			RegWrite = true;
			ALUOp1 = true;
		}
		// load instructions
		else if (opcode.equals("100011") // lw opcode
				|| opcode.equals("100001") // lh opcode
				|| opcode.equals("100101") // lhu opcode
				|| opcode.equals("100000") // lb opcode
				|| opcode.equals("100100") // lbu opcode
				|| opcode.equals("001111")) // lui opcode
		{
			MemRead = true;
			MemtoReg = true;
			RegWrite = true;
			ALUsrc = true;
		}
		// store instructions
		else if (opcode.equals("101011") // sw opcode
				|| opcode.equals("101001") // sh opcode
				|| opcode.equals("101000")) // sb opcode
		{
			MemWrite = true;
			ALUsrc = true;
		}
		// branch instructions
		else if (opcode.equals("000100") // beq opcode
				|| opcode.equals("000101")) // bne opcode
		{
			Branch = true;
			ALUOp0 = true;
			// I-type instructions
		} else if (opcode.equals("001000") // addi opcode
				|| opcode.equals("001100") // andi opcode
				|| opcode.equals("001101")) // ori opcode
		{
			RegWrite = true;
			ALUsrc = true;
		}
		// jump instructions
		else if (opcode.equals("000010") // j opcode
				|| opcode.equals("000011") // jal opcode
				|| opcode.equals("001000")) // jr opcode
		{
			Jump = true;
		}
	}

	public boolean RegDist() {
		return RegDist;
	}

	public boolean Branch() {
		return Branch;
	}

	public boolean MemRead() {
		return MemRead;
	}

	public boolean MemtoReg() {
		return MemtoReg;
	}

	public boolean ALUOp1() {
		return ALUOp1;
	}

	public boolean ALUOp0() {
		return ALUOp0;
	}

	public boolean MemWrite() {
		return MemWrite;
	}

	public boolean ALUsrc() {
		return ALUsrc;
	}

	public boolean RegWrite() {
		return RegWrite;
	}

	public boolean Jump() {
		return Jump;
	}

}
