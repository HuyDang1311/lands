package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	public String md5(String str){
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bi = new BigInteger(1, md.digest());
			
			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String stringDrop(String str) {
		int number = 650;
		String param ="";
		if (str.length() >= number) {
			if(' '!=(str.charAt(number))) {
				for (int i = number; i < str.length(); i++) {
					if(' '==(str.charAt(i))) {
						param = str.substring(0,i) + " ...";
						break;
					}
				}
			} else {
				param = str.substring(0, number)+" ...";
			}
		} else {
			param=str;
		}
		return param;
	}
	
	public String nameCland(String str) {
		int number = 40;
		String param ="";
		if (str.length() >= number) {
			if(' '!=(str.charAt(number))) {
				for (int i = number; i < str.length(); i++) {
					if(' '==(str.charAt(i))) {
						param = str.substring(0,i) + " ...";
						break;
					}
				}
			} else {
				param = str.substring(0, number)+" ...";
			}
		} else {
			param=str;
		}
		return param;
	}
	
	public String nameClandSmall(String str) {
		int number = 20;
		String param ="";
		if (str.length() >= number) {
			if(' '!=(str.charAt(number))) {
				for (int i = number; i < str.length(); i++) {
					if(' '==(str.charAt(i))) {
						param = str.substring(0,i) + " ...";
						break;
					}
				}
			} else {
				param = str.substring(0, number)+" ...";
			}
		} else {
			param=str;
		}
		return param;
	}
	
	public static void main(String[] args) {
		StringUtil str = new StringUtil();
		System.out.println(str.stringDrop("Khu đô thị FPT City Đà Nẵng - Ước mơ chắp cánh với một nơi an cư sinh thái trong lòng sinh thái.\r\n" + 
				"Bạn có biết điều gì đã xảy ra! Khu đô thị FPT City nay đã mở bán đất nền. Nơi Tận hưởng cuộc sống trong lành tại khu đô thị xanh ven sông, kề biển văn minh bậc nhất Đà Nẵng.\r\n" + 
				"Cơ hội sở hữu đất nền FPT City Đà Nẵng:\r\n" + 
				"300 cơ hội vàng, giá hấp dẫn với 695 triệu/nền (tương đương từ 7tr - 8tr/m2). Diện tích đa dạng từ 90 - 105m2. Được ngân hàng TP Bank hỗ trợ chính sách vay.\r\n" + 
				"Số lượng có hạn, khách hàng nhanh tay book quỹ hàng ngay hôm nay.\r\n" + 
				"Giới thiệu phân khu mở bán đất nền FPT City Đà Nẵng:\r\n" + 
				"Vị trí mở bán là V1 (khu bắc) và V5 (khu tây)- Nơi đẹp nhất ngay trung tâm khu đô thị dễ dàng tiếp cận mọi tiện ích: Trường Đại Học FPT, trường Trung Học FPT, công viên xanh, khu TT giải trí.\r\n" + 
				"\r\n" + 
				"Chính sách thanh toán linh hoạt dành cho khách hàng:\r\n" + 
				"ĐỢT 1: Giữ chỗ 50 triệu VNĐ.\r\n" + 
				"ĐỢT 2: Thanh toán 50% (ký hợp đồng đặt cọc) - 15 ngày sau đợt 1.\r\n" + 
				"ĐỢT 3: Thanh toán đủ 100% (ký hợp đồng chuyển nhượng) - 120 ngày sau đợt 1.\r\n" + 
				"ĐỢT 4: Nhận sổ - 30 ngày sau đợt 3.\r\n" + 
				"Chương trình khuyến mãi:\r\n" + 
				"Ưu đãi quà tân gia: Xây nhà ngay, tặng liền móng 7o triệu.\r\n" + 
				"Khu đô thị FPT City - tận hưởng cuộc sống xanh, tri thức, văn minh bậc nhất Đà Nẵng.\r\n" + 
				"FPT City là giấc mơ đã thành hiện thực của Chủ tịch FPT Trương Gia Bình về một Thung lũng Silicon ở Việt Nam.\r\n" + 
				"Dự án được Công ty Cổ phần Đô thị FPT Đà Nẵng làm chủ đầu tư với tổng diện tích hơn 181 hécta, trong đó mật độ xây dựng chỉ chiếm 40%, 60% còn lại dành cho cây xanh và mặt nước, kiến tạo nên KĐT sinh thái thân thiện với môi trường và phát triển bền vững.\r\n" + 
				"Vị trí KĐT ngay trục giao thông chính: Võ Nguyên Giáp, Nam Kì Khởi Nghĩa, Võ Chí Công, Trần Đại Nghĩa. Dự án ven sông Cổ Cò, nằm giữa 2 sân golf đẳng cấp quốc tế: BRG & Montgomerie Links, tiếp giáp hàng loạt Resort đẳng cấp 5* bên bờ biển Mỹ Khê tuyệt đẹp. Hạ tầng khu đô thị"));
	}

}
