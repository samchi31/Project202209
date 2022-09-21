package teamProject.reservation;

public class SeatNumVO {
	/**
	 * row index: 0 ~ 5 (1 ~ 6) col index: 0 ~ 3 (A ~ D)
	 */

	private int row;
	private int col;

	public SeatNumVO(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * 좌석 번호 받아서 행 열 값 저장
	 * 
	 * @param str A1 ~ D6
	 */
	public SeatNumVO(String str) {
		switchSeatNo(str);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void switchSeatNo(String str) {
		// A~D 저장
		switch (str.charAt(0)) {
		case 'A':
			col = 0;
			break;
		case 'B':
			col = 1;
			break;
		case 'C':
			col = 2;
			break;
		case 'D':
			col = 3;
			break;
		}
		// 1~6 저장
		switch (Character.getNumericValue(str.charAt(1))) {
		case 1:
			row = 0;
			break;
		case 2:
			row = 1;
			break;
		case 3:
			row = 2;
			break;
		case 4:
			row = 3;
			break;
		case 5:
			row = 4;
			break;
		case 6:
			row = 5;
			break;
		}
	}

}
