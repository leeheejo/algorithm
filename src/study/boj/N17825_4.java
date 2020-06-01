package study.boj;

import java.util.Arrays;
import java.util.Scanner;

public class N17825_4 {

	public static class Node {
		int v;
		boolean isEmpty, isEnd;
		Node shortcut, next;

		public Node(int v) {
			super();
			this.v = v;
			this.isEmpty = true;
			this.isEnd = false;
			shortcut = null;
			next = null;
		}

		static Node getNode(int v) { // 여기틀림
			Node tmp = start;
			while (true) {
				if (tmp == null)
					return null;
				if (v == tmp.v)
					return tmp;
				tmp = tmp.next;
			}
		}

		Node addNext(int v) {
			Node next = new Node(v);
			this.next = next;
			return next;
		}
	}

	static Node start;
	static Node[] markers = new Node[4];
	static int[] dice = new int[10];
	static int[] order = new int[10];
	static int ans = Integer.MIN_VALUE;
	static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++)
			dice[i] = sc.nextInt();

		init();
		Arrays.fill(markers, start);
		dfs(0);
		System.out.println(ans);
	}

	public static void dfs(int index) {
		if (index == 10) {
			Arrays.fill(markers, start);
			int a = play();
			ans = Math.max(a, ans);
			recover();

			return;
		}
		for (int i = 0; i < 4; i++) {
			order[index] = i;
			dfs(index + 1);
		}

	}

	public static int play() {
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			Node cur = markers[order[i]];
			cur.isEmpty = true;
			int mv = dice[i];

			for (int k = 0; k < mv; k++) {
				if (k == 0 && cur.shortcut != null) {
					cur = cur.shortcut;
				} else {
					cur = cur.next;
				}

			}

			if (!cur.isEnd && !cur.isEmpty)
				return Integer.MIN_VALUE;
			else {
				cur.isEmpty = false;
				markers[order[i]] = cur;
				sum += cur.v;
			}
		}
		return sum;
	}

	public static void init() {
		start = new Node(0);
		Node end, center;
		Node tmp = start;
		for (int i = 2; i <= 40; i += 2) {
			tmp = tmp.addNext(i);
		}

		end = tmp.addNext(0); // 여기 주의
		end.isEnd = true;
		end.next = end;

		center = new Node(25);
		tmp = center.addNext(30);
		tmp = tmp.addNext(35);
		tmp.next = Node.getNode(40);

		tmp = Node.getNode(10);
		tmp = tmp.shortcut = new Node(13);
		tmp = tmp.addNext(16);
		tmp = tmp.addNext(19);
		tmp.next = center;

		tmp = Node.getNode(20);
		tmp = tmp.shortcut = new Node(22);
		tmp = tmp.addNext(24);
		tmp.next = center;

		tmp = Node.getNode(30);
		tmp = tmp.shortcut = new Node(28);
		tmp = tmp.addNext(27);
		tmp = tmp.addNext(26);
		tmp.next = center;
	}

	public static void recover() {
		for (int i = 0; i < 4; i++) {
			markers[i].isEmpty = true;
		}
	}
}
