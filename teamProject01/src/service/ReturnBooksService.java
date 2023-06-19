package service;
/* 2023-06-16 14:13 김석호 출력문 수정 완료 및 추천 메소드 추가*/
import dao.ReturnBooksDAO;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;

public class ReturnBooksService {
   private ReturnBooksService() {}
   private static ReturnBooksService instance = null;
   public static ReturnBooksService getInstance() {
      if(instance == null) instance = new ReturnBooksService();
      return instance;
   }
   ReturnBooksDAO rtBooks = ReturnBooksDAO.getInstance();
   JDBCUtil jdbc = JDBCUtil.getInstance();
   public int returnBookForStudent() {
	   ScanUtil.putSomeMessage();
	  System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
	  if(rtBooks.lendCountInfo()==0) {
		  System.out.println("　　　　　　　　　　　　　　♡ 반납할 책이 없습니다 ♡");
		  ScanUtil.anyPress();
		  return View.S_LRMAIN;
	  }
	  
      System.out.println("　　　　　　　　　　　　　　♡ 반납할 책의 책 번호를 입력해주세요 ♡");
      System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
      System.out.print("　　　　　　　　　　　　　　♡ 책 번호 : ");
      String bkno = ScanUtil.nextLine();
      if(bkno.equals("뒤로가기")) {
    	  ScanUtil.anyPress();
    	  return View.S_LRMAIN;
      }
      /* 책이 유효한 번호&&책 상태가 보유중인지 먼저 확인 */
      if(bkno.equals(String.valueOf(jdbc.isDuplicateBK(bkno, "BK_NO")))) {
         // 유효한 책일 경우 대출자가 본인인지 확인
         if(rtBooks.lendingStudentIsU(String.valueOf(JDBCUtil.loginUser.get("STD_NO")), bkno)) {
            // 반납 처리
            int a = rtBooks.returnBooksLend(String.valueOf(JDBCUtil.loginUser.get("STD_NO")), bkno);
            rtBooks.returnBooksBook(String.valueOf(JDBCUtil.loginUser.get("STD_NO")), bkno);
            if(a>=1) {
               // 연체가 아닌 경우
               if(!rtBooks.isOverdue(String.valueOf(JDBCUtil.loginUser.get("STD_NO")), bkno)) {
                  System.out.println("　　　　　　　　　　　　　　♡ 정상반납 되었습니다 ♡");
               } else {
            	   ScanUtil.errSleep();
                  System.err.println("　　　　　　　　　　　　　　♨ 연체 되었습니다 ♨");
                  // 대출 이력에 연체일자 업데이트
                  rtBooks.returnOverdueCaseLend(String.valueOf(JDBCUtil.loginUser.get("STD_NO")), bkno);
                  // 학생 정보에 정지일자 업데이트
                  rtBooks.returnOverdueCaseStudent(String.valueOf(JDBCUtil.loginUser.get("STD_NO")), bkno);
               }
               /* 추천 물어보는 메소드 */
               recoBooks(bkno);
            }else {
            	ScanUtil.errSleep();
              System.err.println("　　　　　　　　　　　　　　♡ 잇힝 ♡ 엣큥 ♡");
               }
         }else {
        	 ScanUtil.errSleep();
            System.err.println("　　　　　　　　　　　　　　♨ 본인이 대출중인 책이 아닙니다 ♨");
         }
      }else {
    	  ScanUtil.errSleep();
         System.err.println("　　　　　　　　　　　　　　♨ 유효하지않은 책 번호입니다 ♨");
         ScanUtil.anyPress();
         return View.S_LRMAIN;
      }
      ScanUtil.anyPress();
      return View.S_LRMAIN;
   }
   
   public int returnBookForManager() {
      
	   ScanUtil.putSomeMessage();
	   System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
      String stdno ="";
      
      stinput : while(true) {
    	  System.out.println("　　　　　　　　　　　　　　　　♡ 반납하는 학생의 학번을 입력해주세요 ♡");
    	  System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
         System.out.print("　　　　　　　　　　　　　　　　♡ 학번 : ");
         stdno = ScanUtil.nextLine();
         if(stdno.equals("뒤로가기")) {
        	 ScanUtil.anyPress();
        	 return View.M_STDBOOK;
         }
         if(stdno.equals(String.valueOf(jdbc.isDuplicateSTD(stdno, "STD_NO")))) {
//            System.out.println("　　　　　　　　　　　　　　　　♡ 학번 : " + stdno);
            System.out.println("　　　　　　　　　　　　　　　　♡ 이름 : " + rtBooks.studentSelectSTDNAME(stdno, "STD_NAME"));
            
            System.out.println("　　　　　　　　　　　　　　　　♡ 해당 정보가 맞습니까?");
            System.out.println("　　　　　　　　　　　　　　　　♡ 1. 예 ♡ 2. 아니오 ♡ 3. 뒤로가기 ♡");
            System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
            String b = ScanUtil.nextLine();
            switch (b) {
            case "1":
               break stinput;
            case "2":
//            	System.out.println("　　　　　　　　　　　　　　　　♡ 반납하는 학생의 학번을 입력해주세요 ♡");
               continue stinput;
            case "3":
            	ScanUtil.anyPress();
               return View.M_STDBOOK;
            default:
            	ScanUtil.errSleep();
               System.err.println("　　　　　　　　　　　　　　　　♡ 다시 입력해주세요 ♡");
               continue stinput;
            }
         }else {
        	 ScanUtil.errSleep();
            System.err.println("　　　　　　　　　　　　　　　　♡ 학번을 잘못 입력하셨습니다 ♡");
         }
      }
      
      System.out.println("　　　　　　　　　　　　　　　　♡ 반납할 책의 책 번호를 입력해주세요 ♡");
      System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
      System.out.print("　　　　　　　　　　　　　　　　♡ 책 번호 : ");
      String bkno = ScanUtil.nextLine();
      if(bkno.equals("뒤로가기")) {
    	  ScanUtil.anyPress();
    	  return View.M_STDBOOK;
      }
      /* 책이 유효한 번호&&책 상태가 보유중인지 먼저 확인 */
      if(bkno.equals(String.valueOf(jdbc.isDuplicateBK(bkno, "BK_NO")))) {
         // 유효한 책일 경우 대출자가 본인인지 확인
         if(rtBooks.lendingStudentIsU(stdno, bkno)) {
            // 반납 처리
            int a = rtBooks.returnBooksLend(stdno, bkno);
            rtBooks.returnBooksBook(stdno, bkno);
            if(a>=1) {
               // 연체가 아닌 경우
               if(!rtBooks.isOverdue(stdno, bkno)) {
                   System.out.println("　　　　　　　　　　　　　　♡ 정상반납 되었습니다 ♡");
               } else {
            	   ScanUtil.errSleep();
                   System.err.println("　　　　　　　　　　　　　　♨ 연체 되었습니다 ♨");
                  // 대출 이력에 연체일자 업데이트
                  rtBooks.returnOverdueCaseLend(stdno, bkno);
                  // 학생 정보에 정지일자 업데이트
                  rtBooks.returnOverdueCaseStudent(stdno, bkno);
               }
               /* 추천 물어보는 메소드 */
               recoBooks(bkno);
            }else {
            	ScanUtil.errSleep();
                System.err.println("　　　　　　　　　　　　　　♡ 잇힝 ♡ 엣큥 ♡");
            }
         }else {
        	 ScanUtil.errSleep();
             System.err.println("　　　　　　　　　　　　　　♨ 본인이 대출중인 책이 아닙니다 ♨");
         }
      }else {
    	  ScanUtil.errSleep();
          System.err.println("　　　　　　　　　　　　　　♨ 유효하지않은 책 번호입니다 ♨");
      }
      ScanUtil.anyPress();
      return View.M_STDBOOK;
   }
   
   protected void recoBooks(String bkno) {
		System.out.println("　　　　　　　　　　　　　　　　♡ 반납 하신 책을 다른 학생에게도 추천하시겠습니까?");
        System.out.println("　　　　　　　　　　　　　　　　♡ 1. 추천 ♡ 2. 비추천 ♡");
		String reco = ScanUtil.nextLine();
		switch (reco) {
		case "1": rtBooks.plusRcntBooks(bkno);
			break;
		case "2":
			System.out.println("　　　　　　　　　　　　　　　　♡ 추천하지 않고 반납을 종료합니다 ♡");
			return;
		default:
			ScanUtil.errSleep();
			System.err.println("　　　　　　　　　　　　　　　　♡ 잘못 입력되어 추천하지 않고 반납을 종료합니다 ♡");
			return;
		}
   }
   
   
   
   
   
}