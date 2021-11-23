package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
/**
 * @auther 서정목
 *  2021-08-05
 *  DB초기처리용 dao작성
 *  1.테이블 작성
 *  2.뷰작성
 *  3.시퀀스작성
 *  4.유저등록(관리자1명,일반회원1명)
 *  5.상품정보,재료, 레시피 초기데이터 등록 
 */
public class OracleInit {
	
	public void init() {
		String sql;
		PreparedStatement pstmt;
		boolean isDbInit = true;

		// DB초기화
		if (isDbInit) {

			try (Connection conn = DBConn.getConnection()) {

				// 기존테이블이 존재할경우 삭제처리
				deleteObject(conn,"T_ORDER","T");

				//테이블이 존재하지 않을경우 Create Table문으로 테이블 신규작성
				sql = "Create Table T_ORDER (";
				sql += "OrderNo           Varchar2(10),"; // 주문번호 YYYYMMDD + 0001 연속
				sql += "MemberNo          Number(5),"; // 회원번호
				sql += "PurchaseCategory  Number(1),"; // 구분 1.포장, 2.매장내식사, 3. 배달
				sql += "PayMethod         Number(1),"; // 지불구분 1.현금, 2.카드, 3.포인트
				sql += "Price             Number(10),"; // 주문금액
				sql += "OrderDate         Date,"; // 주문일
				sql += "Constraint T_ORDER_PK Primary KEY(OrderNO)";  //T_ORDER테이블의 주Key를 정의. OrderNO는 중복불가, Null값허용안됨
				sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeQuery();				
				pstmt.close();

				// T_ORDERMENU 테이블삭제
				deleteObject(conn,"T_ORDERMENU","T");
				
				//Create Table문으로 테이블 신규작성
				sql = "Create Table T_ORDERMENU (";
				sql += "OrderNo           Varchar2(10),"; // 주문번호 YYYYMMDD + 0001 연속   
				sql += "OrderSubNo        Number(1),";    // 명세번호 
				sql += "ProductCode       Varchar2(3),";  // 상품코드
				sql += "ProductName       VARCHAR2(50),"; // 상품명
				sql += "Quantity          Number(5),";    // 수량
				sql += "Price             Number(10),";   // 금액
				sql += "Constraint T_ORDERMENU_PK Primary KEY(OrderNo,OrderSubNo)";  //주Key.
				sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
				
				// T_MEMBERMANAGE 삭제
				deleteObject(conn,"T_MEMBERMANAGE","T");

				sql = "Create Table T_MEMBERMANAGE (";
				sql += "MemberNo          Number(5),"; // 회원번호
				sql += "Id                Varchar2(10),"; //
				sql += "PW                Varchar2(10),"; //
				sql += "Name              Varchar2(30),"; // 이름
				sql += "PhoneNo           Varchar2(20),"; // 전화번호
				sql += "Addr              Varchar2(100),"; // 주소
				sql += "Point             Number(10),";   // 포인트
				sql += "ManagerFlag       Number(1),"; // 관리자구분 1.관리자, 2.유저
				sql += "Constraint T_MEMBERMANAGE_PK Primary KEY(MemberNo)"; //주Key.
				sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();			
				pstmt.close();

				// 재고테이블삭제
				deleteObject(conn,"T_INVENTORYMANAGE","T");
				
				sql = "Create Table T_INVENTORYMANAGE (";
				sql += "IngredientCode        Varchar2(3),"; // 재료코드I1, I2, I3 ~
				sql += "IngredientName        Varchar2(50),"; // 재료코드I1, I2, I3 ~
				sql += "AppropriateInventory  Number(7),";   // 적정재고량
				sql += "NowInventory          Number(7),";   // 현재고량
				sql += "PurchasePrice         Number(10),";  // 구입단가
				sql += "Unit                  Number(7),";   // 구입단위
				sql += "Constraint T_INVENTORYMANAGE_PK Primary KEY(IngredientCode)";
				sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();

				// 레시피테이블삭제
				deleteObject(conn,"T_RECIPE","T");

				sql = "Create Table T_RECIPE (";
				sql += "ProductCode           Varchar2(3),"; // 상품코드 와플 W1,W2,W3.... 음료 D1,D2,D3...
				sql += "IngredientCode        Varchar2(5),"; // 재료코드
				sql += "ingredientQuantity    Number(7),"; // 수량
				sql += "Constraint T_RECIPE_PK Primary KEY(ProductCode,IngredientCode)";
				sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();				
				pstmt.close();

				// 판매상품초기화
				deleteObject(conn,"T_SALEPRODUCT","T");
 
				sql = "Create Table T_SALEPRODUCT (";
				sql += "ProductCode       Varchar2(3)"; // 상품코드 와플 W1,W2,W3.... 음료 D1,D2,D3...
				sql += ",ProductName      Varchar2(50)"; // 상품명
				sql += ",ProductSelect    Number(1)"; // 구분 1.와플메뉴, 2.음료메뉴
				sql += ",Price            Number(10)"; // 가격
				sql += ", Constraint T_SALEPRODUCT_PK Primary KEY(ProductCode)";
				sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();		
				pstmt.close();
				
				//매출리스트 조회용 뷰작성
				//T_Order와 T_OrderDetail을 주문번호로 연결해 하나의 뷰 작성
				sql = "CREATE OR REPLACE VIEW V_ORDERLIST AS";
				sql += " SELECT o.OrderNo,o.MemberNo,o.PurchaseCategory,o.PayMethod,o.Price TotalPrice,o.OrderDate,";
				sql += " od.OrderSubNo,od.ProductCode,od.ProductName,od.Quantity,od.Price";
				sql += " FROM T_ORDER o LEFT OUTER JOIN T_ORDERMENU od ON o.OrderNo = od.OrderNo ";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
				
				//레시피 조회용 뷰작성
				//T_RECIPE와 T_SALEPRODUCT,T_INVENTORYMANAGE을 상품번호, 재료번호로 연결해 하나의 뷰 작성
				sql = "CREATE OR REPLACE VIEW V_RECIPE AS";
				sql += " SELECT rcp.ProductCode,sp.ProductName,rcp.IngredientCode,ivt.IngredientName,rcp.ingredientQuantity";
				sql += " FROM T_RECIPE rcp LEFT OUTER JOIN T_SALEPRODUCT sp ON rcp.ProductCode = sp.ProductCode ";
				sql += "      LEFT OUTER JOIN T_INVENTORYMANAGE ivt ON rcp.IngredientCode = ivt.IngredientCode ";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
				
				//회원번호용시퀀스초기화
				deleteObject(conn,"MEMBER_SEQ","S");
				
			    //회원번호용시퀀스작성: 시퀀스이름 MEMBER_SEQ, 시작숫자 1, 증감숫자 1, 최소값 1,최대값 99999, 순환하지않음
				sql = "CREATE SEQUENCE MEMBER_SEQ START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999 NOCYCLE";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
				
				//오더번호용시퀀스초기화
				deleteObject(conn,"ORDER_SEQ","S");
				
				//오더번호용시퀀스작성: 시퀀스이름 ORDER_SEQ,시작숫자 1 ,증감숫자 1, 최소값 1,최대값 9999, 순환함
				sql = "CREATE SEQUENCE ORDER_SEQ START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999 CYCLE";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();

				//관리자 1명 신규등록 (id: admin,  pw:1234, Point:50,0000 ManagerFlag:1)
				sql = "Insert Into T_MEMBERMANAGE (MemberNo,Id,Pw,Name,PhoneNo,Addr,Point,ManagerFlag) values (";
				sql += "MEMBER_SEQ.NEXTVAL,'admin','1234','휴먼','123-4567-8900','서울시 영등포구',500000,1)";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
			
				//일반유저 1명 신규등록 (id: test,  pw:1234, Point:0 ManagerFlag:2)
				sql = "Insert Into T_MEMBERMANAGE (MemberNo,Id,Pw,Name,PhoneNo,Addr,Point,ManagerFlag) values (";
				sql += "MEMBER_SEQ.NEXTVAL,'test','1234','휴먼','123-4567-8900','서울시 영등포구',0,2)";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
				
				//txt파일에서 DB등록
				int iCnt;
				boolean bHead;
				String[] TableData = {"RECIPE","SALEPRODUCT","INVENTORYMANAGE"};
				for (int i = 0; i < TableData.length; i++) {
					String tableName = "T_" + TableData[i];
					String fileName  = TableData[i] + ".txt";
					String[] fieldName = null;
					iCnt = 0;
					bHead = true;
					File f = new File(fileName);
					if (f.exists()) {
						BufferedReader br = new BufferedReader(new FileReader(fileName));
						String line = "";
						String[] aStr;
						while ((line = br.readLine()) != null) {  //1줄씩 읽기
							aStr = line.split("\t");               //탭구분 aStr배열변수에 대입
							//1열데이터에서 필드명, 필드수 취득
							if (bHead) {
								fieldName = aStr;
								sql = "Insert into " + tableName + " (";
								for (int j = 0; j < fieldName.length; j++) {
									if (j > 0) {sql += ",";} 
									sql += fieldName[j]; 
								}
								sql += ") values (";
								for (int j = 0; j < fieldName.length; j++) {
									if (j > 0) {sql += ",";} 
									sql += "?"; 
								}
								sql += ")"; 
								//System.out.println(sql);
								//System.out.println("fileName:"+fileName + ", tableName:" + tableName + ",filedName:" + Arrays.toString(fieldName));
								bHead = false;
							} else {
								aStr = line.split("\t");
								if (aStr.length == fieldName.length) {
									//등록용 쿼리문
									pstmt = conn.prepareStatement(sql);
									for (int j = 0; j < fieldName.length; j++) {
										pstmt.setString(j+1, aStr[j].trim()); 
									}
									iCnt = iCnt + pstmt.executeUpdate(); //pstmt.executeUpdate() 실행결과가 int(등록수결과)로 반환됨. iCnt에 누적등록수를 기록
									pstmt.close();
								}
							}
						}
						//System.out.println(tableName + "테이블 " + iCnt + "건 레코드추가");
						br.close();
					}
				}				
//				System.out.println("처리완료!");

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//테이블(시퀀스) 존재여부 체크, 존재시 테이블(시퀀스) 삭제
	private void deleteObject(Connection conn, String pName, String tsFlag) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		boolean tableExist = false;
		String objName;
		
		try {
			//TEST유저가 보유한 테이블에서 테이블 명으로 존재여부확인용쿼리문(sql)
			if (tsFlag.equals("T")) {
				objName = "table";
				sql = "select count(*) From User_tables where Table_name = ?";
			} else {
				objName = "sequence";
				sql = "select count(*) From User_Sequences where Sequence_Name = ?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1) { tableExist = true;}
			rs.close();
			pstmt.close();	
			if (tableExist) {
				sql = "drop " + objName + " " + pName;
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				pstmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
