package study;

import java.util.Scanner;

//처음에 중복 제거 하는 방식으로 접근했다가 도저히 못풀겠어서 풀이를 봄 
//그러고 보니 문자들이 오름차순이 아니면...스킵하면 되는거엿음. ㅠㅠ
public class N15650 {

	static int N, M;
	static int[] arr = new int[8];
	static boolean[] visited = new boolean[9];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		run(0);

	}

	public static void run(int index) {
		if (index == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			if (index == 0 || (index > 0 && arr[index - 1] < i)) {
				arr[index] = i;
				visited[i] = true;
				run(index + 1);
				visited[i] = false;
			}
		}

	}

}
