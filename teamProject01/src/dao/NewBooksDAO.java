package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.JDBCUtil;

public class NewBooksDAO {
	private static NewBooksDAO instance=null;
	private NewBooksDAO() {}
	
	public static NewBooksDAO getInstance() {
		if(instance==null) instance=new NewBooksDAO();
		return instance;
	}
	
	Scanner sc = new Scanner(System.in);
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public int newBooksDao(String bk_ctcode, String bk_shfcode, String bk_title, String bk_writer, String bk_pub) {
		String sql = " {CALL reg_book( ?, ?, ?, ?, ?)} ";// '분류코드', '책장번호', '책제목, '저자', '출판사'
		List<Object> param = new ArrayList<>();
		param.add(bk_ctcode);
		param.add(bk_shfcode);
		param.add(bk_title);
		param.add(bk_writer);
		param.add(bk_pub);

		return jdbc.procedureUpdate(sql, param);
	}
}
