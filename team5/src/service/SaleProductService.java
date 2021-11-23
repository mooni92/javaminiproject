package service;

import static util.Common.nextInt;
import static util.Common.nextLine;
import static util.Common.printTitle;

import java.util.List;

import dao.SaleProductDao;
import vo.SaleProduct;
/**
 * @auther 서정목
 *  2021-08-05
 *  판매상품관리용 service작성
 *  전메뉴조회, 신규메뉴 등록, 메뉴수정, 메뉴삭제
 */
public class SaleProductService {
	
	public SaleProductDao sdDao = new SaleProductDao();
  
	//전메뉴조회
	public void showList() {

		printTitle("코드  상품명                      금액" , 60);
		List<SaleProduct> pt = sdDao.getProduct();
		for (SaleProduct p: pt) {
			System.out.println(p);
		}
	}

	//신규메뉴 등록
	public void insert() {

		int rtn = sdDao.productAdd(new SaleProduct(nextLine("상품코드입력 3글자이하입력 >"), nextLine("상품명입력>"), nextInt("1.와플, 2.음료 선택>"), nextInt("가격입력>")));
		if (rtn == 1) System.out.println("등록하였습니다");
	}
	
	//메뉴수정
	public void update() {
		String cd = nextLine("수정할 상품코드입력 3글자이하입력 >");
		if (!sdDao.productFindby(cd)) {
			System.out.println("입력한 상품정보가 존재하지 않습니다.");
			return;
		}
		
		int rtn = sdDao.productUpdate(new SaleProduct(cd, nextLine("상품명입력>"), nextInt("1.와플, 2.음료 선택>"), nextInt("가격입력>")));
		if (rtn == 1) System.out.println("수정하였습니다");
	}
	
	//메뉴삭제
	public void delete() {
		String cd = nextLine("삭제할 상품코드입력 >");
		if (!sdDao.productFindby(cd)) {
			System.out.println("입력한 상품정보가 존재하지 않습니다.");
			return;
		}
		
		int rtn = sdDao.productDelete(cd);
		if (rtn == 1) System.out.println("삭제하였습니다");
	}
}
