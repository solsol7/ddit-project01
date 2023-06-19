package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.DAOConnection;
import util.JDBCUtil;

public class ReturnBooksDAO {
   Scanner sc = new Scanner(System.in);
   private ReturnBooksDAO(){}
   private static ReturnBooksDAO instance = null;
   public static ReturnBooksDAO getInstance() {
      if(instance==null) instance = new ReturnBooksDAO();
      return instance;
   }
   JDBCUtil jdbc = JDBCUtil.getInstance();
   DAOConnection daoConn = DAOConnection.getInstance();
   
   // 입력받은 책번호를 입력받은 학생정보로 빌리고 있는지 확인하는 메소드
   public boolean lendingStudentIsU(String stdno, String bkno) {
      String sql = "SELECT * FROM BOOK WHERE BK_NO = ?";
      if(stdno.equals(String.valueOf(jdbc.selectOneValue(sql, bkno, "BK_LDSNO")))) {
         return true;
      }else {
         return false;
      }
   }
   // 로그인한 학생이 빌린 책의 수를 반환해주는 메숴드 
	public int lendCountInfo() {
		String sql = "SELECT COUNT(*) A FROM BOOK WHERE BK_LDSNO = ?";
		return Integer.parseInt(String.valueOf(jdbc.selectOneValue(sql, JDBCUtil.loginUser.get("STD_NO"), "A")));
	}
   
   // 책 반납시 LEND 테이블에서 대출이력에 반납일을 삽입하는 메소드
   public int returnBooksLend(String stdno, String bkno) {
      String sql = "UPDATE LEND SET LD_RETDATE = SYSDATE WHERE LD_RETDATE IS NULL AND STD_NO = ? AND BK_NO = ?";
      List<Object> param = new ArrayList<Object>();
      param.add(stdno);
      param.add(bkno);
      return jdbc.update(sql, param);
      
   }
   
   // 책 반납시 BOOK 테이블에서 대출자를 null로 만들어주는 메소드
   public int returnBooksBook(String stdno, String bkno) {
      String sql = "UPDATE BOOK SET BK_LDSNO = NULL WHERE BK_LDSNO = ?  AND BK_NO = ?";
      List<Object> param = new ArrayList<Object>();
      param.add(stdno);
      param.add(bkno);
      return jdbc.update(sql, param);
   }
   
   
   // 반납한 책이 연체인지 확인하는 메소드
   public boolean isOverdue(String stdno, String bkno) {
      String sql = "SELECT TRUNC(LD_RETDATE - LD_EPDATE) A FROM LEND WHERE STD_NO = ? AND BK_NO = ? AND TO_CHAR(LD_RETDATE,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')";
      List<Object> param = new ArrayList<Object>();
      param.add(stdno);
      param.add(bkno);
      
      //연체인 경우 true
      if(0 < Integer.parseInt(String.valueOf((jdbc.selectOneRow(sql, param)).get("A")))) {
         return true;
      }else { // 연체가 아닌경우 false
         return false;
      }
   }
   
   
   // 연체인 경우 대출이력에 연체일을 업데이트하는 메소드
   public int returnOverdueCaseLend(String stdno, String bkno) {
      List<Object> param = new ArrayList<Object>();
      param.add(stdno);
      param.add(bkno);
      String sqlGetOverdue = "SELECT TRUNC(LD_RETDATE - LD_EPDATE) A FROM LEND WHERE STD_NO = ? AND BK_NO = ? AND TO_CHAR(LD_RETDATE,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')";
      int overdue = Integer.parseInt(String.valueOf(jdbc.selectOneValue(sqlGetOverdue, param, "A")));
      String sql = "UPDATE LEND SET LD_OVERDUE = " + overdue + " WHERE STD_NO = ? AND BK_NO = ? AND TO_CHAR(LD_RETDATE,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')";
      return jdbc.update(sql, param);
   }
   
   // 연체인 경우 연체한 학생의 정지일을 갱신하는 메소드
   public int returnOverdueCaseStudent(String stdno, String bkno) {
      List<Object> param = new ArrayList<Object>();
      param.add(stdno);
      param.add(bkno);
      List<Object> paramS = new ArrayList<Object>();
      paramS.add(stdno);
      
      // 학생이 정지일이 기존에 있을경우 날짜형태의 문자열, 없을경우 0의 문자열로 가져오는 쿼리문
      String sqlGetStopdate = "SELECT NVL2(STD_STDATE,TO_CHAR(STD_STDATE,'YYYY-MM-DD'),'0') LO FROM STUDENT WHERE STD_NO = ?";
      String stopDate = String.valueOf(jdbc.selectOneValue(sqlGetStopdate, stdno, "LO"));
      
      // 연체일을 가져오는 메숴드
      String sqlGetOverdue = "SELECT TRUNC(LD_RETDATE - LD_EPDATE) B FROM LEND WHERE STD_NO = ? AND BK_NO = ? AND TO_CHAR(LD_RETDATE,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')";
      int overdue = Integer.parseInt(String.valueOf((jdbc.selectOneRow(sqlGetOverdue, param)).get("B")));
      
      // 학생 정지일이 없었을 경우
      if(stopDate.equals("0")) {
         String sql = "UPDATE STUDENT SET STD_STDATE = SYSDATE + " + overdue + " WHERE STD_NO = ?";
         return jdbc.update(sql, paramS);
      }else {
//    	  String sqlGetStopdate2 = "SELECT TO_CHAR((STD_STDATE+"+overdue+"),'YYYY-MM-DD') DEAD FROM STUDENT WHERE STD_NO = ?";
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//         LocalDateTime stdate = LocalDateTime.parse(stopDate, formatter);
//         stdate = stdate.plusDays(overdue);
//         String newStopDate = stdate.format(formatter);
         String sql = "UPDATE STUDENT SET STD_STDATE = (SELECT STD_STDATE FROM STUDENT WHERE STD_NO= '"+JDBCUtil.loginUser.get("STD_NO")+"')+" + overdue + "WHERE STD_NO = '"+JDBCUtil.loginUser.get("STD_NO")+"'";
//         String sql = "UPDATE STUDENT SET STD_STDATE = TO_DATE('" + stopDate + "'+"+overdue+", 'YYYY-MM-DD') WHERE STD_NO = ?";
         return jdbc.update(sql);
      }
   }

   public String studentSelectSTDNAME(String stdno, String colmName) {
      String sql = "SELECT * FROM STUDENT WHERE STD_NO = ?";
      return String.valueOf(jdbc.selectOneValue(sql,stdno,colmName));
   }
   
   public int plusRcntBooks(String bkno) {
	   String sql = "UPDATE BOOK SET BK_RCNT = (SELECT BK_RCNT FROM BOOK WHERE BK_NO = '" + bkno + "')+1 WHERE BK_NO = '" + bkno + "'";
	   return jdbc.update(sql);
   }
}