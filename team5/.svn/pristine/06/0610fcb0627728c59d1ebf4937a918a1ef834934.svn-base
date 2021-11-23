package vo;

/**
 * @auther 김재은
 *  2021-08-12
 *  재고 관리용 vo작성 
 *  재료코드, 재료명,적정재고량, 현재고량, 구입단가, 구입단위 */
public class InventoryManage {
	private String ingredientCode;//재료코드
	private String ingredientName;//재료명
	private int appropriateInventory;//적정재고량
	private int nowInventory;//현재고량
	private long purchasePrice;//구입단가
	private int unit;//구입단위



	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getAppropriateInventory() {
		return appropriateInventory;
	}

	public void setAppropriateInventory(int appropriateInventory) {
		this.appropriateInventory = appropriateInventory;
	}

	public int getNowInventory() {
		return nowInventory;
	}

	public void setNowInventory(int nowInventory) {
		this.nowInventory = nowInventory;
	}

	public long getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public InventoryManage() {
	}



	public InventoryManage(String ingredientCode, String ingredientName, int appropriateInventory, int nowInventory,
			long purchasePrice, int unit) {
		super();
		this.ingredientCode = ingredientCode;
		this.ingredientName = ingredientName;
		this.appropriateInventory = appropriateInventory;
		this.nowInventory = nowInventory;
		this.purchasePrice = purchasePrice;
		this.unit = unit;
	}

	@Override
	public String toString() {
		return String.format("    %5s  %13s  %14d	 %13d  %13d  %13d ",getSpace(getIngredientCode(),13),getSpace(getIngredientName(),13), appropriateInventory,
				getNowInventory(), getPurchasePrice(), getUnit());

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