package kr.block.common.action.nomove;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.vo.BoardVo;

/***
 * 게시글 반환 
 */
public class GetBoardAction extends BaseDAOAction<BoardVo, BoardDAO>{

	public GetBoardAction() {
		this.dao = new BoardDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			int boardNo = Integer.valueOf(req.getParameter("no"));
			System.out.println();
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain"); // 미디어 유형 설정
			resp.getWriter().write(JsonUtils.getVoJson(dao.getBoard(boardNo)).toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

}
