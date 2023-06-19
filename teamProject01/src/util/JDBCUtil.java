package util;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

	DAOConnection daoConn = DAOConnection.getInstance();
	private static JDBCUtil instance = null;

	private JDBCUtil() {
	}

	public static JDBCUtil getInstance() {
		if (instance == null)
			instance = new JDBCUtil();
		return instance;
	}
	
	/*
	 * daoConn을 만든 순간 protected static Connection conn = null; protected Statement
	 * stmt = null; protected PreparedStatement pstmt = null; protected ResultSet rs
	 * = null; 에 접근 가능..? daoConn. 으로 접근해서 사용
	 */
	public static Map<String, Object> loginUser = null;

	public void connectConn() {
		daoConn.connectConn();

	}

	public void disconnectConn() {
		daoConn.disconectConn();
	}

	public void clearConsole() { // 콘솔창에 공백 80줄 입력해서 비워주는 메소드
		for (int i = 0; i < 80; i++)
			System.out.println();
		
	}

	public List<Map<String, Object>> selectList(String sql, List<Object> param) {
		// ?에 적용할 값을  List로 받아서 모든 튜플을 맵을 요소로하는 List로 반환해주는 메소드
		Map<String, Object> oneRow = null;
		List<Map<String, Object>> info = new ArrayList<>();
		try {
			daoConn.pstmt = daoConn.conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				daoConn.pstmt.setObject(i + 1, param.get(i));
			}

			daoConn.rs = daoConn.pstmt.executeQuery();

			ResultSetMetaData rsmd = daoConn.rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			while (daoConn.rs.next()) {
				oneRow = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					oneRow.put(rsmd.getColumnName(i + 1), daoConn.rs.getObject(i + 1));
				}
				info.add(oneRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	public List<Map<String, Object>> selectList(String sql, Object param) {
		// ?에 적용할 값을  Object로 하나를 받아서 모든 튜플을 맵을 요소로하는 List로 반환해주는 메소드
		Map<String, Object> oneRow = null;
		List<Map<String, Object>> info = new ArrayList<>();
		try {
			daoConn.pstmt = daoConn.conn.prepareStatement(sql);
			daoConn.pstmt.setObject(1, param);
			daoConn.rs = daoConn.pstmt.executeQuery();
			
			ResultSetMetaData rsmd = daoConn.rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while (daoConn.rs.next()) {
				oneRow = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					oneRow.put(rsmd.getColumnName(i + 1), daoConn.rs.getObject(i + 1));
				}
				info.add(oneRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	public List<Map<String, Object>> selectList(String sql) {
		// sql문장만 받아서 조회되는 모든 튜플을 Map을 요소로하는 List로 반환해주는 메소드
		Map<String, Object> oneRow = null;
		List<Map<String, Object>> info = new ArrayList<>();
		try {
			daoConn.stmt = daoConn.conn.createStatement();
			daoConn.rs = daoConn.stmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = daoConn.rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while (daoConn.rs.next()) {
				oneRow = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					oneRow.put(rsmd.getColumnName(i + 1), daoConn.rs.getObject(i + 1));
				}
				info.add(oneRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	public Map<String, Object> selectOneRow(String sql, List<Object> param) {
		// 튜플 하나를 선택해서 모든 컬럼을 가져오는 메소드
		Map<String, Object> info = null;
		try {
			daoConn.pstmt = daoConn.conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				daoConn.pstmt.setObject(i + 1, param.get(i));
			}

			daoConn.rs = daoConn.pstmt.executeQuery();

			ResultSetMetaData rsmd = daoConn.rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			while (daoConn.rs.next()) {
				info = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					info.put(rsmd.getColumnName(i + 1), daoConn.rs.getObject(i + 1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	public Map<String, Object> selectOneRow(String sql, Object param) {
		// 튜플 하나를 선택해서 모든 컬럼을 가져오는 메소드
		Map<String, Object> info = null;
		try {
			daoConn.pstmt = daoConn.conn.prepareStatement(sql);
			daoConn.pstmt.setObject(1, param);
			daoConn.rs = daoConn.pstmt.executeQuery();
			
			ResultSetMetaData rsmd = daoConn.rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while (daoConn.rs.next()) {
				info = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					info.put(rsmd.getColumnName(i + 1), daoConn.rs.getObject(i + 1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	public Object selectOneValue(String sql, Object param, String columnName) {
		// 튜플의 한 컬럼 값만 가져오는 메소드, 컬럼에 값이 있는지 확인할 때 사용(isDuplicate와 함께 사용)
		// where절 조건 하나만 달 수 있음(pk 가져와서 사용, 조건 더 달고싶으면 param-List로 선언해서 다시만들어야함)
		Object result = null;
		try {
			daoConn.pstmt = daoConn.conn.prepareStatement(sql);
			daoConn.pstmt.setObject(1, param);
			daoConn.rs = daoConn.pstmt.executeQuery();

			while (daoConn.rs.next()) {
				result = daoConn.rs.getObject(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Object selectOneValue(String sql, List<Object> param, String columnName) {
		// 튜플의 한 컬럼 값만 가져오는 메소드, 컬럼에 값이 있는지 확인할 때 사용(isDuplicate와 함께 사용)
		// where절 조건 하나만 달 수 있음(pk 가져와서 사용, 조건 더 달고싶으면 param-List로 선언해서 다시만들어야함)
		Object result = null;
		try {
			daoConn.pstmt = daoConn.conn.prepareStatement(sql);
			for (int i = 0; i < param.size(); i++) {
				daoConn.pstmt.setObject(i + 1, param.get(i));
			}
			
			daoConn.rs = daoConn.pstmt.executeQuery();
			while (daoConn.rs.next()) {
				result = daoConn.rs.getObject(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int update(String sql, List<Object> param) {
		// sql=" insert into emp(empno, ename, job, sal, deptno)
		// values (?, ?, ?, ?, ?) "
		// 리스트 param 안에 있는 갯수만큼 ?갯수 설정가능
		int ret = 0;
		try {

			daoConn.pstmt = daoConn.conn.prepareStatement(sql);
			for (int i = 0; i < param.size(); i++) {
				daoConn.pstmt.setObject(i + 1, param.get(i));
			}
			ret = daoConn.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	// SQL문만 받아서 바로 업데이트쿼리를 실행하는 메소드
	public int update(String sql) {
		int ret = 0;
		try {
			daoConn.stmt = daoConn.conn.createStatement();
			ret = daoConn.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public int procedureUpdate(String sql, List<Object> param) {
		// sql=" insert into emp(empno, ename, job, sal, deptno)
		// values (?, ?, ?, ?, ?) "
		// 리스트 param 안에 있는 갯수만큼 ?갯수 설정가능
		int ret = 0;
		try {

			daoConn.cstmt = daoConn.conn.prepareCall(sql);
			for (int i = 0; i < param.size(); i++) {
				daoConn.cstmt.setString(i + 1, (String) param.get(i));
			}
			ret = daoConn.cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public Object isDuplicateSTD(Object param, String columnName) {
		// STDENT테이블의 매개변수의 값과 같은 값이 매개변수의 컬럼에 있는지 확인하는 메소드
		// DAO 패키지임-> 쿼리 수정해서 사용(테이블, where절 컬럼명 수정)
		// 상속받아 쓰는것보다 만드는 클래스 안에 메소드 가져가서 쓰는거 추천!
		String sql = "select * from STUDENT where STD_NO=? ";

		return selectOneValue(sql, param, columnName);

	}

	public Object isDuplicateBK(Object param, String columnName) {
		// STDENT테이블의 매개변수의 값과 같은 값이 매개변수의 컬럼에 있는지 확인하는 메소드
		// DAO 패키지임-> 쿼리 수정해서 사용(테이블, where절 컬럼명 수정)
		// 상속받아 쓰는것보다 만드는 클래스 안에 메소드 가져가서 쓰는거 추천!
		String sql = "select * from BOOK where BK_NO=? ";

		return selectOneValue(sql, param, columnName);

	}
}
