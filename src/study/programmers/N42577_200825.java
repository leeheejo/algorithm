package study.programmers;

public class N42577_200825 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] phone_book = { "119", "97674223", "1195524421" };

		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		int len = phone_book.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (i == j)
					continue;
				if (phone_book[i].startsWith(phone_book[j]))
					return false;
			}
		}
		return true;
	}
}
