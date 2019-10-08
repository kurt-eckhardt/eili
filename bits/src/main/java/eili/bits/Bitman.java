package eili.bits;

public class Bitman {

    public static int getBit(int bitset, int bit) {
        return (bitset >> bit) & 0x1;
    }

    public static int setBit(int bitset, int bit) {
        return bitset | (1 << bit);
    }

    // Clear the bit and then set it back to either 1 or 0
    public static int setBit(int bitset, int bit, int toValue) {
        return clearBit(bitset, bit) | (toValue << bit);
    }

    public static int toggleBit(int bitset, int bit) {
        return bitset ^ (1 << bit);
    }

    // Make a zero mask then invert it where bit is at (e.g. 11101111)
    public static int clearBit(int bitset, int bit) {
        int allOnesWithHole = ~(1 << bit);
        return bitset & allOnesWithHole;
    }


    public static void printBits(int bitset) {
        for (int i=31; i > 0; i--) {
            System.out.print(getBit(bitset, i));
        }
        System.out.println();
    }


    // aka. Hamming weight
    // 01101000
    // 01100111
    public static int count1Bits(int bitset) {
        int count=0;
        while (bitset > 0) {
            bitset &= (bitset - 1);
            count++;
        }
        return count;
    }

    
    /**
     * In 2's complement, flip all bits and add 1 
     * to get negative value.  If anding these
     * numbers equals our original value then the
     * original value must be a power of 2.
     * For example:
     *    8 = 00001000
     * & -8 = 11111000 (flip bits+1: 11110111 + 1)
     *      = 00001000
     *
     *    3 = 00000011
     * & -3 = 11111101
     *      = 00000001
     */
    public static boolean isPowerOf2(int bitset) {
        return (bitset & -bitset) == bitset;
    }


    /**
     *  A   01101000
     * ~A = 10010111
     * +1 = 10011000
     *
     *  A   01101000
     * &    10011000
     *    = 00001000
     * @param bitset
     * @return
     */
    public static int lowestOneBit(int bitset) {
        return (bitset & -bitset);
    }

    /**
     * Returns the most significant (highest)
     * bit that is set to 1.
     *
     */
    public static int highestOneBit(int bitset) {
        bitset |= (bitset >> 1);
        bitset |= (bitset >> 2);
        bitset |= (bitset >> 4);
        bitset |= (bitset >> 8);
        bitset |= (bitset >> 16);
        return bitset ^ (bitset >> 1);
    }


    /**
     * Rotates all bits to the right
     * This is essentially just a right shift
     * and moving LSB into the MSB bit.
     *
     * For Example: 101100 => 010110
     *
     * @param bitset
     * @return
     */
    public static int rotateRight(int bitset) {
        return ((bitset & 0x1) << 31) | (bitset >>> 1);
    }

    /**
     * Rotates all bits to the right
     * This is essentially just a right shift
     * and moving LSB into the MSB bit.
     *
     * For Example: 010110 => 101100
     *
     * @param bitset
     * @return
     */
    public static int rotateLeft(int bitset) {
        return (bitset << 1) | (bitset >>> 31);
    }


    public static String bitsToString(int bitset) {
        StringBuilder sb = new StringBuilder(32);
        for (int i=31; i >= 0; i--) {
            sb.append(getBit(bitset, i));
        }
        return sb.toString();
    }

}
