package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N17822 {
	static int N, M, T;
	static int[][] map;
	static int[][] command;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();

		map = new int[M + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[j][i] = sc.nextInt();
			}
		}

		command = new int[3][T];
		for (int i = 0; i < T; i++) {
			command[0][i] = sc.nextInt();
			command[1][i] = sc.nextInt();
			command[2][i] = sc.nextInt();

			move(command[0][i], command[1][i], command[2][i]);
			check();
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				ans += map[j][i];
			}
		}

		System.out.println(ans);

	}

	public static void move(int x, int d, int k) {// x배수의 원판 d방향으로 k번..
		for (int i = 0; i < k; i++) {
			switch (d) {
			case 0: // 시계..
				for (int j = 1; j <= N; j++) {
					if (j % x != 0)
						continue;
					int tmp = map[M][j];
					for (int m = M - 1; m >= 1; m--) {
						map[m + 1][j] = map[m][j];
					}
					map[1][j] = tmp;
				}
				break;

			case 1: // 반시계..
				for (int j = 1; j <= N; j++) {
					if (j % x != 0)
						continue;
					int tmp = map[1][j];
					for (int m = 1; m < M; m++) {
						map[m][j] = map[m + 1][j];
					}
					map[M][j] = tmp;
				}
				break;
			default:
				break;
			}
		}
	}

	public static void check() {
		int[] ax = { 1, -1, 0, 0 };
		int[] ay = { 0, 0, 1, -1 };
		int sum = 0;
		int cnt = 0;
		Queue<int[]> zero = new LinkedList<int[]>();
		Queue<int[]> points = new LinkedList<int[]>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[j][i] == 0)
					continue;
				cnt++;
				sum += map[j][i];

				points.add(new int[] { j, i });

				for (int k = 0; k < 4; k++) {
					int nextX = j + ax[k];
					int nextY = i + ay[k];

					if (nextX < 1 || nextY < 1 || nextX > M || nextY > N)
						continue;

					if (map[j][i] == map[nextX][nextY]) {
						zero.add(new int[] { j, i });
						zero.add(new int[] { nextX, nextY });
					}
				}

				if (map[j][i] != 0 && (j == 1 || j == M)) { //마지막 애랑 제일 처음 애랑 비교해야함 
					if (map[1][i] == map[M][i]) {
						zero.add(new int[] { 1, i });
						zero.add(new int[] { M, i });
					}
				}
			}
		}

		if (!zero.isEmpty()) {
			while (!zero.isEmpty()) {
				int[] tmp = zero.poll();
				map[tmp[0]][tmp[1]] = 0;
			}
		} else {
			if (cnt != 0) { // 0으로 나누게 되면 런타임에러 발생 ***주의하자***
				int avg = sum / cnt;
				while (!points.isEmpty()) {
					int[] tmp = points.poll();
					if (map[tmp[0]][tmp[1]] < avg || (map[tmp[0]][tmp[1]] == avg && sum % cnt > 0)) {
						map[tmp[0]][tmp[1]] = map[tmp[0]][tmp[1]] + 1;
					} else if (map[tmp[0]][tmp[1]] > avg) {
						map[tmp[0]][tmp[1]] = map[tmp[0]][tmp[1]] - 1;
					}
				}
			}
		}

	}
}
