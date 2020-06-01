package study.boj;

import java.util.Scanner;

public class N13458_200507 {

	static int N;
	static int mp, sp;
	static int[] arr;
	static long ans = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		mp = sc.nextInt();
		sp = sc.nextInt();

		for (int i = 0; i < N; i++) {
			ans++;
			arr[i] -= mp;
			if (arr[i] > 0) { 
				ans += arr[i] / sp;
				if (arr[i] % sp != 0)
					ans++;
			}
		}

		System.out.println(ans);

	}

}
