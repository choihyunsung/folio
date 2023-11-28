package kr.block.common.action.nomove;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.prop.CommonProperties;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.dto.BoardListDTO;
import kr.block.model.member.vo.AuthorWithBoardVo;
import kr.block.model.member.vo.BoardVo;

public class SearchAction extends BaseDAOAction<BoardVo, BoardDAO> {
	
	public SearchAction() {
		this.dao = new BoardDAO();
	}

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			String searchString = req.getParameter("searchText");
			String searchCode = req.getParameter("searchType");
			int pageCount = Integer.valueOf(req.getParameter("pageCount"));
			//testline	
			Pair<List<AuthorWithBoardVo>, Integer> pair = dao.getPageAuthorWithBoardSearchList(searchString, searchCode, pageCount, CommonProperties.pageMaxCount);
			
		
			int boardTotalCount = pair.getSecond();
			int totalCount = boardTotalCount/CommonProperties.pageMaxCount;
			if(boardTotalCount % CommonProperties.pageMaxCount !=0 ) { //전체 카운트에서 패이지당 불러올 카운트가 나누어 떨어지지 않을경우 즉 나머지가 있을경우  +1
				totalCount += 1;
			}
			BoardListDTO boardListDTO = new BoardListDTO(pair.getFirst(), totalCount);
			String boardListJsonString = JsonUtils.getDTOJson(boardListDTO).toJSONString();
			System.out.println(boardListJsonString);
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(boardListJsonString);
			pair.getFirst().forEach(data -> {
				System.out.println("작성자 : " + data.getAuthor() + ", 글번호 : " + data.getBoardVo().getNo() + ",게시글" + data.getBoardVo().getContent() + ", 제목 :" + data.getBoardVo().getTitle());
			});
			//testline	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
}
