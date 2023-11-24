package kr.block.model.member.vo;

import java.sql.Date;

import kr.block.common.base.vo.BaseVo;
import lombok.Getter;

@Getter
public class BoardVo extends BaseVo{
	private int no; //글번호 
	private int cno; //작성자 회원번호 (fk)
	private Date date; //작성일 
	private String title; //제목 
	private String content; //내용
	
	public BoardVo(int no, int cno, Date date, String title, String content) {
		super();
		this.no = no;
		this.cno = cno;
		this.date = date;
		this.title = title;
		this.content = content;
	}
}
