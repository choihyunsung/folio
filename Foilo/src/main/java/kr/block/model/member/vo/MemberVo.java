package kr.block.model.member.vo;

import kr.block.common.base.vo.BaseVo;

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
	public int getCno() {
		return cno;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getCstNm() {
		return cstNm;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getAboutMe() {
		return aboutMe;
	}

}
