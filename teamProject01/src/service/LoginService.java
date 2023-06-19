package service;
/* 표시형식 수정 완료 2023-06-16 김석호 */
import java.util.Map;

import dao.LoginDAO;
import util.JDBCUtil;
import util.View;
import util.ScanUtil;

public class LoginService {
	private static LoginService instance=null;
	private LoginService(){}
	
	public static LoginService getInstance() {
		if(instance==null) instance=new LoginService();
		return instance;
	}
	
	LoginDAO loginDao=LoginDAO.getInstance();
	JDBCUtil jdbc=JDBCUtil.getInstance();
	
	public int loginService() {
		
		while (true) {
			System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다.");
			System.out.print("　　　　　　　　　　　　　　♡ 아이디  : ");
			String id=ScanUtil.nextLine();
			if(id.equals("뒤로가기")) {
				ScanUtil.anyPress();
				return View.MAIN;
			}
				System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다.");
			System.out.print("　　　　　　　　　　　　　　♡ 비밀번호 : ");
			String pw=ScanUtil.nextLine();
			if(pw.equals("뒤로가기")) {
				ScanUtil.anyPress();
				return View.MAIN;
			}
			
			Map<String, Object> result = loginDao.loginDAO(id, pw);
			JDBCUtil.loginUser=result;
			if (result != null) {
				if(JDBCUtil.loginUser.get("STD_CLSCODE").equals("1")) {
					System.out.println("　　　　　　　　　　　　　　♡"+result.get("STD_NAME") + "님♡ 반갑습니다.");
					ScanUtil.anyPress();
					return View.S_MAIN;
				}else {
					System.out.println("　　　　　　　　　　　　　　♡ 사서 ♡"+result.get("STD_NAME") + "님♡ 반갑습니다.");
					System.out.println("　　　　　　　　　　　　　　♡ 관리자모드로 진입합니다 ♡");
					ScanUtil.anyPress();
					return View.M_MAIN;
				}
			}else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♡ 학번 또는 비밀번호가 잘못되었습니다 ♡");
				ScanUtil.putErrorMessage();
				return View.MAIN;
			}
		}
	}
}
