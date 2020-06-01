package study.boj;

//https://buddev.tistory.com/25
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class N17143 {
	public static class Fish implements Comparable<Fish> {
		int r, c, s, d, z;

		public Fish(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		public int getR() {
			return r;
		}

		public void setR(int r) {
			this.r = r;
		}

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}

		public int getS() {
			return s;
		}

		public void setS(int s) {
			this.s = s;
		}

		public int getD() {
			return d;
		}

		public void setD(int d) {
			this.d = d;
		}

		public int getZ() {
			return z;
		}

		public void setZ(int z) {
			this.z = z;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			if (this.c == o.c) {
				if (this.r == o.r)
					return this.z < this.z ? 1 : -1;
				else
					return this.r < o.r ? -1 : 1;
			}
			return this.c < o.c ? -1 : 1;
		}

	}

	static int R, C, M;
	static ArrayList<Fish> list;
	static int[][] size;
	static int[][] speed;
	static int[][] dir;

	static int[] ar = { 0, -1, 1, 0, 0 };
	static int[] ac = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();

		list = new ArrayList<Fish>();

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();
			list.add(new Fish(r - 1, c - 1, s, d, z));
		}

		int time = 0;
		int result = 0;

		while (time < C) {
			Collections.sort(list);

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).c == time) {
					result += list.get(i).z;
					list.remove(i);
					break;
				}
			}

			for (int i = 0; i < list.size(); i++) {
				Fish f = list.get(i);
				int r = f.r;
				int c = f.c;
				int dir = f.d;
				int cnt = f.s;

				while (cnt > 0) {
					r += ar[dir];
					c += ac[dir];
					if (r < 0 || c < 0 || r >= R || c >= C) {
						r -= ar[dir];
						c -= ac[dir];

						if (dir == 1)
							dir = 2;
						else if (dir == 2)
							dir = 1;
						else if (dir == 3)
							dir = 4;
						else if (dir == 4)
							dir = 3;
						continue;
					}
					cnt--;
				}

				list.get(i).setR(r);
				list.get(i).setC(c);
				list.get(i).setD(dir);
			}

			size = new int[R][C];
			speed = new int[R][C];
			dir = new int[R][C];

			for (int i = 0; i < list.size(); i++) {
				int r = list.get(i).r;
				int c = list.get(i).c;

				if (size[r][c] == 0) {
					size[r][c] = list.get(i).z;
					speed[r][c] = list.get(i).s;
					dir[r][c] = list.get(i).d;
				} else {
					if (size[r][c] < list.get(i).z) {
						size[r][c] = list.get(i).z;
						speed[r][c] = list.get(i).s;
						dir[r][c] = list.get(i).d;
					}
				}
			}

			list.clear();

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (size[i][j] != 0) {
						list.add(new Fish(i, j, speed[i][j], dir[i][j], size[i][j]));
					}
				}
			}

			time++;
		}

		System.out.println(result);

	}

}
