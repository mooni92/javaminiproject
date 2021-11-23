package ex;

import static util.Common.nextInt;
import static util.Common.nextLine;

import dao.OracleInit;
import service.InventoryManageService;
import service.MemberManageService;
import service.OrderService;
import service.RecipeService;
import service.SaleProductService;
import service.SalesManagementService;

/**
 * 
 * @author MiniTeam5조
 * 
 */

public class OrderEx {
	static boolean flag = false;
	static boolean flag2 = false;
	static boolean flag3 = false;
	static boolean flag4 = false;

	static OrderService orderSvs = new OrderService(); // 주문메뉴 서정목
	static InventoryManageService ivtengorySvs = new InventoryManageService(); // 관리자메뉴 재료매출 김재은
	static MemberManageService memberSvs = new MemberManageService(); // 회원가입 서비스 양희찬
	static SaleProductService productSvs = new SaleProductService(); // 관리자메뉴 상품관리 서정목
	static RecipeService recipeSvs = new RecipeService(); // 관리자메뉴 레시피관리 김영문
	static SalesManagementService managementSvs = new SalesManagementService(); // 관리자메뉴 매출관리 김영문 김재은
	// 메인메뉴

	public static void main(String[] args) {
		{
			OracleInit oi = new OracleInit();
			oi.init();
		}

		flag = false;
		while (!flag) {
			try {
				execute();
			} catch (NumberFormatException e) {
				System.out.println("정확한 숫자를 입력해 주세요");
			}
		}
	}

	// 메인메뉴 실행부
	static void execute() {
		System.out.println("▨▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▧");
		System.out.println("▦                                                                ▦");
		System.out.println("▦             H U M A N     W a f f l e  。 . .                  ▦");
		System.out.println("▦                                                                ▦");
		System.out.println("▧▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▤▨");
		System.out.println("");
		int input = nextInt(" ① 회원주문  ② 비회원주문  ③ 회원가입  ④ 관리자메뉴  ⑤ 종료>", 1, 5);
		

		switch (input) {
		case 1:
			// 주문;
			orderSvs.orderMember();

			break;
		case 2:
			// 비회원주문;
			// 주문;
			orderSvs.orderNonMember();

			break;
		case 3:
			// 회원가입;
			memberSvs.joinMember();

			break;
		case 4:
			// 관리자메뉴;
			managerMenu();

			break;
		case 5:
			System.out.println("종료");
			flag = true;
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}

//	관리자메뉴 관리자 로그인
	static void managerMenu() {
		String id;
		String pw;
		try {
			flag2 = false;
			while (!flag2) {
				System.out.println("관리자 admin/1234");
				id = nextLine("ID를 입력해주세요,99입력시 중단");
				if (id.equals("99")) {
					return;
				}
				pw = nextLine("pw를 입력해주세요,99입력시 중단");
				if (pw.equals("99")) {
					return;
				}
				if (memberSvs.managerCheck(id,pw) ) {
					flag2 = true;
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}

			flag2 = false;
			while (!flag2) {
				try {
					executeManager();
				} catch (NumberFormatException e) {
					System.out.println("정확한 숫자를 입력해 주세요");
				}
			}

		} catch (Exception e) {

		}

	}

//  관리자메뉴 실행부
	static void executeManager() {

		int input = nextInt("관리자메뉴  ① 회원조회   ②매출조회  ③재고관리   ④판매상품관리  ⑤ 레시피관리 ⑥ 종료  > ", 1, 6);

		switch (input) {
		case 1:
			// 회원조회
			memberSvs.list();
			break;
		case 2:
			// 매출조회;
			salesManage();
			break;
		case 3:
			// 재고관리;
			inventoryMange();
			break;
		case 4:
			// 판매상품관리;
			productMgr();
			break;
		case 5:
			// 레시피관리;
			recipeMgr();
			break;
		case 6:
			System.out.println("종료");
			flag2 = true;
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}
	

	// 매출관리용.
	static void salesManage() {
		System.out.println("매출관리");
		flag3 = false;
		while (!flag3) {
			try {
				executeSalesManage();
			} catch (Exception e) {
				System.out.println("에러:" + e.getMessage());
			}
		}

	}

	// 매출관리용 상세메뉴
	static void executeSalesManage() {
		int input = nextInt("①전체 매출 조회  ②주문 번호 조회   ③종료   >", 1, 3);

		switch (input) {
		case 1:
			managementSvs.orderList(); // 1.전체 매출 조회
			break;
		case 2:
			managementSvs.lookup(); // 2. 주문 번호 조회
			break;
		case 3:
			System.out.println("종료"); // 5.종료
			flag3 = true;
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}

	}

	// 상품관리용
	static void productMgr() {

		System.out.println("상품관리");
		flag3 = false;
		while (!flag3) {
			try {
				executeProduct();
			} catch (Exception e) {
				System.out.println("에러:" + e.getMessage());
			}
		}
	}

	// 상품관리용 상세메뉴
	static void executeProduct() {

		int input = nextInt("①조회  ②등록   ③수정   ④삭제  ⑤종료   >", 1, 5);

		switch (input) {
		case 1:
			productSvs.showList(); // 1.조회
			break;
		case 2:
			productSvs.insert(); // 2.등록
			break;
		case 3:
			productSvs.update(); // 3.수정
			break;
		case 4:
			productSvs.delete(); // 4.삭제
			break;
		case 5:
			System.out.println("종료"); // 5.종료
			flag3 = true;
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}

	}

//	레시피 관리용
	static void recipeMgr() {

		System.out.println("레시피 관리");
		flag3 = false;
		while (!flag3) {
			try {
				executeRecipe();
			} catch (Exception e) {
				System.out.println("에러:" + e.getMessage());
			}
		}
	}

//레시피 관리용 상세메뉴
	static void executeRecipe() {

		int input = nextInt("①조회  ②등록   ③수정   ④삭제  ⑤종료   >", 1, 5);

		switch (input) {
		case 1:
			recipeSvs.showList(); // 1.조회
			break;
		case 2:
			recipeSvs.insert(); // 2.등록
			break;
		case 3:
			recipeSvs.update(); // 3.수정
			break;
		case 4:
			recipeSvs.delete(); // 4.삭제
			break;
		case 5:
			System.out.println("종료"); // 5.종료
			flag3 = true;
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}

	// 재고관리용
	static void inventoryMange() {

		System.out.println("재고관리");
		flag3 = false;
		while (!flag3) {
			try {
				executeInventory();
			} catch (Exception e) {
				System.out.println("에러:" + e.getMessage());
			}
		}
	}

//재고 관리용 상세메뉴
	static void executeInventory() {

		int input = nextInt("①조회  ②등록   ③수정   ④삭제  ⑤ 재료 구매 ⑥종료   >", 1, 6);

		switch (input) {
		case 1:
			ivtengorySvs.showList(); // 1.조회
			break;
		case 2:
			ivtengorySvs.insert(); // 2.등록
			break;
		case 3:
			ivtengorySvs.update(); // 3.수정
			break;
		case 4:
			ivtengorySvs.delete(); // 4.삭제
			break;
		case 5:
			ivtengorySvs.purchase(); // 5.재료구매
			break;
		case 6:
			System.out.println("종료"); //6..종료
			flag3 = true;
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}

}