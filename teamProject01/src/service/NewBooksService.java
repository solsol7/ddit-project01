package service;
/* 2023-06-16 16:45 김석호 출력 형식 변경 완료 */
import util.ScanUtil;
import dao.NewBooksDAO;
import util.JDBCUtil;
import util.View;

public class NewBooksService {
	private static NewBooksService instance = null;

	private NewBooksService() {
	}

	public static NewBooksService getInstance() {
		if (instance == null)
			instance = new NewBooksService();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	NewBooksDAO newBooksDAO = NewBooksDAO.getInstance();

	public int newBooksService() {
		boolean bl01 = true;
		String bk_ctcode = "";
		String bk_shfcode = "";
		String bk_title = "";
		String bk_writer = "";
		String bk_pub = "";
		while (bl01) {
			ScanUtil.putSomeMessage();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　　　도서등록");
			System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			ctcode : while(true) {
				System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
				System.out.println("　　　　　　　　　　　　　　♡ 분류코드는 숫자 세 자리로 이루어집니다 ♡");
				System.out.print("　　　　　　　　　　　　　　　　♡ 분류번호 입력 : ");
				bk_ctcode = ScanUtil.nextLine();
				if(bk_ctcode.equals("뒤로가기")) {
					ScanUtil.anyPress();
					return View.M_MANBOOK; 
				}
				if(ScanUtil.canInsertColumn(bk_ctcode,3) && ScanUtil.checkCtcode(bk_ctcode)) {
					break ctcode;
				}else {
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 맞지않는 분류코드를 입력하셨습니다 ♨");
					ScanUtil.errSleep();
				}
			}
			shfcode : while(true) {
				System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
				System.out.println("　　　　　　　　　　　　　　♡ 서가코드는 알파벳 대문자 A~J 한 글자와 0~9의 숫자 하나로 이루어집니다 ♡");
				System.out.print("　　　　　　　　　　　　　　　　♡ 서가코드 입력 : ");
				bk_shfcode = ScanUtil.nextLine();
				if(bk_shfcode.equals("뒤로가기")) {
					ScanUtil.anyPress();
					return View.M_MANBOOK; 
				}
				if(ScanUtil.canInsertColumn(bk_shfcode,3) && ScanUtil.checkShfcode(bk_shfcode)) {
					break shfcode;
				}else {
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 맞지않는 서가코드를 입력하셨습니다 ♨");
					ScanUtil.errSleep();
				}
			}
			title : while(true) {
				System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
				System.out.print("　　　　　　　　　　　　　　　　♡ 책제목 입력 : ");
				bk_title = ScanUtil.nextLine();
				if(bk_title.equals("뒤로가기")) {
					lo1 : while(true) {
						System.out.println("　　　　　　　　　　　　　　　　♡ 1. 책 제목이 \"뒤로가기\" ♡");
						System.out.println("　　　　　　　　　　　　　　　　♡ 2. 뒤로가기 ♡");
						System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
						switch (ScanUtil.nextLine()) {
						case "1":
							break lo1;
						case "2":
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						default:
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
							break;
						}
					}
				ScanUtil.anyPress();
				return View.M_MANBOOK; 
				}
				if(ScanUtil.canInsertColumn(bk_title,500)) {
					break title;
				}else {
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 너무 긴 글자를 입력하셨습니다 ♨");
					ScanUtil.errSleep();
				}
			}
			wri : while(true) {
				System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
				System.out.print("　　　　　　　　　　　　　　　　♡ 저자 입력 : ");
				bk_writer = ScanUtil.nextLine();
				if(bk_writer.equals("뒤로가기")) {
					lo1 : while(true) {
						System.out.println("　　　　　　　　　　　　　　　　♡ 1. 저자가 \"뒤로가기\" ♡");
						System.out.println("　　　　　　　　　　　　　　　　♡ 2. 뒤로가기 ♡");
						System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
						switch (ScanUtil.nextLine()) {
						case "1":
							break lo1;
						case "2":
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						default:
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
							break;
						}
					}
				ScanUtil.anyPress();
				return View.M_MANBOOK; 
				}
				if(ScanUtil.canInsertColumn(bk_writer,200)) {
					break wri;
				}else {
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 너무 긴 글자를 입력하셨습니다 ♨");
					ScanUtil.errSleep();
				}
			}
			pub : while(true) {
				System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
				System.out.print("　　　　　　　　　　　　　　　　♡ 출판사 입력 : ");
				bk_pub = ScanUtil.nextLine();
				if(bk_pub.equals("뒤로가기")) {
					lo1 : while(true) {
						System.out.println("　　　　　　　　　　　　　　　　♡ 1. 출판사가 \"뒤로가기\" ♡");
						System.out.println("　　　　　　　　　　　　　　　　♡ 2. 뒤로가기 ♡");
						System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
						switch (ScanUtil.nextLine()) {
						case "1":
							break lo1;
						case "2":
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						default:
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
							break;
						}
					}
				ScanUtil.anyPress();
				return View.M_MANBOOK;
				}
				if(ScanUtil.canInsertColumn(bk_writer,200)) {
					break pub;
				}else {
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 너무 긴 글자를 입력하셨습니다 ♨");
					ScanUtil.errSleep();
				}
			}
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　♡ 사서님, 장난으로 책을 등록하시면 안됩니다 ♡ 등록할 책 정보를 한번 더 확인해주세요 ♡");
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　　　♡ 분류번호 : " + bk_ctcode+" ♡");
			System.out.println("　　　　　　　　　　　　　　　　♡ 서가코드 : " + bk_shfcode+" ♡");
			System.out.println("　　　　　　　　　　　　　　　　♡ 책제목 : " + bk_title+" ♡");
			System.out.println("　　　　　　　　　　　　　　　　♡ 저자 : " + bk_writer+" ♡");
			System.out.println("　　　　　　　　　　　　　　　　♡ 출판사 : " + bk_pub+" ♡");
			System.out.println("　　　　　　　　　　　　　　♡ 도서를 등록하시겠습니까? ♡");
			System.out.println("　　　　　　　　　　　　　　　　♡ 1. 예 ♡ 2. 아니오 ♡ 3. 나가기 ♡");
			System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
			String a = ScanUtil.nextLine();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			switch (a) {
			case "1":
				int result = newBooksDAO.newBooksDao(bk_ctcode, bk_shfcode, bk_title, bk_writer, bk_pub);
				if (result == 1) {
					System.out.println("　　　　　　　　　　　　　　♡ 도서 입력 완료 ♡");
					bl01 = false;
				} else {
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 도서 입력 실패 ♨");
					bl01 = false;
				}
				break;
			case "2": // while문으로 아니오 클릭했을 때 다시 입력받으려고하면 분류코드입력 sc.nextLine 오류남
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 도서 입력 실패 ♨");
				bl01 = false;
				break;
			case "3":
				System.out.println("　　　　　　　　　　　　　　♡ 도서 입력을 취소하고 나갑니다 ♡");
				bl01 = false;
				break;
			default:
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨");
				bl01 = false;

			}
		}
		ScanUtil.anyPress();
		return View.M_MANBOOK;
	}
}
