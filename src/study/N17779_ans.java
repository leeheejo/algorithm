package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N17779_ans {

	static int[][] population;
	static int[][] map;
	static int[] area;
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = stoi(br.readLine());

		ans = Integer.MAX_VALUE;
		population = new int[N + 1][N + 1];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; ++j) {
				population[i][j] = stoi(st.nextToken());
			}
		}

		// 1. map�뿉 �꽑嫄곌뎄瑜� 洹몃┛�떎.
		for (int x = 1; x <= N; ++x) {
			for (int y = 1; y <= N; ++y) {
				for (int d1 = 1; d1 <= N; ++d1) {
					for (int d2 = 1; d2 <= N; ++d2) {
						if ((x + d1 + d2) <= N && (y - d1) >= 1 && (y - d1) < y && (y + d2) > y && (y + d2) <= N) {
							map = new int[N + 1][N + 1];
							area = new int[6];
							System.out.println(x + " " + y + " " + d1 + " " + d2);
							setBoundary(x, y, d1, d2);
							setArea(x, y, d1, d2);
							calPopulation();
						}
					}
				}
			}
		}
		System.out.println(ans);
	}

	// �셿�꽦�맂 �꽑嫄곌뎄�뿉�꽌 �꽑嫄곌뎄蹂� �씤援ъ닔瑜� �빀�븳�떎.
	private static void calPopulation() {
		for (int r = 1; r <= N; ++r) {
			for (int c = 1; c <= N; ++c) {
				area[map[r][c]] += population[r][c];
			}
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 1; i < 6; ++i) {
			max = area[i] > max ? area[i] : max;
			min = area[i] < min ? area[i] : min;
		}
		
		System.out.println();
	 	System.out.println(max-min);
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
		int gap = max - min;
		ans = ans > gap ? gap : ans;
	}

	private static void setArea(int x, int y, int d1, int d2) {
//		1踰� �꽑嫄곌뎄: 1 �돞 r < x+d1, 1 �돞 c �돞 y
//		2踰� �꽑嫄곌뎄: 1 �돞 r �돞 x+d2, y < c �돞 N
//		3踰� �꽑嫄곌뎄: x+d1 �돞 r �돞 N, 1 �돞 c < y-d1+d2
//		4踰� �꽑嫄곌뎄: x+d2 < r �돞 N, y-d1+d2 �돞 c �돞 N

		for (int r = 1; r <= N; ++r) {
			for (int c = 1; c <= N; ++c) {
				if (map[r][c] != 0)
					continue;

				if (r < (x + d1) && c <= y)
					map[r][c] = 1;
				else if ((x + d2) >= r && c > y)
					map[r][c] = 2;
				else if ((x + d1) <= r && c < (y - d1 + d2))
					map[r][c] = 3;
				else if ((x + d2) < r && (y - d1 + d2) <= c)
					map[r][c] = 4;
				else
					map[r][c] = 5;
			}
		}
	}

	private static void setBoundary(int x, int y, int d1, int d2) {
//		1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
//		2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
//		3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
//		4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		// 蹂��솕媛�
		map[x][y] = 5;
		int adder1 = 0, adder2 = 0;

		while (++adder1 <= d1)
			map[x + adder1][y - adder1] = 5;
		while (++adder2 <= d2)
			map[x + adder2][y + adder2] = 5;

		adder1 = 0;
		adder2 = 0;
		while (++adder2 <= d2)
			map[x + d1 + adder2][y - d1 + adder2] = 5;
		while (++adder1 <= d1)
			map[x + d2 + adder1][y + d2 - adder1] = 5;

		// 5援ъ뿭 梨꾩슦湲�
		for (int r = 1; r <= N; ++r) {
			int left = -1;
			int right = -1;

			int idx = 1;
			while (idx <= N) {
				if (map[r][idx] == 5) {
					left = idx;
					break;
				}
				idx++;
			}

			idx = N;
			while (idx >= 0) {
				if (map[r][idx] == 5) {
					right = idx;
					break;
				}
				idx--;
			}

			if (left != right) {
				for (int i = left; i < right; ++i)
					map[r][i] = 5;
			}
		}
	}

	private static void print() {
		for (int r = 1; r <= N; ++r) {
			for (int c = 1; c <= N; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}