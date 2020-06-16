package com.koreait.app.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardBean;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FilesDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardBean bean = new BoardBean();
		BoardDAO b_dao = new BoardDAO();
		FilesDAO f_dao = new FilesDAO();
		
		ActionForward forward = new ActionForward();
		
//		servlet.com/cos : 파일 업로드 라이브러리
//		requset.getServletContext().getRealPath("/") : 서버 경로
		String saveFolder = "D:\\web4_1500_chj\\workspace\\board_mvc2222222\\WebContent\\app\\upload";
		int fileSize = 5 * 1024 * 1024;	//5M
		boolean b_result = false;
		boolean f_result = false;
		
		try {
			MultipartRequest multi = null;
			
			multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			bean.setBoard_title(multi.getParameter("board_title"));
			bean.setBoard_contents(multi.getParameter("board_contents"));
			bean.setMember_id(multi.getParameter("member_id"));
			b_result = b_dao.insertBoard(bean);
			System.out.println("들어옴1");
			System.out.println(b_dao.getBoardSeq());
			f_result = f_dao.insertFiles(multi, b_dao.getBoardSeq());
			System.out.println("들어옴2");
			
			if(!b_result || !f_result) {
				System.out.println("들어옴3");
				PrintWriter out = response.getWriter();
				response.setContentType("text/html;charset=UTF-8");
				out.println("<script>");
				out.println("alert('게시글 등록 실패. 다시 시도해주세요.');");
				out.println("</script>");
				out.close();
				return null;
			}
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardList.bo");
			System.out.println("들어옴4");
			return forward;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
}











