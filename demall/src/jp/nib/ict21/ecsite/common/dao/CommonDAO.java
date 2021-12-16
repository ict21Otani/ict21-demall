/**
 *
 */
package jp.nib.ict21.ecsite.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author NiB
 * Dao共通クラス
 *
 */
public class CommonDAO {
	 protected static final String URL = "jdbc:postgresql://localhost/ecsite";
	 protected static final String USER = "postgres";


	 protected static final String PASSWORD = "postgres";

	 /**コネクション変数 接続に利用する*/
	 private Connection connection;

	/**コネクションの取得*/
	public Connection getConnection() throws SQLException {
		this.connection=DriverManager.getConnection(URL,USER,PASSWORD);
		return connection;
	}
}


