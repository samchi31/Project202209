package teamProject.join;

public class JoinVO {
	private String memId;
	private String password;
	private String memName;
	private int memBir;
	private String account;
	private String bank;
	private int mileage;
	
	public JoinVO(String memId, String password, String memName, int memBir, String account, String bank) {
		this.memId = memId;
		this.password = password;
		this.memName = memName;
		this.memBir = memBir;
		this.account = account;
		this.bank = bank;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public int getMemBir() {
		return memBir;
	}

	public void setMemBir(int memBir) {
		this.memBir = memBir;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return String.format("입력한 정보 \n [memId=%s\npassword=%s\nmemName=%s\nmemBir=%s\naccount=%s\nbank=%s\nmileage=%s]",
				memId, password, memName, memBir, account, bank, mileage);
	}
	
}