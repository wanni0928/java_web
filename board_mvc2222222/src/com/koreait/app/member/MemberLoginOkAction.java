package com.koreait.app.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberDAO;

public class MemberLoginOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		MemberDAO m_dao = new MemberDAO();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		
		Map<String, String>resultMap = m_dao.login(id, pw);
		
		if(resultMap != null) {
			session.setAttribute("session_id", resultMap.get("MEMBER_ID"));
			session.setAttribute("admin", resultMap.get("ADMIN"));
			forward.setPath(request.getContextPath() + "/board/BoardList.bo");
		}else {
			forward.setPath(request.getContextPath() + "/member/MemberLogin.me?login=false");
		}
		forward.setRedirect(true);
		return forward;
	}
}











