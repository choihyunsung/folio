package kr.block.common.action;

import java.io.IOException; 
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.impl.Action;
import kr.block.model.member.dao.MemberDAO;

public class IdCheckedAction implements Action {

	private MemberDAO dao = new MemberDAO(); //Id를 체크할 MomberDAO
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("UTF-8");
			String id = req.getParameter("id");
			System.out.println("id :" + id);
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain"); // 미디어 유형 설정
			resp.getWriter().write(String.valueOf(dao.isMemberById(id)));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
