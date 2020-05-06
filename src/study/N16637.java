package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class N16637 {

	public static int t;// 괄호의 수
	public static int N;
	public static int ans = Integer.MIN_VALUE;
	public static char[] arr;
	public static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new char[N];
		visited = new boolean[N];

		String s = bf.readLine();
		for (int i = 0; i < N; i++) {
			arr[i] = s.charAt(i);
		}

		for (int i = 0; i <= (N - 1) / 3; i++) {
			t = i;
			dfs(0, 0);
		}

		System.out.println(N == 1 ? arr[0] - '0' : ans);
	}

	public static void dfs(int index, int c) {
		if (t == index) {
			ArrayList<Character> op = new ArrayList<Character>();
			ArrayList<Integer> num = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				if (!visited[i])
					if (i % 2 == 0)
						num.add(arr[i] - '0');
					else
						op.add(arr[i]);
				else {
					int tmp = arr[i] - '0';
					int tp = arr[++i];
					if (tp == '+') {
						tmp += arr[++i] - '0';
					} else if (tp == '*') {
						tmp *= arr[++i] - '0';
					} else if (tp == '-') {
						tmp -= arr[++i] - '0';
					}
					num.add(tmp);
				}
			}
			int a = calc(op, num);
			ans = Math.max(a, ans);
			return;
		}

		for (int i = c; i < N - 2;) {
			if (visited[i] == true) {
				i++;
				continue;
			}
			if (i % 2 == 1) {
				i++;
				continue;
			}

			visited[i] = true;
			visited[i + 1] = true;
			visited[i + 2] = true;
			dfs(index + 1, i + 3);
			visited[i] = false;
			visited[i + 1] = false;
			visited[i + 2] = false;
			i += 2;
		}

	}

	public static int calc(ArrayList<Character> op, ArrayList<Integer> num) {
		int a = num.get(0);
		for (int i = 0; i < op.size(); i++) {

			if (op.get(i) == '+') {
				a += num.get(i + 1);
			} else if (op.get(i) == '*') {
				a *= num.get(i + 1);
			} else if (op.get(i) == '-') {
				a -= num.get(i + 1);
			}

		}
		return a;
	}
}
