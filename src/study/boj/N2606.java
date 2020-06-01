package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N2606 {

	static int arr[][] = new int[101][101];
	static int C; // ��ǻ�� ��
	static int R; // ����� ��ǻ��
	static int ans = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		R = sc.nextInt();

		for (int i = 0; i < R; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1;
			arr[b][a] = 1;
		}

		run();
		System.out.print(ans);

	}

	public static void run() {
		boolean[] visited = new boolean[C + 1];

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		visited[1] = true;
		while (!q.isEmpty()) {
			int num = q.poll();
			for (int i = 1; i <= C; i++) {
				if (visited[i] == false && arr[num][i] != 0) {
					visited[i] = true;
					q.add(i);
					ans++;
				}
			}
		}

	}

}
