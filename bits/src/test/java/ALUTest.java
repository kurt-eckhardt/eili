public class ALUTest {

    public static void main(String[] args) {
        System.out.println("Half Adder 0+0="+ALU.add(0,0));
        System.out.println("Half Adder 0+1="+ALU.add(0,1));
        System.out.println("Half Adder 1+0="+ALU.add(1,0));
        System.out.println("Half Adder 1+1="+ALU.add(1,1));
        System.out.println("Full Adder 0+0+0="+ALU.add(0,0,0));
        System.out.println("Full Adder 0+1+0="+ALU.add(0,1,0));
        System.out.println("Full Adder 1+0+0="+ALU.add(1,0,0));
        System.out.println("Full Adder 1+1+0="+ALU.add(1,1,0));
        System.out.println("Full Adder 0+0+1="+ALU.add(0,0,1));
        System.out.println("Full Adder 0+1+1="+ALU.add(0,1,1));
        System.out.println("Full Adder 1+0+1="+ALU.add(1,0,1));
        System.out.println("Full Adder 1+1+1="+ALU.add(1,1,1));
        System.out.println("Add Nibble(3,10)="+ALU.addNibble(new int[] {1,1,0,0}, new int[] {0,1,0,1}));
        System.out.println("Add Nibble(6,11)="+ALU.addNibble(new int[] {0,1,1,0}, new int[] {1,1,0,1}));
    }
}
