/**
 *
 */
package jp.java.demall.login;



/**
 * @author ta2ro
 * Usersテーブル情報クラス
 */
public class User {

	private String userId;

	private String password;

	private String name;

	private String address;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String user_id) {
		this.userId = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
