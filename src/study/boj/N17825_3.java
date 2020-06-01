package study.boj;

import java.util.Arrays;
import java.util.Scanner;

public class N17825_3 {
	static Node start;
	static Node[] markers = new Node[4];
	static int[] dice = new int[10];
	static int[] order = new int[10];
	static int MAX = Integer.MIN_VALUE;

	public static class Node {
		int v;
		boolean isEnd, isEmpty;
		Node next, shortcut;

		public Node(int v) {
			super();
			this.v = v;
			this.isEnd = false;
			this.isEmpty = true;
			this.next = null;
			this.shortcut = null;
		}

		static Node getNode(int v) {
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
			Node n = new Node(v);
			this.next = n;
			return n;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + "]";
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			dice[i] = sc.nextInt();
		}
		init();
		dfs(0);

		System.out.println(MAX);
	}

	public static void init() {
		start = new Node(0);
		Node end = null;
		Node center = new Node(25);

		Node temp = start;
		for (int i = 2; i <= 40; i += 2) {
			temp = temp.addNext(i);
		}

		end = temp.addNext(0);
		end.next = end;
		end.isEnd = true;

		// 13 16 18 25
		temp = Node.getNode(10);
		temp = temp.shortcut = new Node(13); // �� ����ٰ� add next�ϸ� �ȵɱ�..? ����next �� ������ϱ�!
		temp = temp.addNext(16);
		temp = temp.addNext(19);
		temp.next = center;

		// 20 22 24 25
		temp = Node.getNode(20);
		temp = temp.shortcut = new Node(22);
		temp = temp.addNext(24);
		temp.next = center;

		// 30 28 27 26 25
		temp = Node.getNode(30);
		temp = temp.shortcut = new Node(28);
		temp = temp.addNext(27);
		temp = temp.addNext(26);
		temp.next = center;

		// 25 30 35 40
		temp = center.addNext(30);
		temp = temp.addNext(35);
		temp.next = Node.getNode(40);

	}

	public static void dfs(int index) {
		if (index == 10) {
			Arrays.fill(markers, start);
			int a = play();
			MAX = Math.max(a, MAX);
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

		for (int j = 0; j < 10; j++) {
			Node cur = markers[order[j]];
			int move = dice[j];// 이번칸에 움직일 숫자...
			cur.isEmpty = true;

			for (int m = 0; m < move; m++) {
				if (m == 0 && cur.shortcut != null)
					cur = cur.shortcut;
				else {
					cur = cur.next;
				}
			}

			if (!cur.isEnd && !cur.isEmpty) // 옮기려는 칸이 엔드가 아닌데 칸이차있으면 안 옮긴..
				return 0;
			else {
				sum += cur.v;
				markers[order[j]] = cur;
				cur.isEmpty = false;
			}
		}

		return sum;
	}

	public static void recover() {
		for (int i = 0; i < 4; i++) {
			markers[i].isEmpty = true;
		}
	}
}
