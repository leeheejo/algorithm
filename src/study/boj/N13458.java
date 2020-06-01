package study.boj;

import java.util.Scanner;

public class N13458 {
	static int N;
	static int[] c;
	static int mp;
	static int sp;
	static long answer = 0; //100������ �����忡 100���� ���ְ�, �Ѱ��� �ΰ����� 1�ΰ�� 
	//int	-2,147,483,648 ~ 2,147,483,647

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		c = new int[N];
		for (int i = 0; i < N; i++)
			c[i] = sc.nextInt();
		mp = sc.nextInt();
		sp = sc.nextInt();

		run();
		System.out.println(answer);
	}

	public static void run() {
		for (int i = 0; i < N; i++) {
			answer += 1;
			if (c[i] - mp > 0) {
				answer += (c[i] - mp) / sp;
				if ((c[i] - mp) % sp > 0)
					answer += 1;
			}
		}
	}
}
