package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import util.JDBCUtil;



public class LoanDAO { // DB로부터 데이터를 가져와서 service로 넘겨줌
	
	private static LoanDAO instance = null;
	private LoanDAO() {
		
	}
	public static LoanDAO getInstance() {
		if(instance == null)
			instance = new LoanDAO();
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	Scanner sc = new Scanner(System.in);

	// 반납예정일이 지난 책이 한권이라도 있는지 판단하는 메소드
	public List<Map<String, Object>> judgeOverdueCount(Object id) {

		String sql = " SELECT TRUNC(TO_DATE(LD_EPDATE, 'yy-MM-dd') - TRUNC(SYSDATE)) AS COUNT FROM LEND WHERE STD_NO =? AND LD_RETDATE IS NULL";
		List<Object> param = new ArrayList<>();
		param.add(id);

		return jdbc.selectList(sql, param);
	}

	// 최대권수가 3권 미만인지 판단하는 메소드
	public Object judgeMax(Object id) {
		String sql = "SELECT COUNT(*) FROM BOOK WHERE BK_LDSNO = ?";
		String columnName = "COUNT(*)";
		Object param = id;

		return jdbc.selectOneValue(sql, param, columnName);
	}

	// 책을 입력하면 그 책에 대한 튜플 한 줄 가져옴
	public Map<String, Object> judgeBook(Object bookNo) {

		String sql = "select * from book where BK_NO = ?";
		List<Object> param = new ArrayList<>();
		param.add(bookNo);

		return jdbc.selectOneRow(sql, param);

	}

	// sql을 이용해서 대출테이블, 학생테이블에 정보 업데이트하는 메소드 //////, 삽입 하는 메소드
	public int loanLendTable(String inputBookNo, String id) { // 완료되면 1 반환, 실패하면 2 반환

		LocalDate now = LocalDate.now();
		LocalDate nowFrom7DayLater = now.plusDays(7);

		String sql = "INSERT INTO LEND(BK_NO, STD_NO, LD_NO, LD_DATE, LD_EPDATE, LD_RETDATE, LD_OVERDUE) VALUES(?, ?, LENDNO_SEQ.NEXTVAL, sysdate, '"
				+ nowFrom7DayLater + "' , NULL, NULL)";
		List<Object> param = new ArrayList<>();
		param.add(inputBookNo);
		param.add(id);

		return jdbc.update(sql, param);
	}

	public int loanBookTable(String inputBookNo, String id) {
		String sql = "UPDATE BOOK SET  BK_LDSNO = ?, BK_LDCNT = BK_LDCNT + 1, BK_RSVSNO = NULL  WHERE BK_NO = ?";
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(inputBookNo);

		return jdbc.update(sql, param);
	}
	
	public Object studentSelectSTDNAME(String std_no, String columnName) {
		String sql = " select * from student where std_no=? ";
		Object param = std_no;
		
		return jdbc.selectOneValue(sql, param, columnName);
	}

	////////////////////////////////////////////// [관리자]////////////////////////////////////////////
	// 학생의 학번이 존재하는지, 있다면 정보를 출력하는 메소드
	public Object verifyStudentNo(String studentNo) {

		
		Object param = studentNo;
		String columnName = "STD_NO";

		return jdbc.isDuplicateSTD(param, columnName);
	}

	public Object getStdate(Object id) {

		
		Object param = id;
		String columnName = "STD_STDATE";

		return jdbc.isDuplicateSTD(param, columnName);
	}

	public Object verifyBookNo(String inputBookNo) {

		Object param = inputBookNo;
		String columnName = "BK_NO";

		return jdbc.isDuplicateBK(param, columnName);
	}
}

class LoanService {
	
	
}
