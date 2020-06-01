package study.programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PG42746 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = { 3, 30, 34, 5, 9 };
		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {
		String answer = "";

		String[] arr = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			arr[i] = (String.valueOf(numbers[i]));
		}

		Arrays.sort(arr, new Comparator<String>() { // Comparator �� ��� �� ��쿡 �ڸ��� �����Ѵ�.
			@Override
			public int compare(String s1, String s2) { //s1�� �ڿ����� ��
				System.out.println(s2 + s1 + " " + s1 + s2);
				System.out.println((s2 + s1).compareTo(s1 + s2));
				return (s2 + s1).compareTo(s1 + s2); // ���� ���������� �� �տ� ���� ������ �����Ѵ�.
			}
		});

		if (arr[0].equals("0")) return "0";
		
		for (int i = 0; i < arr.length; i++) {
			answer += arr[i];
		}
		return answer;
	}
}
