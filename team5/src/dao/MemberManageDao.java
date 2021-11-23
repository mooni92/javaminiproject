package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberManage;
/**
 * @auther 양희찬
 *  2021-08-11
 *  회원관리 서비스
 *  회원가입처리
 *  회원조회출력
 */
public class MemberManageDao {
	public Boolean findbyManager(String id, String pw) {
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean bRtn = false; 
	try (Connection conn = DBConn.getConnection()) {

		// 일반회원 검색쿼리문
		sql = "Select count(*) From T_MEMBERMANAGE WHERE ManagerFlag = 1 And Id = ? and Pw = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			if ( rs.getInt(1) >=1 ) {bRtn = true;}
		}
		rs.close();
		pstmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bRtn;
}
	
	//회원 가입처리
	public int insertData(MemberManage dto){
		int result = 0;

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

			try {
			sql = "insert into T_MEMBERMANAGE (MemberNo,Id,PW,Name,PhoneNo,Addr,Point,ManagerFlag) ";
			sql+= "values (MEMBER_SEQ.NEXTVAL,?,?,?,?,?,0,2)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhoneNo());
			pstmt.setString(5, dto.getAddr());
			
			result = pstmt.executeUpdate();
			pstmt.close();			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	//탈퇴/삭제
	public int deleteDate(String id, String pw){
		int result = 0;
		
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "delete naverMember where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;	
	}
	
	// 회원 조회 출력
	public List<MemberManage> getMemberList() {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;

		List<MemberManage> memList = new ArrayList<>();

		try (Connection conn = DBConn.getConnection()) {
			//카테고리 지정없는 쿼리문 (전상품정보 취득용)
			sql = "Select MemberNo,Id,PW,Name,PhoneNo,Addr,Point,ManagerFlag From T_MEMBERMANAGE order by MemberNo";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memList.add(new MemberManage(rs.getInt(1), rs.getString(2) ,rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8)));
			// int memberNo, String id, String pw, String name, String phoneNo, String addr, int point, int managerFlag
			}
			rs.close();
			pstmt.close();
			return memList;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return memList;
	}

}



