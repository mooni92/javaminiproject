package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Recipe;
import vo.RecipeImpl;

public class RecipeDao {


	/**
	 * @auther 김영문
	 *  2021-08-11
	 *  레시피용 dao작성
	 *  레시피정보 취득
	 *  레시피등록처리
	 *  레시피수정처리
	 *  레시피삭제처리
	 *  제품코드, 재료코드로검색
	 */
	
	//레시피 정보 취득
	public List<RecipeImpl> getProduct() {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;

		List<RecipeImpl> rp = new ArrayList<>();

		try (Connection conn = DBConn.getConnection()) {
			//카테고리 지정없는 쿼리문 (전상품정보 취득용)
			sql = "Select productCode ,productName,ingredientCode,ingredientName,ingredientQuantity From V_RECIPE order by productCode ,ingredientCode";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//rs.getString(1)  첫번째 필드명 상품코드(ProductCode)가 반환됨   ProductCode 타입 String에 맞추어 rs.getString(1) 사용
				//rs.getString(2)  두번째 필드명 상품명(ProductName)이 반환됨 	ProductName 타입 String에 맞추어 rs.getString(2) 사용
				//rs.getString(3)     세번째 필드명 재료코드(IngredientCode)가 반환됨   IngredientCode 타입 String에 맞추어 rs.getString(3) 사용
				//rs.getString(4)     네번째 필드명 재료이름(IngredientName)이 반환됨 	IngredientName 타입 String에 맞추어 rs.getString(4) 사용
				//rs.getInt(5)     다섯번째 필드명 재료 양(IngredientQuantity)이 반환됨  IngredientQuantity 타입 int에 맞추어 rs.getInt(5) 사용
				
				//rp = new ArrayList<>() 에 취득정보 추가
				rp.add(new RecipeImpl(rs.getString(1), rs.getString(2) ,rs.getString(3),rs.getString(4),rs.getInt(5)));
			}
			rs.close();
			pstmt.close();
			return rp;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rp;
	}

	// 레시피 등록처리
	public int add(Recipe r) {
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {

			// 제품코드, 재료코드로 기존데이터 확인, 중복존재하면 Insert문 실행시 오라클 주키 중복 에러 발생.
			// 신규등록용 코드가 이미 존재할 경우 처리를 중단함.
			if (findby(r.getProductCode(), r.getIngredientCode()))	return iRtn;  //중복 방지

			//신규등록용 쿼리문
			sql = "Insert into T_RECIPE (productCode,ingredientCode,ingredientQuantity) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getProductCode().trim()); //상품코드
			pstmt.setString(2, r.getIngredientCode().trim()); //재료코드
			pstmt.setInt(3, r.getIngredientQuantity());         // 재료량                //가격
			iRtn = pstmt.executeUpdate();  //pstmt.executeUpdate()등록결과(등록수)를 변수에 대입
			pstmt.close();
			return iRtn;       //호출한 곳에 레코드 등록수를 반환  성공할 경우 1, 실패일 경우 0 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return iRtn;
	}

	// 레시피 수정처리
	public int update(Recipe r) {
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;

		try (Connection conn = DBConn.getConnection()) {
			//수정용 쿼리문
			sql = "Update T_RECIPE Set ingredientQuantity = ?  where  productCode = ? and ingredientCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  r.getIngredientQuantity()); 	//재료 량
			pstmt.setString(2, r.getProductCode().trim());    //제품코드
			pstmt.setString(3, r.getIngredientCode().trim());  // 재료코드
			iRtn = pstmt.executeUpdate();                //실행결과를 iRtn에 대입 수정성공일 경우 1이 대입됨
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return iRtn;
	}
	
	// 레시피 삭제처리
	public int delete(String pCd, String iCd) {  //상품코드,재료코드만 파라미터로 받음
		int iRtn = 0;
		String sql;
		PreparedStatement pstmt;
		
		try (Connection conn = DBConn.getConnection()) {
				
			//삭제용 쿼리문
			sql = "Delete  T_RECIPE where   productCode = ? and ingredientCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pCd);       //상품코드
			pstmt.setString(2,iCd);       //재료코드
			iRtn =  pstmt.executeUpdate(); //실행결과를 iRtn에 대입  삭제성공일 경우 1이 대입됨
			pstmt.close();
			return iRtn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRtn;
	}


	//상품코드로 T_RECIPE 내 데이터 존재여부확인
	// 파라미터 pCd:제품코드, iCd: 재료코드
	// 해당 제품,재료코드가 존재할 경우 true를 반환함.
	// 해당 제품,재료코드가 존재하지 않을 경우 false를 반환함.
	public boolean findby(String pCd, String iCd) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		boolean rtn = false;
		try (Connection conn = DBConn.getConnection()) {

			//상품코드로 검색쿼리문
			sql = "Select Count(*) From T_RECIPE WHERE ProductCode = ? and ingredientCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCd);
			pstmt.setString(2, iCd);

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
}
