package study.programmers;

import java.util.PriorityQueue;

public class N42626 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] scoville = { 1, 2, 3};
		int K = 11;

		System.out.println(solution(scoville, K));
	}

	public static int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < scoville.length; i++) {
			q.add(scoville[i]);
		}

		while (true) {
			if (q.size() == 1 && q.peek() < K)
				return -1;
			boolean flag = true;
			int num1 = q.poll();
			if (num1 < K) {
				flag = false;
				answer++;
				int num2 = q.poll();
				int num3 = num1 + (num2 * 2);
				q.add(num3);
			}

			if (flag)
				break;
		}

//        while (heap.peek() <= K) {
//            if (heap.size() == 1) {
//                return -1;
//            }
//            int a = heap.poll();
//            int b = heap.poll();
//
//
//            int result = a + (b * 2);
//
//            heap.offer(result);
//            answer ++;
//        }
		return answer;
	}
}
