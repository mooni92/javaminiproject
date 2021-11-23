package service;
import static util.Common.nextLine;
import static util.Common.printTitle;

import dao.MemberManageDao;

public class MemberManageService {
	public MemberManageDao memberDao = new MemberManageDao();
	
	public boolean managerCheck(String id, String pw) {
		return memberDao.FindbyManager(id, pw);
	}
	
	public void joinMember(){
		printTitle(" 회원 추가 ");
		
		String id = nextLine("사용하실 아이디를 입력하세요:");
		String pw = nextLine("사용하실 비밀번호를 입력하세요:");
		
		/*
		System.out.println("사용하실 아이디를 입력하세요:");
		String id = scan.nextLine("사용하실 아이디를 입력하세요:");
		System.out.println("사용하실 비밀번호를 입력하세요:");
		String pw = scan.nextLine();
		System.out.println("이름을 입력하세요:");
		name = scan.nextLine();
		System.out.println("주소를 입력하세요:");
		String addr = scan.nextLine();
		System.out.println("전화번호를 입력하세요:");
		String phoneNo = scan.nextLine();
		
		MemberManage.add = new Member(id,pw,name,addr,phoneNo); 
		*/		
	}




	public void list() {  // OrderEx 회원메뉴
		printTitle(" 회뭔 목록 조회 ");
	}

}