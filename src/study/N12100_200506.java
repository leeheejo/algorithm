package study;

import java.util.Scanner;

public class N12100_200506 {
	static int N;
	static int[][] map;
	static int ans = Integer.MIN_VALUE;
// ¹Ý·Ê https://www.acmicpc.net/board/view/24061
//	5
//
//	2 2 4 8 16
//
//	0 0 0 0 0
//
//	0 0 0 0 0
//
//	0 0 0 0 0
//
//	2 2 4 8 16

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, map);
		System.out.println(ans);

	}

	public static void dfs(int index, int[][] arr) {
		if (index == 5) {
//			print(arr);
			ans = Math.max(ans, check(arr));
			return;
		}

		for (int i = 0; i < 4; i++) {
			int[][] next = new int[N][N];
			next = cloneMap(next, arr);
			next = up(next);
			dfs(index + 1, next);
			arr = rotateMap(arr);
		}
	}

	public static int[][] up(int[][] arr) {
		int[][] temp = new int[N][N];

		for (int j = 0; j < N; j++) {
			int target = -1;
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				if (arr[i][j] != 0) {
					temp[++target][j] = arr[i][j];
					if (target != 0) {
						if (temp[target][j] == temp[target - 1][j] && flag == true) {
							temp[target - 1][j] *= 2;
							temp[target][j] = 0;
							target--;
							flag = false;
						} else {
							flag = true;
						}
					}
				}
			}
		}
		arr = cloneMap(arr, temp);

		return arr;
	}

	public static int[][] rotateMap(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = arr[N - j - 1][i];
			}
		}
		arr = cloneMap(arr, temp);

		return arr;
	}

	public static int check(int[][] arr) {
		int a = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a = Math.max(a, arr[i][j]);
			}
		}
		return a;
	}

	public static int[][] cloneMap(int[][] dest, int[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dest[i][j] = origin[i][j];
			}
		}
		return dest;
	}

	public static void print(int[][] arr) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
