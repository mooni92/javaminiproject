package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.OrderList;
import vo.SalesManagement;
/**
 * @auther 김영문
 *  2021-08-11
 *  매출관리용 dao작성
 *  상품정보 취득

 *  주문번호로 검색
 */
public class SalesManagementDao {
	
	// 상품정보 취득
	public List<OrderList> getProduct() {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;

		List<OrderList> ol = new ArrayList<>();

		try (Connection conn = DBConn.getConnection()) {
			// 카테고리 지정없는 쿼리문 (전상품정보 취득용)
			sql = "Select orderNo,memberNo,purchaseCategory,payMethod,totalPrice,orderDate,orderSubNo,productCode,productName,quantity,price From V_ORDERLIST Order By orderNo";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				// pt = new ArrayList<>() 에 취득정보 추가
				ol.add(new OrderList(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getLong(5),
						rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10),
						rs.getLong(11)));
			}
			rs.close();
			pstmt.close();
			return ol;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ol;
	}
	
	// 상품정보 취득
	public List<OrderList> getProduct(String orderNo) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;

		List<OrderList> ol = new ArrayList<>();

		try (Connection conn = DBConn.getConnection()) {
			// 카테고리 지정없는 쿼리문 (전상품정보 취득용)
			sql = "Select orderNo,memberNo,purchaseCategory,payMethod,totalPrice,orderDate,orderSubNo,productCode,productName,quantity,price From V_ORDERLIST  where orderNo = ? Order By orderNo";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				ol.add(new OrderList(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getLong(5),
						rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10),
						rs.getLong(11)));
			}
			rs.close();
			pstmt.close();
			return ol;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ol;
	}
	
	//주문번호로 V_ORDERLIST  내 데이터 존재여부확인
		// 파라미터 orderNo :주문번호
		// 해당 주문번호가 존재할 경우 true를 반환함.
		// 해당 주문번호가 존재하지 않을 경우 false를 반환함.
	public boolean findby(String orderNo) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		boolean rtn = false;
		try (Connection conn = DBConn.getConnection()) {

			// 주무 번호로 검색쿼리문
			sql = "Select Count(*) From V_ORDERLIST WHERE OrderNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1)
					rtn = true;
			}
			rs.close();
			pstmt.close();
			return rtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return rtn;
	}
}

