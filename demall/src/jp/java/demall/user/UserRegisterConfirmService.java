package jp.java.demall.user;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.common.CommonService;
import jp.java.demall.login.User;

/**
 * 会員登録クラスのサービス情報
 *
 * */
public class UserRegisterConfirmService extends CommonService{

	/**
	 * 入力情報をセットして返す。
	 *
	 * */
	public User excute(HttpServletRequest request) {

		User user = new User();
		user.setUserId(request.getParameter("id"));
		user.setPassword(request.getParameter("password1"));
		user.setName(request.getParameter("name"));
		user.setAddress(request.getParameter("address"));

		return user;
	}



}
