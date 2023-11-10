package kr.block.common.action.nomove;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.block.common.action.base.BaseAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;

public class LogoutAction extends BaseAction {

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
		//TODO HSCHOE 모든 세션을 그냥 날려버리는게 맞는것인지 질문 하기 로희나 강사님에게  !
		req.getSession().invalidate();
		//TODO HSCHOE 모든 세션을 그냥 날려버리는게 맞는것인지 질문 하기 로희나 강사님에게  !
		JSONObject stateObject = new JSONObject();
		stateObject.put("isLogout", true); //로그인 여부 
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.getWriter().write(stateObject.toJSONString()); //이건 맨날 보내야하나. -.- 어휴 ...
		} catch (IOException e) {
			e.printStackTrace();
		} //데이터 보내고 

		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
}
