package kr.block.common.action.nomove;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class PasswordModifyAction extends BaseDAOAction<MemberVo, MemberDAO>{
	
	public PasswordModifyAction() {
		dao = new MemberDAO();
	}

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
}
