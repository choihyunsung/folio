package kr.block.common.action.nomove;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class SettingModifyAction extends BaseDAOAction<MemberVo, MemberDAO>{
	
	public SettingModifyAction() {
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
		
			JSONObject stateObject = new JSONObject();
			stateObject.put(
					"isModifyOkay",
					dao.noPasswordDataUpdate(
					jsonObject.get("cno").toString(),
					jsonObject.get("cstNm").toString(),
					jsonObject.get("gender").toString(),
					jsonObject.get("email").toString(),
					jsonObject.get("address").toString(),
					jsonObject.get("aboutMe").toString())
					);
			//@FIXME HSCHOE 나중에 리펙토링 진행 
			//수정된 정보로 세션 갈아엎기
			MemberVo loginMemberVo = dao.getMemberByCno(jsonObject.get("cno").toString());
			loginMemberVo.resetPassword();
			HttpSession session = req.getSession();
			session.setAttribute("memberInfo", JsonUtils.getVoJson(loginMemberVo));
			//수정된 정보로 세션 갈아엎기
			//@FIXME HSCHOE 나중에 리펙토링 진행 수정된 정보로 세션 갈아엎기
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
