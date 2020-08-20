package study.boj;

import java.util.Scanner;

public class N13458_200812 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] c = new int[N];

		for (int i = 0; i < N; i++) {
			c[i] = sc.nextInt();
		}

		int B = sc.nextInt();
		int C = sc.nextInt();
		long ans = 0;
		for (int i = 0; i < N; i++) {
			ans++;
			int sub = c[i] - B;
			if (sub > 0) {
				ans += sub / C;
				if (sub % C > 0)
					ans++;
			}
		}

		System.out.println(ans);

	}

}
