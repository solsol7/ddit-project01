package dao;

import java.util.List;
import java.util.Map;
import util.JDBCUtil;

public class SelectDAO {
	private SelectDAO() {}
	private static SelectDAO instance = null;
	public static SelectDAO getInstance() {
		if(instance == null) instance = new SelectDAO();
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<Map<String, Object>> selectBook(String title){
		String sql = "SELECT B2.BK_NO, B2.BK_SHFCODE, B2.BK_TITLE, B2.BK_WRITER, B2.BK_PUB, NVL2(B2.BK_LDSNO,'대출중','대출가능') A, NVL2(B2.BK_RSVSNO,'예약중','예약없음') B " + 
                "FROM (SELECT REPLACE(BK_TITLE, ' ','') TITLE, BK_NO FROM BOOK) B1, BOOK B2 " + 
                "WHERE B1.BK_NO=B2.BK_NO AND B1.TITLE LIKE REPLACE( ? , ' ', '') ORDER BY B2.BK_TITLE ASC ";
		return jdbc.selectList(sql, title);
	}
	
	public List<Map<String, Object>> selectLendInfo(){
		String sql = "SELECT * FROM BOOK WHERE BK_LDSNO = ? ";
		return jdbc.selectList(sql, String.valueOf(JDBCUtil.loginUser.get("STD_NO")));
	}
	
	public int lendCountInfo() {
		String sql = "SELECT COUNT(*) A FROM BOOK WHERE BK_LDSNO = ?";
		return Integer.parseInt(String.valueOf(jdbc.selectOneValue(sql, JDBCUtil.loginUser.get("STD_NO"), "A")));
	}
	public Map<String, Object> LendReturndateInfo(String bkno) {
		String sql = "SELECT TO_CHAR(LD_EPDATE,'YYYY-MM-DD') LD_EPDATE FROM LEND WHERE STD_NO = ? AND BK_NO = '"+bkno+"' AND LD_RETDATE IS NULL";
		return jdbc.selectOneRow(sql, String.valueOf(JDBCUtil.loginUser.get("STD_NO")));
	}
	public Map<String, Object> LendReturndateInfo(String stdno, String bkno) {
		String sql = "SELECT * FROM LEND WHERE STD_NO = '"+stdno+"' AND BK_NO = '"+bkno+"' AND LD_RETDATE IS NULL";
		return jdbc.selectOneRow(sql, String.valueOf(JDBCUtil.loginUser.get("STD_NO")));
	}
	public int overdueState() {
		String sql = "SELECT COUNT(*) A FROM LEND WHERE STD_NO = ? AND LD_EPDATE < SYSDATE AND LD_RETDATE IS NULL";
		return Integer.parseInt(String.valueOf(jdbc.selectOneValue(sql, JDBCUtil.loginUser.get("STD_NO"), "A")));
	}
	public String stopdStateInfo() {
		String sql = "SELECT NVL2(STD_STDATE,TO_CHAR(STD_STDATE,'YYYY-MM-DD'),'0') A FROM STUDENT WHERE STD_NO = ?";
		return String.valueOf(jdbc.selectOneValue(sql, JDBCUtil.loginUser.get("STD_NO"), "A"));
	}
	
	public Map<String, Object> reserveInfo() {
		String sql = "SELECT BK_NO, NVL2(BK_LDSNO, '미반납', '대출가능') A, BK_TITLE FROM BOOK WHERE BK_RSVSNO = ?";
		return jdbc.selectOneRow(sql, String.valueOf(JDBCUtil.loginUser.get("STD_NO")));
	}
	
	public List<Map<String, Object>> selectOverList(){
		//칼럼 항목
		// BKNO-책번호 | OVERDUE-연체일 | NAME 학생이름  | STDNO-학번 | TITLE 책제목
		String sql = "SELECT L.BK_NO BKNO, TRUNC(SYSDATE - L.LD_EPDATE) OVERDUE, S.STD_NAME NAME, L.STD_NO STDNO, B.BK_TITLE TITLE FROM LEND L, STUDENT S, BOOK B WHERE L.STD_NO = S.STD_NO  AND L.BK_NO = B.BK_NO AND L.LD_RETDATE IS NULL AND L.LD_EPDATE < SYSDATE";
		return jdbc.selectList(sql);
	}
	public List<Map<String, Object>> selectRequestList(){
		String sql = "SELECT * FROM REQUEST";
		return jdbc.selectList(sql);
	}
	public List<Map<String, Object>> selectRetrunPleaseToday(){
		String sql = "SELECT L.BK_NO BKNO, B.BK_TITLE TITLE, L.STD_NO STDNO, S.STD_NAME NAME FROM LEND L, BOOK B, STUDENT S WHERE L.BK_NO = B.BK_NO AND L.STD_NO = S.STD_NO AND TO_CHAR(L.LD_EPDATE,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD') AND L.LD_RETDATE IS NULL";
		return jdbc.selectList(sql);
	}
	/*
	 * 필요한 조회 메소드
	 * 
	 * 대출현황 조회
	 * 마이페이지
	 * 연체현황조회
	 * 도서신청 현황조회
	 * 반납기한이 금일인 책 조회
	 */
}
