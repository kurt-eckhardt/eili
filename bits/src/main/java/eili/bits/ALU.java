package eili.bits;

public class ALU {


    // Half Adder
    // The resulting int holds two values:
    // bit0 = sum
    // bit1 = carry out
    // For example: Adding 1+1 returns (10) bit1=1, bit0=0)
    // int halfAdd = (bitA & bitB) << 1 | (bitA ^ bitB);
    // -----------------
    // | A | B |COT|SUM|
    // |---|---|---|---|
    // | 0 | 0 | 0 | 0 |
    // | 0 | 1 | 0 | 1 |
    // | 1 | 0 | 0 | 1 |
    // | 1 | 1 | 1 | 0 |
    // ---------------------
    public static int add(int bitA, int bitB) {
        int carry = and(bitA, bitB);
        int sum   = xor(bitA, bitB);
        return (carry << 1) | sum;
    }


    // FullAdder built using two half adders
    // ---------------------
    // | A | B |CIN|COT|SUM|
    // |---|---|---|---|---|
    // | 0 | 0 | 0 | 0 | 0 |
    // | 0 | 1 | 0 | 0 | 1 |
    // | 1 | 0 | 0 | 0 | 1 |
    // | 1 | 1 | 0 | 1 | 0 |
    // | 0 | 0 | 1 | 0 | 1 |
    // | 0 | 1 | 1 | 1 | 0 |
    // | 1 | 0 | 1 | 1 | 0 |
    // | 1 | 1 | 1 | 1 | 1 |
    // ---------------------
    public static int add(int bitA, int bitB, int carryIn) {
        int half1 = add(bitA, bitB);
        int half2 = add(half1 & 0x1, carryIn);  // add ha1Sum + carryIn
        int carry = or(half1 >> 1, half2 >> 1); // faCarry = ha1 carry or ha2 carry
        return (carry << 1) | (half2 & 0x1);  // faCarry in bit1, ha2Sum in bit0
    }

    /**
     * 4 bit Ripple Adder
     * Note: Look into Carry-Look-Ahead adder
     * @param num1Bits
     * @param num2Bits
     * @return
     */
    public static int addNibble(int[] num1Bits, int[] num2Bits) {

        int add0 = add(num1Bits[0], num2Bits[0]);
        int add1 = add(num1Bits[1], num2Bits[1], add0 >> 1);
        int add2 = add(num1Bits[2], num2Bits[2], add1 >> 1);
        int add3 = add(num1Bits[3], num2Bits[3], add2 >> 1);

        int result = 0;
        result |= (add0 & 0x1) << 0;
        result |= (add1 & 0x1) << 1;
        result |= (add2 & 0x1) << 2;
        result |= (add3 & 0x1) << 3;
        result |= (add3 >> 1)  << 4; // carryOut

        return result;
    }


    // Negate (how to do binary subtraction?
    public static int flipBits(int bits) {
        return 0-bits;
    }


    public static int and(int bitA, int bitB) {
        return (bitA & bitB) & 0x1;
    }

    public static int or(int bitA, int bitB) {
        return (bitA | bitB) & 0x1;
    }

    public static int not(int bitA) {
        return ~bitA & 0x1;
    }

    // -------------
    // | A | B |XOR|
    // |---|---|---|
    // | 0 | 0 | 0 |
    // | 0 | 1 | 1 |
    // | 1 | 0 | 1 |
    // | 1 | 1 | 0 |
    // -------------
    public static int xor(int bitA, int bitB) {
        int orVal  = or(bitA, bitB);
        int notAnd = not(and(bitA, bitB));
        return and(orVal, notAnd);
    }
}
