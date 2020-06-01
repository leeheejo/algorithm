package study.boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class N17143_2 {
	public static class Shark implements Comparable<Shark> {
		int x, y, s, d, z;

		public Shark(int x, int y, int s, int d, int z) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			if (this.y == o.y) {
				if (this.x == o.x) {
					return this.z < o.z ? -1 : 1;
				} else
					return this.x < o.x ? -1 : 1;

			}
			return this.y < o.y ? -1 : 1;
		}

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}

	}

	static int R, C, M;
	static ArrayList<Shark> sharks;
	static int[] ax = { 0, -1, 1, 0, 0 };
	static int[] ay = { 0, 0, 0, 1, -1 };
	static int sum = 0;

	public static void main(String[] args) {
		sharks = new ArrayList<Shark>();
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();
			sharks.add(new Shark(r - 1, c - 1, s, d, z));
		}

		for (int i = 0; i < C; i++) {
//			print();
//			System.out.println();
			getShark(i);
			move();
		}

		System.out.println(sum);
	}

	public static void getShark(int person) {

		Collections.sort(sharks);
		for (int i = 0; i < sharks.size(); i++) {
			if (sharks.get(i).y == person) {
				sum += sharks.get(i).z;
				sharks.remove(i);
				return;
			}
		}
	}

	public static void move() {
		for (int i = 0; i < sharks.size(); i++) {
			Shark s = sharks.get(i);
			int nx = s.x;
			int ny = s.y;
			for (int j = 0; j < s.s; j++) {
				nx = nx + ax[s.d];
				ny = ny + ay[s.d];
				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					nx = nx - ax[s.d];
					ny = ny - ay[s.d];
					if (s.d % 2 == 0)
						s.d -= 1;
					else
						s.d += 1;
					nx = nx + ax[s.d];
					ny = ny + ay[s.d];
				}
			}
			s.x = nx;
			s.y = ny;
		}

		int[][] speed = new int[R][C];
		int[][] direction = new int[R][C];
		int[][] size = new int[R][C];

		for (int i = 0; i < sharks.size(); i++) {
			Shark s = sharks.get(i);

			if (size[s.x][s.y] != 0) {
				if (size[s.x][s.y] < s.z) { //���� ���ǹ� speed�� �־ ������� speed�� 0�γ��� ���� 
					size[s.x][s.y] = s.z;
					speed[s.x][s.y] = s.s;
					direction[s.x][s.y] = s.d;
				}

			} else {
				size[s.x][s.y] = s.z;
				speed[s.x][s.y] = s.s;
				direction[s.x][s.y] = s.d;
			}
		}
		
		sharks.clear();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (size[i][j] != 0)
					sharks.add(new Shark(i, j, speed[i][j], direction[i][j], size[i][j]));

			}
		}
	}

	public static void print() {
		System.out.println(sharks.toString());
	}
}
