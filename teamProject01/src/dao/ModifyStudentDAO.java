package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.JDBCUtil;

public class ModifyStudentDAO {
	private static ModifyStudentDAO instance=null;
	private ModifyStudentDAO() {}
	
	public static ModifyStudentDAO getInstance() {
		if(instance==null) instance=new ModifyStudentDAO();
		return instance;
	}
	
	JDBCUtil jdbc=JDBCUtil.getInstance();

	public int mdfSuspended(String std_no) {
		String sql = " UPDATE STUDENT SET STD_STDATE=null WHERE STD_NO=? ";
		List<Object> param = new ArrayList<>();
		
		param.add(std_no);
		
		return jdbc.update(sql, param);
	}
	
	public int mdfPw(String std_no, String std_pw) {
		
		
		
		String sql = " UPDATE STUDENT SET STD_PW=? WHERE STD_NO=? ";
		List<Object> param = new ArrayList<>();
		
		param.add(std_pw);
		param.add(std_no);
		
		return jdbc.update(sql, param);
	}

	public Map<String, Object> getSpdInfo(String std_no) {
		String sql = " select std_name 이름, to_char(std_stdate, 'yyyy-mm-dd' ) 정지일 from student where std_no=? ";
		List<Object> param = new ArrayList<>();
		param.add(std_no);
		return jdbc.selectOneRow(sql, param);
	}
	
	public Map<String, Object> getPwInfo(String std_no) {
		String sql = " select std_name 이름, std_pw 비밀번호 from student where std_no=? ";
		List<Object> param = new ArrayList<>();
		param.add(std_no);
		return jdbc.selectOneRow(sql, param);
	}
}
