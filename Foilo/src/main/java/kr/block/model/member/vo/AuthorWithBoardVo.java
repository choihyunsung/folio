package kr.block.model.member.vo;

import kr.block.common.base.vo.BaseVo;
import lombok.Getter;

/***
 * 작성자를 포함하는 Vo
 * @author choehyeonseong
 * Board -> MemberVo Inner Join 
 */
@Getter
public class AuthorWithBoardVo extends BaseVo{
	private String author;  //작성자 
	private BoardVo boardVo; //게시글 
	
	public AuthorWithBoardVo(String author, BoardVo boardVo) {
		super();
		this.author = author;
		this.boardVo = boardVo;
	}
	
	
}
