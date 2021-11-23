
package vo;
/**
 * @auther 서정목
 *  2021-08-05
 *  와플주문용 vo작성 
 *  SaleProduct(상품코드, 상품명, 카테고리, 판매가격), 주문가능수량
 */
public class SaleProductImpl extends SaleProduct{

	private int maximumOrder;  //주문가능수량
	
	public SaleProductImpl() {}

	public SaleProductImpl(String productCode, String productName, int productSelect, int price, int maximumOrder) {
		super(productCode,productName,productSelect,price);
		this.maximumOrder = maximumOrder;
	}

	public int getMaximumOrder() {
		return maximumOrder;
	}

	public void setMaximumOrder(int maximumOrder) {
		this.maximumOrder = maximumOrder;
	}

	@Override
	public String toString() {
		return String.format("%s  %s  %6S      %-7d  ", getProductCode(),  getSpace(getProductName(),13),  getPrice() + "원" , maximumOrder);
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
