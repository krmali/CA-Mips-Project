package pack;

public class MIPSWires {
	public static String control = "000000";
	public static String rReg1 = "00000";
	public static String rReg2 = "00000" ;
	public static String wReg = "00000";
	public static String toSignExtend = "0000000000000000";
	public static String toShift2 = "00000000000000000000000000000000";
	public static String jumpAdrs = "00000000000000000000000000";
	public static String pcOut = "00000000000000000000000000000000";
	public static String pcIn = "00000000000000000000000000000000";
	public static String aluIn1 = "00000000000000000000000000000000";
	public static String aluIn2 = "00000000000000000000000000000000";
	public static String zero = "0";
	public static String aluOut = "00000000000000000000000000000000";
	public static String regWriteData = "00000000000000000000000000000000";
	//Control signals here
	public static String RegDst = "0";
	public static String Branch = "0";
	public static String MemRead = "0";
	public static String MemtoReg = "0";
	public static String MemWrite = "0";
	public static String ALUsrc = "0";
	public static String RegWrite = "0";
	public static String Jump = "0";
	public static String ALUOp = "00";
	public static String rd2 = "00000000000000000000000000000000";
	public static String AluControlOutput = "0000";
	
	public static void reset() {
		control = "000000";
		rReg1 = "00000";
		rReg2 = "00000" ;
		wReg = "00000";
		toSignExtend = "0000000000000000";
		toShift2 = "00000000000000000000000000000000";
		jumpAdrs = "00000000000000000000000000";
		pcOut = "00000000000000000000000000000000";
		pcIn = "00000000000000000000000000000000";
		aluIn1 = "00000000000000000000000000000000";
		aluIn2 = "00000000000000000000000000000000";
		zero = "0";
		aluOut = "00000000000000000000000000000000";
		regWriteData = "00000000000000000000000000000000";
		//Control signals here
		RegDst = "0";
		Branch = "0";
		MemRead = "0";
		MemtoReg = "0";
		MemWrite = "0";
		ALUsrc = "0";
		RegWrite = "0";
		Jump = "0";
		ALUOp = "00";
		rd2 = "00000000000000000000000000000000";
		AluControlOutput = "0000";
	}
}