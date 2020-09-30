package com.vfc.useradmin.system.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.vfc.useradmin.system.api.UserService;
import com.vfc.useradmin.system.bo.SystemBO;
import com.vfc.useradmin.system.dao.SystemDao;

@Service("userServicer")
public class UserServiceImpl implements UserService{

	@Autowired
	private SystemBO systemBO;
	
	@Override
	public String findAllUser() {
		// TODO 
		
		return "findAllUser";
	}

	@Override
	public String findUserByUsername(String loginName) {
		// TODO Auto-generated method stub
		return JSON.toJSONString(systemBO.findUserByUniName(loginName));
	}
	
	public static void main(String[] args) {
	    String url = "jdbc:oracle:thin:@192.168.50.22:1521:TLCS";
	    String username = "ARCHIVE";
	    String password = "ARCHIVE";
	    Connection conn = null;
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			conn= DriverManager.getConnection(url, username, password);
			
			String sql=" select oid,orgname,lawman from org.Org where rownum<5 ";
			
			
			PreparedStatement stmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE
					,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs =stmt.executeQuery();
			int cols = rs.getMetaData().getColumnCount();
			rs.last();
			int rowTol = rs.getRow();
			rs.beforeFirst();
			System.out.println(rowTol);
			Object[][] rsArr=new Object[cols][rowTol];
			int rownum=0;
			while(rs.next()){
				rownum=rs.getRow();
				for(int columnIndex=0 ; columnIndex < cols ; columnIndex++){
					rsArr[columnIndex][rs.getRow()-1]=rs.getObject(columnIndex+1);
				}
			}

			for(int i=0;i<cols;i++){
				for(int j=0;j<rownum;j++){
					System.out.println(rsArr[i][j]);
				}
				
			}
			
			stmt.close();
			conn.close();
	    } catch (Exception e) {
			e.printStackTrace();
		}
}
	
}
