package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class RequestDAO {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	private RequestDAO() {}
	private static RequestDAO instance = null;
	public static RequestDAO getInstance() {
		if(instance == null) instance = new RequestDAO();
		return instance;
	}
	public int requestDAO(String req_title, String req_writer, String req_pub, int req_price) {
		String sql = " INSERT INTO REQUEST(REQ_TITLE,REQ_WRITER,REQ_PUB,REQ_PRICE) " + "VALUES( ?, ?, ?, ?) ";
		List<Object> param = new ArrayList<>();
		param.add(req_title);
		param.add(req_writer);
		param.add(req_pub);
		param.add(req_price);

		return jdbc.update(sql, param);
	}
	
	public int countRequestListDAO() {
		String sql = "SELECT COUNT(*) A FROM REQUEST";
		List<Map<String, Object>> list = jdbc.selectList(sql);
		return  Integer.parseInt(String.valueOf(list.get(0).get("A")));
	}
	
	public int clearRequestListDAO() {
		String sql = "DELETE FROM REQUEST WHERE 1=1";
		return jdbc.update(sql);
	}
}
