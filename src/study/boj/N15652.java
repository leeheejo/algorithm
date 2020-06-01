package study.boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class N15652 {
	static int N, M;
	static int[] arr = new int[8];

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		bw.flush();
		run(0);
		bw.close();

	}

	public static void run(int index) throws IOException {
		if (index == M) {
			for (int i = 0; i < M; i++) {
				bw.write(arr[i] + " ");
			}
			bw.write("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (index == 0 || (index > 0 && arr[index - 1] <= i)) {
				arr[index] = i;
				run(index + 1);
			}
		}
	}

}
