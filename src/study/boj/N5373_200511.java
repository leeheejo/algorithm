package study.boj;

import java.util.Scanner;

public class N5373_200511 {

	static char[][] up = { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };
	static char[][] down = { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
	static char[][] front = { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
	static char[][] back = { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
	static char[][] left = { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
	static char[][] right = { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };

	static int K;

	public static void main(String[] args) {
		// TODO Ato-generated method stub\
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			up = new char[][] { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };
			down = new char[][] { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
			front = new char[][] { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
			back = new char[][] { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
			left = new char[][] { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
			right = new char[][] { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };

			int num = sc.nextInt();
			for (int j = 0; j < num; j++) {
				String[] s = sc.next().split(" ");
				for (int k = 0; k < s.length; k++) {
					char side = s[k].charAt(0);
					char dir = s[k].charAt(1);
					play(side, dir);
				}

			}

			print(up);
			
		}

	}

	public static void play(char side, char dir) {
		int idx = 0;
		if (dir == '+') {
			idx = 1;
		} else if (dir == '-') {
			idx = 3;
		}

		switch (side) {
		case 'U':
			for (int i = 0; i < idx; i++) {
				up();
			}
			break;
		case 'D':
			for (int i = 0; i < idx; i++) {
				down();
			}
			break;
		case 'L':
			for (int i = 0; i < idx; i++) {
				left();
			}
			break;
		case 'R':
			for (int i = 0; i < idx; i++) {
				right();
			}
			break;
		case 'F':
			for (int i = 0; i < idx; i++) {
				front();
			}
			break;
		case 'B':
			for (int i = 0; i < idx; i++) {
				back();
			}
			break;

		}

	}

	// ��
	public static void up() {
		rotate(up);
		char temp1 = right[0][0];
		char temp2 = right[0][1];
		char temp3 = right[0][2];

		right[0][0] = back[0][0];
		right[0][1] = back[0][1];
		right[0][2] = back[0][2];

		back[0][0] = left[0][0];
		back[0][1] = left[0][1];
		back[0][2] = left[0][2];

		left[0][0] = front[0][0];
		left[0][1] = front[0][1];
		left[0][2] = front[0][2];

		front[0][0] = temp1;
		front[0][1] = temp2;
		front[0][2] = temp3;
	}

	public static void down() {
		rotate(down);
		char temp1 = right[2][0];
		char temp2 = right[2][1];
		char temp3 = right[2][2];

		right[2][0] = front[2][0];
		right[2][1] = front[2][1];
		right[2][2] = front[2][2];

		front[2][0] = left[2][0];
		front[2][1] = left[2][1];
		front[2][2] = left[2][2];

		left[2][0] = back[2][0];
		left[2][1] = back[2][1];
		left[2][2] = back[2][2];

		back[2][0] = temp1;
		back[2][1] = temp2;
		back[2][2] = temp3;

	}


	public static void right() {
		rotate(right);
		char temp1 = up[0][2];
		char temp2 = up[1][2];
		char temp3 = up[2][2];

		up[0][2] = front[0][2];
		up[1][2] = front[1][2];
		up[2][2] = front[2][2];

		front[0][2] = down[2][0];
		front[1][2] = down[1][0];
		front[2][2] = down[0][0];

		down[2][0] = back[2][0];
		down[1][0] = back[1][0];
		down[0][0] = back[0][0];

		back[2][0] = temp1;
		back[1][0] = temp2;
		back[0][0] = temp3;
	}

	public static void left() {
		rotate(left);
		char temp1 = up[0][0];
		char temp2 = up[1][0];
		char temp3 = up[2][0];

		up[0][0] = back[2][2];
		up[1][0] = back[1][2];
		up[2][0] = back[0][2];

		back[2][2] = down[2][2];
		back[1][2] = down[1][2];
		back[0][2] = down[0][2];

		down[2][2] = front[0][0];
		down[1][2] = front[1][0];
		down[0][2] = front[2][0];

		front[0][0] = temp1;
		front[1][0] = temp2;
		front[2][0] = temp3;
	}

	public static void front() {
		rotate(front);
		char temp1 = up[2][0];
		char temp2 = up[2][1];
		char temp3 = up[2][2];

		up[2][0] = left[2][2];
		up[2][1] = left[1][2];
		up[2][2] = left[0][2];

		left[2][2] = down[2][0];
		left[1][2] = down[2][1];
		left[0][2] = down[2][2];

		down[2][0] = right[0][0];
		down[2][1] = right[1][0];
		down[2][2] = right[2][0];

		right[0][0] = temp1;
		right[1][0] = temp2;
		right[2][0] = temp3;

	}

	public static void back() {
		rotate(back);

		char temp1 = up[0][0];
		char temp2 = up[0][1];
		char temp3 = up[0][2];

		up[0][0] = right[0][2];
		up[0][1] = right[1][2];
		up[0][2] = right[2][2];

		right[0][2] = down[0][0];
		right[1][2] = down[0][1];
		right[2][2] = down[0][2];

		down[0][0] = left[2][0];
		down[0][1] = left[1][0];
		down[0][2] = left[0][0];

		left[2][0] = temp1;
		left[1][0] = temp2;
		left[0][0] = temp3;
	}

	public static void rotate(char[][] side) {
		char[][] temp = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				temp[i][j] = side[3 - j - 1][i];
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				side[i][j] = temp[i][j];
			}
		}
	}

	public static void print(char[][] side) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(side[i][j]);
			}
			System.out.println();
		}
	}

}
