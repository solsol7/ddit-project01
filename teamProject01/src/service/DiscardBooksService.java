package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.DiscardBooksDAO;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;

public class DiscardBooksService {
	private static DiscardBooksService instance = null;

	private DiscardBooksService() {
	}

	public static DiscardBooksService getInstance() {
		if (instance == null)
			instance = new DiscardBooksService();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	DiscardBooksDAO dBDao = DiscardBooksDAO.getInstance();

	public int discardBooks() {
		String bk_no = null;
		while (true) {
			ScanUtil.putSomeMessage();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
			System.out.print("　　　　　　　　　　　　　　♡ 폐기할 도서(책번호 입력) : ");
			bk_no = ScanUtil.nextLine();
			if(bk_no.equals("뒤로가기")) {
				return View.M_MANBOOK;
			}
			if (bk_no.equals(jdbc.isDuplicateBK(bk_no, "BK_NO"))
					&& ((String) jdbc.isDuplicateBK(bk_no, "BK_STM")).equals("1")) { //// "1"-> STM 디폴트값 1로 변경됐는지 잘 확인
				List<Object> param = new ArrayList<>();
				param.add(bk_no);
				Map<String,Object> map1 = dBDao.bkInfo(bk_no);
				/*책번호 제목 출판사 저자*/
				System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %4s ♡\n", map1.get("책번호"),map1.get("제목"),map1.get("출판사"),map1.get("저자"));
				System.out.println("　　　　　　　　　　　　　　♡ 폐기하려는 도서가 맞습니까?");
				System.out.println("　　　　　　　　　　　　　　　　♡ 1. 예 ♡ 2. 아니오 ♡");
	            System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
				String a = ScanUtil.nextLine();;
				switch (a) {
				case "1":
					int result1 = dBDao.discardBooksDAO(bk_no);
					if (dBDao.retInfo(bk_no) != null) {
						int result2 = dBDao.setdcbDate(bk_no);
						if (result1 == 1 && result2 == 1) {
							System.out.println("　　　　　　　　　　　　　　♡ 도서 폐기 완료 ♡");
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						} else if (result1 != 1) {
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 도서 폐기 실패 : 폐기처리 오류 ♨");
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						} else if (result2 != 1) {
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 도서 폐기 실패 : 폐기날짜 기입 오류 ♨");
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						}
					} else {
						if (result1 == 1) {
							System.out.println("　　　　　　　　　　　　　　♡ 도서 폐기 완료 ♡");
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						} else {
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 도서 폐기 실패 : 폐기처리 오류 ♨");
							ScanUtil.anyPress();
							return View.M_MANBOOK;
						}
					}
					break;
				case "2":
					
					System.out.println("　　　　　　　　　　　　　　♡ 폐기를 다시 시작합니다 ♡");
					ScanUtil.anyPress();
					break;
				default:
					ScanUtil.errSleep();
					System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
					ScanUtil.anyPress();
					break;
				}
//				if (a.equals("1")) {
					/* a가 1일때 이동중*/
//				} else if (a.equals("2")) {
//					System.out.println("　　　　　　　　　　　　　　♡ 도서 폐기를 취소하고 뒤로 나갑니다 ♡");
//					ScanUtil.anyPress();
//					return View.M_MANBOOK;
//				} else {
//					System.err.println("　　　　　　　　　　　　　　♨ 잘못된 입력입니다. 다시 입력하세요 ♨");
//				}
			} else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 없는 도서입니다 ♨");
				ScanUtil.anyPress();
			}
		}

	}
}
