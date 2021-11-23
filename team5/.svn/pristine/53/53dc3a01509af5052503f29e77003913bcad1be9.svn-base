package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.InventoryManage;
import vo.MemberManage;
import vo.Order;
import vo.OrderMenu;
import vo.Recipe;
import vo.SaleProductImpl;
/**
 * @auther 서정목
 *  2021-08-05
 *  구매용 dao작성
 *  1.회원검색
 *  2.상품정보 취득
 *  3.재고리스트 취득
 *  4.레시피리스트 취득
 *  5.주문등록
 */
public class OrderDao {

	//회원검색
	public MemberManage findbyMember(String id, String pw) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		MemberManage mInfo = null;
		try (Connection conn = DBConn.getConnection()) {

			// 일반회원 검색쿼리문
			sql = "Select MEMBERNO,ID,PW,NAME,PHONENO,ADDR,POINT,MANAGERFLAG From T_MEMBERMANAGE WHERE ManagerFlag = 2 And Id = ? and Pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mInfo = new MemberManage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mInfo;
	}

	// 상품정보 취득
	// TestEx.ShowList(os, ?)에서 호출
	// 인자 c1 = 1 일경우T_SALEPRODUCT.ProductSelect가 1의 레코드만 취득 와플정보
	// 인자 c1 = 2 일경우T_SALEPRODUCT.ProductSelect가 2의 레코드만 취득 음료정보
	// 인자 c1 = 3 T_SALEPRODUCT.ProductSelect구분없이 전상품정보 반환
	public List<SaleProductImpl> getProduct(int iFlag) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;

		List<SaleProductImpl> pt = new ArrayList<SaleProductImpl>();

		try (Connection conn = DBConn.getConnection()) {
			//
			if (iFlag == 1 || iFlag == 2) { // 1: 와플, 2:음료
				sql = "Select ProductCode,ProductName,ProductSelect,Price From T_SALEPRODUCT Where ProductSelect = ? Order By ProductSelect,ProductCode";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, iFlag);
			} else {
				sql = "Select ProductCode,ProductName,ProductSelect,Price From T_SALEPRODUCT Order By ProductSelect,ProductCode";
				pstmt = conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// pt = new ArrayList<>() 에 취득정보 추가
				pt.add(new SaleProductImpl(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), 0));
			}
			rs.close();
			pstmt.close();
			return pt;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pt;
	}
	
	//재고리스트 취득
	public List<InventoryManage> getInventory() {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		List<InventoryManage> invtry = new ArrayList<InventoryManage>();
		try (Connection conn = DBConn.getConnection()) {
			//
	
			sql = "Select INGREDIENTCODE,NOWINVENTORY From T_INVENTORYMANAGE Order By INGREDIENTCODE";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// pt = new ArrayList<>() 에 취득정보 추가
				invtry.add(new InventoryManage(rs.getString(1), "", 0, rs.getInt(2),0,0));
			}
			rs.close();
			pstmt.close();
			return invtry;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invtry;		 
	}
	
	//레시피리스트 취득
	public List<Recipe> getRecipe() {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		List<Recipe> recipe = new ArrayList<Recipe>();
		try (Connection conn = DBConn.getConnection()) {
			//	
			sql = "Select PRODUCTCODE,INGREDIENTCODE,INGREDIENTQUANTITY From T_RECIPE Order By PRODUCTCODE,INGREDIENTCODE";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// pt = new ArrayList<>() 에 취득정보 추가
				recipe.add(new Recipe(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}
			rs.close();
			pstmt.close();
			return recipe;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;		 
	}

	//주문등록
	public boolean insertNewOrder(Order order) {

		String sql;
		String OrderNo;
		PreparedStatement pstmt;
		ResultSet rs;
		String iCode = "";// 재료코드
		int iAmount = 0; //재료량

		try (Connection conn = DBConn.getConnection()) {

			sql = "Select to_Char(sysdate, 'YYMMDD') ||LPAD(ORDER_SEQ.NextVal,4,'0') from Dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			OrderNo = rs.getString(1); // 주문번호 취득 yymmdd + 4자리 연속번호(외쪽0채움)
			rs.close();
			pstmt.close();

			// 신규등록용 쿼리문
			sql = "Insert into T_Order(OrderNo,MemberNo,PurchaseCategory,PayMethod,Price,OrderDate) values (?,?,?,?,?,SysDate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, OrderNo); // 주문번호
			pstmt.setInt(2, order.getMemberNo()); // 회원번호
			pstmt.setInt(3, order.getPurchaseCategory()); // 구입구분
			pstmt.setInt(4, order.getPayMethod()); // 결제방법
			pstmt.setLong(5, order.getPrice()); // 주문금액
			pstmt.executeUpdate();
			pstmt.close();
			
			// 주문상품명세 등록
			int idx = 0;
			for (OrderMenu om : order.getMenu()) {
				sql = "Insert into T_OrderMenu (ORDERNO,ORDERSUBNO,PRODUCTCODE,PRODUCTNAME,QUANTITY,PRICE) values (?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, OrderNo); // 주문번호
				pstmt.setInt(2, ++idx); // 명세번호
				pstmt.setString(3, om.getProductCode()); // 상품코드
				pstmt.setString(4, om.getProductName()); // 상품명
				pstmt.setInt(5, om.getQuantity()); // 수량
				pstmt.setLong(6, om.getPrice()); // 금액
				pstmt.executeUpdate();
				pstmt.close();
				
				// 재료 감산
				sql = "Select IngredientCode,ingredientQuantity From T_RECIPE Where ProductCode = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, om.getProductCode()); // 상품코드
				rs = pstmt.executeQuery();
				while (rs.next()) {
					iCode = rs.getString(1);
					iAmount = rs.getInt(2) * om.getQuantity();  //재료량 * 수량
					sql = "Update T_INVENTORYMANAGE Set NowInventory = NowInventory - ? where IngredientCode =?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, iAmount); // 감산재료량
					pstmt.setString(2, iCode); // 재료코드
					pstmt.executeUpdate();
				}
				rs.close();
				pstmt.close();
			}

			// Ponint부여
			if ((order.getMemberNo() != 0) && (order.getPayMethod() != 3)) {
				sql = "Update T_MEMBERMANAGE Set Point = Point + ? Where MemberNo = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, order.getPrice() / 10);
				pstmt.setInt(2, order.getMemberNo());
				pstmt.executeUpdate();
				pstmt.close();
			}

			if (order.getPayMethod() == 3) {
				// Ponint로 결제시 포인트 차감
				sql = "Update T_MEMBERMANAGE Set Point = Point - ? Where MemberNo = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, order.getPrice());
				pstmt.setInt(2, order.getMemberNo());
				pstmt.executeUpdate();
				pstmt.close();

			} else {
				// 관리자 에 매출액 가산
				sql = "Update T_MEMBERMANAGE Set Point = Point + ? Where MANAGERFLAG = 1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, order.getPrice());
				pstmt.executeUpdate();
				pstmt.close();
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
