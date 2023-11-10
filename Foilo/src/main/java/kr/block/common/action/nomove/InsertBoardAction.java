package kr.block.common.action.nomove;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.action.impl.ActionImpl;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.BoardVo;

public class InsertBoardAction extends BaseDAOAction<BoardVo, BoardDAO> {

	public InsertBoardAction() {
		this.dao = new BoardDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
