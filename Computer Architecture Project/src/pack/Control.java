package pack;

public class Control {

	public Control() {
		String opcode = MIPSWires.control;
		// R-type instructions
		if ((opcode.equals("000000") && !MIPSWires.toSignExtend.equals("0000000000001000"))// add, or, sll, slt, srl, sub, and opcode
				|| opcode.equals("100111")) // nor opcode
		{
			MIPSWires.RegDst = "1";
			MIPSWires.RegWrite = "1";
			MIPSWires.ALUOp = "10";
		}
		// load instructions
		else if (opcode.equals("100011") // lw opcode
				|| opcode.equals("100001") // lh opcode
				|| opcode.equals("100101") // lhu opcode
				|| opcode.equals("100000") // lb opcode
				|| opcode.equals("100100") // lbu opcode
				|| opcode.equals("001111")) // lui opcode
		{
			MIPSWires.MemRead = "1";
			MIPSWires.MemtoReg = "1";
			MIPSWires.RegWrite = "1";
			MIPSWires.ALUsrc = "1";
		}
		// store instructions
		else if (opcode.equals("101011") // sw opcode
				|| opcode.equals("101001") // sh opcode
				|| opcode.equals("101000")) // sb opcode
		{
			MIPSWires.MemWrite = "1";
			MIPSWires.ALUsrc = "1";
		}
		// branch instructions
		else if (opcode.equals("000100") // beq opcode
				|| opcode.equals("000101")) // bne opcode
		{
			MIPSWires.Branch = "1";
			MIPSWires.ALUOp = "01";
		}
		// I-type instructions
		else if (opcode.equals("001000") // addi opcode
				|| opcode.equals("001100") // andi opcode
				|| opcode.equals("001101")) // ori opcode
		{
			MIPSWires.RegWrite = "1";
			MIPSWires.ALUsrc = "1";
			MIPSWires.ALUOp = "10";
		}
		// jump instructions
		else if (opcode.equals("000010") // j opcode
				|| opcode.equals("000011") // jal opcode
				|| opcode.equals("000000")) // jr opcode
		{
			MIPSWires.Jump = "1";
		}
		printMIPSWires();
	}

	public void printMIPSWires() {
		System.out
				.println("\n======================CONTROL UNIT===================");
		System.out.println("RegDst: " + MIPSWires.RegDst);
		System.out.println("Branch: " + MIPSWires.Branch);
		System.out.println("MemRead: " + MIPSWires.MemRead);
		System.out.println("MemtoReg: " + MIPSWires.MemtoReg);
		System.out.println("MEmWrite: " + MIPSWires.MemWrite);
		System.out.println("ALUsrc: " + MIPSWires.ALUsrc);
		System.out.println("RegWrite: " + MIPSWires.RegWrite);
		System.out.println("Jump: " + MIPSWires.Jump);
		System.out.println("ALUOp: " + MIPSWires.ALUOp);
		System.out
				.println("======================================================");
	}

}
