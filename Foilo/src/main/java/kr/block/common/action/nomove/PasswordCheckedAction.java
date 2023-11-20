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
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class PasswordCheckedAction extends BaseDAOAction<MemberVo, MemberDAO>{
	
	public PasswordCheckedAction() {
		dao = new MemberDAO();
	}

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			StringBuilder builder = new StringBuilder();                                                                                                                                   
			BufferedReader bufferedReader = req.getReader();
			String line;
			while((line = bufferedReader.readLine())!= null) {
					builder.append(line);
			}
			System.out.println(builder.toString());
			JSONObject jsonObject = JsonUtils.getStringToJson(builder.toString());
			String id = jsonObject.get("id").toString();
			String password = dao.getPasswordById(id);
			String inputPassword = jsonObject.get("password").toString();		
			JSONObject stateObject = new JSONObject();
			stateObject.put("isPasswordChecked", password.equals(inputPassword)); //로그인 여부 
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(stateObject.toJSONString()); //데이터 보내고 
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
}
