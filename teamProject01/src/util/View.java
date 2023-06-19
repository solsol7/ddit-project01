package util;

public interface View {
      //시스템 - 1.로그인 2.시스템종료
      int MAIN = 1;
      
      //학생모드
      int S_MAIN = 31; // 학생모드 메인         O
      int S_RECOBOOK = 32; // 추천 도서 조회 메인   O
      int S_MYPAGE = 33; // 마이페이지 메인         Ox
      int S_REQBOOK = 34;   //도서신청            O
      int S_SRHBOOK = 35;   //도서 검색         O
      int S_LENDSTMT = 36;   //대출현황조회         O
      int S_LRMAIN = 37;   //대출 및 반납 메인      O
      int S_RSVBOOK = 38;	//예약
      int S_LOAN = 39;		//학생대출
      
      //관리자모드
      int M_MAIN = 71;    //관리자모드 메인      O
      int M_MODI = 72;    //학생정보 수정 메인      O
      int M_STDBOOK = 73;    //대출반납 대리업무 메인   O
      int M_LOANM = 74;   //대출대행            
      int M_RETURNM = 75;   //반납대행            O
      int M_MANBOOK = 76;   //도서관리 메인         O
      int M_NEWBOOK = 77;   //도서등록 메인         O
      int M_DSCBOOK = 78;    //도서폐기 메인         O
      int M_SOSTMT = 79;   //연체현황조회         O
      int M_RQSTMT=80;      //도서 신청 현황 조회 및 튜플 삭제
      
      
   }