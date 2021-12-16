/**
 *
 */
package jp.java.demall.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author ta2ro
 * サービス共通関数
 *
 */
public class CommonService {

	/***
	 * paswardの文字数に合わせて*表示を返す
	 * return パスワード文字数分の*
	 */
	public String getPasswordStar(String pwd) {


		StringBuilder sb=new StringBuilder();


		for(int i=0;i<pwd.length();i++) {
			sb.append("*");
		}

		String str=sb.toString();
		return str;
	}



	/***
	 *
	 * パスワード（文字列暗号化sha256）
	 * commons-codec-1.15(Apache Commons Codec)を利用
	 * @param pwd　
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptPasswordSHA256hex(String pwd)  {

		String hexString = DigestUtils.sha256Hex(pwd);
		return hexString;



	}






}

