package eili.string;

public class HuffmanTest {
    public static void main(String[] args) {
        String text  = "hello world";
        char[] chars = text.toCharArray();

        System.out.println(text);
        // uncompressed ASCII text
        StringBuilder raw = new StringBuilder();
        for (char c : chars) {
            int charval = (int)c;
            for (int bitIdx = 7; bitIdx >= 0; bitIdx--) {
                raw.append((charval >> bitIdx) & 0x1);
            }
        }


        Huffman.Node root = Huffman.buildHuffmanTree(chars);
        String compressed = Huffman.compress(root, chars);
        Huffman.decompress(root, compressed);

        int rawLen = raw.length();
        int comLen = compressed.length();
        int pctDec = (rawLen - comLen) * 100 / rawLen;
        System.out.println("uncompressed length="+raw.length() + " compressed="+compressed.length() + " decrease="+pctDec+  "%");
    }


}
