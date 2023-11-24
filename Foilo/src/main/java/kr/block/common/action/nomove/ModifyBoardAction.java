package kr.block.common.action.nomove;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import kr.block.common.action.base.BaseDAOAction;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;
import kr.block.common.utils.JsonUtils;
import kr.block.model.member.dao.BoardDAO;
import kr.block.model.member.vo.BoardVo;

//게시판 수정 
public class ModifyBoardAction extends BaseDAOAction<BoardVo, BoardDAO>{
	
	public ModifyBoardAction() {
		this.dao = new BoardDAO();
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
			System.out.println(builder.toString());
			BoardVo boardVo =  JsonUtils.getJsonToVO(builder.toString(), BoardVo.class);
			JSONObject stateObject = new JSONObject();
			stateObject.put("isModifyBoard", dao.update(boardVo)); //로그인 여부 
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain"); // 미디어 유형 설정
			resp.getWriter().write(stateObject.toJSONString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Pair<ResponseMethodType, String>(ResponseMethodType.NONE, "");
	}
}
