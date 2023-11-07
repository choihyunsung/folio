package kr.block.model.member.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.block.common.base.dao.BaseDao;
import kr.block.model.member.vo.MemberVo;

/***
 * 회원 가입에 데이터 베이스를 처리하기 위한 DAO
 * @author user1
 *
 */
public class MemberDAO extends BaseDao<MemberVo> {
	
	private final static String TABLE_NAME_MEMBER = "member"; 
	private Connection conn = null;
	
	public MemberDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/" + "jdbc/mysql");
			conn = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(MemberVo vo) {
		//TODO TESTLINE 나중에 공통으로 처리 
		try {
			String query = "INSERT INTO " + TABLE_NAME_MEMBER + " ("
					+ "cno, id, password, cstNm, gender, email, address, aboutMe)"
					+ " VALUES ("
					+ 0 + ",'"
					+ vo.getId() + "','" 
					+ vo.getPassword() + "','" 
					+ vo.getCstNm() + "','" 
					+ vo.getGender() + "','" 
					+ vo.getEmail() + "','" 
					+ vo.getAddress() + "','" 
					+ vo.getAboutMe() + 
					"');";
				System.out.println("query : "+ query);
				conn.createStatement().executeUpdate(query);
				conn.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	
		//TODO TESTLINE
	}

	@Override
	public List<MemberVo> selectAll(String tableName) {
		//TODO TESTLINE 나중에 공통으로 처리
			List<MemberVo> list = new ArrayList<MemberVo>();
		try {
			String query = "SELECT * FROM " + tableName;
			System.out.println("query : "+ query);
			ResultSet rs = conn.createStatement().executeQuery(query);
		
			while(rs.next()) {
				MemberVo vo = new MemberVo(
						rs.getInt("cno"),
						rs.getString("id"),
						rs.getString("password"),
						rs.getString("cstNm"),
						rs.getString("gender"),
						rs.getString("email"),
						rs.getString("address"),
						rs.getString("aboutMe")
						);
				list.add(vo);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO TESTLINE 나중에 공통으로 처리 
		return list;
	}
	
	public boolean isMemberById(String id) {
		boolean isId = false;
		try {
			String query = "SELECT COUNT(*) FROM " + TABLE_NAME_MEMBER +" WHERE id = '" + id + "';";
			System.out.println("query : "+ query);
			ResultSet rs = conn.createStatement().executeQuery(query);
			rs.next(); //TODO HSCHOE 이거 나중에 꼭 리펙토링 해야함 너무 더러움
			int count = rs.getInt(1);
			isId = (count > 0)? true : false; //아이디 가 있으면 true 아니면 false	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("2");
			e.printStackTrace();
		}
		return isId;
	}

	@Override
	public void delete(MemberVo vo) {
	}

	@Override
	public void update(MemberVo vo) {
	}

}
