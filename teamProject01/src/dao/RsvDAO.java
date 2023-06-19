package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class RsvDAO {
	private static RsvDAO instance = null;

	private RsvDAO() {
	}

	public static RsvDAO getInstance() {
		if (instance == null)
			instance = new RsvDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public Map<String, Object> bkInfo1(String bk_no) {
		String sql = " select BK_TITLE 책제목, BK_WRITER 저자, BK_PUB 출판사 from book where bk_no=? ";
		List<Object> param = new ArrayList<>();
		param.add(bk_no);
		return jdbc.selectOneRow(sql, param);
	}

	public Map<String, Object> bkInfo2(String bk_no) {
		String sql = " select * from book where bk_no=? ";
		List<Object> param = new ArrayList<>();
		param.add(bk_no);
		return jdbc.selectOneRow(sql, param);
	}

	public Map<String, Object> bkInfoFromRsv(String std_no) {
		String sql = " select BK_TITLE 책제목, BK_WRITER 저자, BK_PUB 출판사 from book where bk_rsvsno=? ";
		List<Object> param = new ArrayList<>();
		param.add(std_no);
		return jdbc.selectOneRow(sql, param);
	}

	public Object rsvInfo(String std_no) {
		String sql = " select * from book where bk_rsvsno=? ";
		List<Object> param = new ArrayList<>();
		param.add(std_no);

		return jdbc.selectOneRow(sql, param);
	}

	public Object rsvValueInfo(String std_no, String columnName) {
		String sql = " select * from book where bk_rsvsno=? ";
		Object param = std_no;
		return jdbc.selectOneValue(sql, param, columnName);
	}

	public Object ldsno(Object param, String columnName) {
		String sql = " select * from book where bk_no=? ";

		return jdbc.selectOneValue(sql, param, columnName);
	}

	public int rsvDAO(Object std_no, Object bk_no) {
		String sql = " update book set BK_RSVSNO=? where BK_NO=? ";
		List<Object> param = new ArrayList<>();
		param.add(std_no);
		param.add(bk_no);

		return jdbc.update(sql, param);
	}

	public int cancel(String std_no) {
		String sql = " update book set bk_rsvsno=null where bk_rsvsno=? ";
		List<Object> param = new ArrayList<>();
		param.add(std_no);

		return jdbc.update(sql, param);

	}
}
