package kr.block.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import kr.block.common.action.impl.Action;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.StringUtils;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class LoginAction implements Action {

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			StringBuilder builder = new StringBuilder();                                                                                                                                   
			BufferedReader bufferedReader = req.getReader();
			String line;
			while((line = bufferedReader.readLine())!= null) {
					builder.append(line);
			}
			JSONObject jsonObject = StringUtils.getStringToJson(builder.toString());
			String id = jsonObject.get("id").toString();
			String password = jsonObject.get("password").toString();

			JSONObject stateObject = new JSONObject();
			stateObject.put("isLogin", valudationLogin(id,password)); //로그인 여부 
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(stateObject.toJSONString()); //데이터 보내고 
			//TODO HSCHOE 여기서 세션을 만드는게 맞는건가 ? 아니면 새로 액션만들어서 처리되고 Main으로 이동하는것이 맞는것인지 ?
			//일단은 구현 
			HttpSession session = req.getSession();
			session.setAttribute("userId", id);
			//TODO HSCHOE 여기서 세션을 만드는게 맞는건가 ? 아니면 새로 액션만들어서 처리되고 Main으로 이동하는것이 맞는것인지 ?
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
	
	/**
	 * 
	 * @param id 입력한 아이디 
	 * @param password 입력한 비밀번호 
	 * @return true : 로그인 성공, false : 로그인 실패
	 */
	private boolean valudationLogin(String id, String password) {
		MemberDAO member = new MemberDAO();
		MemberVo memberVo = member.getMemberById(id);		
		if(memberVo != null) {
			return (memberVo.getId().equals(id) && memberVo.getPassword().equals(password));
		}else {
			return false;
		}
	}
}
