package vo;
/**
 * @auther 양희찬
 *  2021-08-11
 *  MemberManage(String id, String pw, String name, String phoneNo, String addr)
 *  MemberManage(int memberNo, String id, String pw, String name, String phoneNo, String addr, int point, int managerFlag)
 *  String change(String pw) 비밀번호 두자리출력후 '*'처리
 *  String getLv(int lv) 회원구분관련해서 관리자와 회원구분
 */
public class MemberManage {
	
	public int memberNo;
	public String id;
	public String pw;
	public String name;
	public String phoneNo;
	public String addr;
	private int point;
	private int managerFlag;
	public int no;
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getManagerFlag() {
		return managerFlag;
	}

	public void setManagerFlag(int managerFlag) {
		this.managerFlag = managerFlag;
	}

	public MemberManage(String id, String pw, String name, String phoneNo, String addr) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phoneNo = phoneNo;
		this.addr = addr;
	}
	public MemberManage(int memberNo, String id, String pw, String name, String phoneNo, String addr, int point, int managerFlag) {
		super();
		this.memberNo = memberNo;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phoneNo = phoneNo;
		this.addr = addr;
		this.point = point;
		this.managerFlag = managerFlag;
	}

	@Override
	public String toString() {
//		return "      " +memberNo+ "          " + id + "          " + pw + ", name=" + name + ", phoneNo="
//				+ phoneNo + ", addr=" + addr + ", point=" + point + ", managerFlag=" + managerFlag + "]";
		return String.format("%5d      %-10s  %-10s  %-5s  %-13s  %-10s  %7d  %s  ",memberNo, id, change(pw), getSpace(name,5), phoneNo, getSpace(addr,8), point, getLv(managerFlag));
	}
	
	public String getSpace(String str, int len) {
		if (str == null) {
			str = " ";
		}
		int spaceCount = len - str.length();
		if (spaceCount > 0) {
			for (int i = 0; i < spaceCount; i++) {
				str += "  ";
			}
		}
		return str;
	}
	
	//회원조회 출력시 비밀번호 두자리출력후 나머지 번호 '*'표시
	public String change(String pw) { // pw를 입력받아 String 타입으로 출력시켜주는 change 메서드
		String str="";
		
		for (int i = 0; i < pw.length(); i++) { // pw 길이 값 까지 i를 증가시켜주는 for문
			if(i<2) {
				str+=pw.charAt(i); // i가 2미만일 경우까지 pw를 str변수에 가져온다
			}
			else {
				str+="*"; // i가 2이상일 경우는 *로표시하여 str변수에 가져온다
			}
		}
		return str;
	}
	
	// 삼항연산자를 통한 관리자 회원 구분 메서드
	public String getLv(int lv) {
		return (lv == 1) ? "관리자" : "회원"; // lv값이 1이면 "관리자"를 리턴받고 아닐경우 "회원"을 리턴받는다
		}
}
