package eili.bits;

public class GrayCodeTest {
    public static void main(String[] args) {
        for (int i=0; i < 32; i++) {
            int graycode = GrayCode.encode(i);
            int gray2 = GrayCode.encode2(i);
            System.out.println("binary="+Bitman.bitsToString(i) + "  to gray="+Bitman.bitsToString(graycode) + " to gray2="+Bitman.bitsToString(gray2) + " to bin="+Bitman.bitsToString(GrayCode.decode(graycode)));
        }
    }
}
