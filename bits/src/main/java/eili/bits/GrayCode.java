package eili.bits;
/**
 *  0000 => 0000
 *  0001 => 0001  (1 | add all pairs of bits and use sum as next bit in gray code - throw away carry?)
 *  0010 => 0011  (1 | XOR pairs of bits as next bit in gray code)
 *  0011 => 0010
 *  0100 => 0110
 *  0101 => 0111
 *  0110 => 0101
 *  0111 => 0100
 *  1000 => 1100
 *  1001 => 1101
 *  1010 => 1111
 *  1011 => 1110
 *  1100 => 1010
 *  1101 => 1011
 *  1110 => 1001
 *  1111 => 1000
 *
 *  Learnings:
 *  1) Preserve MSB
 *  2) XOR adjacent pairs of bits from MSB down to 0 to get next GreyBit
 */
 public class GrayCode {

    // Preserve the High one bit, then...
    // Add all pairs of bits and use sum as next bit in gray code.
    // Note this is the same as XORing them since the carry is thrown away.
    // For example:
    // 1011
    // 0101
    // 1110
    public static int encode(int bitset) {
        int msb = Bitman.highestOneBit(bitset);
        return msb | bitset ^ (bitset >> 1);
    }

    // Oh man, this is a bunch of XOR operations to decode
    // Preserve high one bit, and then xor it with the next
    // lower bit and so on until we reach bit 0:
    // E.g.: bit 0 binary = bit 1 binary ^ bit 0 gray

    public static int decode(int bitset) {
        int binary = Bitman.highestOneBit(bitset);
        for (int i=30; i >= 0; i--) {
            binary |= (Bitman.getBit(binary,i+1) ^ Bitman.getBit(bitset, i)) << i;
        }
        return binary;
    }


    /**
     * This solution unrolls the optimized "encode" solution.
     *
     * @param bitset
     * @return
     */
    public static int encode2(int bitset) {
        int msb = Bitman.highestOneBit(bitset);
        int result = msb;
        int numBinaryDigits = (int)(Math.log(bitset) / Math.log(2))+1;
        for (int i=numBinaryDigits-1; i >=0; i--) {
            result |= ((Bitman.getBit(bitset, i+1) ^ Bitman.getBit(bitset, i)) << i);
        }
        return result;
    }
}
