package kr.block.common.action.nomove;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.MemberDAO;
import kr.block.model.member.vo.MemberVo;

public class DeleteAccountAction extends BaseDAOAction<MemberVo, MemberDAO>{
	
	//HSCHOE 자바에서는 매개변수에 대한 초기화작업을 허용하지 않기 때문에 생성자를 만들어서 직접 객체를 생성해줘야한다. 
	public DeleteAccountAction() {
		this.dao = new MemberDAO();
	}
	
	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("회원탈퇴 액션 진입");
		try {
			StringBuilder builder = new StringBuilder();                                                                                                                                   
			BufferedReader bufferedReader;
			bufferedReader = req.getReader();
			String line;
			while((line = bufferedReader.readLine())!= null) {
					builder.append(line);
			}
			MemberVo memberInfo = JsonUtils.getJsonToVO(builder.toString(), MemberVo.class);
			System.out.println(memberInfo.getCno());
			if(!memberInfo.getId().equals("test")) { //FIXME  HSCHOE테스트 계정 통과로 나중에 제거 해야함 .
				dao.delete(memberInfo);
			}
			//TODO HSCHOE 모든 세션을 그냥 날려버리는게 맞는것인지 질문 하기 로희나 강사님에게  !
			req.getSession().invalidate(); //회원 탈퇴후 세션 날림.
			JSONObject stateObject = new JSONObject();
			stateObject.put("isDeleteAccount", true); //로그인 여부 
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(stateObject.toJSONString()); //데이터 보내고 
			//TODO HSCHOE 모든 세션을 그냥 날려버리는게 맞는것인지 질문 하기 로희나 강사님에게  !
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}

}
