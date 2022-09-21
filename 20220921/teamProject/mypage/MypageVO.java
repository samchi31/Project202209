package teamProject.mypage;

public class MypageVO {
	private String memId;
	private String memName;
	private int memBir;
	private String account;
	private String bank;
	private int mileage;

	public MypageVO(String memId, String memName, int memBir, String account, String bank, int mileage) {
		this.memId = memId;
		this.memName = memName;
		this.memBir = memBir;
		this.account = account;
		this.bank = bank;
		this.mileage = mileage;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
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

}
