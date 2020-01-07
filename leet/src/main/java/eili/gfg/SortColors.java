package eili.gfg;

public class SortColors {

    public static void main(String[] args) {
        int[] sorted = sortColors(new int[] {2,1,0,1,2,0,1,1,2,0,0,0,2,2,2,1,1,2,0,1,0,2,1,0});
        for (int c: sorted) {
            System.out.print(c + " ");
        }
    }

    // swap down
    public static int[] sortColors(int[] colors) {

        int first1Idx = -1;
        int first2Idx = -1;

        for (int i=0; i < colors.length; i++) {

            for (int c: colors) {
                System.out.print(c + " ");
            }
            System.out.println();

            if (colors[i] == 0) {
                if (first2Idx != -1) {
                    swap(colors, first2Idx, i);
                    if (first1Idx != -1) {
                        swap(colors, first1Idx, first2Idx);
                        first1Idx++;
                    }
                    first2Idx++;
                }
            } else if (colors[i] == 1) {
                if (first2Idx != -1) {
                    swap(colors, first2Idx, i);
                    if (first1Idx == -1) first1Idx = first2Idx;
                    first2Idx++;
                }
                if (first1Idx == -1) first1Idx = i;
            } else {
                if (first2Idx == -1) first2Idx = i;
            }
        }

        return colors;
    }


    private static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }
}
