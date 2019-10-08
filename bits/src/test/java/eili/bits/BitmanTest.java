package eili.bits;

public class BitmanTest {
    public static void main(String[] args) {
        int bitset = 0xff;
        Bitman.printBits(bitset);
        Bitman.printBits(Bitman.toggleBit(bitset, 5));
        System.out.println(Bitman.bitsToString(0x2c) + " Hamming weight="+ Bitman.count1Bits(0x2C)); //00101100 => 3

        Bitman.printBits(0xFF);
        System.out.println("Toggling bit 3: [" + Bitman.bitsToString(Bitman.toggleBit(0xFF, 31)) + "]");
        System.out.println("Clearing bit 3: [" + Bitman.bitsToString(Bitman.clearBit(0xFF, 3)) + "]");
        System.out.println("Setting  bit 3: [" + Bitman.bitsToString(Bitman.setBit(0xFF, 3)) + "]");
        System.out.println("Setting  bit 3 to 0: [" + Bitman.bitsToString(Bitman.setBit(0xFF, 3, 0)) + "]");
        System.out.println("Setting  bit 3 to 1: [" + Bitman.bitsToString(Bitman.setBit(0xFF, 3, 1)) + "]");
        System.out.println("Rotate 0XF0 Left:  [" + Bitman.bitsToString(Bitman.rotateLeft(0xF0)) + "]");
        System.out.println("Rotate 0X0F Right: [" + Bitman.bitsToString(Bitman.rotateRight(0x0F)) + "]");
    }
}
