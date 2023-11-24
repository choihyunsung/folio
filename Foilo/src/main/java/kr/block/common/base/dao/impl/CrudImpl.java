package kr.block.common.base.dao.impl;

import java.util.List;

import kr.block.common.base.vo.BaseVo;

public interface CrudImpl<Vo extends BaseVo> {
	public void insert(Vo vo); //삽입.
	public List<Vo> selectAll(); //모든 데이터 반환.
	public void delete(Vo vo); //삭제
	public boolean update(Vo vo); //업데이트	 
}
