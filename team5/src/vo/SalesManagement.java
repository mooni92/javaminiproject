package vo;
/**
 * @auther 김영문
 *  2021-08-11
 *  매출관리용 vo작성 
 *  주문번호, 명세번호, 제품코드, 제품이름 , 양, 일매출, 순이익, 재료비
 */
public class SalesManagement {

	private String orderNo;
	private int orderSubNo;
	private String productCode;
	private String productName;
	private int quantity;
	private long price;
	private long dailySales;
	private long netProfit;
	private long metarialCost;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public long getDailySales() {
		return dailySales;
	}

	public void setDailySales(long dailySales) {
		this.dailySales = dailySales;
	}

	public long getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(long netProfit) {
		this.netProfit = netProfit;
	}

	public long getMetarialCost() {
		return metarialCost;
	}

	public void setMetarialCost(long metarialCost) {
		this.metarialCost = metarialCost;
	}

	public SalesManagement() {
	}

	public SalesManagement(String orderNo, int orderSubNo, String productCode, String productName, int quantity,
			long price, long dailySales, long netProfit, long metarialCost) {
		super();
		this.orderNo = orderNo;
		this.orderSubNo = orderSubNo;
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.dailySales = dailySales;
		this.netProfit = netProfit;
		this.metarialCost = metarialCost;
	}

	@Override
	public String toString() {
		return "SalesManagement [orderNo=" + orderNo + ", orderSubNo=" + orderSubNo + ", productCode=" + productCode
				+ ", productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", dailySales="
				+ dailySales + ", netProfit=" + netProfit + ", metarialCost=" + metarialCost + "]";
	}

}
