package study.programmers;

public class PG42862 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };
		System.out.println(solution(n, lost, reserve));
	}

	public static int solution(int n, int[] lost, int[] reserve) {
		int answer = n - lost.length;
		for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if (reserve[j] == -1 && lost[i] == -1)
					continue;
				if (lost[i] == reserve[j]) {
					lost[i] = -1;
					reserve[j] = -1;
					answer++;
				}
			}
		}
		
	       for(int i =0; i<reserve.length; i++){
	            for(int j =0; j<lost.length; j++){
	                //자격박탈된애들 빼주기
	                if(lost[j]==-10 && reserve[i]==-10){
	                    continue;
	                }
	                //reserve 자격 박탈
	                if(lost[j]==reserve[i]){
	                    lost[j]=-10;
	                    reserve[i]=-10;
	                    answer++;
	                }
	            }
	        }

		for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if (reserve[j] == -1 || lost[i] == -1)
					continue;
				if (Math.abs(lost[i] - reserve[j]) == 1) {
					reserve[j] = -1;
					answer++;
					break;
				}
			}
		}

		return answer;
	}

}
