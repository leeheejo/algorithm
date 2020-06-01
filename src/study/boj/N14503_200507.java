package study.boj;

import java.util.Scanner;

public class N14503_200507 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] ax = { -1, 0, 1, 0 };
	static int[] ay = { 0, 1, 0, -1 };
	static int ans = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];

		int x = sc.nextInt();
		int y = sc.nextInt();
		int d = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int nd = d;
		int nx = x;
		int ny = y;
		map[nx][ny] = 2;
		visited[x][y] = true;
		ans++;

		while (true) {
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				nd = (nd + 3) % 4;
				nx = x + ax[nd];
				ny = y + ay[nd];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (map[nx][ny] == 1)
					continue;
				if (map[nx][ny] == 0) {
					ans++;
					map[nx][ny] = 2;
					flag = true;
					break;
				}
			}

			if (!flag) { // ����
				nx = x + ax[(nd + 2) % 4];
				ny = y + ay[(nd + 2) % 4];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 1) {
					break;
				}
				x = nx;
				y = ny;
			} else {
				visited[nx][ny] = true;
				x = nx;
				y = ny;
			}

//			print();
			// ���� Ž��
			// �ƴ� ���
			// ���� ȸ��
			// Ž��

			// 4�� ��� �ƴѰ��
			// ���� Ž��
			// ���� ��� ����

		}

		System.out.println(ans);

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
