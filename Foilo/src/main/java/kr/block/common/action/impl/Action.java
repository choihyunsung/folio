package kr.block.common.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public String excute(HttpServletRequest req, HttpServletResponse resp);
}
