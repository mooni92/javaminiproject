package service;
import static util.Common.nextInt;

import static util.Common.nextLine;
import static util.Common.printTitle;

import java.util.List;

import dao.RecipeDao;
import vo.Recipe;
import vo.RecipeImpl;
/**
 * @auther 김영문
 *  2021-08-11
 *  레시피용 service작성
 *  전메뉴조회, 신규메뉴 등록, 메뉴수정, 메뉴삭제
 */
public class RecipeService {
	public RecipeDao rpDao = new RecipeDao();
	  
	//조회(전메뉴)
	public void showList() {
		printTitle(
				"제품코드         제품이름	        재료코드                      재료이름                             재료양");
		List<RecipeImpl> rp = rpDao.getProduct();
		for (RecipeImpl p: rp) {
			System.out.println(p);
		}

	}

	// 레시피 등록
	public void insert() {

		int rtn = rpDao.add(new Recipe(nextLine("상품코드입력 3글자이하입력 >"), nextLine("재료 코드입력>"),nextInt("재료량 입력>")));
		if (rtn == 1) System.out.println("등록하였습니다");
	}
	
	// 레시피 수정
	public void update() {
		String pCd = nextLine("수정할 상품코드입력 3글자이하입력 >");
		String iCd = nextLine("수정할 재료코드입력 3글자이하입력 >");
		if (!rpDao.findby(pCd, iCd)) {
			System.out.println("입력한 상품정보가 존재하지 않습니다.");
			return;
		}
		
		int rtn = rpDao.update(new Recipe(pCd ,iCd ,nextInt("재료량 입력>")));
		if (rtn == 1) System.out.println("수정하였습니다");
	}
	
	// 레시피 삭제
	public void delete() {
		String pCd = nextLine("삭제할 상품코드입력 >");
		String iCd = nextLine("삭제할 재료코드입력 >");
		if (!rpDao.findby(pCd, iCd)) {
			System.out.println("입력한 상품정보가 존재하지 않습니다.");
			return;
		}
		
		int rtn = rpDao.delete(pCd,iCd);
		if (rtn == 1) System.out.println("삭제하였습니다");
	}

}
	
	
	
	
	

