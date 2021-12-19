/**
 *
 */
package jp.java.demo.ecsite.manage.shop.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import jp.java.demall.common.constant.ConstParam;
import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

/**
 * @author shisa
 *
 */
public class ItemRegistCSVService {

	/***
	 * ファイルの内容をItemsクラスのリストにしてかえす。
	 * @param request
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 * @param path uploadfileの保存先
	 */
	public List<Items> excute(HttpServletRequest request ,String realPath) throws IOException, SQLException, ServletException {


		Part part= request.getPart("csv");
		//System.out.println(part.getSubmittedFileName());
		//System.out.println(part.getHeader("Content-Disposition"));


		 String name =part.getSubmittedFileName();
	     part.write(realPath+ "/" + name);

	   //ファイルパスとしてアップロードファイルを定義する
	     String file =realPath+ "/" + name;


		File csv = new File(file);

		FileReader fr = new FileReader(csv);
		ItemsDAO dao = new ItemsDAO();

		List<Items> splitList = new ArrayList<Items>();

		try (BufferedReader br = new BufferedReader(fr)) {
			String line = null;

			//読み込むものがあったとき
			while ((line = br.readLine()) != null) {
				//分割処理
				String[] text = line.split(",");
				//一番上の行ではない時		//一行目は飛ばす
				if (text[0].equals("商品名") == false) {
					//アイテムクラスに代入する用の変数
					Items items = new Items();

					//CSVの項目数＝商品テーブルのカラム数（現在）違う場合は入れない
					if (text.length == ConstParam.CSV_MAX_LENGTH) {
						items.setName(text[0]);
						items.setCategoryId(Integer.parseInt(text[1]));
						items.setColor(text[2]);
						items.setManufacturer(text[3]);
						items.setStock(Integer.parseInt(text[4]));
						items.setPrice(Integer.parseInt(text[5]));
						boolean recomented;
						if (text[6] != null && text[6].equals("Y")) {
							recomented = true;
						} else {
							recomented = false;
						}
						items.setRecommended(recomented);

						//インサートする
						dao.insertItems(items);

						splitList.add(items);
					} else {
						//行数を記録

						//項目数が違うことを7項目ではないことのメッセージセット

					}
					//TODO number formatExceptionがあった場合もエラーを記録する//
				}
			}

		} catch (FileNotFoundException e) {
			//ファイルをアップして登録ボタンを押す前に対象のファイルを削除するとエラー
			throw e;
		} catch (IOException e) {
			//ファイルの中に読み取れないものがあったとき
			throw e;
		} catch (SQLException e) {
			throw e;

		}
		return splitList;

	}

}
