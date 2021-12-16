/**
 *
 */
package jp.java.demo.ecsite.manage.shop.login;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.common.CommonService;
import jp.java.demo.ecsite.manage.shop.dao.ShopUserDAO;

/**
 * @author ta2ro
 * @return 取得したショップ管理ユーザーの情報ないときはnullが帰る
 * @param リクエスト
 *
 */
public class ShopLoginService extends CommonService{

	public ShopUsers execute(HttpServletRequest request) throws SQLException {

		ShopUserDAO dao = new  ShopUserDAO();
		String userId=request.getParameter("id");
		ShopUsers user=null;
		String enpPwd=encryptPasswordSHA256hex(request.getParameter("password"));
		try {
			user=dao.findById(userId);
			//取得できた時はパスワードをチェック
			if(null!=user) {

				if(user.getPassword().equals(enpPwd)) {
					//一致
					return user;//ユーザー情報をそのまま返す
				}else {
					return null;//パスワードが一致しないためnullを返す。
				}

			}else {
				return null;
			}
		} catch (SQLException e) {
			throw e;

		}



	}
}
