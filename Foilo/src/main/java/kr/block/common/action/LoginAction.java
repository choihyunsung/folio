package kr.block.common.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.block.common.action.impl.Action;
import kr.block.common.object.Pair;
import kr.block.common.prop.UrlProperties;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

/***
 * @author choehyeonseong
 * 로그인 완료후 세션 생성 메인으로 이동 메인으로 이동 
 */
public class LoginAction implements Action {

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			MemberDAO memberDao = new MemberDAO();
			req.setCharacterEncoding("UTF-8");
			MemberVo loginMemberVo = memberDao.getMemberById((String)req.getParameter("id"));
			loginMemberVo.resetPassword();
			HttpSession session = req.getSession();
			session.setAttribute("memberInfo", loginMemberVo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.Redirect, UrlProperties.URL_MAIN_PAGE);
	}
}
