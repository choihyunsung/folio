package kr.block.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.block.common.mapper.ActionMapper;
import kr.block.common.object.Pair;
import kr.block.common.type.ResponseMethodType;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_MAPPING_NAME = "uriMappingConfigLocation"; //web.xml에서 등록되어 있는 Mapping Param name정의 
	private ActionMapper mapper = null; //Action Mapper는 init에서 생성될것임. 
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		mapper = new ActionMapper(getServletContext(), PARAM_MAPPING_NAME);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doProcess(request,response);
	}
	
	/***
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * post /get 방식 둘다 처리 되기 위함임. (service에서 처리되면 안되는지?)
	 */
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String action = RequestURI.substring(contextPath.length());
		System.out.println("Request Uri : " + RequestURI);
		System.out.println("action : " + action);
		request.setCharacterEncoding("UTF-8"); //TODO HSCHOE 한글문자 깨짐 방지. 나중에 공통으로 처리될수 있으면 공통쪽으로 壺峠.
		Pair<ResponseMethodType, String > info = mapper.getAction(action).excute(request, response); //mapper에서 Front단의 uri를 가지고 해당 action의 Excute를 실행한다.
		moveToPage(response,info.getFirst(),info.getSecond()); //만약 페이지 이동이 있을경우 이동 없을 경우 아무것도 않마 .
	}
	
	/***
	 * 
	 * @param response 단순히 응답 객체 
	 * @param respType 리다이렉트냐 포워드냐 열거형 
	 * @param url 이동할 url
	 */
	private void moveToPage(HttpServletResponse response, ResponseMethodType respType, String url) {
		try {
			switch(respType) {
				case Redirect://리다이렉트
					response.sendRedirect(url);
					break;
				case Forward://포워드 방식
					break;
				case NONE: //아무것도 안함 
				default:
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}