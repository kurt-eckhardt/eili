package eili.bits;

public class BaseConverterTest {
    public static void main(String[] args) {
        int base10 = 298715793;
        for (int i=2; i <= 16; i++) {
            String itoa = BaseConverter.itoa(base10, i);
            int    atoi = BaseConverter.atoi(itoa, i);
            System.out.println(base10 + " in base" + i + "="+ itoa + " and back again="+atoi);
        }
    }
}
