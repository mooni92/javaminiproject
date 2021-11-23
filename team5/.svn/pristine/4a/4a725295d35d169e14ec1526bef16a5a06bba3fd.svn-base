package util;

import java.util.Scanner;
/**
 * 
 * @author MiniTeam5조
 * 
 */
public class Common {

	static Scanner scanner = new Scanner(System.in);

	//숫자(int) 입력용 스캐너
	public static int nextInt(String text) {
		return Integer.parseInt(nextLine(text));
	}

	//숫자(long) 입력용 스캐너
	public static long nextLong(String text) {
		return Long.parseLong(nextLine(text));
	}

	//문자 입력용 스캐너
	public static String nextLine(String text) {
		System.out.print(text);
		return scanner.nextLine();
	}

	//숫자(int) 입력용 스캐너 From, To 사이의 숫자만 입려가능.
	public static int nextInt(String text, int from, int to) {
		boolean Ok = false;
		int val = 0;
		while (!Ok) {
			try {
				val = Integer.parseInt(nextLine(text));
				Ok = true;
				if (val < from || val > to) {
					Ok = false;
					System.out.print(from + "에서 " + to + "사이의 숫자만 입력해 주세요.");
				}
			} catch (NumberFormatException ex2) {
				System.out.print("ERROR:" + ex2.getMessage() + ", 숫자를 입력해 주세요 >");
			}
		}

		return val;
	}

	//상하 라인과 동시 화면표시용 메서드
	public static void printTitle(String line) {
		printLine();
		System.out.println("   " + line);
		printLine();
	}

	//고정라인길이 표시용 메서드
	public static void printLine() {
		System.out.println("========================================================================================================================================================");
	}
	
	//상하 라인과 동시 화면표시용 메서드, 라인길이는 파라미터로 조정
	public static void printTitle(String line, int iCnt) {
		printLine(iCnt);
		System.out.println("   " + line);
		printLine(iCnt);
	}
	
	//라인길이 표시용 메서드, 라인길이는 파라미터로 조정
	public static void printLine( int iCnt) {		
		String str ="";
		for (int i = 0; i < iCnt; i++)	str += "=";
		System.out.println(str);
	}
}
