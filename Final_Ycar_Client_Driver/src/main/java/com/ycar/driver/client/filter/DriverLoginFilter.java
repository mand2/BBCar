package com.ycar.driver.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

public class DriverLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);
		
		boolean loginCheck = false; //로그인 되어있는지 확인하는 변수
		
		if(session != null && session.getAttribute("loginInfo") != null) {
			loginCheck = true;
		}
		
		
		//로그인 되어있으면
		if(loginCheck) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/login");
		}
		
	}

	@Override
	public void destroy() {

	}

}
