package kr.block.common.action.nomove;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseAction;
import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.action.impl.ActionImpl;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class IdCheckedAction extends BaseDAOAction<MemberVo, MemberDAO> {

	public IdCheckedAction() {
		this.dao = new MemberDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
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
		
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

}
