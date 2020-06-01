package study.programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class PG42629 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int stock = 4;
		int[] dates = { 4, 10, 15 };
		int[] supplies = { 20, 5, 10 };
		int k = 30;
		System.out.println(solution(stock, dates, supplies, k));

	}

	public static int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		int dateIndex = 0;

		// dates에 들어있는 날짜에 공급되는 밀가루는 작업 시작 전 새벽에 공급되는 것을 기준으로 합니다.
		// 예를 들어 9일째에 밀가루가 바닥나더라도, 10일째에 공급받으면 10일째에는 공장을 운영할 수 있습니다.
		for (int day = 0; day < k; day++) {

			// 1. stock-=1
			// 2. dates index 필요, if( dates[index] == day ) priority_queue(add)
			// 3. if (stock == 0) stock += priority_queue.poll() answer++

			if (dateIndex < dates.length && dates[dateIndex] == day) {
				pq.add(supplies[dateIndex++]);
			}

			if (stock == 0) {
				int newStock = pq.poll();
				stock += newStock;
				answer++;
			}
			--stock;
		}
		return answer;
	}
}
