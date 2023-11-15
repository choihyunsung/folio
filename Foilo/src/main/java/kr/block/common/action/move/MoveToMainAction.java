package kr.block.common.action.move;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.action.base.BaseAction;
import kr.block.common.object.Pair;
import kr.block.common.prop.UrlProperties;
import kr.block.common.type.ResponseMethodType;

/***
 * @author choehyeonseong 메인 페이지로 이동하기 위한 액션 
 */
public class MoveToMainAction extends BaseAction{

	@Override
	public Pair<ResponseMethodType, String> excute(HttpServletRequest req, HttpServletResponse resp) {
		return new Pair<ResponseMethodType, String>(ResponseMethodType.Redirect, UrlProperties.URL_MAIN_PAGE);
	}

}
