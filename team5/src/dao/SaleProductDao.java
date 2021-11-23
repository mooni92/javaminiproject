package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.SaleProduct;
/**
 * @auther 서정목
 *  2021-08-05
 *  판매상품관리용 dao작성
 *  상품정보 취득
 *  상품등록처리
 *  상품수정처리
 *  상품삭제처리
 *  상품코드로검색
 */
public class SaleProductDao {

	// 상품정보 취득
	public List<SaleProduct> getProduct() {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;

		List<SaleProduct> pt = new ArrayList<>();

		try (Connection conn = DBConn.getConnection()) {
			//카테고리 지정없는 쿼리문 (전상품정보 취득용)
			sql = "Select ProductCode,ProductName,ProductSelect,Price From T_SALEPRODUCT Order By ProductSelect,ProductCode";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//rs.getString(1)  첫번째 필드명 상품코드(ProductCode)가 반환됨   Prouct.ProductCode 타입 String에 맞추어 rs.getString(1) 사용
				//rs.getString(2)  두번째 필드명 상품명(ProductName)이 반환됨 
				//rs.getInt(3)     세번째 필드명 카테고리(ProductSelect)가 반환됨   Prouct.ProductSelect 타입 int에 맞추어 rs.getInt(3) 사용
				//rs.getInt(4)     네번째 필드명 가격(Price)이 반환됨 
				
				//pt = new ArrayList<>() 에 취득정보 추가
				pt.add(new SaleProduct(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
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

	// 상품등록처리
	public int productAdd(SaleProduct p) {
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {

			// 상품코드로 기존데이터 확인, 중복존재하면 Insert문 실행시 오라클 주키 중복 에러 발생.
			// 신규등록용 코드가 이미 존재할 경우 처리를 중단함.
			if (productFindby(p.getProductCode()))	return iRtn;  //중복 방지

			//신규등록용 쿼리문
			sql = "Insert into T_SALEPRODUCT (ProductCode,ProductName,ProductSelect,Price) values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProductCode().trim()); //상품코드
			pstmt.setString(2, p.getProductName().trim()); //상품명
			pstmt.setInt(3, p.getProductSelect());         //카테고리     
			pstmt.setInt(4, p.getPrice());            //가격
			iRtn = pstmt.executeUpdate();  //pstmt.executeUpdate()등록결과(등록수)를 변수에 대입
			pstmt.close();
			return iRtn;       //호출한 곳에 레코드 등록수를 반환  성공할 경우 1, 실패일 경우 0 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return iRtn;
	}

	// 상품수정처리
	public int productUpdate(SaleProduct p) {
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {
			//수정용 쿼리문
			sql = "Update T_SALEPRODUCT Set ProductName = ?, ProductSelect = ?,Price = ? Where ProductCode =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProductName().trim());    //상품명
			pstmt.setInt(2, p.getProductSelect());            //카테고리
			pstmt.setInt(3, p.getPrice());               //가격
			pstmt.setString(4, p.getProductCode().trim());    //상품코드
			iRtn = pstmt.executeUpdate();                //실행결과를 iRtn에 대입 수정성공일 경우 1이 대입됨
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return iRtn;
	}
	
	// 상품삭제처리
	public int productDelete(String code) {  //상품코드만 파라미터로 받음
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;
		
		try (Connection conn = DBConn.getConnection()) {
				
			//삭제용 쿼리문
			sql = "Delete T_SALEPRODUCT Where ProductCode =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,code);       //상품코드
			iRtn =  pstmt.executeUpdate(); //실행결과를 iRtn에 대입  삭제성공일 경우 1이 대입됨
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return iRtn;
	}

	//상품코드로 T_SALEPRODUCT 내 데이터 존재여부확인
	// 파라미터 Cd :상품코드
	// 해당 상품코드가 존재할 경우 true를 반환함.
	// 해당 상품코드가 존재하지 않을 경우 false를 반환함.
	public boolean productFindby(String Cd) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		boolean rtn = false;
		try (Connection conn = DBConn.getConnection()) {

			//상품코드로 검색쿼리문
			sql = "Select Count(*) From T_SALEPRODUCT WHERE ProductCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Cd);

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
