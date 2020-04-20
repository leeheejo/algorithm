package study;

import java.util.Scanner;

//dfs + 백트레킹으로 풀었으나 시간과초과 남 ㅋ
//생각해보 시간초과가 나는게 당연함 100이 인풋일 경우에 
// 100* 100 * 100*... 백벅하는꼴이니 ...

public class N10844_re {
	static int N;
	static int[] visited = new int[101];
	static long answer = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		run(0);
		System.out.println(answer % 1000000000);

	}

	public static void run(int index) {
		if (index == N) {
			answer++;
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (index == 0 && i == 0)
				continue;
			if (index > 0 && Math.abs(visited[index - 1] - i) != 1)
				continue;

			visited[index] = i;
			run(index + 1);
			visited[index] = 0;
		}
	}

}
