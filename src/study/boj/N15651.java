package study.boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

//�ð��ʰ� ����... => bufferedReader ����ؾ��� 
public class N15651 {
	static int N, M;
	static int[] arr = new int[8];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		bw.flush(); // �̰Ŷ�
		run(0);
		bw.close();  //�̰� ���ϴϱ� ����� �ȵ���!! 
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
			arr[index] = i;
			run(index + 1);
		}
	}

}
