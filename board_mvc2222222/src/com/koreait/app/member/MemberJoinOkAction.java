package com.koreait.app.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberBean;
import com.koreait.app.member.dao.MemberDAO;

public class MemberJoinOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		MemberDAO m_dao = new MemberDAO();
		MemberBean member = new MemberBean();
		ActionForward forward = new ActionForward();
		boolean check = false;
		
		member.setMember_id(request.getParameter("member_id"));
		member.setMember_pw(request.getParameter("member_pw"));
		member.setMember_name(request.getParameter("member_name"));
		member.setMember_age(Integer.parseInt(request.getParameter("member_age")));
		member.setMember_gender(request.getParameter("member_gender"));
		member.setMember_email(request.getParameter("member_email"));
		member.setMember_zipcode(request.getParameter("member_zipcode"));
		member.setMember_address(request.getParameter("member_address"));
		member.setMember_address_detail(request.getParameter("member_address_detail"));
		member.setMember_address_etc(request.getParameter("member_address_etc"));
		
		check = m_dao.join(member);
		
		if(!check) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("alert('회원가입 실패. 잠시 후 다시 시도해주세요.');");
			out.println("</script>");
			out.close();
		}
		forward.setRedirect(true);
		//Redirect : setPath를 사용할 경우 이전 root경로도 없어지기 때문에
		//반드시 request.getContextPath()를 적어준다.
		//forward : setPath를 사용하면 "/"앞에 root경로가 그대로 전달되기 때문에
		//request.getContextPath()를 적어주면 안된다.
		forward.setPath(request.getContextPath() + "/app/member/loginForm.jsp");
		return forward;
	}
}













