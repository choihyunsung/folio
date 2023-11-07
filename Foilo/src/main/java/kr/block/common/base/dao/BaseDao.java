package kr.block.common.base.dao;

import kr.block.common.base.dao.impl.CrudImpl;
import kr.block.common.base.vo.BaseVo;

/***
 *
 * @author user1
 * HSCHOE [23.08.18] 
 * 모든 DAO의 부모 
 * 기본으로 CrudImpl을 상속받음 
 * @param <VO> 해당 Dao가 처리할 VO 클래스 선언 
 */
public abstract class BaseDao<VO extends BaseVo> implements CrudImpl<VO>{}
