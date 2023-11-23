package kr.block.common.action.nomove;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.BoardDAO;
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
			String boardListJsonString = JsonUtils.ListToJsonArray(dao.selectAll()); 
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
