package vo;

import java.util.List;

/**
 * @auther 서정목
 *  2021-08-05
 *  와플주문용 vo작성
 */
public class Order {

	private int memberNo;           //회원번호
	private int purchaseCategory;   //구입구분  1.포장 2.매장내식사 3.배달
	private List<OrderMenu> menu;   //메뉴 (상품코드, 상품명, 수량, 금액)
	private int payMethod;          //결제방법 (1.현금  2.카드  3.포인트)
	private long price;             //주문금액
	private String orderDate;       //주문일시 (디비에서날자타입으로 관리예정) 
	private String orderNo;         //주문번호
	
	public Order() {}
	
	public Order(int memberNo, int purchaseCategory, List<OrderMenu> menu, int payMethod, long price,
			String orderDate, String orderNo) {
		super();
		this.memberNo = memberNo;
		this.purchaseCategory = purchaseCategory;
		this.menu = menu;
		this.payMethod = payMethod;
		this.price = price;
		this.orderDate = orderDate;
		this.orderNo = orderNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getPurchaseCategory() {
		return purchaseCategory;
	}

	public void setPurchaseCategory(int purchaseCategory) {
		this.purchaseCategory = purchaseCategory;
	}

	public List<OrderMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<OrderMenu> menu) {
		this.menu = menu;
	}

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "Order [memberNo=" + memberNo + ", purchaseCategory=" + purchaseCategory + ", menu=" + menu
				+ ", payMethod=" + payMethod + ", price=" + price + ", orderDate="
				+ orderDate + ", orderNo=" + orderNo + "]";
	}	
	
}

