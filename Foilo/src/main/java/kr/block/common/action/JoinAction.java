package kr.block.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.impl.Action;
import kr.block.common.object.Pair;
import kr.block.common.prop.UrlProperties;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class JoinAction implements Action {

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		MemberDAO dao = new MemberDAO();
		dao.insert(parserVo(req));
		return new Pair<ResponseMethodType, String>(ResponseMethodType.Redirect,UrlProperties.URL_LOGIN_PAGE);
	}

	private MemberVo parserVo(HttpServletRequest req) {
		String id = req.getParameter("id"); // -> (PK)auto increment
		String password = req.getParameter("password");
		String cstNm = req.getParameter("cstNm");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String aboutMe = req.getParameter("aboutMe");
		return new MemberVo(0, id, password, cstNm, gender, email, address, aboutMe);
	}
}
