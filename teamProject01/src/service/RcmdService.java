package service;
/* 2023-06-16 16:58 김석호 출력 형식 변경 완료*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import dao.RcmdDAO;
import util.View;
import util.ScanUtil;
public class RcmdService {
	private static RcmdService instance = null;

	private RcmdService() {
	}

	public static RcmdService getInstance() {
		if (instance == null)
			instance = new RcmdService();
		return instance;
	}

	RcmdDAO rcmdDAO = RcmdDAO.getInstance();

	public int rcmdService() {
		ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　추천 도서");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　             1. 인기책 조회                                                          2. 추천책 조회");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　             3. 뒤로가기");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
		// while()
		String a = ScanUtil.nextLine();
		switch (a) {
		case "1":
			ScanUtil.putSomeMessage();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			List<Map<String, Object>> list1 = new ArrayList<>();
			list1 = rcmdDAO.popularity();

			for (int i = 0; i < 7; i++) {
				/*
				 * String.valueOf(list1.get(i).get("BK_TITLE"))
				 * String.valueOf(list1.get(i).get("BK_WRITER"))
				 * String.valueOf(list1.get(i).get("BK_PUB"))
				 * String.valueOf(list1.get(i).get("BK_NO"))
				 * String.valueOf(list1.get(i).get("BK_SHFCODE"))
				 * String.valueOf(list1.get(i).get("대출"))
				 * String.valueOf(list1.get(i).get("예약"))
				 */
				System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %4s ♡ 서가코드 : %2s ♡ 대출여부 : %4s ♡ 예약여부 : %4s ♡\n", String.valueOf(list1.get(i).get("BK_TITLE")),String.valueOf(list1.get(i).get("BK_WRITER")),String.valueOf(list1.get(i).get("BK_PUB")),String.valueOf(list1.get(i).get("BK_NO")),String.valueOf(list1.get(i).get("BK_SHFCODE")),String.valueOf(list1.get(i).get("대출")),String.valueOf(list1.get(i).get("예약")));
				System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
				
				/* 2023-06-16 16:48 김석호
				 * 수정 사항 : 표시 형식 변경을 위해 블록 주석처리
				 * */
				/*
				System.out.print("제목 : " + list1.get(i).get("BK_TITLE") + "   |   ");
				System.out.println();
				System.out.print("저자 : " + list1.get(i).get("BK_WRITER") + "   |   ");
				System.out.print("출판사 : " + list1.get(i).get("BK_PUB") + "   |   ");
				System.out.println();
				System.out.print("책번호 : " + list1.get(i).get("BK_NO") + "   |   ");
				System.out.print("서가코드 : " + list1.get(i).get("BK_SHFCODE") + "   |   ");
				System.out.print("대출자 : " + list1.get(i).get("대출") + "   |   ");
				System.out.println("예약자 : " + list1.get(i).get("예약") + "   |   ");
				System.out.println("=========================================================================");
				*/
			}
			ScanUtil.anyPress();
			return View.S_RECOBOOK;
		case "2":
			List<Map<String, Object>> list2 = new ArrayList<>();
			list2 = rcmdDAO.suggestion();
			int[] num = new int[list2.size()];
			for (int i = 0; i < num.length; i++) {
				num[i] = i;
			}
			for (int i = 0; i < 100000; i++) {
				int rnd = (int) (Math.random() * num.length);
				int temp = num[0];
				num[0] = num[rnd];
				num[rnd] = temp;
			}
			ScanUtil.putSomeMessage();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			for (int i = 0; i < 7; i++) {
				System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %4s ♡ 서가코드 : %2s ♡ 대출여부 : %4s ♡ 예약여부 : %4s ♡\n", String.valueOf(list2.get(num[i]).get("BK_TITLE")),String.valueOf(list2.get(num[i]).get("BK_WRITER")),String.valueOf(list2.get(num[i]).get("BK_PUB")),String.valueOf(list2.get(num[i]).get("BK_NO")),String.valueOf(list2.get(num[i]).get("BK_SHFCODE")),String.valueOf(list2.get(num[i]).get("대출")),String.valueOf(list2.get(num[i]).get("예약")));
				System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
				/* 상동
				System.out.print("제목 : " + list2.get(num[i]).get("BK_TITLE") + "   |   ");
				System.out.println();
				System.out.print("저자 : " + list2.get(num[i]).get("BK_WRITER") + "   |   ");
				System.out.print("출판사 : " + list2.get(num[i]).get("BK_PUB") + "   |   ");
				System.out.println();
				System.out.print("책번호 : " + list2.get(num[i]).get("BK_NO") + "   |   ");
				System.out.print("서가코드 : " + list2.get(num[i]).get("BK_SHFCODE") + "   |   ");
				System.out.print("대출자 : " + list2.get(num[i]).get("대출") + "   |   ");
				System.out.println("예약자 : " + list2.get(num[i]).get("예약") + "   |   ");
				System.out.println("=========================================================================");
				*/
			}
			ScanUtil.anyPress();
			return View.S_RECOBOOK;
		case "3":
			ScanUtil.anyPress();
			return View.S_MAIN;
		default:
			ScanUtil.errSleep();
			System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨");
			ScanUtil.anyPress();
			return View.S_RECOBOOK;

		}
		
	}
}
