package kr.block.model.member.vo;

import kr.block.common.base.vo.BaseVo; 
import lombok.Getter;

@Getter
public class MemberVo extends BaseVo {
	
	private int cno; //회원 정보 (pk)
	private String id; //아이디
	private String password;//패스워드 
	private String cstNm;  //이름
	private String gender; //성별
	private String email;  //이메일
	private String address; //주소 
	private String aboutMe; //자기소개 
	
	public MemberVo(int cno, String id, String password, String cstNm, String gender, String email, String address, String aboutMe) {
		this.cno = cno;
		this.id = id;
		this.password = password;
		this.cstNm = cstNm;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.aboutMe = aboutMe;
	}

	/*VO는 ReadOnly이므로 setter메소드를 제공하지 않음.*/

	/***
	 * 현재 패스워드는 암호화 되어 있지 않고 암호화 되어있어도 프론트단에 내려줄 이유가 없기에 해당 메소드를 만듬.
	 * Setter로 변경 하고 싶으나 VO는 ReadOnly이기에 함수로 제공함.
	 */
	public void resetPassword() {
		this.password ="민감한 부분이라 제거";
	}

}
