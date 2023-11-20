package kr.block.model.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.block.common.base.dao.BaseDao;
import kr.block.common.utils.DataBaseUtils;
import kr.block.model.member.vo.MemberVo;

/***
 * 회원 가입에 데이터 베이스를 처리하기 위한 DAO
 * @author user1
 *
 */
public class MemberDAO extends BaseDao<MemberVo> {
	
	private final static String TABLE_NAME_MEMBER = "member"; 
	private DataBaseUtils dataBaseUtils = DataBaseUtils.getInstance();
	
	@Override
	public void insert(MemberVo vo) {
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
				dataBaseUtils.updateQuery(query);
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	@Override
	public List<MemberVo> selectAll(String tableName) {
			List<MemberVo> list = new ArrayList<MemberVo>();
		try {
			String query = "SELECT * FROM " + tableName;
			System.out.println("query : "+ query);
			ResultSet rs = dataBaseUtils.executeQuery(query);
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/***
	 * 해당아이디가 있는지 여부 
	 * @param id 아이디 
	 * @return true : 테이블에 해당 아이디가 있음 false : 해당 테이블에 아이디 없음.
	 */
	public boolean isMemberById(String id) {
		boolean isId = false;
		try {
			String query = "SELECT COUNT(*) FROM " + TABLE_NAME_MEMBER +" WHERE id = '" + id + "';";
			System.out.println("query : "+ query);
			ResultSet rs = dataBaseUtils.executeQuery(query);
			rs.next();
			int count = rs.getInt(1);
			isId = (count > 0)? true : false; //아이디 가 있으면 true 아니면 false
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isId;
	}
	
	/***
	 * 특정 회원에 비밀번호를 반환한다.
	 * 참고 사항:
	 * 		HSCHOE 프론트 단에서 암호를 노출하는것은 위험하기 떄문에 프론트단에서 받아온 계정의 암호를 다
	 * 		조회하기 위한 용도로 사용하기 위해 만듬. 
	 * @param cno 회원 번호 
	 * @return cno 에 맞는 회원이 있으면 비밀번호를 반환 아니면 null을 반환한다.
	 */
	public String getPassword(int cno) {
		String passwordColumn = "password";
		String password = null;
		try {
			String query = "SELECT " + passwordColumn + " " +
					 	   "FROM " + TABLE_NAME_MEMBER + " " + 
					 	   "WHERE cno = " + cno + ";";
			ResultSet rs = dataBaseUtils.executeQuery(query);
			while(rs.next()) {
				password = rs.getString(passwordColumn);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}
	
	/***
	 * 아이디로 테이블에서 해당 아이디를 가져옴
	 * @param id 아이디
	 * @return 해당 아이디 vo 반환 만약에 아이디가 없으면 null을 보낸다.
	 */
	public MemberVo getMemberById(String id) {
		MemberVo memberVo = null;
		try {
			String query = "SELECT * FROM " + TABLE_NAME_MEMBER +" WHERE id = '" + id + "';";
			System.out.println("query : "+ query);
			ResultSet rs = dataBaseUtils.executeQuery(query);
			if(rs.next()) {
				memberVo = new MemberVo(
					rs.getInt("cno"),
					rs.getString("id"),
					rs.getString("password"),
					rs.getString("cstNm"),
					rs.getString("gender"),
					rs.getString("email"),
					rs.getString("address"),
					rs.getString("aboutMe")
				);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVo;
	}
	/***
	 * 회원의 cno로 데이터 베이스에서 계정을 삭제.
	 * @param vo 계정의 vo 객체
	 */
	@Override
	public void delete(MemberVo vo) {
		try {
			String query = "DELETE FROM " + TABLE_NAME_MEMBER + 
						   " WHERE cno = "+vo.getCno();
				dataBaseUtils.updateQuery(query);
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	@Override
	public void update(MemberVo vo) {
		try {
			String query = "UPDATE " +
								TABLE_NAME_MEMBER + " " + 
						   "SET "+
							   "cno = " + vo.getCno() + ", " +
							   "id = " + "'" + vo.getId() + "'"  + ", " +
							   "password = " + "'" + vo.getPassword() + "'" + ", " +
							   "cstNm = " + "'" + vo.getCstNm() + "'" + ", " +
							   "gender = " + "'" + vo.getGender() + "'" + ", " +
							   "email = " + "'" + vo.getEmail() + "'" + ", " +
							   "address = " + "'" + vo.getAddress() + "'"  + ", " +
							   "aboutMe = " + "'" + vo.getAboutMe()+ "'" + " " +
						   "WHERE " + 
							   "cno = " +vo.getCno()+ ";";
					dataBaseUtils.updateQuery(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
