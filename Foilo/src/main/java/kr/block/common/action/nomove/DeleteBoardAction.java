package kr.block.common.action.nomove;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.vo.BoardVo;

public class DeleteBoardAction extends BaseDAOAction<BoardVo, BoardDAO>{
	
	public DeleteBoardAction() {
		this.dao = new BoardDAO();
	}

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			int boardNo = Integer.valueOf(req.getParameter("no"));
			System.out.println("boardNo : " + boardNo);
			JSONObject stateObject = new JSONObject();
			stateObject.put("isDeleteBoard", dao.deleteByNo(boardNo)); //로그인 여부 
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain"); // 미디어 유형 설정
			resp.getWriter().write(stateObject.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

}
