package kr.block.common.type;

/*** 
 * 
 * @author user1
 * 특정 서블릿에서 어떠한 정보를 뷰에다 넣을경우 리다이렉트 방식으로 진행할것인지 포워드로 진행할것인지에 대한 타입.
 */

public enum ResponseMethodType {
	Redirect(1), //리다이렉트
	Forward(0); // 포워드 
	
	public int typeCode; //응답 코드의 타입코드 1 : 리다이렉트, 2 : 포워드

	ResponseMethodType(int typeCode) {
		this.typeCode = typeCode;
	}
}
