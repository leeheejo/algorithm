package study;

import java.util.Scanner;

//�ݷ�
//6
//100
//400
//2
//1
//4
//200
//���� �ǳʶٴ� ��� �߻� 

public class N2156 {

	static int N;
	static int[] arr = new int[10001];
	static long[] ans = new long[10001];
	static long answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		ans[1] = arr[1];
		ans[2] = arr[2] + arr[1];
		ans[3] = Math.max(ans[2], arr[3] + Math.max(arr[2], arr[1])); // �� �κ��� �߿� ��
		for (int i = 4; i <= N; i++) {

			ans[i] = Math.max(ans[i - 2], ans[i - 3] + arr[i - 1]) + arr[i];
			ans[i] = Math.max(ans[i], ans[i - 1]); //�̺κ��� �߿��� 

		}

		System.out.println(ans[N]);
	}

}
