package kr.block.common.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseUtils {
	private static DataBaseUtils instance = null;
	private static Connection connection = null;
	
	//DataBaseUtils는 싱글톤 객체이기 때문에 생성자를 Private로 선언한다.
	private DataBaseUtils() {} 
	
	public static DataBaseUtils getInstance() {
		if(instance == null) {
			instance = new DataBaseUtils();
			createConnection();
		}
		return instance;
	}
	
	/**
	 * 데이터 조회문
	 */
	public synchronized ResultSet executeQuery(String query) throws SQLException {
		createConnection();
		System.out.println("excuteQuery : "+ query);
		ResultSet resultSet = connection.createStatement().executeQuery(query);
		return resultSet; 
	}
	
	/***
	 * 업데이트 쿼리 실행 -> 데이터 삽입 삭제 업데이트등.
	 */
	public synchronized int updateQuery(String query) throws SQLException {
		System.out.println("updateQuery : "+ query);
		createConnection();
		int result = connection.createStatement().executeUpdate(query);
		return result;
	}
	
	/***
	 * 커넥션 객체 닫기
	 * @throws SQLException
	 */
	public synchronized void closeConnection() throws SQLException {
		if(connection != null || !connection.isClosed()) {
			connection.close();
		}
	}
	
	/**
	 * Connection 객체가 널이거나 닫혀 있으면 커넥션 객체를 생성함.
	 */
	private synchronized static void createConnection() {
		try {
			if(connection == null || connection.isClosed()) {
				Context ctx = new InitialContext();
				DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/" + "jdbc/mysql");
				connection = dataSource.getConnection();
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
}
