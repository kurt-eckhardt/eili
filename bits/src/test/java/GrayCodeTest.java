public class GrayCodeTest {
    public static void main(String[] args) {
        for (int i=0; i < 32; i++) {
            int graycode = GrayCode.encode(i);
            System.out.println("binary="+Bitman.bitsToString(i) + "  to gray="+Bitman.bitsToString(graycode) + " to bin="+Bitman.bitsToString(GrayCode.decode(graycode)));
        }
    }
}
