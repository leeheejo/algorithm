package study.boj;

import java.util.Scanner;

//ó���� �ߺ� ���� �ϴ� ������� �����ߴٰ� ������ ��Ǯ�ھ Ǯ�̸� �� 
//�׷��� ���� ���ڵ��� ���������� �ƴϸ�...��ŵ�ϸ� �Ǵ°ſ���. �Ф�
public class N15650 {

	static int N, M;
	static int[] arr = new int[8];
	static boolean[] visited = new boolean[9];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		run(0);

	}

	public static void run(int index) {
		if (index == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			if (index == 0 || (index > 0 && arr[index - 1] < i)) {
				arr[index] = i;
				visited[i] = true;
				run(index + 1);
				visited[i] = false;
			}
		}

	}

}
