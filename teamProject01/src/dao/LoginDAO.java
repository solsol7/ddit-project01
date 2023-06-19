package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class LoginDAO {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	private LoginDAO() {}
	private static LoginDAO instance = null;
	public static LoginDAO getInstance() {
		if(instance == null) instance = new LoginDAO();
		return instance;
	}
	public Map<String, Object> loginDAO(String id, String pw) {
		String sql = "select * from student where std_no=? and std_pw=?";
		List<Object> param = new ArrayList<>();

		param.add(id);
		param.add(pw);

		return jdbc.selectOneRow(sql, param);
	}
}
