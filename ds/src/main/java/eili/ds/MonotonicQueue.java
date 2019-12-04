package eili.ds;

public class MonotonicQueue {


    public static void main(String[] args) {

        int k = 3;
        int[] nums   = new int[] {1,2,3,5,1,0,7};
        int[] answer = new int[nums.length];

        for (int i=0; i <= k; i++) {
            answer[i] = nums[i];
        }

        for (int i=k+1; i < nums.length; i++) {
            answer[i] = Integer.MAX_VALUE;
            for (int j=k; j > 0; j--) {
                answer[i] = Math.min(answer[i], answer[i-j]+nums[i]);
            }
        }

        for (int a: answer) {
            System.out.print(a + ",");
        }
    }

}
