package kr.block.common.action.nomove;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.dto.BoardListDTO;
import kr.block.model.member.vo.AuthorWithBoardVo;
import kr.block.model.member.vo.BoardVo;

/**
 * 게시글 조회하기 
 * @author choehyeonseong
 *
 */
public class LookUpBoardAction extends BaseDAOAction<BoardVo, BoardDAO> {

	public LookUpBoardAction() {
		this.dao = new BoardDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int readCountNumber = 5;
			req.setCharacterEncoding("UTF-8");
			int pageCount = Integer.valueOf(req.getParameter("pageCount"));
			int boardTotalCount = dao.getBoardCount();
			int totalCount = boardTotalCount/readCountNumber;
			System.out.println("boardCount : " + boardTotalCount +", readCount : " + readCountNumber);
			if(boardTotalCount % readCountNumber !=0 ) { //전체 카운트에서 패이지당 불러올 카운트가 나누어 떨어지지 않을경우 즉 나머지가 있을경우  +1
				totalCount += 1;
			}
			BoardListDTO boardListDTO = new BoardListDTO(dao.getPageAuthorWithBoardList(pageCount, readCountNumber), totalCount);
			String boardListJsonString = JsonUtils.getDTOJson(boardListDTO).toJSONString();
			System.out.println(boardListJsonString);
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(boardListJsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

}
