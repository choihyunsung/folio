package kr.block.common.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/***
 * 싱글톤이 아니라 그냥 단순히 스트링을 반환하는 메소드만을 모와놓은 스택틱 클래스로 사용함.
 *
 * @author choehyeonseong
 *
 */
public class StringUtils {
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
}
