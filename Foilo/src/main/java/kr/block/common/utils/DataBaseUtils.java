package kr.block.common.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/***
 * @author choehyeonseong
 * @FIXME 이거 내일 강사님 께 검증.
 * 커넥션 객체를 디비 연결시에 계속 호출하니 메모리릭이 나서 DAO 의 생성자에 넣어서 처리 
 * 생각해보니 만약 DAO Class가 여러 개 만들어지는 상황이라면 Connect가 여러 개 생성되니까 다시 메모리릭이 발생할수 있을것같음.
 * 그래서 싱글톤으로 빼놨는데 어짜피 connection은 close를 하게되면.. conn의 기능이 단순 쿼리 조회 만들 위해 사용된다면 여기서 데이터를 넘기는 식으로 구현되고 close되어도될지. 
 */
public class DataBaseUtils {
	private static DataBaseUtils instance = null;
	private Connection connection = null;
	
	//DataBaseUtils는 싱글톤 객체이기 때문에 생성자를 Private로 선언한다.
	private DataBaseUtils() {} 
	
	public static DataBaseUtils getInstance() {
		if(instance == null) {
			instance = new DataBaseUtils();
		}
		return instance;
	}
	/***
	 * 커넥션 객체 반환.
	 * @return
	 */
	public Connection getConnection() {
		try {
			if(connection == null || connection.isClosed()) {
				Context ctx = new InitialContext();
				DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/" + "jdbc/mysql");
				connection = dataSource.getConnection();
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	
		return connection;
	}
}
