package study.boj;

import java.util.Scanner;

//반례 참고 https://2jinishappy.tistory.com/60
// 위 아래 오른쪽 왼쪽으로 동일한작업 하는 애들은 
// 맵을 회전하는 것을생각하자 왜냐면 4 만드는 건 실수가 많아지고 디버깅시간만 오래걸려짐 

public class N12100 {
	static int N, ans;

	public static class BOARD {
		int[][] map = new int[20][20];

		public BOARD() {
			super();
		}

		public BOARD(int[][] map) {
			super();
			this.map = map;
		}

		void copy(BOARD b) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.map[i][j] = b.map[i][j];
				}
			}

		}

		void rotate() {
			int[][] temp = new int[20][20];
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					temp[y][x] = map[N - x - 1][y];
				}
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					map[y][x] = temp[y][x];
				}
			}
		}

		int getmax() {
			int ret = Integer.MIN_VALUE;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					ret = Math.max(ret, map[y][x]);
				}
			}
			return ret;
		}

		void up() {
			int[][] temp = new int[20][20];
			for (int x = 0; x < N; x++) {
				boolean flag = true;
				int target = -1;
				for (int y = 0; y < N; y++) {
					if (map[y][x] == 0)
						continue;

					temp[++target][x] = map[y][x];
					if (target != 0) {
						if (temp[target - 1][x] == temp[target][x] && flag == true) {
							temp[target - 1][x] *= 2;
							temp[target][x] = 0;
							target--;
							flag = false;
						} else {
							flag = true;
						}
					}

				}
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					map[y][x] = temp[y][x];
				}
			}
		}
	}

	public static void dfs(BOARD cur, int count) {
		if (count == 5) {
			ans = Math.max(ans, cur.getmax());
//			print(cur);
			return;
		}
		for (int i = 0; i < 4; i++) {
			BOARD next = new BOARD();
			next.copy(cur);
			next.up();
			dfs(next, count + 1);
			cur.rotate();
		}


		return;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		BOARD board = new BOARD();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board.map[i][j] = sc.nextInt();
			}
		}

		ans = 0;
		dfs(board, 0);

		System.out.println(ans);
	}

	public static void print(BOARD b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(b.map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
}
