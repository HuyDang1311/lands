package util;

public class Regular {
	public boolean checkNameEmpty(String str) {
		return str.matches("^[ \\s]+|[ \\s]+$");
	}

	public boolean checkSoKiTu(String str) {
		String string = checkSpace(str);
		return string.matches("^[a-zA-Z0-9\\s.*[^\\\\x20-\\\\x7E].*_-]{3,}$");
	}

	public String checkSpace(String str) {
		return str.trim().replaceAll("\\s+", " ");
	}

	public boolean checkUserName(String str) {
		return str.matches("[a-zA-Z0-9_-]{5,32}$");
	}

	public boolean checkFullName(String str) {
		String string = checkSpace(str);
		return string.matches("^[a-zA-Z\\s.*[^\\\\x20-\\\\x7E].*_-]{3,32}$");
	}

	public boolean checkPassword(String str) {
		return str.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,32})");
	}

	public boolean checkTenTin(String str) {
		String string = checkSpace(str);
		return string.matches("^[a-zA-Z0-9\\s.*[^\\\\x20-\\\\x7E].*_-]{5,}$");
	}	
	
	public boolean checkSdt(String str) {
		return str.matches("[0-9]{10,13}$");
	}
	public static void main(String[] args) {
		Regular rd = new Regular();
		System.out.println(rd.checkSdt("01645315444"));
	}
	
	
}
