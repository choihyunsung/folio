package kr.block.common.action.nomove;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.StringUtils;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class JoinAction extends BaseDAOAction<MemberVo, MemberDAO> {

	public JoinAction() {
		this.dao = new MemberDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
		StringBuilder builder = new StringBuilder();                                                                                                                                   
		BufferedReader bufferedReader;
		bufferedReader = req.getReader();
		String line;
		while((line = bufferedReader.readLine())!= null) {
				builder.append(line);
		}
		JSONObject memberJson = StringUtils.getStringToJson(builder.toString());
		MemberDAO dao = new MemberDAO();
		dao.insert(parserVo(memberJson));
		//FIXME HSCHOE해당 코드 false일때 예외 처리 해야하지만 지금 급해서 그냥 둠 나중에 무조건 처리하도록 
		JSONObject stateObject = new JSONObject();
		stateObject.put("isJoin", true); //로그인 여부 위에서 예외처리 안일어났으면 성공한거이므로 ture를 전송 
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(stateObject.toJSONString());
		//FIXME HSCHOE해당 코드 false일때 예외 처리 해야하지만 지금 급해서 그냥 둠 나중에 무조건 처리하도록 
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

	private MemberVo parserVo(JSONObject memberJson) {
		String id = memberJson.get("id").toString(); // -> (PK)auto increment
		String password = memberJson.get("password").toString();
		String cstNm = memberJson.get("name").toString();
		String gender = memberJson.get("gender").toString();
		String email = memberJson.get("email").toString();
		String address = memberJson.get("address").toString();
		String aboutMe = memberJson.get("aboutMe").toString();
		return new MemberVo(0, id, password, cstNm, gender, email, address, aboutMe);
	}
}
