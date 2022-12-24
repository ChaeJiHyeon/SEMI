package semi;

public class StockVo {
	int stock_combiCode;
	String stock_combiName;
	int stock_contCode;
	String stock_contName;
	String stock_fishCode, stock_fishName;
	String stock_date1="2017-12-01";
	String stock_date2="2017-12-31";
	String stock_amt;
	String stock_type="일별";	//일별,월별
	double iAmt;	//전체입고량
	double oAmt;	//전체출고량
	double sAmt;	//전체재고량
	String stock_date;	//차트 표시 날짜
	int stock_idx;
	
	public int getStock_idx() {
		return stock_idx;
	}
	public void setStock_idx(int stock_idx) {
		this.stock_idx = stock_idx;
	}
	public String getStock_date() {
		return stock_date;
	}
	public void setStock_date(String stock_date) {
		this.stock_date = stock_date;
	}
	public String getStock_type() {
		return stock_type;
	}
	public void setStock_type(String stock_type) {
		this.stock_type = stock_type;
	}
	public double getiAmt() {
		return iAmt;
	}
	public void setiAmt(double iAmt) {
		this.iAmt = iAmt;
	}
	public double getoAmt() {
		return oAmt;
	}
	public void setoAmt(double oAmt) {
		this.oAmt = oAmt;
	}
	public double getsAmt() {
		return sAmt;
	}
	public void setsAmt(double sAmt) {
		this.sAmt = sAmt;
	}
	public int getStock_combiCode() {
		return stock_combiCode;
	}
	public void setStock_combiCode(int stock_combiCode) {
		this.stock_combiCode = stock_combiCode;
	}
	public String getStock_combiName() {
		return stock_combiName;
	}
	public void setStock_combiName(String stock_combiName) {
		this.stock_combiName = stock_combiName;
	}
	public int getStock_contCode() {
		return stock_contCode;
	}
	public void setStock_contCode(int stock_contCode) {
		this.stock_contCode = stock_contCode;
	}
	public String getStock_contName() {
		return stock_contName;
	}
	public void setStock_contName(String stock_contName) {
		this.stock_contName = stock_contName;
	}
	public String getStock_fishCode() {
		return stock_fishCode;
	}
	public void setStock_fishCode(String stock_fishCode) {
		this.stock_fishCode = stock_fishCode;
	}
	public String getStock_fishName() {
		return stock_fishName;
	}
	public void setStock_fishName(String stock_fishName) {
		this.stock_fishName = stock_fishName;
	}
	public String getStock_date1() {
		return stock_date1;
	}
	public void setStock_date1(String stock_date1) {
		this.stock_date1 = stock_date1;
	}
	public String getStock_date2() {
		return stock_date2;
	}
	public void setStock_date2(String stock_date2) {
		this.stock_date2 = stock_date2;
	}
	public String getStock_amt() {
		return stock_amt;
	}
	public void setStock_amt(String stock_amt) {
		this.stock_amt = stock_amt;
	}
	
}
