package com.koreait.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardBean;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FilesBean;
import com.koreait.app.board.dao.FilesDAO;

public class BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO b_dao = new BoardDAO();
		FilesDAO f_dao = new FilesDAO();
		BoardBean b_bean = new BoardBean();
		
		int board_num = Integer.parseInt(request.getParameter("seq"));
		
		b_dao.updateReadCount(board_num);
		b_bean = b_dao.getDetail(board_num);
		List<FilesBean> filesBeanList = f_dao.getDetail(board_num);
		
		if(b_bean != null) {
			request.setAttribute("boardBean", b_bean);
			if(filesBeanList!=null) {
				request.setAttribute("filesBeanList", filesBeanList);
			}
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/board/boardView.jsp");
//			System.out.println("게시판 뷰!" + "제목 : " + b_bean.getBoard_title() + " 내용 : " + b_bean.getBoard_contents());
			return forward;
		}
		
		return null;
	}

}
