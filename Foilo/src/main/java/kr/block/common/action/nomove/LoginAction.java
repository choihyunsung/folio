package kr.block.common.action.nomove;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

/***
 * @author choehyeonseong
 */
public class LoginAction extends BaseDAOAction<MemberVo, MemberDAO> {

	public LoginAction() {
		dao = new MemberDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			MemberVo loginMemberVo = dao.getMemberById((String)req.getParameter("id"));
			loginMemberVo.resetPassword();
			HttpSession session = req.getSession();
			session.setAttribute("memberInfo", JsonUtils.getVoJson(loginMemberVo));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
}
