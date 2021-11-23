package vo;
/**
 * @auther 서정목
 *  2021-08-05
 *  와플주문용 vo작성
 *  메뉴, 메뉴명, 수량, 가격
 */
public class OrderMenu {

	private String productCode;  //상품(메뉴)
	private String productName;  //상품명(메뉴명)
	private int quantity;        //주문수량
	private long price;          //주문금액

	public OrderMenu() {}

	public OrderMenu(String productCode, String productName, int quantity, long price) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%s  %4d  %8s  ", getSpace(productName,13), quantity, price+"원");
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
