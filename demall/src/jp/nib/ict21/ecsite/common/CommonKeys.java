package jp.nib.ict21.ecsite.common;



//setAttributeやgetParameterする時のキー名。.javaにキー名をべたうちすると
//間違えるのでできるだけこれを使いましょう。
//ただしJSP（EL式）では使えないのでその場合はキー名をここからコピペしましょう。
public class CommonKeys {
	public static final String SESSION_LOGGED_IN_KEY = "login";
	public static final String REQUEST_KEY＿LOGIN ="loginId";
	public static final String REQUEST_KEY＿PASSWORD ="password";
}
