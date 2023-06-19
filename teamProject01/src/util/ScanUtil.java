package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanUtil {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
 
	// 입력할 문자열과 칼럼에 할당된 바이트값을 매개변수로 받아서 넣을수 있는지 확인하는 메소드 
	public static boolean canInsertColumn(String str, int useByte) {
		char[] chArray = str.toCharArray();
		int sumByte = 0;
		for(int i = 0; i < chArray.length ; i++) {
			boolean a = false;
			boolean b = false;
			if((chArray[i] >= '\u3131') && (chArray[i] <= '\u3163')) {
				a = true;
			} // 한글 자모음 분해해서 확인한후 맞으면 a가 true
			if((chArray[i] >= '\uac00') && (chArray[i] <= '\ud7a3')) {
				b = true;
			} // 한글로된 글자에 해당하면 b가 true
			// a나 b 둘중 하나라도 true이면 오라클에서는 3 BYTE 차지
			if(a||b) {
				sumByte +=3;
			}else { // 한글이 아니라면
				sumByte +=1;
			}
		}
		/* 칼럼 바이트수보다 작거나 같으면 true, 넘으면 false로 반환 */
		if(sumByte <= useByte) {
			return true;
		}else {
			return false;
		}
	}
	/* 책장번호에 맞는 문자열인지 확인하는 메소드 */
	public static boolean checkShfcode(String str) {
		char[] chArray = str.toCharArray();
		if(chArray.length != 2) { //2자리인지 먼저 확인
			return false;
		}
		if(!(chArray[0]>=65 && chArray[0]<=74)) {//첫자리가 대문자알파벳 A~J인지 확인 /* 2023-06-19 08:30 수정 */
			return false;
		}
		if(!(chArray[1]>=48 && chArray[1]<=57)) {//두번째자리가 숫자 0~9인지 확인
			return false;
		}
		return true;
	}
	/* 분류번호에 맞는 문자열인지 확인하는 메소드 */
	public static boolean checkCtcode(String str) {
		char[] chArray = str.toCharArray();
		if(chArray.length != 3 ) { // 3자리인지 먼저 확인
			return false;
		}
		for(int i = 0 ; i < chArray.length ; i ++) {
			if(!(chArray[i]>=48 && chArray[i]<=57)) { // 각 자리가 숫자 0~9가 맞는지 확인 
				return false;
			}
		}
		return true;
	}
	
	public static String nextLine() {
		while (true) {
			String findSemicolon = sc.nextLine();
			if (!(findSemicolon.contains(";"))) {
				return findSemicolon;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			System.err.println("　　　　　　　　　　　　　　♨ 우리 도서관 시스템은 어떤경우에도 \"세미콜론(;)\"이 포함된 문자열은 입력할 수 없습니다 ♨");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			System.out.print("　　　　　　　　　　　　　　♡ 다시 입력해주세요 :");
		}
	}

	private static int a = 15;

	public static int nextInt() {
		while (true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			} catch (NumberFormatException e) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException ee) {
				}
				System.err.println("　　　　　　　　　　　　　　♨ 숫자로만 입력해주세요 ♨ 최소치 : -2,147,483,648 ♨ 최대치 : 2,147,483,647 ♨");
				try {
					Thread.sleep(1);
				} catch (InterruptedException ee) {
				}
				System.out.print("　　　　　　　　　　　　　　♡ 다시 입력해주세요 :");
			}
		}
	}

	/* 해당 메소드는 화면이 넘어갈 때 스스로 선택해서 넘어갈 수 있도록 선택권을 주는 메소드 */
	/*
	 * 
	 * 
	 * 
	 * ********************************************************************
	 * ********************************************************************
	 * ********************************************************************
	 * ********************************************************************
	 * ********************************************************************
	 * ********************************************************************
	 * ********************************************************************
	 * ******************************************************************** 에러 메시지가
	 * 아닌곳에서 호출***********************************************
	 * ********************************************************************
	 */
//	ScanUtil.anyPress();
	public static void anyPress() {
		System.out.print("　　　　　　　　　　　　　　♡ 아무키나 입력하여 다음 화면으로 가기 ♡ ");
		while (true) {
			String assa = ScanUtil.nextLine();
			if (assa != null) {
//				putSomeMessage();				
				break;
			}
		}
		/* 20230616 11:14 김석호 */
		/* 이 위치에 관리자인지 학생인지 판단해서 공백대신 넣어줄 메소드 완성하기 */
//		putEmptySpace();
		/* 2023-06-16 17:16 김석호 : 해당 메소드를 putSomeMessage에서 마지막에 호출 */
	}

//	ScanUtil.anyPressError();
	public static void anyPressError() {
		System.out.print("　　　　　　　　　　　　　　♡ 아무키나 입력하여 다음으로 가기 ♡ ");
		while (true) {
			String assa = ScanUtil.nextLine();
			if (assa != null) {
				break;
			}
		}
	}

//			ScanUtil.putSomeMessage();
	/* 2023-06-16 17:10 김석호 메소드 추가 */
	/* 학생인지 관리자인지 판단해서 화면 넘길때 메시지 출력해주는 메소드 */
	public static void putSomeMessage() {
		putEmptySpace();
		/* 이 위치에 판단문 */
		if (JDBCUtil.loginUser == null)
			return;
		if (String.valueOf(JDBCUtil.loginUser.get("STD_CLSCODE")).equals("1")) { // 학생일 경우
			messageForStudent();
		} else if (String.valueOf(JDBCUtil.loginUser.get("STD_CLSCODE")).equals("2")) { // 관리자일 경우
			messageForManager();
		} else {

		}
	}

	public static void putEmptySpace() {
		for (int i = 0; i < 80; i++) {
			System.out.println();
		}
	}

	/* 2023-06-16 17:10 김석호 메소드 추가 */
	/* err 출력하는 곳에 전부 호출하기 */
	public static void putErrorMessage() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　　　▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		emoti.add("　　　　　　　　　　　　　　　　████▌▄▌▄▐▐▌█████");
		emoti.add("　　　　　　　　　　　　　　　　████▌▄▌▄▐▐▌▀████");
		emoti.add("　　　　　　　　　　　　　　　　▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		for (String str : emoti) {
			try {
				Thread.sleep(95);
			} catch (Exception e) {
			}
			System.err.println(str);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		anyPressError();
	}

	public static void putErrorMessageFor() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　　　▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		emoti.add("　　　　　　　　　　　　　　　　████▌▄▌▄▐▐▌█████");
		emoti.add("　　　　　　　　　　　　　　　　████▌▄▌▄▐▐▌▀████");
		emoti.add("　　　　　　　　　　　　　　　　▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		for (String str : emoti) {
			try {
				Thread.sleep(95);
			} catch (Exception e) {
			}
			System.err.println(str);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		anyPress();
	}

	/* 메시지 추가를 원할 경우 하기 메소드만 수정 하면 됨 */
	/* 2023-06-16 17:20 김석호 학생일 경우 출력할 출력문 */
	/* 2023-06-17 08:27 김석호 추가 수정 */
	public static void messageForStudent() {
		/* 학생에게 보여줄 문구를 넣는 문자열 배열 */
		List<String> message = new ArrayList<>();
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에 음식물을 들고오지 마세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에서 과한 애정행각을 하지 마세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책을 소중히 다뤄주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 독서는 마음의 양식입니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에서는 정숙해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 반납 기한을 지켜주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에서는 핸드폰을 무음으로 해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 장난으로 도서 신청을 하지 말아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에서는 뛰지 말아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에서는 사뿐사뿐 걸어주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 교내 흡연시 과태료 10만원입니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관은 PC방이 아닙니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관은 운동장이 아닙니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관은 놀이터가 아닙니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 시스템 이용후 로그아웃을 잊지마세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 로그아웃을 생활화 해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 개인 정보 보호를 위해 로그아웃을 잊지 마세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 특별한 사정으로 연체했을 경우 사서에게 문의주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책장 틈새에 틈새라면을 꽂아놓지 말아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책을 보고나면 제자리에 꽂거나 북트럭에 꽂아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책을 못찾겠으면 사서에게 문의주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책을 살살 넘겨주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 마음이 힘들땐 1393 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 당신곁엔 1393 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 청소년 상담은 1388 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 고민이 있을땐 1388 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관은 석식시간이 끝날때 까지 운영합니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책을 깨끗하게 이용해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책에 낙서하지 말아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 손씻기를 생활화 합시다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관의 책은 혼자만 보는 책이 아닙니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 찾는 책이 없다면 도서 신청 기능을 이용해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 쓰레기는 쓰레기통에 버려주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책장이나 책 사이에 쓰레기를 넣지 말아주세요 ♡");
		/* 이모티의 갯수만큼으로 수정하면 됨 */
//		int a = 0 ; //2023-06-17 김석호 09:40
		/* ScanUtil안에서만 사용할 공통 변수라 필드에 private static int로 변경 */
		int b = ((int) (Math.random() * a)) + 1; // 이모티 중에 하나를 랜덤으로 선택하기 위한 난수
		switch (b) { // 해당 난수가 나오면 이모티 순서대로 case에 호출하면 됨
		case 1:
			emoti1();
			break;
		case 2:
			emoti2();
			break;
		case 3:
			emoti3();
			break;
		case 4:
			emoti4();
			break;
		case 5:
			emoti5();
			break;
		case 6:
			emoti6();
			break;
		case 7:
			emoti7();
			break;
		case 8:
			emoti8();
			break;
		case 9:
			emoti9();
			break;
		case 10:
			emoti10();
			break;
		case 11:
			emoti11();
			break;
		case 12:
			emoti12();
			break;
		case 13:
			emoti13();
			break;
		case 14:
			emoti14();
			break;
		case 15:
			emoti15();
			break;
		default:
			break;
		}
		/* 학생에게 보여줄 문구를 랜덤으로 하나 출력하는 코드 */
		int rnd = (int) (Math.random() * message.size());
		System.out.println(message.get(rnd));
	}

	/* 2023-06-16 17:20 김석호 관리자일 경우 출력할 출력문 */
	/* 2023-06-17 08:27 김석호 추가 수정 */
	public static void messageForManager() {
		/* 관리자에게 보여줄 문구를 넣는 문자열 배열 */
		List<String> message = new ArrayList<>();
		message.add("　　　　　　　　　　　　　　　　♡ 반납받은 책의 상태를 확인해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 구석에서 몰래 음식을 먹는 학생이 있을수도 있습니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 대출, 반납 대행업무를 할 경우, 학생이 본인인지 학생증으로 확인해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 반납 대행업무를 했을 경우, 연체되었다면 연체사실을 학생에게 알려주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 책장 사이에 틈새라면이 꽂혀있을 수 있습니다. 책장 틈새를 잘 확인해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 학생이 구석에서 어두운 표정을 하고 있다면 해당 학생의 명찰을 보고 담임선생님을 찾아 알려주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관 폐관시 창문을 잘 잠궈주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관 폐관시 소등상태를 확인해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관 폐관시 모든 멀티탭을 OFF 해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 구석에서 과한 애정행각을 하는 학생이 있을 수 있습니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 학생이 책을 추천해 달라고해도 아무책이나 함부로 추천해주시면 안됩니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 학생이 책을 못찾는 경우 적극적으로 도와주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 한달에 한번은 도서신청 현황을 꼭 확인 해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서 신청 현황은 목록을 출력한 후 과감히 비워주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관 순회를 자주 해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 학생 정지일을 마구 삭제하시면 안됩니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 시스템 사용후 로그아웃을 필히 해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 학생이 사용후 로그아웃을 하지않을 때가 많습니다. 자주 확인해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 분실물은 카운터에서 보관해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관 청소시 쓰레기인지 분실물인지 헷갈린다면 분실물로 보관해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 분실물 홍보를 항상 해주시고 한달이 넘어가면 버려주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 사서도 음식물 드시면 안됩니다 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관 밖으로 자주 나가지 말아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 근무 시에는 사적인 통화나 스마트폰 사용을 자제해주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 도서관에 외부인을 들이지 말아주세요 ♡");
		message.add("　　　　　　　　　　　　　　　　♡ 학생들에게 존댓말을 사용해주세요 극성 학부모에게 클레임이 들어옵니다 ♡");
		/* 이모티의 갯수만큼으로 수정하면 됨 */
//		int a = 0 ; //2023-06-17 김석호 09:40
		/* ScanUtil안에서만 사용할 공통 변수라 필드에 private static int로 변경 */
		int b = ((int) (Math.random() * a)) + 1; // 이모티 중에 하나를 랜덤으로 선택하기 위한 난수
		switch (b) { // 해당 난수가 나오면 이모티 순서대로 case에 호출하면 됨
		case 1:
			emoti1();
			break;
		case 2:
			emoti2();
			break;
		case 3:
			emoti3();
			break;
		case 4:
			emoti4();
			break;
		case 5:
			emoti5();
			break;
		case 6:
			emoti6();
			break;
		case 7:
			emoti7();
			break;
		case 8:
			emoti8();
			break;
		case 9:
			emoti9();
			break;
		case 10:
			emoti10();
			break;
		case 11:
			emoti11();
			break;
		case 12:
			emoti12();
			break;
		case 13:
			emoti13();
			break;
		case 14:
			emoti14();
			break;
		case 15:
			emoti15();
			break;
		default:
			break;
		}
		/* 관리자에게 보여줄 문구를 랜덤으로 하나 출력하는 코드 */
		/* 배열의 인덱스를 랜덤으로 고르는 코드 */
		int rnd = (int) (Math.random() * message.size());
		/* 난수로 생성된 문자열 인덱스의 값을 하나 출력하는 코드 */
		System.out.println(message.get(rnd));
	}

	public static void emoti1() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣴⣶⣶⣶⣶⣶⠶⣶⣤⣤⣀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⠁⠀⢀⠈⢿⢀⣀⠀⠹⣿⣿⣿⣦⣄⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⠿⠀⠀⣟⡇⢘⣾⣽⠀⠀⡏⠉⠙⢛⣿⣷⡖⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⣾⣿⣿⡿⠿⠷⠶⠤⠙⠒⠀⠒⢻⣿⣿⡷⠋⠀⠴⠞⠋⠁⢙⣿⣄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⢸⣿⣿⣯⣤⣤⣤⣤⣤⡄⠀⠀⠀⠀⠉⢹⡄⠀⠀⠀⠛⠛⠋⠉⠹⡇ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀⣀⣠⣤⣤⣤⣤⣤⣤⣤⣼⣇⣀⣀⣀⣛⣛⣒⣲⢾⡷ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⠤⠒⠒⢼⣿⣿⠶⠞⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⣼⠃ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢮⠀⠀⠀⠀⣿⣿⣆⠀⠀⠻⣿⡿⠛⠉⠉⠁⠀⠉⠉⠛⠿⣿⣿⠟⠁⠀⣼⠃⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠈⠓⠶⣶⣾⣿⣿⣿⣧⡀⠀⠈⠒⢤⣀⣀⡀⠀⠀⣀⣀⡠⠚⠁⠀⢀⡼⠃⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣷⣤⣤⣤⣤⣭⣭⣭⣭⣭⣥⣤⣤⣤⣴⣟⠁         ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti2() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣶⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⣀⡀⣠⣾⡇⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⢿⣿⣿⡇⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⣶⣿⣦⣜⣿⣿⣿⡟⠻⣿⣿⣿⣿⣿⣿⣿⡿⢿⡏⣴⣺⣦⣙⣿⣷⣄⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⣯⡇⣻⣿⣿⣿⣿⣷⣾⣿⣬⣥⣭⣽⣿⣿⣧⣼⡇⣯⣇⣹⣿⣿⣿⣿⣧⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠸⣿⣿⣿⣿⣿⣿⣿⣷⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti3() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣷⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣀⣶⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣦⣀⡀⠀⢀⣴⣇⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⣴⣿⣿⣿⣿⠛⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⣾⣿⣿⣿⣿⣿⣶⣿⣯⣭⣬⣉⣽⣿⣿⣄⣼⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢸⣿⣿⣿⣿⠟⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠁⣿⣿⣿⣿⡿⠛⠉⠉⠉⠉⠁ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠘⠛⠛⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti4() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣿⣿⣷⣶⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⡟⠁⣰⣿⣿⣿⡿⠿⠻⠿⣿⣿⣿⣿⣧⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠏⠀⣴⣿⣿⣿⠉⠀⠀⠀⠀⠀⠈⢻⣿⣿⣇⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⢀⣠⣼⣿⣿⡏⠀⢠⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⣰⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⢰⣿⣿⡿⣿⣿⣿⡇⠀⠘⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⢀⣸⣿⣿⣿⠁⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⣿⣿⣿⠁⣿⣿⣿⡇⠀⠀⠻⣿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣿⣿⣿⠃⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⢰⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⢸⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠛⠉⢉⣿⣿⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⢸⣿⣿⣇⠀⣿⣿⣿⠀⠀⠀⠀⠀⢀⣤⣤⣤⡀⠀⠀⢸⣿⣿⣿⣷⣦⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⢻⣿⣿⣶⣿⣿⣿⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣦⡀⠀⠉⠉⠻⣿⣿⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠛⠿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠈⠹⣿⣿⣇⣀⠀⣠⣾⣿⣿⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣦⣤⣤⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⠿⠋⠉⠛⠋⠉⠉⠁⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁⠀⠀⠀               ⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti5() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti6() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀  ⠀⠀⠀⠀⠀       ⠀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⢀⣿⣿⡿⠟⠙⠛⠛⠁⠀⠈⠙⢿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⣼⣿⣿⠀⠀⠊⣉⡙⠆⣤⣞⡉⠃⢸⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⠀⠀⠀⠀⠀⣻⣿⣿⡆⠀⠀⠈⢁⣠⣈⡛⠁⠀⢸⣿⡟⠀⠀⢀⢀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀ ⠀⠀⠀⢰⣿⣿⣿⣿⠃⠀⠀⠰⣯⣼⣿⣿⠀⠀⠀⢻⣿⣦⡀⡏⠉⠑⡄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⠀⠀⠀⣾⣿⣿⣿⡇⠀⠀⠀⠀⠙⠿⠿⠋⠀⠀⠀⠈⣿⣿⡃⢱⠀⠀⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⢸⣿⣿⣿⣷⠤⣄⣀⣀⣀⠀⠀⠀⠀⢀⡠⠾⠿⠟⢡⠃⠀⠀⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⠀⠀⠀⠈⠙⠓⠀⠀⠀⣴⣿⣧⣼⣽⣷⣾⣟⠉⠉⠒⠒⠊⠀⠀⠀⡇⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⣠⠎⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⢀⠜⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣀⡤⠖⠋⠀⠀         ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti7() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠉⠉⠉⠉⠉⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⢀⣠⣶⣶⣶⣶⣤⡀⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⡏⠄⠄⣾⡿⢿⣿⣿⡿⢿⣿⡆⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄⠄⢿⣇⣸⣿⣿⣇⣸⡿⠃⠄⠄⠸⣿⣿⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⡿⠋⠄⠄⠄⠄⠄⠉⠛⠛⠛⠛⠉⠄⠄⠄⠄⠄⠄⠙⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⡟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⡟⠄⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⡟⠄⠄⠄⢠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣧⠄⠄⠄⠈⢿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⡇⠄⠄⠄⣾⣿⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢰⣿⣧⠄⠄⠄⠘⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣇⠄⣰⣶⣿⣿⣿⣦⣀⡀⠄⠄⠄⠄⠄⠄⠄⢀⣠⣴⣿⣿⣿⣶⣆⠄⢀⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⢸⣿⠇⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣴⣾⣿⣶⣤⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");

		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti8() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠄⠄⠄⠄⠄⣀⣤⣴⣶⣶⣶⣶⣶⣶⣤⣄⣀⠄⠄⠄⠄⠄⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠄⢀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠄⠄⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣆⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⣾⣿⡿⠟⡋⠉⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠉⠙⠻⣿⣿⣇⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⢠⣿⡏⢰⣿⣿⡇⠄⠄⢸⣿⣿⣿⠿⠿⣿⣿⣿⠁⣾⣿⣷⠄⠄⠘⣿⣿⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠸⣿⣇⠈⠉⠉⠄⠄⢀⣼⡿⠋⠄⠄⠄⠄⠙⢿⣄⠙⠛⠁⠄⠄⢠⣿⣿⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⢿⣿⡇⠄⠄⠄⣶⣿⣿⢁⣤⣤⣤⣤⣤⣤⠄⣿⣷⠄⠄⠄⠈⢹⣿⡟⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠈⢿⡗⠄⠄⢸⣿⣿⣿⣶⣶⣶⣶⣶⣶⣶⣶⣿⣿⠄⠄⠄⠄⢸⡟⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠳⡀⠄⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠄⠄⠄⠄⠌⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⠄⠄⠄⠄⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠄⠄⠄⠄⠉⠙⠻⠿⠿⣿⣿⣿⣿⠿⠿⠛⠉⠄⠄⠄⠄⠄⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti9() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡶⠶⢦⣄⠀⠀⠀⠀⠀⣴⠟⠛⢧⣠⣶⣿⠻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⡟⠦⠌⠛⠉⠉⠉⢹⠇⢠⣶⣼⣷⣞⢙⣧⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣤⠃⠀⠀⠀⠀⠀⠀⣿⠀⠈⢻⡃⠀⢸⡿⡄⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠘⠷⠖⠛⠛⠛⢿⡗⢋⣴⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⢻⡀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡶⠾⣷⠆⠀⠀⣤⡀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡀⠀⠐⢺⡟⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⢿⡦⠀⠀⠛⠃⠀⠀⢠⢶⣄⠀⠀⠈⠛⠀⠀⠀⣺⠓⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣼⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⣖⡀⣀⣀⡀⠀⠈⠉⠉⠀⠀⣀⣀⣀⠀⣲⣯⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠶⡆ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢻⡈⠻⣦⣀⣀⣀⣀⣀⠀⠀⠀⠁⣴⠟⠉⠁⠀⠉⠛⢦⡀⢀⡴⠛⠉⠁⠈⠙⠻⣄⠀⠁⣀⣠⣤⣤⣤⣤⡤⠖⠋⣸⠇ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⡿⠳⣤⣀⡀⠀⠀⠉⠉⠉⠳⢦⣼⠃⠀⠀⠀⠀⠀⠀⠀⠿⠋⠀⠀⠀⠀⠀⠀⠀⠹⣦⡞⠉⠀⠀⠀⠀⠀⢀⣠⠶⢻⡆ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠻⣦⣀⠀⠀⠀⡴⠶⢦⡀⠀⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⡴⠚⠳⡄⠈⢉⣀⣠⡾⠁ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠸⣍⣉⣁⡀⣇⠀⠀⠑⠀⢠⡿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢷⡀⠀⠓⠀⢀⡇⢤⣈⣭⠿⠁⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠙⠷⠤⠿⠶⠦⠶⠞⠋⠘⢻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠃⠈⠻⠦⠴⠖⠻⠶⠶⠛⠁⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠻⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠶⣄⡀⢀⣤⠶⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti10() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⡴⠒⠚⣻⠇⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠓⠒⠒⠒⠒⢤⣤⠴⠚⠉⠀⡸⠁⣠⠞⠁⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡆⠀⠀⣠⠖⠋⠀⠀⠀⠀⢀⡧⠞⠣⠤⣀⡀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⣤⠔⠒⠚⣏⠉⠉⠉⠉⠉⠉⠉⠒⠒⠲⠤⠒⠋⠉⠉⠉⠉⠉⠒⠒⠻⢴⠋⠀⠀⠀⠀⠀⣠⠔⠋⠀⠀⠀⠀⠀⠉⠑⠲⢤⡀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠈⠙⠒⠤⢄⣘⣦⡀⠀⠀⠀⠀⠀⠀⡔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⠤⠖⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼      ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠈⢉⣿⣗⡒⠒⠒⡾⠁⣠⣶⠒⡆⠀⠀⠀⠀⠀⠀⠀⣀⣄⡀⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⢠⡎⠀⠀⠙⢦⣀⠇⠀⠻⣼⡿⠁⠀⠀⢠⡄⠀⠀⠸⣷⣼⣷⠀⢸⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠋⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠈⣏⠀⠀⠀⠀⡿⠖⠲⣄⠀⠀⣤⡀⢀⣤⣀⠀⠀⢀⠈⠋⠁⠀⢸⣿⡉⠓⠦⣀⡀⠀⠀⠀⠀⢀⡴⠁⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⢹⡀⠀⠀⠀⡇⠀⠀⣸⠀⠀⢸⣯⠟⠛⠛⢿⣿⠋⠀⢰⠟⠉⠹⡇⢷⠀⠀⠀⠉⠓⠦⣄⣠⠎⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⠀⠀⠹⡦⠴⠋⠀⠀⠀⢹⡄⠀⢀⡼⠁⠀⠀⣇⠀⠀⢠⡇⣀⣧⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⠀⠙⢆⠀⠀⠀⠀⠀⠹⠤⠋⠀⠀⠀⠀⠈⠓⡶⠋⠙⠳⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡄⠀⠀⠀⠀⠑⠶⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⠖⠋⠀⠀⠀⠀⠀⠀⠉⠲⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣶⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⠀⠀⠀⠀⠀⠀⢀⣷⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⠤⣤⠤⠴⠒⠒⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⡆⢀⣠⠤⠒⠒⠒⠂⠀⠀⠐⠒⠒⠒⠒⠲⢦⡀⠀⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣿⡟⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠒⠾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(25);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti11() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢠⣴⣾⣿⣶⣶⣆⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⢀⣀⢀⣤⢀⢀⡀⢀⣿⣿⣿⣿⣷⣿⣿⡇⢀⢀⢀⢀⣤⣀⢀⢀⢀⢀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀ ⣶⢻⣧⣿⣿⠇ ⢸⣿⣿⣿⣷⣿⣿⣿⣷⢀⢀⢀⣾⡟⣿⡷⢀⢀⢀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⠈⠳⣿⣾⣿⣿⢀⠈⢿⣿⣿⣷⣿⣿⣿⣿⢀⢀⢀⣿⣿⣿⠇⢀⢀⢀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⢀⢀⢿⣿⣿⣿⣤⡶⠺⣿⣿⣿⣷⣿⣿⣿⢄⣤⣼⣿⣿⡏⢀⢀⢀⢀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⢀⢀⣼⣿⣿⣿⠟⢀⢀⠹⣿⣿⣿⣷⣿⣿⣎⠙⢿⣿⣿⣷⣤⣀⡀⢀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⢀ ⢸⣿⣿⣿⡿⢀⢀⣤⣿⣿⣿⣷⣿⣿⣿⣄⠈⢿⣿⣿⣷⣿⣿⣷⡀⢀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⢀⢀⣿⣿⣿⣿⣷⣀⣀⣠⣿⣿⣿⣿⣷⣿⣷⣿⣿⣷⣾⣿⣿⣿⣷⣿⣿⣿⣆ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⠛⠋⠉⠉⢻⣿⣿⣿⣿⡇⡀⠘⣿⣿⣿⣷⣿⣿⣿⠛⠻⢿⣿⣿⣿⣿⣷⣦ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣧⡀⠿⠇⣰⣿⡟⠉⠉⢻⡆⠈⠟⠛⣿⣿⣿⣯⡉⢁⣀⣈⣉⣽⣿⣿⣿⣷ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⡿⠛⠛⠒⠚⠛⠉⢻⡇⠘⠃⢸⡇⢀⣤⣾⠋⢉⠻⠏⢹⠁⢤⡀⢉⡟⠉⡙⠏⣹ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣦⣶⣶⢀⣿⣿⣿⣷⣿⣿⣿⡇⢀⣀⣹⣶⣿⣷⠾⠿⠶⡀⠰⠾⢷⣾⣷⣶⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣇⣿⣿⣿⣷⣿⣿⣿⣇⣰⣿⣿⣷⣿⣿⣷⣤⣴⣶⣶⣦⣼⣿⣿⣿⣷ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti12() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⢠⡾⠛⠉⠙⢻⣦⣤⣴⠶⠶⢶⣤⣴⡿⠋⠉⠙⢿⣆⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⢿⡇⠀⢀⣴⠟⠋⠁⠀⠀⠀⠀⠀⠉⠛⢷⣄⠀⠀⣿⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠘⢷⣴⡟⠁⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣦⡾⠋⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⣿⡿⠀⠀⠀⠀⢸⣿⠇⠀⠀⢻⡆⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠘⣿⠀⠀⠀⢀⡤⠖⣒⣒⡒⠦⣄⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⢻⣆⠀⢠⠏⠀⠘⢿⡿⠋⠀⠈⣇⠀⢀⣿⠁⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⣀⣴⠿⣦⡘⡆⠰⢤⣀⣇⣠⠔⢀⡏⣠⡾⢷⣄⡀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⢀⣴⠿⠋⠀⢀⣼⠿⢿⣦⣄⣀⣀⣠⣴⣿⠿⢿⣄⠀⠉⠻⣦⡄⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⣠⡿⠁⠀⠀⣠⡿⠁⠀⠀⠀⠉⠉⠉⠉⠀⠀⠀⠀⠻⣦⠀⠀⠈⠻⣦⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢰⡟⠀⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣧⠀⠀⠀⠹⣧⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⡇⠀⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⣿⠄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠘⢷⣤⣤⡾⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡷⣦⣤⣴⠟⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⣠⡶⠟⠛⠻⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⠾⠛⠛⠷⣦⡀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⣸⠏⠀⠀⠀⠀⠀⠙⣷⡀⠀⠀⠀⠀⠀⣼⠟⠀⠀⠀⠀⠀⠘⣷⡀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⠀⢰⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠹⣧⠀⠀⠀⠀⠀⠀⣸⡿⣦⣤⣤⣤⡾⣿⠀⠀⠀⠀⠀⠀⣠⡿⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠙⢷⣤⣀⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠙⢷⣄⣀⣀⣠⣴⠟⠁⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠈⠉⣉⡉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢉⡉⠉⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti13() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⢰⠁⡠⠐⠋⠉⠉⠉⠑⡲⠩⡉⠑⣆⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠸⡜⠀⠀⠀⠀⠀⠀⠀⠈⠘⠄⠀⢸⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⢀⠃⣀⣆⠀⣤⠀⠀⠀⠀⠀⠰⣠⠞⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⢈⡞⣿⣷⠈⠛⢄⠀⠀⠀⠀⢠⠇⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠘⡄⢠⠀⠀⠀⠈⠆⠀⠀⣠⡞⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⣠⠞⣿⣿⣷⢶⣶⣾⣦⣴⠊⠁⠉⢦⡀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⡴⠁⠀⣽⣿⠃⣾⣿⡟⠃⠘⢕⠄⠀⠀⢱⡀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⡸⠀⠀⢰⣷⡿⠀⣿⣿⡇⠀⠀⢘⢸⠀⠀⠀⡇ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⣴⠓⡒⠒⢿⣿⡇⠀⣿⣿⡇⣀⣀⣎⠂⠀⠀⠀⢹ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣼⠁⠀⠀⠀⡀⡗⡊⡉⠛⢿⠋⠀⠀⢻⡄⠀⠀⢀⡞ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢱⠀⠀⠀⠀⢸⠃⠀⠀⠑⡀⠉⠢⠀⠀⢙⡶⠶⠋⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠈⠳⣄⠀⠀⣸⡀⠀⠀⠀⠐⡀⠀⢁⡠⠞⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠉⠉⠀⠳⣄⠀⠀⢀⢇⡴⠋⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti14() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⢷⣷⣣⠀⠀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⢏⣿⣿⡵⡀⠀⠀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⣠⣮⣷⣿⣿⣿⣿⣷⣄⣄⠀⠀⠀⠀⠈⢞⣿⣿⡵⡀⠀⠀⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⠀⠀⡠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣏⢦⣤⡀⠀⠀⠀⠫⣻⣿⣾⢄⠀⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⠀⣔⣿⣿⣿⣿⣿⣿⠿⣿⠻⢟⣿⣿⣿⣿⣿⡆⠀⠀⠀⠑⡿⣿⣯⢆⠀⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⢰⣸⢿⣻⢟⠃⠉⠉⠀⡠⠤⠸⣸⣿⣿⣿⡳⠁⠀⠀⠀⠀⡨⠺⠿⠇⢓⡄ ♡");
		emoti.add("　　　　　　　　　　　　　　♡  ⠧⠊⠁⠘⣖⣳⠠⣶⣋⡹⠁⠀⠛⣩⢻⠋⠀⠀⠀⠀⠀⢀⠇⠀⠀⠀⠀⢾⠀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⢠⠂⠁⠓⠒⠊⠀⡠⠤⡀⢠⠀⠚⠀⠀⠀⠀⠀⡠⠊⢀⠤⡤⣔⠩⠼⡀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⢇⠀⠀⢀⡠⢔⣪⠠⠖⠇⡘⠀⠀⠀⢀⠄⠒⠉⢀⠔⠁⠀⣧⢞⠮⠭⠵⡀ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠘⠒⠉⣾⣀⣀⠀⣀⣀⠦⠗⠹⠙⠃⠁⠀⡠⠔⡡⠔⠒⠉⡨⢴⢹⣿⣏⡆ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⠀⡸⠉⠀⠀⠁⠀⠀⠀⠀⣇⡠⡄⡶⠯⠔⠈⠀⠀⡠⠊⠀⠀⡿⣿⣿⡇ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⠀⢀⠇⠀⠀⠀⠀⢀⣀⠤⡤⠵⠊⢸⠀⡠⠤⠤⠐⠉⠀⠀⠀⠀⣷⣿⢿⡇ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⠀⠀⢀⠃⠀⢀⣀⣀⣀⣠⣀⣀⣿⠉⠉⠉⠉⠀⠀                            ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void emoti15() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿ ♡");
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		for (String str : emoti) {
			try {
				Thread.sleep(45);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	public static void messageMain() {
		List<String> emoti = new ArrayList<>();
		emoti.add("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⡛⠉⠀⢄⠀⡉⢛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣛⠫⣑⠠⡐⢄⢢⣐⠦⡒⠌⡀⢤⠈⣙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡿⢟⠫⠉⠢⡁⢦⠱⣌⠦⢙⠪⢑⠢⡑⢆⠳⡘⠦⡓⠌⡁⠤⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡏⠂⠤⠀⡠⠄⠂⠁⠀⠀⠢⠀⢄⠠⢐⠂⢁⠀⡁⢀⠑⡀⠢⠐⠈⠁⠠⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠙⠢⢄⣀⡡⠄⡂⠅⡂⢄⠢⡀⢄⡠⢜⡂⢅⡂⣅⠢⡑⣄⠢⠔⠂⠉⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠑⠤⣀⣀⡩⠄⡓⠍⡒⢌⠲⡈⢦⡱⠌⠒⠉⠀⠀⠀⠀⣀⡿⢟⠛⢉⠉⠻⢿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠂⠄⡀⠀⡉⠤⠑⠉⠉⠂⠄⡀⠀⡀⠄⠒⠍⠀⢌⠀⡈⢀⠠⠌⠂⢹⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠠⢐⠈⢁⠠⡁⢀⠁⡀⠤⠐⠈⠐⠀⡐⢀⠠⡀⠄⠒⠈⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⢀⡠⢄⡒⢍⡂⢌⠢⡘⣆⡳⠜⠂⡟⢄⡁⠀⠐⣈⠤⠙⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⠄⡈⠀⠌⢂⡡⠌⠓⠉⠀⠀⠀⠀⡇⠀⠀⠉⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⠀⠀⠉⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣦⣀⣀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⣀⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣶⣷⣄⠀⠀⡇⢀⣠⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀⣀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		emoti.add("　　　　　　　　　　　　　　♡ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿♡");
		for (String str : emoti) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			System.out.println(str);
		}
	}

	
//	  ScanUtil.errSleep();
	 
	public static void errSleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
	}

}