package service;
/**
 * @auther 김영문
 *  2021-08-11
 *  매출관리용 service작성
 *  전체 매출리스트 조회, 주문번호에 해당하는 리스트 조회
 */
import static util.Common.nextLine;
import static util.Common.printTitle;

import java.util.List;

import dao.SalesManagementDao;
import vo.OrderList;

public class SalesManagementService {
	public SalesManagementDao smDao = new SalesManagementDao();

	// 조회(전체 매출 리스트)
	public void orderList() {
		printTitle(
				"주문날짜            주문번호		회원/비회원       구입구분           제품이름                  갯수         가격                 총 금액");
		List<OrderList> ol = smDao.getProduct();
		for (OrderList p : ol) {
			System.out.println(p);
		}

	}
	// 조회 주문번호로 해당 리스트조회
	public void lookup() {
		String no = nextLine("주문번호를 입력하세요>");
		if (smDao.findby(no)) {
			System.out.println("입력한 주문정보가 존재하지 않습니다.");
			return;
		} else {
			printTitle(
					"주문날짜            주문번호		회원/비회원       구입구분           제품이름                  갯수         가격                 총 금액");
			List<OrderList> orl = smDao.getProduct(no);
			for (OrderList p : orl) {
				System.out.println(p);
			}
		}
	}
}