package kr.block.common.action.base;

import kr.block.common.base.dao.BaseDao;
import kr.block.common.base.vo.BaseVo;

/***
 * @author choehyeonseong 
 * 데이터 베이스 접근 가능한 액션 프레임 
 * @param <DAO> 사용할 DAO 객체 
 * @param <VO>  DAO 객체에서 사용될 VO
 */
public abstract class BaseDAOAction<VO extends BaseVo, DAO extends BaseDao<VO>> extends BaseAction{
	protected DAO dao;
}
