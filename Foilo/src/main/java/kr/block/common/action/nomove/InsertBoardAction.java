package kr.block.common.action.nomove;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.BoardVo;
import kr.block.model.member.vo.MemberVo;

public class InsertBoardAction extends BaseDAOAction<BoardVo, BoardDAO> {

	public InsertBoardAction() {
		this.dao = new BoardDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		
			try {
				MemberDAO memberDao = new MemberDAO();
				req.setCharacterEncoding("UTF-8");
				int cno = Integer.valueOf(req.getParameter("cno"));
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				dao.insert(new BoardVo(0, cno, null, title, content));
				JSONObject stateObject = new JSONObject();
				stateObject.put("isInsertBoard", true); //로그인 여부 
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("text/plain"); // 미디어 유형 설정
				resp.getWriter().write(stateObject.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

}
