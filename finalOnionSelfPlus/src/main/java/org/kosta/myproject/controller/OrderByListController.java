/*
package org.kosta.myproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.model.FilmDAO;
import org.kosta.myproject.model.ReviewVO;
import org.kosta.myproject.vo.Pagination;
import org.springframework.web.servlet.mvc.Controller;

public class OrderByListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		String sort = request.getParameter("sort1");
		String sorton = request.getParameter("sorton");
		String opendate = "openDate";
		String filmName = "filmName";
		String avgStar = "avgStar";
		if(session.getAttribute("sorting")==null) {
			if(sorton!=null) {
				session.setAttribute("sorting", sort);
			}else{
				session.setAttribute("sorting", avgStar);
			}
		}
		if(sorton!=null) {
			session.setAttribute("sorting", sort);
		}
		String sort1 =(String) session.getAttribute("sorting");
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(FilmDAO.getInstance().getTotalPostCount());
		}else {
			pagination=new Pagination(FilmDAO.getInstance().getTotalPostCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.

		if(sort1.equals(opendate)) {
			list = FilmDAO.getInstance().orderByOpenDate(pagination);
		}else if(sort1.equals(filmName)) {
			list = FilmDAO.getInstance().orderByFilmName(pagination);
		}else{
			list = FilmDAO.getInstance().findPostList(pagination);
		}
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", list);
		request.setAttribute("url", "board/list.jsp");
		return "layout.jsp";
	}
}
*/