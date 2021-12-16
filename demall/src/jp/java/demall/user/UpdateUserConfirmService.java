package jp.java.demall.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.common.CommonService;
import jp.java.demall.login.User;

/***
 * 変更画面
 * @author ta2ro
 *
 */
public class UpdateUserConfirmService extends CommonService {


	/**
	 * 入力情報をセットして返す。
	 * @throws SQLException
	 *
	 * */
	public User execute(HttpServletRequest request)   {

		User user = new User();
		user.setUserId(request.getParameter("id"));
		user.setPassword(request.getParameter("password1"));
		user.setName(request.getParameter("name"));
		user.setAddress(request.getParameter("address"));

		return user;
	}




}
