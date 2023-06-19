package controller;


import service.DiscardBooksService;
import service.LoanService;
import service.LoginService;
import service.LogoutService;
import service.ModifyStudentService;
import service.NewBooksService;
import service.RcmdService;
import service.RequestService;
import service.ReturnBooksService;
import service.RsvService;
import service.SelectService;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;

public class Controller {

   public static void main(String[] args) {
      JDBCUtil jdbc = JDBCUtil.getInstance();
      DiscardBooksService dcb = DiscardBooksService.getInstance();   //도서폐기
      LoginService login = LoginService.getInstance();            //로그인
      RcmdService rc1 = RcmdService.getInstance();               //도서 추천
      RequestService r1 = RequestService.getInstance();            //도서신청
      NewBooksService newB=NewBooksService.getInstance();            //도서등록
      MainS m1=MainS.getInstance();                           //학생메인메뉴
      MainM m2 = MainM.getInstance();                           //관리자 메인메뉴
      MyPage myp = MyPage.getInstance();                        //마이페이지
      StudentBook stdB=StudentBook.getInstance();                        //대출반납대행메인
      SelectService s1=SelectService.getInstance();               //현황조회(검색,대출,연체,신청)
      ReturnBooksService reB = ReturnBooksService.getInstance();
      LoanService ls = LoanService.getInstance();
      StudBook stb = StudBook.getInstance();
      RsvService resvS = RsvService.getInstance();
      ManBook m3=ManBook.getInstance();
      Main ma = Main.getInstance();
      ModifyStudentService modi = ModifyStudentService.getInstance();
      RequestS rqs = RequestS.getInstance();
      
      jdbc.connectConn();
      
     while(true) {
      loop01 : while(true) {
  		ScanUtil.putEmptySpace();
  		ScanUtil.messageMain();
    	  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
    	  System.out.println("　　　　　　　　　　　　　　　　비밀번호가 기억이 안날 경우 사서에게 문의 부탁드립니다");
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　　　시스템 메인 화면");
			System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			System.out.println();
			System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　                     1. 로그인                                                               2. 시스템 종료");
			System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
	        System.out.println();
	        System.out.println();
	        System.out.println();
	        System.out.println();
	        System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
          String menu0 = ScanUtil.nextLine();
          switch (menu0) {
          case "1":
             login.loginService();
             if(!(JDBCUtil.loginUser==null)) {
            	 break loop01;
             }else {
            	 continue loop01;
             }
          case "2":
             System.out.println("　　　　　　　　　　　　　　♡♡ 시스템을 종료합니다 ♡♡");
             jdbc.disconnectConn();
             return;
          default:
        	  ScanUtil.errSleep();
        	  System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
        	 ScanUtil.putErrorMessage();
             continue loop01;
          }
      }
      
      if (JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {   //학생메뉴
         int view = View.S_MAIN;
         while (true) {
        	if(JDBCUtil.loginUser==null) break;
            switch (view) {
            case View.MAIN :            //메인
                view = ma.main();
                break;
            case View.S_MAIN :            //메인
               view = m1.main();
               break;
            case View.S_SRHBOOK :         //도서검색
               view = s1.selectBookList();
               break;   
            case View.S_RECOBOOK :         //추천도서 검색
               view = rc1.rcmdService();
               break;
            case View.S_LENDSTMT :               //대출현황조회
               view = s1.selectLendStatement();
               break;
            case View.S_REQBOOK :         //도서 신청
               view = r1.requestService();
               break;
            case View.S_MYPAGE :         //마이페이지
               view = myp.myPage();
               break;
            case View.S_LRMAIN :         //학생 대출 반납 메인
               view = stb.stdBook();
               break;
            case View.S_RSVBOOK :         //예약
                view=resvS.rsvService();
                break;
            case View.S_LOAN :
            	view = ls.loanAsStudent();
            default:
               break;
            }
         }
      }else {                                       //관리자메뉴
         int view = View.M_MAIN;
         while(true) {
        	if(JDBCUtil.loginUser==null) break;
            switch (view) {
            case View.MAIN :            //메인
                view = ma.main();
                break;
            case View.M_MAIN :            //메인
               view=m2.main();
               break;
            case View.S_SRHBOOK :            //도서 검색
               view = s1.selectBookList();
               break;
            case View.M_NEWBOOK :      //도서 등록
               view=newB.newBooksService();
               break;
            case View.M_DSCBOOK :      //도서 폐기
               view=dcb.discardBooks();
               break;
            case View.M_MANBOOK :      //도서 관리(등록, 폐기)
               view=m3.manBook();
               break;
            case View.M_LOANM :            //대출대행
               view= ls.loanAsManager();
               break;
            case View.M_RETURNM :            //반납대행
               view=reB.returnBookForManager();
               break;
            case View.M_STDBOOK :            //대출반납대행 메뉴
               view=stdB.stdBook();
               break;
            case View.M_SOSTMT :            //연체현황조회
               view=s1.selectOverStatement();
               break;
            case View.M_RQSTMT :            //도서 신청 현황 조회 및 튜플 삭제
//               view=s1.selectRequestStatement();
            	/* 2023-06-16 김석호 14:33 도서신청 현황 조회 메인 호출 수정*/
            	view=rqs.requestS();
               break;
            case View.S_LRMAIN :         //학생 대출 반납 메인
                view = stb.stdBook();
                break;
            case View.M_MODI : //학생정보 수정
            	view = modi.mdfServiceM();
            default:
               break;
            }
         }
      }
   }
   }
   }


class Main{
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	LoginService login = LoginService.getInstance();
	
	private static Main instance = null;

	   private Main() {
	   }

	   public static Main getInstance() {
	      if (instance == null)
	         instance = new Main();
	      return instance;
	   }

	public int main() {
		ScanUtil.putEmptySpace();
		ScanUtil.messageMain();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　비밀번호가 기억이 안날 경우 사서에게 문의 부탁드립니다");
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　시스템 메인 화면");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　                     1. 로그인                                                               2. 시스템 종료");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
        System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
        String menu0 = ScanUtil.nextLine();
        switch (menu0) {
        case "1":
        	int as = login.loginService(); 
        	if(!(JDBCUtil.loginUser==null)) {
        		return as;
        	}else {
        		return View.MAIN;
        	}
        case "2":
           System.out.println("　　　　　　　　　　　　　　♡♡ 시스템을 종료합니다 ♡♡");
           jdbc.disconnectConn();
           return 0;
        default:
        	ScanUtil.errSleep();
           System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
           ScanUtil.putErrorMessage();
           return View.MAIN;
//        case "1":
//            login.loginService();
//         case "2":
//            System.out.println("　　　　　　　　　　　　　　♡♡ 시스템을 종료합니다 ♡♡");
//            jdbc.disconnectConn();
//            return;
//         default:
//       	  System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
//       	 ScanUtil.putErrorMessage();
//            continue loop01;
	}
}
}


class MainS {
   /* 수정인 : 김석호  수정일시 230615 11:50
    * 수정사항 : 대출 / 예약, 로그아웃 추가
    */
   StudBook stb = StudBook.getInstance();
   LogoutService logout = LogoutService.getInstance();
   private static MainS instance = null;

   private MainS() {}
 
   public static MainS getInstance() {
      if (instance == null)
         instance = new MainS();
      return instance;
   }
   
   public int main() {
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　학생 메인 화면");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　                     1. 도서 검색                                                         2. 추천도서 조회");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　                     3. 대출현황 조회                                                   4. 도서 신청");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　                     5. 마이페이지                                                       6. 대출 및 반납");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　                     7. 로그아웃");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
      String view = ScanUtil.nextLine();
         switch (view) {
         case "1":
            return View.S_SRHBOOK;
         case "2":
            return View.S_RECOBOOK;
         case "3":
            return View.S_LENDSTMT;
         case "4":
            return View.S_REQBOOK;
         case "5":
            return View.S_MYPAGE;
         case "6":
            return View.S_LRMAIN;
         case "7":
            return logout.logout();
         default:
        	 ScanUtil.errSleep();
            System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
            ScanUtil.putErrorMessageFor();
            return View.S_MAIN;
         }
      }
}

class MainM {
	LogoutService logout = LogoutService.getInstance();
   private static MainM instance = null;

   private MainM() {
   }

   public static MainM getInstance() {
      if (instance == null)
         instance = new MainM();
      return instance;
   }

   public int main() {
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　관리자 메인 화면");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              1. 도서 검색                                                             2. 도서 등록 및 폐기");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              3. 대출 및 반납 대행                                                  4. 미반납 연체 조회");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              5. 도서 신청 현황 관리                                               6. 학생정보 수정");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              7. 로그아웃");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
      String view = ScanUtil.nextLine();
      switch (view) {
      case "1":
         return View.S_SRHBOOK;
      case "2":
         return View.M_MANBOOK;
      case "3":
         return View.M_STDBOOK;
      case "4":
         return View.M_SOSTMT;
      case "5":
         return View.M_RQSTMT;
      case "6" :
    	  return View.M_MODI;
      case "7":
          return logout.logout();
      default:
    	  ScanUtil.errSleep();
          System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
          ScanUtil.putErrorMessageFor();
         return View.M_MAIN;
      }
   }
}

class ManBook {

   private static ManBook instance = null;

   private ManBook() {
   }

   public static ManBook getInstance() {
      if (instance == null)
         instance = new ManBook();
      return instance;
   }

   public int manBook() {
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　도서 등록 및 폐기");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              1. 도서 등록                                                           2. 도서 폐기");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              3. 뒤로가기");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
      String view = ScanUtil.nextLine();
      switch (view) {
      case "1":
         return View.M_NEWBOOK;
      case "2":
         return View.M_DSCBOOK;
      case "3":
          return View.M_MAIN;
      default:
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
         ScanUtil.putErrorMessageFor();
         return View.M_MANBOOK;
      }
   }
}

class MyPage {

   private static MyPage instance = null;

   private MyPage() {
   }

   public static MyPage getInstance() {
      if (instance == null)
         instance = new MyPage();
      return instance;
   }

   ModifyStudentService mdfSS = ModifyStudentService.getInstance();
   RsvService rsvS = RsvService.getInstance();
   SelectService ss = SelectService.getInstance();
   public int myPage() {
      /* 출력정보 표기해줄 공간 */
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　마이 페이지");
		System.out.println("　　　　　　　　　　　　　　　　♡ 학번 : " + JDBCUtil.loginUser.get("STD_NO"));
		System.out.println("　　　　　　　　　　　　　　　　♡ 이름 : " + JDBCUtil.loginUser.get("STD_NAME"));
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		/* 대출 가능상태인지 불가상태인지 출력해줄 장소*/
		ss.viewMypageStateInfo();
		/* 정지일이 있을 경우 정지일 정보를 출력해주는 메소~드 */
		ss.viewMypageStopInfo();
		/* 현재 대출 권수 */
		ss.viewMypageLendInfo();
		/* 예약 현황 null or 책제목 , 대출가능여부 */
		ss.viewMypageReserveInfo();
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              1. 비밀번호 변경                                                           2. 예약 취소");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              3. 뒤로가기");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
      String view = ScanUtil.nextLine();
      switch (view) {
      case "1":
         return mdfSS.mdfServiceS();
      case "2":
         return rsvS.cancRsvService();
      case "3":
         return View.S_MAIN;
      default:
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
         ScanUtil.putErrorMessage();
         return View.S_MYPAGE;
      }
   }
}

class StudentBook {
   ReturnBooksService reB = ReturnBooksService.getInstance();
   LoanService l1=LoanService.getInstance();

   private static StudentBook instance = null;
   private StudentBook() {}

   public static StudentBook getInstance() {
      if (instance == null)
         instance = new StudentBook();
      return instance;
   }
   

   public int stdBook() {
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　관리자 메뉴 - 대출 및 반납 대행");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              1. 대출 대행                                                                 2. 반납 대행");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　              3. 뒤로가기");
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
      String view = ScanUtil.nextLine();
      switch (view) {
      case "1":
         return l1.loanAsManager();      //대출대행메소드
      case "2":
         return reB.returnBookForManager();      //반납대행메소드
      case "3":
    	 ScanUtil.anyPress();
         return View.M_MAIN;
      default:
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
         ScanUtil.putErrorMessage();
         return View.M_STDBOOK;
      }
   }
}



class RequestS{
   
   private static RequestS instance = null;
   private RequestS() {}

   public static RequestS getInstance() {
      if (instance == null)
         instance = new RequestS();
      return instance;
   }
   
   SelectService s1=SelectService.getInstance();
   RequestService rqsS =RequestService.getInstance();
   
   public int requestS() {
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　도서신청현황 조회 및 삭제");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　             1. 도서신청 현황 조회                                                 2. 도서신청 목록 삭제");
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
      String view = ScanUtil.nextLine();
      switch (view) {
      case "1":
         return s1.selectRequestStatement();      //도서신청 현황조회
      case "2":
         return rqsS.requestClear();   // 도서신청 삭제
         
         /* 2023-06-16 14:34 김석호 뒤로가기 추가*/
      case "3":
          return View.M_MAIN;
      default:
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
         ScanUtil.putErrorMessage();
         return View.M_RQSTMT;
      }
   }
}

/* 수정자 : 김석호
 * 추가사항 : 학생 도서대출 및 반납기능 메뉴 추가
 * 수정 일시 : 2023.06.15 11:40
 */
class StudBook{
   private static StudBook instance = null;
   private StudBook() {}

   public static StudBook getInstance() {
      if (instance == null)
         instance = new StudBook();
      return instance;
   }
   ReturnBooksService reB = ReturnBooksService.getInstance();
   LoanService l1=LoanService.getInstance();
   
   public int stdBook() {
	   ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　대출 및 반납");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　             1. 대출                                                                   2. 반납");
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
      String view = ScanUtil.nextLine();
      switch (view) {
      case "1":
         return l1.loanAsStudent();      //대출메소드
      case "2":
         return reB.returnBookForStudent();      //반납메소드
      case "3":
         return View.S_MAIN;
      default:
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
         ScanUtil.putErrorMessage();
         return View.S_LRMAIN;
      }
   }
   
   
   
}