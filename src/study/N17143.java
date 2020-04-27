package study;
//https://buddev.tistory.com/25
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class N17143 {
	public static class Shark {
		int index, r, c, speed, direction, size;
		boolean alive;

		public Shark(int index, int r, int c, int speed, int direction, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
			this.alive = true;
		}
	}

	static int R, C, M;
	static ArrayList<Shark>[][] map;
	static Shark[] sharks;
	static int ans = 0;
	static int[] ar = { 0, -1, 1, 0, 0 };
	static int[] ac = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		map = new ArrayList[R + 1][C + 1];
		sharks = new Shark[M];

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = new ArrayList<Shark>();
			}
		}

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();
			Shark shark = new Shark(i, r, c, s, d, z);
			map[r][c].add(shark);
			sharks[i] = shark;
		}

		for (int i = 1; i <= 2; i++) {
			get(i);
			move();
			print();
			System.out.println();
		}

		System.out.println(ans);

	}

	public static void move() { // 상어이동.. alive 가 false 면 안타게 하자..
		for (int i = 0; i < sharks.length; i++) {
			Shark s = sharks[i];
			if (!s.alive)
				continue;
			int nr = s.r;
			int nc = s.c;
			for (int j = 0; j < s.speed; j++) {
				nr = nr + ar[s.direction];
				nc = nc + ac[s.direction];
				if (nr < 1 || nc < 1 || nr > R || nc > C) { // 범위 이탈하면 방향 변경해서 재시도한다..
					if (s.direction % 2 == 0)
						s.direction -= 1;
					else
						s.direction += 1;
					nr = nr + ar[s.direction] + ar[s.direction];
					nc = nc + ac[s.direction] + ac[s.direction];
				}
			}


			map[s.r][s.c].remove(0);
			s.r = nr;
			s.c = nc;
			if (map[nr][nc].size() == 0) {
				map[nr][nc].add(s);
			} else {
				Shark tmp = map[nr][nc].get(0);
				if (tmp.size < s.size) {
					sharks[tmp.index].alive = false;
					map[nr][nc].remove(0);
					map[nr][nc].add(s);
				} else {
					sharks[s.index].alive = false;
				}
			}

		}

	}

	public static void get(int idx) { // 물고기 잡기..
		for (int i = 1; i <= R; i++) {
			if (map[i][idx].size() != 0) {
				ans += map[i][idx].get(0).size;
				map[i][idx].get(0).alive = false;
				map[i][idx].remove(0);
				break;
			}
		}

	}

	public static void print() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(map[i][j].size() + " ");
			}
			System.out.println();
		}
	}

}
