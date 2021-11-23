package service;

import static util.Common.nextInt;
import static util.Common.nextLine;
import static util.Common.printLine;
import static util.Common.printTitle;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import vo.InventoryManage;
import vo.MemberManage;
import vo.Order;
import vo.OrderMenu;
import vo.Recipe;
import vo.SaleProductImpl;
/**
 * @auther 서정목
 *  2021-08-05
 *  구매처리용 service작성
 *  회원구매, 비회원구매, 구매공통부분
 */
public class OrderService {

	public OrderDao odDao = new OrderDao();

	//회원구매
	public void orderMember() {
		printTitle("회원주문 test/1234",60);

		String id;
		String pw;
		boolean flag = false;

		try {
			MemberManage mInfo = null;
			while (!flag) {
				id = nextLine("회원 아이디를 입력해 주세요,99입력시 중단  >");
				if (id.equals("99")) {
					return;
				}
				pw = nextLine("회원 패스워드를 입력해 주세요,99입력시 중단  >");
				if (pw.equals("99")) {
					return;
				}
				mInfo = odDao.findbyMember(id, pw);
				if (mInfo != null) {
					flag = true;
				} else {
					System.out.println("해당회원정보가 없습니다. 다시입력해 주세요");
				}
			}
			orderExecute(mInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//비회원구매
	public void orderNonMember() {
		printTitle("비회원주문",60);
		try {
			MemberManage mInfo = null;
			orderExecute(mInfo);
		} catch (Exception e) {

		}
	}

	//구매공통부분로직
	public void orderExecute(MemberManage mInfo) {

		List<OrderMenu> om = new ArrayList<>();
		String menu = ""; // 주문메뉴
		int qty = 0; // 주문수량
		boolean flag = false;
		boolean flag2 = false;
		long price = 0;
		int iOrderFlag;
		int purchaseCategory; // 구입구분
		int payMethod = 0; // 지불방법
		int iFlag = 0;
		String mName = "";
		long mPrice = 0;

		try {

			printLine(60);

			// 재고리스트 취득
			List<InventoryManage> invtry = odDao.getInventory();

			// 재고리스트 취득
			List<Recipe> recipe = odDao.getRecipe();

			flag = false;
			List<SaleProductImpl> pt = null;
			String sMenu = "";
			while (!flag) {

				iFlag = nextInt("주문종류를 입력해 주세요. 1.와플주문 2.음료주문  >", 1, 2);
				if (iFlag == 1) {
					sMenu = "와플";
				} else {
					sMenu = "음료";
				}

				System.out.println("코드  상품명                      금액  주문가능수량");
				printLine(60);
				pt = odDao.getProduct(iFlag);
				for (SaleProductImpl p : pt) {
					// 주문가능수량 계산
					setMaximunOrder(p, recipe, invtry);
					System.out.println(p);
				}
				printLine(60);

				SaleProductImpl cPt = null;
				flag2 = false;
				while (!flag2) {
					menu = nextLine("주문할 " + sMenu + "메뉴코드를입력해 주세요,99입력시 중단  >");
					if (menu.equals("99")) {
						return;
					}
					cPt = findByMenu(iFlag, pt, menu);
					if (cPt == null) {
						System.out.println("입력하신 메뉴가 존재하지 않습니다.");
					} else {
						flag2 = true;
					}
				}
				flag2 = false;
				while (!flag2) {
					qty = nextInt("구입수량을입력해 주세요,99입력시 중단 >", 1, 99);
					if (qty == 99) {
						return;
					}
					if (qty > cPt.getMaximumOrder()) {
						System.out.println("주문가능수량을 초과하였습니다. 주문가능수량:" +cPt.getMaximumOrder() + " 주문수량:" + qty);
					} else {
						flag2 = true;
					}
				}

				mName = cPt.getProductName();
				mPrice = cPt.getPrice() * qty;
				price += mPrice;

				// 주문 명세 등록
				om.add(new OrderMenu(menu, mName, qty, mPrice));

				// 주문후 재고수 재조정
				setInventory(menu, qty, recipe, invtry);

				iOrderFlag = nextInt("추가 구매하시겠습니다까?  1.예  2.아니오  >", 1, 2);
				if (iOrderFlag == 2)
					flag = true;
			}

			purchaseCategory = nextInt("구입구분을 선택하세요 1.포장 2.매장내식사 3.배달  >", 1, 3);

			if (purchaseCategory == 3) {
				// 배달비추가
				om.add(new OrderMenu("000", "배달비", 1, 3000));
				price += 3000;
			}

			printLine(60);
			System.out.println("구입상품                      수량     금액");
			printLine(60);
			for (OrderMenu o : om) {
				System.out.println(o);
			}
			printLine(60);
			System.out.println("합계:" + price);

			flag2 = false;
			while (!flag2) {
				if (mInfo == null) {
					// 비회원주문
					payMethod = nextInt("결제방법을 선택하세요 1.현금  2.카드  >", 1, 2);
					flag2 = true;
				} else {
					payMethod = nextInt("결제방법을 선택하세요 1.현금  2.카드  3.포인트  >", 1, 3);
					if (payMethod == 3 && price > mInfo.getPoint()) {
						System.out.println("포인트가 부족합니다.  결제금액:" + price + ", 보유 포인트:" + mInfo.getPoint());
					} else {
						flag2 = true;
					}
				}
			}

			if (mInfo == null) {
				if (odDao.insertNewOrder(new Order(0, purchaseCategory, om, payMethod, price, "", ""))) {
					printLine(60);
					System.out.println("주문 감사합니다.");
					printLine(60);
				}
			} else {
				if (odDao.insertNewOrder(new Order(mInfo.getMemberNo(), purchaseCategory, om, payMethod, price, "", ""))) {
					printLine(60);
					System.out.println("주문 감사합니다.");
					printLine(60);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 주문한 메뉴코드가 존재하는지 체크, 상품구분과 상품코드가 일치하면 true를 반환
	public SaleProductImpl findByMenu(int iFlag, List<SaleProductImpl> spt, String target) {
		for (SaleProductImpl p : spt) {
			if (iFlag == p.getProductSelect() && target.equals(p.getProductCode())) {
				return p;
			}
		}
		return null;
	}

	//주문가능수량을 레시피와 재로수량으로 계산후 SaleProductImpl의 주문가능수에 대입
	private void setMaximunOrder(SaleProductImpl spi, List<Recipe> recipe, List<InventoryManage> invtry) {
		String productCode = spi.getProductCode();
		int iMaximunOrder = 0;
		boolean bHit = false;

		for (Recipe rcp : recipe) {
			if (productCode.equals(rcp.getProductCode())) { // 레시피의 상품코드일치 재료 찾기
				for (InventoryManage ivt : invtry) { // 재료 재고량과 레시피 사용량 계산, 최대제조수량 조사
					if (rcp.getIngredientCode().equals(ivt.getIngredientCode())) {
						if (!bHit) { // 최초로 레시피의 재료코드와 재고의 재료코드가 일치한 경우
							iMaximunOrder = ivt.getNowInventory() / rcp.getIngredientQuantity(); // 재고 / 레시피의 사용량
							bHit = true;
						} else {
							if (iMaximunOrder > ivt.getNowInventory() / rcp.getIngredientQuantity()) {
								iMaximunOrder = ivt.getNowInventory() / rcp.getIngredientQuantity();
							}
						}
					}
				}
			}
		}
		spi.setMaximumOrder(iMaximunOrder); // 최대 주문수 계산
	}
	
	//주문후 주문수량에만큼 계산하여 재료를 마이너스 처리
	private void setInventory(String productCode, int qty, List<Recipe> recipe, List<InventoryManage> invtry) {
		for (Recipe rcp : recipe) {
			if (productCode.equals(rcp.getProductCode())) { // 레시피의 상품코드일치 재료 찾기
				for (InventoryManage ivt : invtry) {        // 재료 재고량계산해서 현재고량에서 
					if (rcp.getIngredientCode().equals(ivt.getIngredientCode())) {
						ivt.setNowInventory(ivt.getNowInventory() - rcp.getIngredientQuantity() * qty);  //현재고량에서 사용량차감
					}
				}
			}
		}
	}
}
