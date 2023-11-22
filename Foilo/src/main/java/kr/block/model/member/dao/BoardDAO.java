package kr.block.model.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.block.common.base.dao.BaseDao;
import kr.block.common.utils.DataBaseUtils;
import kr.block.model.member.vo.BoardVo;

public class BoardDAO extends BaseDao<BoardVo> {
	private final static String TABLE_NAME_BOARD = "board";
	private DataBaseUtils dataBaseUtils = DataBaseUtils.getInstance();
	
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
	public List<BoardVo> selectAll(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(BoardVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BoardVo vo) {
		// TODO Auto-generated method stub
		
	}

}
