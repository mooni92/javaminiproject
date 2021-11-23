package service;

import static util.Common.nextLine;
import static util.Common.printTitle;

import java.util.List;

import dao.MemberManageDao;
import vo.MemberManage;
import vo.OrderMenu;
/**
 * @auther 양희찬
 *  2021-08-11
 *  회원관리 서비스
 *  회원가입 , 회원조회 부분
 */
public class MemberManageService {
	public MemberManageDao memberDao = new MemberManageDao();

	public boolean managerCheck(String id, String pw) {
		return memberDao.findbyManager(id, pw);
	}

	public void joinMember() {
		printTitle(" 회원 추가 ");

		String id = nextLine("사용하실 아이디를 입력하세요:");
		String pw = nextLine("사용하실 비밀번호를 입력하세요:");
		String name = nextLine("사용하실 이름을 입력하세요:");
		String addr = nextLine("사용하실 주소를 입력하세요:");
		String phoneNo = nextLine("사용하실 전화번호를 입력하세요:");

		MemberManage mem = new MemberManage(id, pw, name, phoneNo, addr);
		int res = memberDao.insertData(mem);
		if (res == 1) {
			System.out.println("회원가입을 환영합니다");
		}
	}

	public void list() {
		printTitle("회원번호 아이디    비밀번호      이름        전화번호         주소           포인트  회원구분", 100);

		List<MemberManage> mList = memberDao.getMemberList();
		
		for (MemberManage m : mList) {
			System.out.println(m);
		}
	}
}