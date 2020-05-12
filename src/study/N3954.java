package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class N3954 {
	public static class Pair {
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}

	public static Stack<Integer> stack;
	public static int mem_size;
	public static int code_len;
	public static int input_size;

	public static String text;
	public static String program;
	public static Pair[] pairs;
	public static int[] mem;

	public static int inputInx;
	public static int memIdx;
	public static int loopCount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testNum = Integer.parseInt(br.readLine());

		for (int i = 0; i < testNum; i++) {
			init();
			String[] temp = br.readLine().split(" ");
			mem_size = Integer.parseInt(temp[0]);
			code_len = Integer.parseInt(temp[1]);
			input_size = Integer.parseInt(temp[2]);
			
			program = br.readLine();
			text = br.readLine();
			
			readBracket();
			solve();
		}

	}

	public static void readBracket() {
		for (int i = 0; i < code_len; i++) {
			if (program.charAt(i) == '[') {
				stack.push(i);
				pairs[i] = new Pair(i, 0);
			} else if (program.charAt(i) == ']') {
				int temp = stack.pop();
				pairs[temp].right = i;
				pairs[i] = new Pair(temp, i);
			}

		}

	}

	public static void solve() {
		int idx = 0;
		int max_index = 0;
		while (true) {
			char command = program.charAt(idx);

			switch (command) {
			case '-':
				mem[memIdx] = (mem[memIdx] - 1) % 256;
				break;
			case '+':
				mem[memIdx] = (mem[memIdx] + 1) % 256;
				break;
			case '<':
				if (memIdx == 0) {
					memIdx = mem_size - 1;
				} else {
					memIdx--;
				}
				break;
			case '>':
				if (memIdx == mem_size - 1) {
					memIdx = 0;
				} else {
					memIdx++;
				}
				break;
			case '[':
				if (mem[memIdx] == 0) {
					idx = pairs[idx--].right;
					loopCount++;
				}
				break;
			case ']':
				if (mem[memIdx] != 0) {
					idx = pairs[idx--].left; // idx-- 왜하는걸까?? -> 마지막에 ++ 공통으로 해주기 때문에
					loopCount++;
				}
				break;
			case '.':
				break;
			case ',':
				if (inputInx == input_size) {
					mem[memIdx] = 255;
				} else {
					mem[memIdx] = (int) text.charAt(inputInx++);
				}
				break;
			}
			idx++;
			loopCount++;

			if (idx > max_index) // 루프에 들어가면 그 값보다 큰 게 못나오니까...
				max_index = idx;

			if (idx == code_len) {
				System.out.println("Terminates");
				break;
			}

			if (loopCount > 50000000) {
				System.out.println("Loops" + " " + pairs[max_index].left + " " + pairs[max_index].right);
				break;
			}
		}
	}

	public static void init() {
		inputInx = 0;
		memIdx = 0;
		loopCount = 0;

		stack = new Stack<Integer>();
		pairs = new Pair[4096];

		mem = new int[100001];
		Arrays.fill(mem, 0);
	}
}
