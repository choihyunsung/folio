package kr.block.common.action;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.impl.Action;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class JoinAction implements Action {

	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		MemberDAO dao = new MemberDAO();
		dao.insert(parserVo(req));
		return null;
	}

	private MemberVo parserVo(HttpServletRequest req) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String cstNm = req.getParameter("cstNm");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String aboutMe = req.getParameter("aboutMe");
		/**cno 오토라 0으로 그냥 넣어줌 */
		return new MemberVo(0, id, password, cstNm, gender, email, address, aboutMe);
	}
}
