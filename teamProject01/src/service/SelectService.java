package service;
 /* 표시형식 수정 완료 2023-06-16 12:36 김석호 */
import java.util.List;
import java.util.Map;
import dao.SelectDAO;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;

public class SelectService {
   int resultSize;
   private static SelectService instance = null;
   private SelectService() {}
   public static SelectService getInstance() {
      if(instance == null) instance = new SelectService();
      return instance;
   }
   SelectDAO sld = SelectDAO.getInstance();
   JDBCUtil jdbc = JDBCUtil.getInstance();
   // 도서 검색
   public int selectBookList() {
	   ScanUtil.putSomeMessage();
	   System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　도서 검색");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
	   String title = "";
	   lo1:while(true) {
		   System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
		   System.out.print("　　　　　　　　　　　　　　♡ 검색할 책 제목을 입력해주세요 : ");
		   title = ScanUtil.nextLine();
		   if(title.equals("뒤로가기")) {
			   System.out.println("　　　　　　　　　　　　　　♡ 1. 검색할 책이름이 \"뒤로가기\"");
			   System.out.println("　　　　　　　　　　　　　　♡ 2. 책이름 다시 입력");
			   System.out.println("　　　　　　　　　　　　　　♡ 3. 뒤로가기");
			   System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
			   switch (ScanUtil.nextLine()) {
			case "1":
				break lo1;
			case "2":
				continue lo1;
			case "3":
				if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {
					ScanUtil.anyPress();
					return View.S_MAIN;
				}else if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("2")) {
					ScanUtil.anyPress();
					return View.M_MAIN;
				}else {
					ScanUtil.anyPress();
					return View.MAIN;
				}
			default:
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
				ScanUtil.anyPressError();
				break;
			}
		   }else {
			   break lo1;
		   }
	   }
      title = "%"+ title + "%";
      List<Map<String, Object>> list = sld.selectBook(title);
      int ls = list.size();
      // 7 개씩 출력하기위한 코드
      int page = 1;
      int i = 0 ;
      final int count = 7;
      if(ls == 0) {
    	  ScanUtil.errSleep();
    	  System.err.println("　　　　　　　　　　　　　　　　♡ 검색된 도서가 없습니다 ♡");
    	if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {
    		ScanUtil.anyPress();
            return View.S_MAIN;
         }else if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("2")) {
        	 ScanUtil.anyPress();
            return View.M_MAIN;
         }else {
        	 ScanUtil.anyPress();
            return View.MAIN;
         }
      }
      int maxPage = 0;
      /* 2023-06-19 08:30 수정 */
      if((ls%count)==0) {
    	  maxPage = (int)(ls/count);
      }else {
    	  maxPage = (int)(ls/count)+1;
      }
      
      
      loop : while(i<ls) {
//         if(i==(list.size()-1)) {break;}
    	  System.out.println("　　　　　　　　　　　　　　　　♡ 총 "+ls+"권의 책이 검색되었습니다. ♡");
    	  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
         for (; i < (page*count); i++) {
            String no = String.valueOf(list.get(i).get("BK_NO"));
            String shf = String.valueOf(list.get(i).get("BK_SHFCODE"));
            String lds = String.valueOf(list.get(i).get("A"));
            String rsv = String.valueOf(list.get(i).get("B"));
            String bookTitle = String.valueOf(list.get(i).get("BK_TITLE"));
            String wri = String.valueOf(list.get(i).get("BK_WRITER"));
            String pub = String.valueOf(list.get(i).get("BK_PUB"));
            System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %4s ♡ 서가코드 : %2s ♡ 대출여부 : %4s ♡ 예약여부 : %4s ♡\n", bookTitle,wri,pub,no,shf,lds,rsv);
            System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
            if(i==(ls-1)) {
            System.out.println("　　　　　　　　　　　　　　　　♡ 더이상 검색 결과가 없어 도서검색을 종료합니다 ♡");
            	
            	break loop;}
         }
         inputLoop :   while (true) {
        	 if(page!=1) {
        		 
        		 System.out.println("　　　　　　　　　　　　　　♡ 1. 이전페이지 ♡ 2. 다음페이지 ♡ 3. 검색종료 ♡ 페이지 "+page+" / "+maxPage+" ♡ 마지막 페이지 도달시 페이지 선택이 불가합니다 ♡");
        		 System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
        		 System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
        		 String input = ScanUtil.nextLine();
        		 switch (input) {
        		 case "1":
        			 page -= 1;
        			 i -= count*2;
        			 jdbc.clearConsole();
        			 break inputLoop;
        		 case "2":
        			 page += 1;
        			 jdbc.clearConsole();
        			 break inputLoop;
        		 case "3":
        			 System.out.println("　　　　　　　　　　　　　　　　♡ 도서검색을 종료합니다 ♡");
        			 if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {
        				 ScanUtil.anyPress();
        				 return 31;
        			 }else if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("2")) {
        				 ScanUtil.anyPress();
        				 return 71;
        			 }else {
        				 ScanUtil.anyPress();
        				 return 1;
        			 }
        		 default:
        			 ScanUtil.errSleep();
        			 System.err.println("　　　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
        			 continue inputLoop;
        		 }// 스위치 문 끝
        	 }else {
        			System.out.println("　　　　　　　　　　　　　　♡ 　　　　　　　　　 2. 다음페이지 ♡ 3. 검색종료 ♡ 페이지 "+page+" / "+maxPage+" ♡ 마지막 페이지 도달시 페이지 선택이 불가합니다 ♡");
        		 System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
        		 System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
        		 String input = ScanUtil.nextLine();
        		 switch (input) {
        		 case "2":
        			 page += 1;
        			 jdbc.clearConsole();
        			 break inputLoop;
        		 case "3":
        			 System.out.println("　　　　　　　　　　　　　　　　♡ 도서검색을 종료합니다 ♡");
        			 if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {
        				 ScanUtil.anyPress();
        				 return 31;
        			 }else if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("2")) {
        				 ScanUtil.anyPress();
        				 return 71;
        			 }else {
        				 ScanUtil.anyPress();
        				 return 1;
        			 }
        		 default:
        			 ScanUtil.errSleep();
        			 System.err.println("　　　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
        			 continue inputLoop;
        		 }// 스위치 문 끝
        	 }
//            jdbc.clearConsole();
         }
      }
      if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {
    	  ScanUtil.anyPress();
         return View.S_MAIN;
      }else if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("2")) {
    	  ScanUtil.anyPress();
         return View.M_MAIN;
      }else {
    	  ScanUtil.anyPress();
         return View.MAIN;
      }
   
   }
   
   
   // 대출 현황 조회 학생용
   public int selectLendStatement() {
	   ScanUtil.putSomeMessage();
      List<Map<String, Object>> list = sld.selectLendInfo();
      int a = list.size();
      switch (a) {
      case 0:
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　　　♡ 대출중인 책이 없습니다 ♡");
         break;
      default:
    	  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
         System.out.println("　　　　　　　　　　　　　　　　♡ 대출 권수 : " + a + "권");
         System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
         for(int i = 0; i < a ; i++) {
            String no = String.valueOf(list.get(i).get("BK_NO"));
            String bookTitle = String.valueOf(list.get(i).get("BK_TITLE"));
            String wri = String.valueOf(list.get(i).get("BK_WRITER"));
            String pub = String.valueOf(list.get(i).get("BK_PUB"));
            Map<String, Object> list1 = sld.LendReturndateInfo(no);
            String epdate = String.valueOf(list1.get("LD_EPDATE"));
            System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s ♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %4s ♡ 반납기한 : %s ♡\n", bookTitle, wri, pub, no,epdate);
            System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
         }
         break;
      }
      ScanUtil.anyPress();
      return View.S_MAIN;
   }
   
   // 대출 현황 조회
//   public int selectLendStatement(String stdno) {
//      List<Map<String, Object>> list = sld.selectLendInfo();
//      int a = list.size();
//      switch (a) {
//      case 0:
//         System.out.println("　　　　　　　　　　　　　　　　♡ 대출중인 책이 없습니다 ♡");
//         break;
//      default:
//    	 System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
//         System.out.println("　　　　　　　　　　　　　　　　♡ 대출 권수 : " + a + "권 ♡");
//         System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
//         for(int i = 0; i < a ; i++) {
//            String no = String.valueOf(list.get(i).get("BK_NO"));
//            String bookTitle = String.valueOf(list.get(i).get("BK_TITLE"));
//            String wri = String.valueOf(list.get(i).get("BK_WRITER"));
//            String pub = String.valueOf(list.get(i).get("BK_PUB"));
//            Map<String, Object> list1 = sld.LendReturndateInfo(no);
//            String epdate = String.valueOf(list1.get("LD_EPDATE"));
//            System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s ♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %4s ♡\n", bookTitle, wri, pub, no);
//         }
//         break;
//      }
//      return View.S_MAIN;
//   }
   
   // 마이페이지에 표시할 대출권수 정보
   public void viewMypageLendInfo() {
      int bookCount = sld.lendCountInfo();
      System.out.println("　　　　　　　　　　　　　　　　♡ 대출권수 : "+bookCount);
   }
   // 마이페이지에 표시할 정지일 정보
   public void viewMypageStopInfo() {
	   String bookCount = sld.stopdStateInfo();
	   if(!bookCount.equals("0")) {
		   System.out.println("　　　　　　　　　　　　　　　　♨ 정지일 ♨ : "+bookCount);
	   }
   }
   /* 마이 페이지에 대출 가능인지 대출 불가인지 상태를 표기해주는 메소드
    * 2023-06-16 19:56 김석호 추가
    * */
   public void viewMypageStateInfo() {
	   String bookCount = sld.stopdStateInfo();
	   int lendBookCount = sld.lendCountInfo();
	   int overState = sld.overdueState();
	   if(bookCount.equals("0")) {
		   if(lendBookCount!=3) {
			   if(overState==0) {
				   System.out.println("　　　　　　　　　　　　　　　　♡ 대출 상태 : 가능 ♡");
				   return;
			   }
		   }
	   }
	   System.out.println("　　　　　　　　　　　　　　　　♡ 대출 상태 : 불가 ♡");
   }
   
   // 마이페이지에 표시할 예약 정보
   public void viewMypageReserveInfo() {
      Map<String, Object> resInfo = sld.reserveInfo();
      if(resInfo != null) {
         String no = String.valueOf(resInfo.get("BK_NO"));
         String bookTitle = String.valueOf(resInfo.get("BK_TITLE"));
         String canLend = String.valueOf(resInfo.get("A"));
         System.out.printf("　　　　　　　　　　　　　　　　♡ 예약정보 ♡\n　　　　　　　　　　　　　　　　♡ 책번호 : %s ♡ 상태 : %s ♡ \n　　　　　　　　　　　　　　　　♡ 제목 : %s ♡ \n",no,canLend,bookTitle);
      }else {
         System.out.println("　　　　　　　　　　　　　　　　♡ 예약정보 : 없음 ♡");
      }
   }
   
   // 연체현황 조회(매니저용)
   public int selectOverStatement() {
	   ScanUtil.putSomeMessage();
	   System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      List<Map<String, Object>> list = sld.selectOverList();
      //칼럼 항목
      // BKNO-책번호 | OVERDUE-연체일 | NAME 학생이름  | STDNO-학번 | TITLE 책제목
      if(!(list.isEmpty())){
    	  System.out.println("　　　　　　　　　　　　　　　　♡ 연체중인 미반납 책 정보 ♡");
    	  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
    	  for(int i = 0 ; i < list.size() ; i++) {
    		  String bkno = String.valueOf(list.get(i).get("BKNO"));
    		  String overdue = String.valueOf(list.get(i).get("OVERDUE"));
    		  String stdname = String.valueOf(list.get(i).get("NAME"));
    		  String stdno = String.valueOf(list.get(i).get("STDNO"));
    		  String title = String.valueOf(list.get(i).get("TITLE"));
    		  System.out.printf("　　　　　　　　　　　　　　　　♡ 책번호 : %s ♡ 연체일 : %s일 ♡ 학번 : %s ♡ 학생이름 : %s ♡\n　　　　　　　　　　　　　　　　♡ 책제목 : %s ♡\n", bkno, overdue, stdno, stdname,title);
    		  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
    	  }
      } else {
    	  System.out.println("　　　　　　　　　　　　　　　　♡ 다행히도 연체중인 책이 없습니다 ♡");
      }
      ScanUtil.anyPress();
      return View.M_MAIN;
   }
   
   // 도서신청 현황 조회
   public int selectRequestStatement() {
      List<Map<String, Object>> list = sld.selectRequestList();
      //칼럼 항목
      //REQ_TITLE REQ_WRITER REQ_PUB REQ_PRICE
      
      // 7 개씩 출력하기위한 코드
      int i = 0 ;
      int count = 7;
      
      ScanUtil.putSomeMessage();
      System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      if(list.isEmpty()) {
    	  System.out.println("　　　　　　　　　　　　　　　　♡ 도서 신청 목록이 없습니다 ♡");
    	  ScanUtil.anyPress();
          return View.M_RQSTMT;
      }
      System.out.println("　　　　　　　　　　　　　　　　♡ 도서신청목록 ♡");
      loop : while(i<list.size()) {
//         if(i==(list.size()-1)) {break;}
         for (; i < count; i++) {
            String bookTitle = String.valueOf(list.get(i).get("REQ_TITLE"));
            String wri = String.valueOf(list.get(i).get("REQ_WRITER"));
            String pub = String.valueOf(list.get(i).get("REQ_PUB"));
            String price = String.valueOf(list.get(i).get("REQ_PRICE"));
            System.out.printf("　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n　　　　　　　　　　　　　　　　♡ 저자 : %s ♡ 출판사 : %s ♡\n　　　　　　　　　　　　　　　　♡ 책 가격 : %s원 ♡\n", bookTitle,wri,pub,price);
            System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
            if(i==(list.size()-1)) {
            	System.out.println("　　　　　　　　　　　　　　　　♡ 더이상 검색 결과가 없습니다 ♡");
            	break loop;}
         }
         inputLoop :   while (true) {
            System.out.println("　　　　　　　　　　　　　　　　♡ 1. 다음페이지 ♡ 2. 되돌아가기 ♡");
            System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
    		System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
            String input = ScanUtil.nextLine();
            jdbc.clearConsole();
            switch (input) {
            case "1":
               count += 7;
               jdbc.clearConsole();
               break inputLoop;
            case "2":
            	ScanUtil.anyPress();
               return View.M_RQSTMT;
            default:
            	ScanUtil.errSleep();
               System.err.println("　　　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
               continue inputLoop;
            }// 스위치 문 끝
         }
      }
      ScanUtil.anyPress();
            return View.M_RQSTMT;
   }
   
   // 오늘이 반납일인 미반납 책 조회
   public int selectReturnToday() {
	   ScanUtil.putSomeMessage();
      List<Map<String, Object>> list = sld.selectRetrunPleaseToday();
      // 칼럼 항목
      // BKNO,TITLE,STDNO,NAME
      System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      if(list.isEmpty()) {
    	  System.out.println("　　　　　　　　　　　　　　　　♡ 금일이 반납기한인 책이 없습니다 ♡");
    	  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      }
      System.out.println("　　　　　　　　　　　　　　　　♡ 금일 반납받아야 할 책 목록 ♡");
      System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      for(int i = 0; i<list.size(); i++) {
         String bkno = String.valueOf(list.get(i).get("BKNO"));
         String title = String.valueOf(list.get(i).get("TITLE"));
         String stdno = String.valueOf(list.get(i).get("STDNO"));
         String name = String.valueOf(list.get(i).get("NAME"));
         System.out.printf("　　　　　　　　　　　　　　　　♡ 책번호 : %s ♡ 학번 : %s ♡ 학생이름 : %s ♡\n　　　　　　　　　　　　　　　　♡ 제목 : %s ♡\n", bkno,stdno,name,title);
         System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      }
      ScanUtil.anyPress();
      return View.M_MAIN;
   }
}