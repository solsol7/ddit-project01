package service;
/* 표시형식 수정 완료 2023-06-16 12:40 김석호 */
import util.ScanUtil;
import util.JDBCUtil;
import util.View;

public class LogoutService {
	private static LogoutService instance=null;
	private LogoutService() {}
	
	public static LogoutService getInstance() {
		if(instance==null) instance=new LogoutService();
		return instance;
	}
	
	JDBCUtil jdbc=JDBCUtil.getInstance();

	
	public int logout() {
		System.out.println("　　　　　　　　　　　　　　♡ "+JDBCUtil.loginUser.get("STD_NAME")+"님♡ 로그아웃하시겠습니까?");
		System.out.println("　　　　　　　　　　　　　　　　♡ 1.예 ♡ 2.아니오 ♡");
        System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
		String a=ScanUtil.nextLine();
		if(a.equals("1")) {
			JDBCUtil.loginUser=null;
		}else if(a.equals("2")){
			if(Integer.parseInt(String.valueOf(JDBCUtil.loginUser.get("STD_CLSCODE")))==1) {
				ScanUtil.anyPress();
				return View.S_MAIN;
			}else {
				ScanUtil.anyPress();
				return View.M_MAIN;
			}
		}else {
			ScanUtil.errSleep();
        	System.err.println("　　　　　　　　　　　　　　　　♡ 잘못된 입력입니다 ♡");
			if(Integer.parseInt(String.valueOf(JDBCUtil.loginUser.get("STD_CLSCODE")))==1) {
				ScanUtil.anyPress();
				return View.S_MAIN;
			}else {
				ScanUtil.anyPress();
				return View.M_MAIN;
			}
		}
		ScanUtil.anyPress();
		return View.MAIN;
	}
}
