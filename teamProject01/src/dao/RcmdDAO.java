package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.JDBCUtil;

public class RcmdDAO {

	private static RcmdDAO instance = null;

	private RcmdDAO() {
	}

	public static RcmdDAO getInstance() {
		if (instance == null)
			instance = new RcmdDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<Map<String, Object>> suggestion() {
		String sql = " select BK_SHFCODE,BK_NO,BK_SHFCODE,NVL2(BK_LDSNO,'대출중   ','대출가능') 대출,NVL2(BK_RSVSNO,'예약중   ','예약없음') 예약,BK_TITLE,BK_WRITER,BK_PUB"
				+ " from (select * from book order by bk_rcnt desc) " + " where rownum<='100' and bk_stm='1' ";
		List<Object> param = new ArrayList<>();

		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> popularity() {
		String sql = " select BK_SHFCODE,BK_NO,BK_SHFCODE,NVL2(BK_LDSNO,'대출중   ','대출가능') 대출,NVL2(BK_RSVSNO,'예약중   ','예약없음') 예약,BK_TITLE,BK_WRITER,BK_PUB"
				+ " from (select * from book order by bk_ldcnt desc) " + "    where rownum<='10' and bk_stm='1' ";
		List<Object> param = new ArrayList<>();

		return jdbc.selectList(sql, param);
	}

}
