package vo;
/**
 * @auther 김영문
 *  2021-08-11
 *  레시피 리스트용 vo작성 
 *  제품이름 , 재료이름
 */
public class RecipeImpl extends Recipe {

	private String productName;
	private String ingredientName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public RecipeImpl() {
	}

	public RecipeImpl(String productCode, String productName, String ingredientCode, String ingredientName,
			int ingredientQuantity) {
		super(productCode, ingredientCode, ingredientQuantity);
		this.productName = productName;
		this.ingredientName = ingredientName;

	}

	@Override
	public String toString() {
		return String.format("%13s  %13s  %13s	 %13s %13d ", getSpace(getProductCode(), 5),
				getSpace(getProductName(), 13), getSpace(getIngredientCode(), 13), getSpace(getIngredientName(), 13),
				getIngredientQuantity());

	}
	
	//화면 표시시, 한글글자로 인한 자간 맞추기 어려워 보정처리
	//인수 len과 인수 str 글자수 차이면큼, 스페이스를 채워줌.
	public String getSpace(String str, int len) {
		if (str == null) {
			str = " ";
		}
		int spaceCount = len - str.length();
		if (spaceCount > 0) {
			for (int i = 0; i < spaceCount; i++) {
				str += "  ";
			}
		}
		return str;
	}

}
