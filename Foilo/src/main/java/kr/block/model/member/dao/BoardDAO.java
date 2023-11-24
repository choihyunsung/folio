package kr.block.model.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.block.common.base.dao.BaseDao;
import kr.block.common.utils.DataBaseUtils;
import kr.block.model.member.vo.BoardVo;

public class BoardDAO extends BaseDao<BoardVo> {
	private final static String TABLE_NAME_BOARD = "board";
	private DataBaseUtils dataBaseUtils = DataBaseUtils.getInstance();
	
	
	public BoardVo getBoard(int boardNo) {
		BoardVo boardVo = null;
		try {
			String query = "SELECT * FROM "+TABLE_NAME_BOARD +" WHERE no="+boardNo;
			ResultSet rs = dataBaseUtils.executeQuery(query);
			while (rs.next()) {
				boardVo = new BoardVo(rs.getInt("no"), rs.getInt("cno"),rs.getDate("date"), rs.getString("title"), rs.getString("content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardVo;
	}
	
	@Override
	public void insert(BoardVo vo) {
		try {
			String query = "INSERT INTO " + TABLE_NAME_BOARD 
					+ "(cno,date,title,content) Values(" + vo.getCno() + ",sysdate()" + ",'" + vo.getTitle() + "','" + vo.getContent()+"')";
			System.out.println(query);
				dataBaseUtils.updateQuery(query);
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public List<BoardVo> selectAll() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		try {
			String query = "SELECT * FROM " + TABLE_NAME_BOARD;
			ResultSet rs = dataBaseUtils.executeQuery(query);
			while (rs.next()) {
				BoardVo boardVo = new BoardVo(rs.getInt("no"), rs.getInt("cno"), rs.getDate("date"), rs.getString("title"), rs.getString("content"));
				list.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/***
	 * 
	 * @param no 게시글 번호 
	 * @return 게시판 삭제 성공시 : true 삭제 실패시에 : false
	 */
	public boolean deleteByNo(int no) {
		boolean result = false;
		String query = "DELETE FROM " + TABLE_NAME_BOARD + " WHERE no="+no;
		try {
			int rs = dataBaseUtils.updateQuery(query);
			result = (rs > 0); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void delete(BoardVo vo) {
	
		
	}

	@Override
	public boolean update(BoardVo vo) {
		//날짜는 수정된 날짜로 덮여쓰임 
		String query = "UPDATE " + TABLE_NAME_BOARD + " SET no = " + vo.getNo() +", cno = " + vo.getCno() + ", date = sysdate() , title = '" + vo.getTitle() + "', content = '" + vo.getContent()+"' WHERE no = " + vo.getNo();
		boolean result = false;
		try {
			int rs = dataBaseUtils.updateQuery(query);
			System.out.println("count = "+rs);
			result = (rs > 0); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
