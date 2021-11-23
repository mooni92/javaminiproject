package vo;
/**
 * @auther 김영문
 *  2021-08-11
 *  주문 리스트용 vo작성 
 *  주문번호 ,회원번호, 카테고리, 지불방법, 총가격, 주문날짜, 명세번호, 제품코드, 제품이름, 양, 가격
 */
public class OrderList {

	private String orderNo;
	private int memberNo;
	private int purchaseCategory;
	private int payMethod;
	private long totalPrice;
	private String orderDate;
	private int orderSubNo;
	private String productCode;
	private String productName;
	private int quantity;
	private long price;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
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

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(int orderSubNo) {
		this.orderSubNo = orderSubNo;
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

	public OrderList() {
	}

	public OrderList(String orderNo, int memberNo, int purchaseCategory, int payMethod, long totalPrice,
			String orderDate, int orderSubNo, String productCode, String productName, int quantity, long price) {
		super();
		this.orderNo = orderNo;
		this.memberNo = memberNo;
		this.purchaseCategory = purchaseCategory;
		this.payMethod = payMethod;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.orderSubNo = orderSubNo;
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%6s  %6s  %13s	 %6s	   %6s	 %d	   %6d	    %10d", getOrderDate(), orderNo, getMsgg(memberNo),
				getMsg(purchaseCategory), getSpace(getProductName(), 13), quantity, price, totalPrice);

	}
// 구입방식을 if문으로 숫자 -> 한글화 진행.
	public String getMsg(int iStr1) {
		String rtn = "";

		if (iStr1 == 1) {
			rtn = "포장";
		}
		if (iStr1 == 2) {
			rtn = "매장내식사";
		}
		if (iStr1 == 3) {
			rtn = "배달";
		}
		return rtn;
	}
// 회원 번호로 회원인지 관리자인지 비회원인지 구분
	public String getMsgg(int iStr2) {
		String rtn = "";

		if (iStr2 == 0) {
			rtn = "비회원";
		}
		else if (iStr2 == 1) {
			rtn = "관리자";
		}else {
			rtn = "회원";
		}
		return rtn;
	}
	
	//화면 표시시, 한글글자로 인한 자간 맞추기 어려워 보정처리
		//인수 len과 인수 str 글자수 차이면큼, 스페이스를 채워줌.
	public String getSpace(String str, int len) {
		int spaceCount = len - str.length();
		if (spaceCount > 0) {
			for (int i = 0; i < spaceCount; i++) {
				str += "  ";
			}
		}
		return str;
	}
}