package service;

import static util.Common.printTitle;
import static util.Common.nextInt;
import static util.Common.nextLine;
import static util.Common.nextLong;

import java.util.List;

import dao.InventoryManageDao;
import vo.InventoryManage;

public class InventoryManageService {
	/**
	 * @auther 김영문
	 *  2021-08-12
	 *  재고관리용 service작성
	 *  재료 조회, 재료등록, 재료수정, 재료삭제,재료구매
	 */
	public void orderList() {
		printTitle("매출조회");
	}


	public InventoryManageDao imDao = new InventoryManageDao();

	// 조회(전메뉴)
	public void showList() {
		printTitle("재료코드                   재료이름	                      적정재고        현재 재고량         구입단가       단위");
		List<InventoryManage> im = imDao.getProduct();
		for (InventoryManage i : im) {
			System.out.println(i);
		}

	}

	// 등록
	public void insert() {

		int rtn = imDao.add(new InventoryManage(nextLine("재료코드입력 3글자이하입력 >"), nextLine("재료명 입력>"), nextInt("적정재고량 입력>"),
				nextInt("현재고량 입력>"), nextInt("구입단가 입력>"), nextInt("구입단위 입력>")));
		if (rtn == 1)
			System.out.println("등록하였습니다");
	}

	// 수정
	public void update() {
		String iCd = nextLine("수정할 재료코드입력 3글자이하입력 >");
		if (!imDao.findby(iCd)) {
			System.out.println("입력한 상품정보가 존재하지 않습니다.");
			return;
		}

		int rtn = imDao.update(new InventoryManage(iCd, nextLine("재료명 입력>"), nextInt("적정재고량 입력>"), nextInt("현재고량 입력>"),
				nextLong("구입단가 입력>"), nextInt("구입단위 입력>")));
		if (rtn == 1)
			System.out.println("수정하였습니다");
	}

	// 삭제
	public void delete() {
		String iCd = nextLine("삭제할 재료코드입력 >");
		if (!imDao.findby(iCd)) {
			System.out.println("입력한 상품정보가 존재하지 않습니다.");
			return;
		}

		int rtn = imDao.delete(iCd);
		if (rtn == 1)
			System.out.println("삭제하였습니다");
	}
	
		//재료구매
	public void purchase() {
		String iCode = nextLine("구매할 재료코드입력 3글자이하입력 >");
		if (imDao.materialpurchase(iCode) == 2) {
			System.out.println("재료구매했습니다.");
		} else {
			System.out.println("재료구매 에러.");
		}

	}

}
