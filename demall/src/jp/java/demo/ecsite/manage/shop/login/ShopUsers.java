/**
 *
 */
package jp.java.demo.ecsite.manage.shop.login;

import jp.java.demall.login.User;

/**
 * @author ta2ro
 * shop_usersテーブル情報クラス
 */
public class ShopUsers extends User{


	private String tel;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}



}
