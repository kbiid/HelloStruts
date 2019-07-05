package kr.co.torpedo.hellostruts.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import kr.co.torpedo.hellostruts.domain.Admin;

public class LoginAction extends ActionSupport implements Preparable {
	private static final long serialVersionUID = 4467110876444763732L;
	private String id;
	private String passwd;
	private String message;
	private ConfigReader reader;
	private Admin admin;

	public void setId(String id) {
		this.id = id;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String execute() throws Exception {
		if (admin.checkAdminInfo(id, passwd)) {// 아이디, 비밀번호를 올바르게 입력한 경우
			return "success";
		} else {
			if (id.equals("")) {
				message = "아이디를 입력해주세요!";
			} else if (passwd.equals("")) {
				message = "비밀번호를 입력해주세요!";
			} else {// 아이디,비밀번호를 다 입력했지만 틀린경우
				return "fail";
			}
			return "back";
		}
	}

	// 초기화 작업
	@Override
	public void prepare() throws Exception {
		reader = new ConfigReader();
		admin = new Admin();
		admin.setId(reader.getAdminId());
		admin.setPasswd(reader.getAdminPwd());
	}
}
