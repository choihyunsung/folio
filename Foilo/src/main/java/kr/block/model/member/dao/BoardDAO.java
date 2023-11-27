package kr.block.model.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.block.common.base.dao.BaseDao;
import kr.block.common.utils.DataBaseUtils;
import kr.block.model.member.vo.AuthorWithBoardVo;
import kr.block.model.member.vo.BoardVo;

public class BoardDAO extends BaseDao<BoardVo> {
	private final static String TABLE_NAME_BOARD = "board";
	private final static String TABLE_NAME_MEMBER = "member";  //@FIXME HSCHOE 임시로 테이블 선언 나중에 빼야함. (조인일경우 테이블을 따로 공통으로 관리해야할수도 있음)
	private DataBaseUtils dataBaseUtils = DataBaseUtils.getInstance();
	
	
	public List<AuthorWithBoardVo> getAuthorWithBoardList() {
		List<AuthorWithBoardVo> list = new ArrayList<AuthorWithBoardVo>();
		try {
			String query = "SELECT member.cstNm, board.* FROM "+ TABLE_NAME_BOARD + " INNER JOIN " + TABLE_NAME_MEMBER + " ON board.cno = member.cno"; 
			ResultSet rs = dataBaseUtils.executeQuery(query);
			
			while(rs.next()) {
				AuthorWithBoardVo authorWithBoardVo = new AuthorWithBoardVo(
						rs.getString("cstNm"), //작성자
						new BoardVo(
								rs.getInt("no"), //글번호 
								rs.getInt("cno"),//작성자 번호 
								rs.getDate("date"), //작성일자 
								rs.getString("title"), //작성 제목
								rs.getString("content") //작성 내용
								)
						);
				list.add(authorWithBoardVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/***
	 * 
	 * @param currentPage 적용될 현재 페이지 
	 * @param MaxPageNumber 한번에 보여줄 페이지 개수
	 * @return 현재 페이지의 리스트만을 반환한다
	 */
	public List<AuthorWithBoardVo> getPageAuthorWithBoardList(int currentPage, int MaxPageNumber) {
		List<AuthorWithBoardVo> list = new ArrayList<AuthorWithBoardVo>();
		
		try {
//			int to =  (currentPage * MaxPageNumber); //현재 선택된 페이지 * 최대 페이지 
			System.out.println("PageCount : "+ currentPage);
			int from = (currentPage > 0)? ((MaxPageNumber) * currentPage ) : 0; // 1보다 크다면 (현재 선택된 페이지 * 최대 페이지) - 최대 페이지 아닐경우 1이다 
			int to = MaxPageNumber;

			//리미트는from 부터 to 개수 만큼이다 form 부터 to 사이가 아니라 .. 꼭 명심 진짜로 !
			String query = "SELECT member.cstNm, board.* FROM "+ 
							TABLE_NAME_BOARD + " INNER JOIN " + TABLE_NAME_MEMBER + " ON board.cno = member.cno LIMIT " + from + ", " + to; 
			ResultSet rs = dataBaseUtils.executeQuery(query);
			while(rs.next()) {
				AuthorWithBoardVo authorWithBoardVo = new AuthorWithBoardVo(
						rs.getString("cstNm"), //작성자
						new BoardVo(
								rs.getInt("no"), //글번호 
								rs.getInt("cno"),//작성자 번호 
								rs.getDate("date"), //작성일자 
								rs.getString("title"), //작성 제목
								rs.getString("content") //작성 내용
								)
						);
				list.add(authorWithBoardVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/***
	 * 
	 * @return 게시글의 모든 카운트를 가져옴.
	 */
	public int getBoardCount() {
		int count = 0;
		try {
			String query = "SELECT COUNT(*) count FROM " + TABLE_NAME_BOARD;
			ResultSet rs = dataBaseUtils.executeQuery(query);
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
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
	
	/***
	 * @param boardNo 조회할 글 번호
	 * @return boardNo로 해당 게시글 조회
	 */
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
