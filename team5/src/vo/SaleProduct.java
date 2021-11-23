package vo;
/**
 * @auther 서정목
 *  2021-08-05
 *  판매상품용 vo작성 
 *  상품코드, 상품명, 카테고리, 판매가격
 */
public class SaleProduct {
	private String productCode;  //상품코드
	private String productName;  //상품명
	private int productSelect;   //카테고리  1.와플, 2.음료
	private int price;      //판매가격
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductSelect() {
		return productSelect;
	}
	public void setProductSelect(int productSelect) {
		this.productSelect = productSelect;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public SaleProduct() {}
	public SaleProduct(String productCode, String productName, int productSelect, int price) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productSelect = productSelect;
		this.price = price;
	}
	
	public String toString() {
		return String.format("%s  %s  %6S", getProductCode(),  getSpace(getProductName(),13),  getPrice() + "원" );
	}
	
	//화면 표시시, 한글글자로 인한 자간 맞추기 어려워 보정처리
	//인수 len과 인수 str 글자수 차이면큼, 스페이스를 채워줌. 
	public String getSpace(String str, int len) {
		if (str ==null) {str="";}
		int spaceCount = len - str.length();
		if (spaceCount > 0) {
			for (int i = 0; i < spaceCount; i++) {
				str += "  ";
			}
		}
		return str;
	}
}
