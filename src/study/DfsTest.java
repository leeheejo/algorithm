package study;

public class DfsTest {
	static int[] arr = { 1, 2, 3, 4 };
	static boolean[] visited = new boolean[4];
	static int[] sel = new int[2];
// for 문을 0부터 시작한 결과 
//	1 1 
//	1 2 
//	1 3 
//	1 4 
//	2 1 
//	2 2 
//	2 3 
//	2 4 
//	3 1 
//	3 2 
//	3 3 
//	3 4 
//	4 1 
//	4 2 
//	4 3 
//	4 4 

//for문 0부터 시작하고 visited 쓴결과 (모든 경우의 수)
//	1 2 
//	1 3 
//	1 4 
//	2 1 
//	2 3 
//	2 4 
//	3 1 
//	3 2 
//	3 4 
//	4 1 
//	4 2 
//	4 3 

//for문 c부터 시작 
//visited 쓴 결과  == 매개변수c를 i+1 로 준 결 (조합) 
//	1 2 
//	1 3 
//	1 4 
//	2 3 
//	2 4 
//	3 4 

//안쓴결과 
//	1 1 
//	1 2 
//	1 3 
//	1 4 
//	2 2 
//	2 3 
//	2 4 
//	3 3 
//	3 4 
//	4 4 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dfs(0, 0);
	}

	public static void dfs(int index, int c) {
		if (index == 2) {
			print();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (visited[i])
				continue;

			sel[index] = arr[i];
			visited[i] = true;
			dfs(index + 1, i + 1);
			visited[i] = false;
		}
	}

	public static void print() {
		for (int i = 0; i < 2; i++)
			System.out.print(sel[i] + " ");
		System.out.println();
	}
}
