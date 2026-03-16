import java.util.*;

class Bai4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[,\\s]+"); 

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = sc.nextInt();

        int left = 0, sum = 0, start = -1, max = 0;

        for(int right = 0; right < n; right++){
            sum += a[right];

            while(sum > k){
                sum -= a[left];
                left++;
            }

            if(sum == k && right - left + 1 > max){
                max = right - left + 1;
                start = left;
            }
        }

        if(start == -1) System.out.println("Khong co day con");
        else{
            for(int i = start; i < start + max; i++){
                System.out.print(a[i]);
                if(i < start + max - 1) System.out.print(", ");
            }
        }
    }
}