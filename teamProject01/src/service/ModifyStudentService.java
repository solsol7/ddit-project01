package service;
/* 2023-06-16 16:38 김석호 출력형식 변경 완료*/

import dao.ModifyStudentDAO;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;

public class ModifyStudentService {
	private static ModifyStudentService instance = null;

	private ModifyStudentService() {
	}

	public static ModifyStudentService getInstance() {
		if (instance == null)
			instance = new ModifyStudentService();
		return instance;
	}

	ModifyStudentDAO mdfDAO = ModifyStudentDAO.getInstance();
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public int mdfServiceM() {
		boolean bl01 = true;
		lolo :while (bl01) {
			boolean bl02 = true;
			ScanUtil.putSomeMessage();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　　　학생 정보 수정");
			System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			System.out.println();
			System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　             1. 비밀번호 변경                                                          2. 정지일 삭제");
			System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡　                                     　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println("　　　　　　　　　　　　　　             3. 뒤로가기");
			System.out.println("　　　　　　　　　　　　　　          ♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println();
			System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
			String a = ScanUtil.nextLine();
			/* 김석호 수정사항 */
			switch (a) {
			case "1":
				ScanUtil.putSomeMessage();
				System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
				System.out.println("　　　　　　　　　　　　　　　　비밀번호 변경 : 비밀번호는 4~30자리 알파벳소문자와 숫자로 이루어진 문자열입니다");
				System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
				while (bl02) {
					System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
					System.out.print("　　　　　　　　　　　　　　♡ 변경할 학생의 학번 입력 : ");
					/* 0615 10:23 김석호 수정
					 * sc.close 추가. 원본 주석처리
					 */
//					sc.next();
//					String std_no1 = sc.nextLine();
					
					String std_no = ScanUtil.nextLine();
					if(std_no.equals("뒤로가기")) {
						bl02 = false;
						break;
					}
					
					if (std_no.equals((jdbc.isDuplicateSTD(std_no, "STD_NO")))) {
						System.out.println("　　　　　　　　　　　　　　　　♡ "+mdfDAO.getPwInfo(std_no));
						System.out.println("　　　　　　　　　　　　　　♡ 변경할 학생 정보가 맞습니까?");
						System.out.println("　　　　　　　　　　　　　　　　♡ 1. 예 ♡ 2. 다시입력 ♡ 3. 나가기");
						System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
						String b = ScanUtil.nextLine();
						switch (b) {
						case "1":
							String newPw = "";
							lololo : while(true) {
								System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
								System.out.print("　　　　　　　　　　　　　　♡ 변경할 비밀번호 입력 : ");/*숫자랑 알파벳만 입력하도록*/
								newPw = ScanUtil.nextLine();
								if(newPw.equals("뒤로가기")) {
									bl02 = false;
									continue lolo;
								}
								if(newPw.length()<4 || newPw.length()>30) {
									ScanUtil.errSleep();
									System.err.println("　　　　　　　　　　　　　　♨ 입력한 비밀번호 자릿수가 허용범위 밖입니다 ♨");
									continue lololo;
								}
								char[] newPwChar = newPw.toCharArray();
								/*숫자코드 48~57 알파벳 소문자 코드 97~122*/
								for (int i = 0; i < newPwChar.length; i++) {
									boolean isDigit = true;
									boolean isLowCaseAlpha = true;
									/* 숫자인지 판단 */
									if(newPwChar[i]>= 48 && newPwChar[i] <=57) {
									}else {
										isDigit = false; // 배열인덱스 i 값이 숫자에 해당 안되면 false
									}
									/* 알파벳 소문자인지 판단 */
									if(newPwChar[i]>= 97 && newPwChar[i] <=122) {
									}else {
										// 배열인덱스 i 값이 소문자알파벳에 해당 안되면 false
										isLowCaseAlpha = false;
									}
									
									if(!isLowCaseAlpha&&!isDigit) {
										// 원래 값이 둘다 false이면 이쪽으로 들어옴
										// 입력한 문자열에 소문자 알파벳이나 숫자에 해당안되면 이쪽으로 들어옴
										ScanUtil.errSleep();
										System.err.println("　　　　　　　　　　　　　　♨ 비밀번호는 알파벳 소문자나 숫자로만 구성해주세요 ♨");
										continue lololo;
									}
								}// 알파벳소문자와 숫자를 판단하는 부분이 끝나는 곳
								break lololo;
							}
								if (mdfDAO.mdfPw(std_no, newPw) == 1) {
									System.out.println();
									System.out.println("　　　　　　　　　　　　　　　　♡ 정보 수정이 완료되었습니다 ♡");
									ScanUtil.anyPress();
//								bl01 = false;
									bl02 = false;
									break;
								} else {
									ScanUtil.errSleep();
									System.err.println("　　　　　　　　　　　　　　♨ 정보 수정에 실패하였습니다 ♨");
									ScanUtil.anyPress();
//								bl01 = false;
									bl02 = false;
									continue lolo;
								}
								
						case "2":
							System.out.println("　　　　　　　　　　　　　　♡ 학생 정보를 다시 입력하세요 ♡");
							break;
						case "3":/*뒤로가기 선택지*/
							bl02 = false;
							ScanUtil.anyPress();
							break;
						default:
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 학생 정보를 다시 입력하세요 ♨");
							ScanUtil.anyPress();
							break;
						}
					} else {
						ScanUtil.errSleep();
						System.err.println("　　　　　　　　　　　　　　♨ 존재하지 않는 학번입니다 ♨");
					}
				}
				break;
			case "2":
				ScanUtil.putSomeMessage();
				System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
				System.out.println("　　　　　　　　　　　　　　　　정지일 삭제");
				System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
				while (bl02) {
					System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
					/**/
					System.out.print("　　　　　　　　　　　　　　♡ 정지일을 삭제할 학생의 학번 입력 : ");
					/* 김석호 수정사항 */
//					sc.next();
//					String std_no1 = sc.nextLine();
					String std_no = ScanUtil.nextLine();
					if(std_no.equals("뒤로가기")) {
						bl02 = false;
						break;
					}
					if (std_no.equals(jdbc.isDuplicateSTD(std_no, "STD_NO"))) {
						System.out.println("　　　　　　　　　　　　　　　　♡ "+mdfDAO.getSpdInfo(std_no));
//						System.out.println("변경할 학생 정보가 맞습니까?");
						System.out.println("　　　　　　　　　　　　　　♡ 변경할 학생 정보가 맞습니까?");
						System.out.println("　　　　　　　　　　　　　　　　♡ 1. 예 ♡ 2. 다시입력 ♡ 3. 나가기");
						System.out.print("　　　　　　　　　　　　　　♡ 선택할 번호 ♡   :  ");
						String b = ScanUtil.nextLine();
						switch (b) {
						case "1":
							if (mdfDAO.mdfSuspended(std_no) == 1) {
								System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
								System.out.println("　　　　　　　　　　　　　　　　♡ 정보 수정이 완료되었습니다 ♡");
								ScanUtil.anyPress();
//								bl01 = false;
								bl02 = false;
								break;
							} else {
								ScanUtil.errSleep();
								System.err.println("　　　　　　　　　　　　　　♨ 정보 수정에 실패하였습니다 ♨");
//								bl01 = false;
								bl02 = false;
								ScanUtil.anyPress();
								break;
//								bl01=false;
//								break;
							}
						case "2":
							System.out.println("　　　　　　　　　　　　　　♡ 학생 정보를 다시 입력하세요 ♡");
							break;
						case "3":
							bl02 = false;
							break;
						default:
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 학생 정보를 다시 입력하세요 ♨");
							ScanUtil.anyPress();
							break;
						}
					} else {
						ScanUtil.errSleep();
						System.err.println("　　　　　　　　　　　　　　♨ 존재하지 않는 학번입니다 ♨");
					}
				}
				break;
			case "3":
				bl01=false;
				ScanUtil.anyPress();
					return View.M_MAIN;
			default:
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 잘못된 번호입니다 ♨ 다시 입력하세요 ♨");
				ScanUtil.anyPress();
			}
		}
		ScanUtil.anyPress();
		return View.M_MAIN;
	}

	public int mdfServiceS() {
		ScanUtil.putSomeMessage();
		System.out.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("　　　　　　　　　　　　　　　　비밀번호 변경 : 비밀번호는 4~30자리 알파벳소문자와 숫자로 이루어진 문자열입니다");
		System.out.println("　　　　　　　　　　　　　　￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		boolean bl01 = true;
		while (bl01) {
			String std_no = (String) JDBCUtil.loginUser.get("STD_NO");
			System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
			System.out.print("　　　　　　　　　　　　　　♡ 현재 비밀번호 입력 : ");
			String std_pw = ScanUtil.nextLine();
			if(std_pw.equals("뒤로가기")) {
				ScanUtil.anyPress();
				return View.S_MYPAGE;
			}
			if (std_pw.equals(JDBCUtil.loginUser.get("STD_PW"))) {
//					String newPw = ScanUtil.nextLine();
//					if(newPw.equals("뒤로가기")) {
//						ScanUtil.anyPress();
//								return View.S_MYPAGE;
//					}
					String newPw = "";
					lololo : while(true) {
						System.out.println("　　　　　　　　　　　　　　♡ \"뒤로가기\"를 입력하면 뒤로갈 수 있습니다 ♡");
						System.out.print("　　　　　　　　　　　　　　♡ 변경할 비밀번호 입력 : ");
						newPw = ScanUtil.nextLine();
						if(newPw.equals("뒤로가기")) {
							return View.S_MYPAGE;
						}
						if(newPw.length()<4 || newPw.length()>30) {
							ScanUtil.errSleep();
							System.err.println("　　　　　　　　　　　　　　♨ 입력한 비밀번호 자릿수가 허용범위 밖입니다 ♨");
							continue lololo;
						}
						char[] newPwChar = newPw.toCharArray();
						/*숫자코드 48~57 알파벳 소문자 코드 97~122*/
						for (int i = 0; i < newPwChar.length; i++) {
							boolean isDigit = true;
							boolean isLowCaseAlpha = true;
							/* 숫자인지 판단 */
							if(newPwChar[i]>= 48 && newPwChar[i] <=57) {
							}else {
								isDigit = false; // 배열인덱스 i 값이 숫자에 해당 안되면 false
							}
							/* 알파벳 소문자인지 판단 */
							if(newPwChar[i]>= 97 && newPwChar[i] <=122) {
							}else {
								// 배열인덱스 i 값이 소문자알파벳에 해당 안되면 false
								isLowCaseAlpha = false;
							}
							
							if(!isLowCaseAlpha&&!isDigit) {
								// 원래 값이 둘다 false이면 이쪽으로 들어옴
								// 입력한 문자열에 소문자 알파벳이나 숫자에 해당안되면 이쪽으로 들어옴
								ScanUtil.errSleep();
								System.err.println("　　　　　　　　　　　　　　♨ 비밀번호는 알파벳 소문자나 숫자로만 구성해주세요 ♨");
								continue lololo;
							}
						}// 알파벳소문자와 숫자를 판단하는 부분이 끝나는 곳
						break lololo;
					}
					
					if (mdfDAO.mdfPw(std_no, newPw) == 1) {
						System.out.println();
						System.out.println("　　　　　　　　　　　　　　　　♡ 정보 수정이 완료되었습니다 ♡");
						JDBCUtil.loginUser.put("STD_PW", newPw);
						bl01 = false;
						break;
					} else {
						ScanUtil.errSleep();
						System.err.println("　　　　　　　　　　　　　　♨ 정보 수정에 실패하였습니다 ♨");
						bl01 = false;
						break;
					}

			} else {
				ScanUtil.errSleep();
				System.err.println("　　　　　　　　　　　　　　♨ 비밀번호를 다시 입력하세요 ♨");
			}
		}
		ScanUtil.anyPress();
		return View.S_MYPAGE;
	}
}
