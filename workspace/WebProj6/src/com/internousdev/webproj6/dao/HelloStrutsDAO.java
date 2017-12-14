/**
 *
 */
package com.internousdev.webproj6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.webproj6.dto.HelloStrutsDTO;
import com.internousdev.webproj6.util.DBConnector;
/**
 * @author internousdev
 *
 */
public class HelloStrutsDAO {

	List<HelloStrutsDTO> helloStrutsDTOList = new ArrayList<HelloStrutsDTO>();

	public List<HelloStrutsDTO> select() {
		boolean ret = false;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from users";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				HelloStrutsDTO dto = new HelloStrutsDTO();
				dto.setUserId(rs.getInt("user_Id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setPassword(rs.getString("password"));
				dto.setResult("MySQLと接続できます。");
				helloStrutsDTOList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return helloStrutsDTOList;
	}

}