package kr.block.common.utils;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import kr.block.common.base.vo.BaseVo;

/***
 * 싱글톤이 아니라 그냥 단순히 스트링을 반환하는 메소드만을 모와놓은 스택틱 클래스로 사용함.
 *
 * @author choehyeonseong
 *
 */
public class JsonUtils {
	/***
	 * @param json JSON 형식의 포멧으로된 String
	 * @return JsonObect 반환
	 * @throws ParseException 파싱 에러 처리 하도록 !
	 */
	public static JSONObject getStringToJson(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		Object obj;
		obj = jsonParser.parse(json);
		return (JSONObject)obj;
	}
	/***
	 * 
	 * @param vo JSONString 으로 변환할 vo
	 * @return 해당 vo를 json String으로 변환.
	 */
	public static JSONObject getVoJson(BaseVo vo) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return getStringToJson(mapper.writeValueAsString(vo)); 
		} catch (ParseException | JsonProcessingException e) {
			e.printStackTrace();      
			return null;
		}       
	}
	
	/***
	 * 
	 * @param <VO> 변경될 VO타
	 * @param json 해당 VO
	 * @param cls  변경될 VO 객체에 Class
	 * @return
	 */
	public static<VO extends BaseVo> VO getJsonToVO(String json, Class<VO> cls) {
		Gson gson = new Gson();
		return gson.fromJson(json.toString(),cls); 
	}
	
	/**
	 * @param list JSON으로 변환할 리스트 
	 * @return 특정 리스트를 JSON파라메터로 변환한다.
	 */
	public static String ListToJsonArray(List<?> list) {
		Gson gson = new Gson();
		return gson.toJson(list, List.class);
	}
}
