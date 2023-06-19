package service;
/* 2023-06-16 17:07 김석호 출력 형식 수정 완료 */

import dao.RequestDAO;
import util.ScanUtil;
import util.View;

public class RequestService {
	private static RequestService instance=null;
	private RequestService() {}
	
	public static RequestService getInstance() {
		if(instance==null) instance=new RequestService();
		return instance;
	}
	
	RequestDAO reqDAO = RequestDAO .getInstance();

	public int requestService() {
		ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　도서 신청");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		String req_title = "";
		String req_writer = "";
		String req_pub = "";
		int req_price = -1;
//		title1 : while(true) {
		title : while(true) {
			System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
			System.out.print("　　　　　　　　　　　　　　　　♡ 제목 입력 : ");
			req_title = ScanUtil.nextLine();
			if(req_title.equals("뒤로가기")) {
				lo1: while(true) {
					System.out.println("　　　　　　　　　　　　　　　　♡ 1. 책 제목이 \"뒤로가기\" ♡");
					System.out.println("　　　　　　　　　　　　　　　　♡ 2. 뒤로가기 ♡");
					System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
					switch (ScanUtil.nextLine()) {
					case "1":
						break lo1;
					case "2":
						ScanUtil.anyPress();
						return View.S_MAIN;
					default:
						ScanUtil.errSleep();
						System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
						break;
					}
				}
			}
			if(ScanUtil.canInsertColumn(req_title,500)) {
				break title;
			}else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 너무 긴 글자를 입력하셨습니다 ♨");
				ScanUtil.errSleep();
			}
		}
//			title2 :while(true) {
//				System.out.println("　　　　　　　　　　　　　　♡ 제목 : "+req_title);
//				System.out.println("　　　　　　　　　　　　　　♡ 1.제목 다시 입력 ♡ 2. 진행 ♡");
//				System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
//				switch (ScanUtil.nextLine()) {
//				case "1": break title2 ;
//				case "2": break title1 ;
//				default:
//					ScanUtil.errSleep();
//					System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
//					break;
//				}
//			}
//		}
//		writer1 : while(true) {
		wri : while(true) {
			System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
			System.out.print("　　　　　　　　　　　　　　　　♡ 저자 입력 : ");
			req_writer = ScanUtil.nextLine();
			if(req_writer.equals("뒤로가기")) {
				lo1: while(true) {
					System.out.println("　　　　　　　　　　　　　　　　♡ 1. 저자가 \"뒤로가기\" ♡");
					System.out.println("　　　　　　　　　　　　　　　　♡ 2. 뒤로가기 ♡");
					System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
					switch (ScanUtil.nextLine()) {
					case "1":
						break lo1;
					case "2":
						ScanUtil.anyPress();
						return View.S_MAIN;
					default:
						ScanUtil.errSleep();
						System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
						break;
					}
				}
			}
			if(ScanUtil.canInsertColumn(req_writer,200)) {
				break wri;
			}else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 너무 긴 글자를 입력하셨습니다 ♨");
				ScanUtil.errSleep();
			}
			
		}
//			writer2 :while(true) {
//				System.out.println("　　　　　　　　　　　　　　♡ 저자 : "+ req_writer);
//				System.out.println("　　　　　　　　　　　　　　♡ 1.저자 다시 입력 ♡ 2. 진행 ♡");
//				System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
//				switch (ScanUtil.nextLine()) {
//				case "1":
//					break writer2;
//				case "2":
//					break writer1;
//				default:
//					ScanUtil.errSleep();
//					System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
//					break;
//				}
//			}
//		}
//		pub1 :while(true) {
		pub : while(true) {
			System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
			System.out.print("　　　　　　　　　　　　　　　　♡ 출판사 입력 : ");
			req_pub = ScanUtil.nextLine();
			if(req_pub.equals("뒤로가기")) {
				lo1: while(true) {
					System.out.println("　　　　　　　　　　　　　　　　♡ 1. 출판사가 \"뒤로가기\" ♡");
					System.out.println("　　　　　　　　　　　　　　　　♡ 2. 뒤로가기 ♡");
					System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
					switch (ScanUtil.nextLine()) {
					case "1": break lo1;
					case "2":
						ScanUtil.anyPress();
						return View.S_MAIN;
					default:
						ScanUtil.errSleep();
						System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
						break;
					}
				}
			}
			if(ScanUtil.canInsertColumn(req_pub,200)) {
				break pub;
			}else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 너무 긴 글자를 입력하셨습니다 ♨");
				ScanUtil.errSleep();
			}
		}
//			pub2 :while(true) {
//				System.out.println("　　　　　　　　　　　　　　♡ 출판사 : "+req_pub);
//				System.out.println("　　　　　　　　　　　　　　♡ 1.출판사 다시 입력 ♡ 2. 진행 ♡");
//				System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
//				switch (ScanUtil.nextLine()) {
//				case "1": break pub2;
//				case "2": break pub1;
//				default:
//					ScanUtil.errSleep();
//					System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
//					break;
//				}
//			}
//		}
		ScanUtil.errSleep();
		System.err.println("　　　　　　　　　　　　　　♡ 장난치지말고 제대로 입력해주세요 ♡");
		price1 : while(true) {
			System.out.println("　　　　　　　　　　　　　　♡ 가격을 모를경우 0, 뒤로가려면 1을 입력하세요 ♡");
			System.out.print("　　　　　　　　　　　　　　　　♡ 가격 입력(숫자만) : ");
			req_price = ScanUtil.nextInt();
			if(req_price==1) {
				ScanUtil.anyPress();
				return View.S_MAIN;
			}else if(req_price<0) {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 금전적인 일에 장난치지말고 제대로 입력하세요 ♨");
				continue price1;
			}else {
				break price1;
			}
//			price2 :while(true) {
//				System.out.println("　　　　　　　　　　　　　　♡ 가격 : "+req_price);
//				System.out.println("　　　　　　　　　　　　　　♡ 1.가격 다시 입력 ♡ 2. 진행 ♡");
//				System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
//				switch (ScanUtil.nextLine()) {
//				case "1": break price2;
//				case "2": break price1;
//				default:
//					ScanUtil.errSleep();
//					System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
//					break;
//				}
//			}
		}
		
		/*수정사항 : null 입력시 도서신청 실패 출력 / 박민주 / 2023.06.15, 11:38*/
		if(req_title.equals("") || req_writer.equals("")|| req_pub.equals("")) {
			ScanUtil.errSleep();
			System.err.println("　　　　　　　　　　　　　　♨ 공란 입력으로 도서 신청 실패 ♨");
			ScanUtil.anyPress();
			return View.S_MAIN;
		} 
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　♡ 제목 : "+req_title);
		System.out.println("　　　　　　　　　　　　　　♡ 저자 : "+ req_writer);
		System.out.println("　　　　　　　　　　　　　　♡ 출판사 : "+req_pub);
		System.out.println("　　　　　　　　　　　　　　♡ 가격 : "+req_price);
			
		System.out.println("　　　　　　　　　　　　　　♡ 해당 정보로 도서를 신청하시겠습니까?");
		System.out.println("　　　　　　　　　　　　　　　　♡ 1. 예 ♡ 2. 아니오 ♡");
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
		String a = ScanUtil.nextLine();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		switch(a) {
		case "1" : 
			int result = reqDAO.requestDAO(req_title, req_writer, req_pub, req_price);
			if (result == 1) {
				System.out.println("　　　　　　　　　　　　　　♡ 도서 신청 완료 ♡");
			} else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 오류로 인한 도서 신청 실패 ♨");
			}
			break;
		case "2" :	//while문으로 아니오 클릭했을 때 다시 입력받으려고하면 제목입력 sc.nextLine 오류남
			System.out.println("　　　　　　　　　　　　　　♡ 도서 신청 취소 ♡");
			break;
		default : 
			ScanUtil.errSleep();
			System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호 입력으로 도서 신청 실패 ♨");
		}
		ScanUtil.anyPress();
		return View.S_MAIN;
	}
	
	public int requestClear() {
		
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		int countRequest = reqDAO.countRequestListDAO();
		if(countRequest!=0) {
			System.out.println("　　　　　　　　　　　　　　　　♡ 총 " + countRequest + "건의 도서 신청이 검색되었습니다.");
			System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			System.out.println("　　　　　　　　　　　　　　♡ 도서 신청 목록을 비우시겠습니까?");
			System.out.println("　　　　　　　　　　　　　　　　♡ 1. 비우기 ♡ 2. 취소 ♡");
			System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
			String menu = ScanUtil.nextLine();
			switch (menu) {
			case "1":
				int result = reqDAO.clearRequestListDAO();
				if(result == countRequest) System.out.println("　　　　　　　　　　　　　　♡ 성공적으로 신청목록을 비웠습니다 ♡");
				break;
			case "2":
				System.out.println("　　　　　　　　　　　　　　♡ 신청 목록을 비우지 않고 나갑니다 ♡");
				break;
			default:
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 입력이 잘못되어 화면을 나갑니다 ♨");
			}
		}else {
			System.err.println("　　　　　　　　　　　　　　♨ 도서 신청 목록이 없습니다 ♨");
		}
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		ScanUtil.anyPress();
		return View.M_RQSTMT;
	}
}