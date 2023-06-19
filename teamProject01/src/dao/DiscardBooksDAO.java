package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class DiscardBooksDAO {
	private static DiscardBooksDAO instance = null;

	private DiscardBooksDAO() {
	}

	public static DiscardBooksDAO getInstance() {
		if (instance == null)
			instance = new DiscardBooksDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public int discardBooksDAO(String bk_no) {
		String sql = " UPDATE BOOK SET BK_STM='2', BK_LDSNO='-', BK_RSVSNO='-' WHERE BK_NO=? ";
		List<Object> param = new ArrayList<>();
		param.add(bk_no);

		int result = jdbc.update(sql, param);

		return result;
	}

	public Map<String, Object> bkInfo(String bk_no) {
		String sql = " select BK_NO 책번호, BK_TITLE 제목, BK_WRITER 저자, BK_PUB 출판사 from book where bk_no=? ";

		List<Object> param = new ArrayList<>();
		param.add(bk_no);

		return jdbc.selectOneRow(sql, param);
	}

	public int setdcbDate(String bk_no) {
		String sql = " update lend set ld_retdate='1111-11-11' where bk_no=? and ld_retdate is null ";
		List<Object> param = new ArrayList<>();
		param.add(bk_no);

		int result = jdbc.update(sql, param);
		return result;
	}

	public Map<String, Object> retInfo(String bk_no) {
		String sql = " select * from lend where bk_no=? and ld_retdate is null ";
		List<Object> param = new ArrayList<>();
		param.add(bk_no);

		Map<String, Object> result = jdbc.selectOneRow(sql, param);
		return result;
	}
}
