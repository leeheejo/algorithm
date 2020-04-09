package study;

import java.util.Scanner;

public class N2748 {

	static int N;
	
	// int 배열로 했다가 틀렸다. 어지간하면 long으로 초기화 하자
	//	byte	-128 ~ 127
	//	short	-32768 ~ 32767
	//	int	-2147483648 ~ 2147483647
	//	long	-9223372036854775808 ~ 9223372036854775807
	
	static long[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new long[N + 1];
		arr[0] = 0;
		arr[1] = 1;

		for (int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}

		System.out.println(arr[N]);
	}
}
