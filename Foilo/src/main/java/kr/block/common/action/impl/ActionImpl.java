package kr.block.common.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;

public interface ActionImpl {
	/***
	 * @TODO HSCHOE 해당 반환값은 뭔가 아직 잘몰라서 이렇게 처리 하였다 (예를들어 떠한 액션이 처리된 이후에 페이지 이동이 안되어야 할수도 있는 상황이 생길수 있
	 * @param req 요쳥 객체 
	 * @param resp 응답 객체 
	 * @return 어떠한 Action이 처리됨에 따라 페이지 이동? 어떠한 처리시에 Url과 페이지 전환 방식을 반환
	 */
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp);
}
