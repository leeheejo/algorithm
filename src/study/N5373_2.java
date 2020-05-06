package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N5373_2 {
	// 3차원 배열은 너무 빡세니 이런식으로 2차원 배열 여러개 만드는 걸 연습해보자!!
	static char[][] up;
	static char[][] down;
	static char[][] front;
	static char[][] back;
	static char[][] left;
	static char[][] right;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			up = new char[][] { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };
			down = new char[][] { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
			front = new char[][] { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
			back = new char[][] { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
			left = new char[][] { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
			right = new char[][] { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };

			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int z = 0; z < n; z++) {
				String str = st.nextToken();
				char side = str.charAt(0);
				char dir = str.charAt(1);

				if (dir == '+') {
					clock(side);
				} else { // 시계방향3번 = 반시계1번
					clock(side);
					clock(side);
					clock(side);
				}
			}

			for (int i = 0; i < up.length; i++) {
				for (int j = 0; j < up[i].length; j++) {
					System.out.print(up[i][j]);
				}
				System.out.println();
			}
		}

	}

	public static void clock(char side) {

		if (side == 'U') {
			rotateSelf(up);

			char tmp1 = back[0][0];
			char tmp2 = back[0][1];
			char tmp3 = back[0][2];

			back[0][0] = left[0][0];
			back[0][1] = left[0][1];
			back[0][2] = left[0][2];

			left[0][0] = front[0][0];
			left[0][1] = front[0][1];
			left[0][2] = front[0][2];

			front[0][0] = right[0][0];
			front[0][1] = right[0][1];
			front[0][2] = right[0][2];

			right[0][0] = tmp1;
			right[0][1] = tmp2;
			right[0][2] = tmp3;

		} else if (side == 'D') {
			rotateSelf(down);

			char tmp1 = back[2][0];
			char tmp2 = back[2][1];
			char tmp3 = back[2][2];

			back[2][0] = right[2][0];
			back[2][1] = right[2][1];
			back[2][2] = right[2][2];

			right[2][0] = front[2][0];
			right[2][1] = front[2][1];
			right[2][2] = front[2][2];

			front[2][0] = left[2][0];
			front[2][1] = left[2][1];
			front[2][2] = left[2][2];

			left[2][0] = tmp1;
			left[2][1] = tmp2;
			left[2][2] = tmp3;

		} else if (side == 'F') { // 이놈들이 헷갈리네 주의
			rotateSelf(front);
			char tmp1 = up[2][0];
			char tmp2 = up[2][1];
			char tmp3 = up[2][2];

			up[2][0] = left[2][2];
			up[2][1] = left[1][2];
			up[2][2] = left[0][2];

			left[2][2] = down[2][0];
			left[1][2] = down[2][1];
			left[0][2] = down[2][2];

			down[2][0] = right[0][0];
			down[2][1] = right[1][0];
			down[2][2] = right[2][0];

			right[2][0] = tmp3;
			right[1][0] = tmp2;
			right[0][0] = tmp1;

		} else if (side == 'B') {
			rotateSelf(back);

			char tmp1 = up[0][0];
			char tmp2 = up[0][1];
			char tmp3 = up[0][2];

			up[0][0] = right[0][2];
			up[0][1] = right[1][2];
			up[0][2] = right[2][2];

			right[2][2] = down[0][2];
			right[1][2] = down[0][1];
			right[0][2] = down[0][0];

			down[0][0] = left[2][0];
			down[0][1] = left[1][0];
			down[0][2] = left[0][0];

			left[2][0] = tmp1;
			left[1][0] = tmp2;
			left[0][0] = tmp3;

		} else if (side == 'L') {
			rotateSelf(left);

			char tmp1 = up[0][0];
			char tmp2 = up[1][0];
			char tmp3 = up[2][0];

			up[0][0] = back[2][2];
			up[1][0] = back[1][2];
			up[2][0] = back[0][2];

			back[2][2] = down[2][2];
			back[1][2] = down[1][2];
			back[0][2] = down[0][2];

			down[2][2] = front[0][0];
			down[1][2] = front[1][0];
			down[0][2] = front[2][0];

			front[0][0] = tmp1;
			front[1][0] = tmp2;
			front[2][0] = tmp3;

		} else if (side == 'R') {
			rotateSelf(right);

			char tmp1 = up[0][2];
			char tmp2 = up[1][2];
			char tmp3 = up[2][2];

			up[0][2] = front[0][2];
			up[1][2] = front[1][2];
			up[2][2] = front[2][2];

			front[2][2] = down[0][0];
			front[1][2] = down[1][0];
			front[0][2] = down[2][0];

			down[0][0] = back[0][0];
			down[1][0] = back[1][0];
			down[2][0] = back[2][0];

			back[0][0] = tmp3;
			back[1][0] = tmp2;
			back[2][0] = tmp1;

		}

	}
//왜 이거 넣으면 마지막꺼만 틀리게 나올까?? 
//	public static void rotateSelf(char[][] arr) { // 해당 면을 시계방향으로 돌리는 것
//		char[][] tmp = new char[3][3];
//		tmp[0][0] = arr[2][0];
//		tmp[0][1] = arr[1][0];
//		tmp[0][2] = arr[0][0];
//
//		tmp[1][0] = arr[2][1];
//		tmp[1][1] = arr[1][1];
//		tmp[1][2] = arr[0][1];
//
//		tmp[2][0] = arr[2][2];
//		tmp[2][1] = arr[1][2];
//		tmp[2][2] = arr[0][2];
//
//		arr = tmp.clone();
//
//	}
	static void rotateSelf(char[][] arr) {
		char tmp1 = arr[0][0];
		char tmp2 = arr[0][1];
		char tmp3 = arr[0][2];

		arr[0][0] = arr[2][0];
		arr[0][1] = arr[1][0];
		arr[0][2] = tmp1;

		arr[2][0] = arr[2][2];
		arr[1][0] = arr[2][1];

		arr[2][1] = arr[1][2];
		arr[2][2] = tmp3;

		arr[1][2] = tmp2;
	}
}
