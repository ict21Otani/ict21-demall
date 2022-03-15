/**
 *
 */
package jp.java.demall.common.constant;

/**
 * @author ta2ro
 *　値関連の定数クラス
 */
public class ConstParam {

	/**一ページ内の表示最大アイテム数*/
	public static final int COUNT_ONEPAGE_ITEMS=10;

	/**表示ページ数最大数*/
	public static final int MAX_PAGE=100;

	/**CSVの項目数*/
	public static final int CSV_MAX_LENGTH=7;

	/**autoログイン機能のcookieキー**/
	public static final String KEY_AUTO_LOGIN="autoLogin";
	/**autoログイン機能を設定した際のログインID用のcookieキー　TODO キートークン実装後削除予定**/
	public static final String KEY_AUTO_LOGIN_ID="autoLoginId";
	/**autoログイン機能を設定した際のログインID用のPWDキー TODO キートークン実装後削除予定**/
	public static final String KEY_AUTO_LOGIN_PWD="autoLoginPwd";
	/**autoログイン機能用キートークン*/
	public static final String KEY_AUTO_LOGIN_TOKEN="autoLoginToken";
}
