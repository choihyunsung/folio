package kr.block.common.action;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.impl.Action;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class JoinAction implements Action {

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		MemberDAO dao = new MemberDAO();
		dao.insert(parserVo(req));
		/**@TODO HSCHOE이거 리펙토링 진행하지 꼭 Url 따로 모와서 처리하도록 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
		String url = "/Foilo/Login.html";
		return new Pair<ResponseMethodType, String>(ResponseMethodType.Redirect,url);
		/**@TODO HSCHOE이거 리펙토링 진행하지 꼭 Url 따로 모와서 처리하도록 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	}

	private MemberVo parserVo(HttpServletRequest req) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String cstNm = req.getParameter("cstNm");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String aboutMe = req.getParameter("aboutMe");
		/**cno는 오토 이기 때문에 0으로 그냥 넣어줌 */
		return new MemberVo(0, id, password, cstNm, gender, email, address, aboutMe);
	}
}
