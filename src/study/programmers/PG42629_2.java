package study.programmers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PG42629_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int stock = 4;
		int[] dates = { 4, 10, 15 };
		int[] supplies = { 20, 5, 10 };
		int k = 30;
	}

	public static int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		Queue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

		int index = 0;
		for (int i = 0; i < k; i++) {
			if (index < dates.length && i == dates[index])
				priorityQueue.add(supplies[index++]);

			if (stock == 0) {
				stock += priorityQueue.poll();
				answer++;
			}
			stock -= 1;
		}
		return answer;
	}
}
