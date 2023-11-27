package kr.block.model.member.dto;

import java.util.List;

import kr.block.common.base.dto.BaseDTO;
import kr.block.model.member.vo.AuthorWithBoardVo;
import lombok.Getter;

/***
 * @author choehyeonseong
 * 프론트 단에 내려줄 DTO 
 */
@Getter
public class BoardListDTO extends BaseDTO {
	List<AuthorWithBoardVo> boardList; //게시글 리스트
	int totalPage; //토탈 페이지 
	
	public BoardListDTO(List<AuthorWithBoardVo> boardList, int totalPage) {
		this.boardList = boardList;
		this.totalPage =totalPage;
	}
}
