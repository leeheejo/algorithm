package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 노가다 문제 꼼꼼하게 풀어야 할듯 잔실수가 많이 온다.. 
public class N5373_3 {
	static int N;

	static char[][] up;
	static char[][] down;
	static char[][] front;
	static char[][] back;
	static char[][] left;
	static char[][] right;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		for (int i = 0; i < N; i++) {
//			윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색이다.
			up = new char[][] { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };// 초기화 하는 방법 주의 **
			down = new char[][] { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
			front = new char[][] { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
			back = new char[][] { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
			left = new char[][] { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
			right = new char[][] { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };

			int k = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < k; j++) {
				String s = st.nextToken();
				char side = s.charAt(0);
				char dir = s.charAt(1);
				if (dir == '+') {
					move(side);
				} else if (dir == '-') {
					move(side);
					move(side);
					move(side);
				}
			}
			print();
		}

	}

	public static void move(char side) {
		if (side == 'U') {
			moveSide(up);

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

		} else if (side == 'D') {

			moveSide(down);

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

		} else if (side == 'L') {

			moveSide(left);

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

		} else if (side == 'R') {

			moveSide(right);

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

		} else if (side == 'F') {
			moveSide(front);

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

		} else if (side == 'B') {

			moveSide(back);

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
	}

	// 돌리는 면도 회전해야됨~!
	public static void moveSide(char[][] m) {
		char[][] temp = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				temp[i][j] = m[3 - j - 1][i];
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				m[i][j] = temp[i][j];
			}
		}

	}

	public static void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(up[i][j]);
			}
			System.out.println();
		}
	}
}
