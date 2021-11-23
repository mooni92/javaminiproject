package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.InventoryManage;

/**
 * @auther 김재은
 *  2021-08-12
 *  재고관리용 dao작성
 *  레시피등록처리
 *  레시피수정처리
 *  레시피삭제처리
 *  재고 정보 취득
 *  재료코드로 검색
 */
public class InventoryManageDao {
	public List<InventoryManage> getProduct() {
		String sql;
		PreparedStatement pstmt;
		ResultSet im;

		List<InventoryManage> ime = new ArrayList<>();

		try (Connection conn = DBConn.getConnection()) {
			// 카테고리 지정없는 쿼리문 (재고리스트 취득용)
			sql = "Select IngredientCode ,IngredientName,AppropriateInventory,NowInventory,PurchasePrice,Unit From T_INVENTORYMANAGE order by IngredientCode";

			pstmt = conn.prepareStatement(sql);
			im = pstmt.executeQuery();
			while (im.next()) {
				// im.getString(1) 첫번째 필드명 재료코드(IngredientCode)가 반환됨 				
				// im.getString(2) 두번째 필드명 재료이름(IngredientName)이 반환됨
				// im.getInt(3) 세번째 필드명 적정재고(AppropriateInventory)가 반환됨
				// im.getInt(4) 네번째 필드명 현재재고(NowInventory)이 반환됨
				// im.getLong(5) 네번째 필드명 구매가격(PurchasePrice)이 반환됨
				// im.getInt(6) 네번째 필드명 구입단위(Unit)이 반환됨

				// pt = new ArrayList<>() 에 취득정보 추가
				ime.add(new InventoryManage(im.getString(1), im.getString(2), im.getInt(3), im.getInt(4), im.getLong(5),
						im.getInt(6)));
			}
			im.close();
			pstmt.close();
			return ime;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ime;
	}

	// 상품등록처리
	// TestEx.Insert(OracleService os)에서 호출
	public int add(InventoryManage i) {
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {

			// 상품코드로 기존데이터 확인, 중복존재하면 Insert문 실행시 오라클 주키 중복 에러 발생.
			// 신규등록용 코드가 이미 존재할 경우 처리를 중단함.
			if (findby(i.getIngredientCode()))
				return iRtn; // 중복 방지

			// 신규등록용 쿼리문
			sql = "Insert into T_INVENTORYMANAGE (IngredientCode ,IngredientName,AppropriateInventory,NowInventory,PurchasePrice,Unit) values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getIngredientCode().trim()); // 재료코드
			pstmt.setString(2, i.getIngredientName().trim()); // 재료명
			pstmt.setInt(3, i.getAppropriateInventory()); // 적정재고량
			pstmt.setInt(4, i.getNowInventory()); // 현재고량
			pstmt.setLong(5, i.getPurchasePrice()); // 구입단가
			pstmt.setInt(6, i.getUnit()); // 구입단위
			iRtn = pstmt.executeUpdate(); // pstmt.executeUpdate()등록결과(등록수)를 변수에 대입
			pstmt.close();
			return iRtn; // 호출한 곳에 레코드 등록수를 반환 성공할 경우 1, 실패일 경우 0
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRtn;
	}

	// 상품수정처리
	// TestEx.Update(OracleService os)에서 호출
	public int update(InventoryManage i) {
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {
			// 수정용 쿼리문
			sql = "Update T_INVENTORYMANAGE Set IngredientName = ?,AppropriateInventory = ?,NowInventory = ?, PurchasePrice =?, Unit =?  where  IngredientCode = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, i.getIngredientName().trim()); // 재료명
			pstmt.setInt(2, i.getAppropriateInventory()); // 적정재고량
			pstmt.setInt(3, i.getNowInventory()); // 현재고량
			pstmt.setLong(4, i.getPurchasePrice()); // 구입단가
			pstmt.setInt(5, i.getUnit()); // 구입단위
			pstmt.setString(6, i.getIngredientCode().trim()); // 재료코드
			iRtn = pstmt.executeUpdate(); // 실행결과를 iRtn에 대입 수정성공일 경우 1이 대입됨
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRtn;
	}

	// 상품삭제처리
	// TestEx.Delete(OracleService os)에서 호출
	public int delete(String iCd) { // 상품코드,재료코드만 파라미터로 받음
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {

			// 삭제용 쿼리문
			sql = "Delete  T_INVENTORYMANAGE where   IngredientCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iCd); // 상품코드
			iRtn = pstmt.executeUpdate(); // 실행결과를 iRtn에 대입 삭제성공일 경우 1이 대입됨
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRtn;
	}

	// 상품코드로 T_SALEPRODUCT 내 데이터 존재여부확인
	// 파라미터 Cd :상품코드
	// 해당 상품코드가 존재할 경우 true를 반환함.
	// 해당 상품코드가 존재하지 않을 경우 false를 반환함.
	public boolean findby(String iCd) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		boolean rtn = false;
		try (Connection conn = DBConn.getConnection()) {

			// 상품코드로 검색쿼리문
			sql = "Select Count(*) From T_INVENTORYMANAGE WHERE IngredientCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iCd);

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
		}
		return rtn;
	}

	// 재료 구입
	public int materialpurchase(String iCd) {
		int iRtn = 0;
		String sql;
		ResultSet rs;
		String iName = ""; // 재료이름
		int ai = 0; // 적정 재고
		int ni = 0; // 현재 재고
		long pPrice = 0; // 구입단가
		int unit = 0; // 단위
		int iAmount = 0; // 구입량
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {
			// 수정용 쿼리문
			sql = "Select IngredientName,AppropriateInventory,NowInventory ,PurchasePrice ,Unit  From T_INVENTORYMANAGE  where   IngredientCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iCd);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ai = rs.getInt(2);
				ni = rs.getInt(3);
				pPrice = rs.getInt(4);
				unit = rs.getInt(5);
				iAmount = ai - ni;
				pPrice = iAmount * pPrice / unit;
				sql = "update T_INVENTORYMANAGE set NOWINVENTORY = NOWINVENTORY + ? where  INGREDIENTCODE = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, iAmount); // 구입량
				pstmt.setString(2, iCd);
				 iRtn += pstmt.executeUpdate(); 
				sql = " update T_MEMBERMANAGE set  POINT =  POINT - ? where  MANAGERFLAG = 1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, pPrice); // 구입금액
				 iRtn += pstmt.executeUpdate(); 
			}
			rs.close();
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRtn;
	}
}
