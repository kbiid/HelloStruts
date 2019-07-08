package kr.co.torpedo.hellostruts.controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import kr.co.torpedo.hellostruts.domain.Admin;

/**
 * struts.xml에서 설정한 클래스
 * 
 * @author user
 *
 */
public class LoginAction implements Preparable, Action, ModelDriven {
	private String message;
	private ConfigReader reader;
	private Admin admin, enteredAdmin;

	public String getMessage() {
		return message;
	}

	// 이 메소드 내에서 처리 후 return한 값에 따라 struts.xml에서 처리
	@Override
	public String execute() throws Exception {
		if (admin.checkAdminInfo(enteredAdmin.getId(), enteredAdmin.getPasswd())) {// 아이디, 비밀번호를 올바르게 입력한 경우
			return "success";
		} else {
			if (enteredAdmin.getId().equals("")) {
				message = "아이디를 입력해주세요!";
			} else if (enteredAdmin.getPasswd().equals("")) {
				message = "비밀번호를 입력해주세요!";
			} else {// 아이디,비밀번호를 다 입력했지만 틀린경우
				return "fail";
			}
			return "back";
		}
	}

	public Admin getEnteredAdmin() {
		return enteredAdmin;
	}

	// 초기화 작업
	@Override
	public void prepare() throws Exception {
		reader = new ConfigReader();
		admin = new Admin();
		admin.setId(reader.getAdminId());
		admin.setPasswd(reader.getAdminPwd());
		enteredAdmin = new Admin();
	}

	@Override
	public Object getModel() {
		return enteredAdmin;
	}
}
