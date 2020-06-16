package com.koreait.app.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;

public class MemberFrontController extends HttpServlet{
	/**
	 * JVM의 메모리에만 머물러 있던 객체를 시스템이 종료되거나 네트워크로 통신을 할 때에도
	 * 그대로 사용할 수 있도록 영구화 목적으로 직렬화를 사용한다.
	 * 직렬화된 객체는 byte형태로 변환되어 있으며, 다시 사용하고 싶을 때에는 역직렬화를 통해서
	 * 	객체로 변환된다. 이 때 SUID(serialVersionUID)라는 값을 고정시켜 구분점으로 사용해서
	 * 	사용자가 원하는 객체가 맞는지 판단하게 된다.
	 * 자주 변경되는 클래스 객체에는 사용하지 않는 것이 좋다.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/member/MemberJoin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/member/joinForm.jsp");
		}else if(command.equals("/member/MemberJoinOk.me")){
			action = new MemberJoinOkAction();
			
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				System.out.println(e);
			}
		}else if(command.equals("/member/MemberCheckIdOk.me")) {
			action = new MemberCheckIdOkAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				System.out.println(e);
			}
		}else if(command.equals("/member/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/member/loginForm.jsp");
		}else if(command.equals("/member/MemberLoginOk.me")) {
			action = new MemberLoginOkAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				System.out.println(e);
			}
		}else if(command.equals("/member/MemberLogOut.me")) {
			action = new MemberLogOutAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/error/404.jsp");
		}
		//일괄처리
		if(forward != null) {
			if(forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispather = req.getRequestDispatcher(forward.getPath());
				dispather.forward(req, resp);
			}
		}
	}
}













