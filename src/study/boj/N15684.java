package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 좌표개념으로 문제가 나오고 맵으로 받아서 푸는게 잘 이해가 안되나봄 
// 왼쪽상단의 좌표를 따라간다고 생각하면 되는 것 같음 조금 더 이해가 필요해보임...
class N15684 {

	static int N, M, H;
	static int[][] map;
	static boolean[][] visited;
	static int max;
	static boolean ok;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H + 2][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}

		for (int i = 0; i <= 3; i++) { // 사다리 개수 별로 찾아본다
			max = i;
			solve(1, 1, 0);
			if (ok)
				break;
		}

		System.out.println(ok ? max : -1);
	}

	static void solve(int x, int y, int cnt) {
		if (ok) // 다른 분기에서 ok로 끝났으면 그냥 나머지 다 리턴해버
			return;

		if (max == cnt) {
			if (check()) {
				ok = true;
			}
			return;
		}

// 다리를 놓을 수 있는 모든 경우의 수를 찾는거..!!  
		for (int i = (y < N ? x : x + 1); i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 1 || map[i][j - 1] == 1 || map[i][j + 1] == 1)
					continue;
				map[i][j] = 1;
				solve(i, j, cnt + 1);
				map[i][j] = 0;
			}
		}

	}

	static boolean check() {
		for (int j = 1; j <= N; j++) {
			int i = 1;
			int temp = j;
			while (i <= H + 1) {

				if (map[i][temp] == 1) {
					temp++; // 오른쪽 사다리 탔으면 옆에 칸으로 이동되는 것
				} else if (map[i][temp - 1] == 1) {
					temp--; // 왼쪽 사다리타는 경우
				}
				i++;
			}
			if (j != temp) {
				return false;
			}
		}
		return true;
	}

	static void print() {
		for (int i = 0; i < H + 2; i++) {
			for (int j = 0; j < N + 1; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}