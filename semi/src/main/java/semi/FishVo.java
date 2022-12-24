package semi;

public class FishVo {
	String io_index; // 인덱스
	String io_type; // 구분(일자별, 월별, 품목별)
	String io_date1; // 기준일자 전, 하나만 필요하면 date1을 쓰장!
	String io_date2; // 기준일자 후
	String io_combiName; // 거래처이름
	String io_combiCode; // 거래처코드
	String io_contName; // 창고이름
	String io_contCode; // 창고코드
	String io_fishName; // 어종명
	String io_fishCode; // 어종코드
	String io_name; // 입고 출고
	String io_code; // 입고:1, 출고:2
	
	
	public String getIo_index() {
		return io_index;
	}
	public void setIo_index(String io_index) {
		this.io_index = io_index;
	}
	String io_amt; // 입출고 수량
	public String getIo_type() {
		return io_type;
	}
	public void setIo_type(String io_type) {
		this.io_type = io_type;
	}
	public String getIo_date1() {
		return io_date1;
	}
	public void setIo_date1(String io_date1) {
		this.io_date1 = io_date1;
	}
	public String getIo_date2() {
		return io_date2;
	}
	public void setIo_date2(String io_date2) {
		this.io_date2 = io_date2;
	}
	public String getIo_combiName() {
		return io_combiName;
	}
	public void setIo_combiName(String io_combiName) {
		this.io_combiName = io_combiName;
	}
	public String getIo_combiCode() {
		return io_combiCode;
	}
	public void setIo_combiCode(String io_combiCode) {
		this.io_combiCode = io_combiCode;
	}
	public String getIo_contName() {
		return io_contName;
	}
	public void setIo_contName(String io_contName) {
		this.io_contName = io_contName;
	}
	public String getIo_contCode() {
		return io_contCode;
	}
	public void setIo_contCode(String io_contCode) {
		this.io_contCode = io_contCode;
	}
	public String getIo_fishName() {
		return io_fishName;
	}
	public void setIo_fishName(String io_fishName) {
		this.io_fishName = io_fishName;
	}
	public String getIo_fishCode() {
		return io_fishCode;
	}
	public void setIo_fishCode(String io_fishCode) {
		this.io_fishCode = io_fishCode;
	}
	public String getIo_name() {
		return io_name;
	}
	public void setIo_name(String io_name) {
		this.io_name = io_name;
	}
	public String getIo_code() {
		return io_code;
	}
	public void setIo_code(String io_code) {
		this.io_code = io_code;
	}
	public String getIo_amt() {
		return io_amt;
	}
	public void setIo_amt(String io_amt) {
		this.io_amt = io_amt;
	}


}
