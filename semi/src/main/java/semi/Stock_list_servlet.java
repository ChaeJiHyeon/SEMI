package semi;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import semi.Page;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/stock_list.do")
public class Stock_list_servlet extends HttpServlet {
	String path = "index.jsp?inc=semi/";
	StockDao dao;

    public Stock_list_servlet() {
    	dao = new StockDao();
    }
    
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(dao==null) dao = new StockDao();
    	String job = req.getParameter("job");
    	System.out.println("서블렛 불렀졌어~");
    	Page pageVo = new Page();
    	switch(job) {
    	case "stock_select":
    		select(pageVo, req, resp);
    		break;
    	}
    	
	}

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(dao==null) dao = new StockDao();
    	String job = req.getParameter("job");
    	Page pageVo = new Page();
    	pageVo.setFindStr(req.getParameter("findStr"));
		pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
		pageVo.setDate(req.getParameter("date"));
		req.setAttribute("pageVo", pageVo);
		
    	switch(job) {
    	case "stock_select":
    		select(pageVo, req, resp);
    		break;
    	}
    }
    
    public void select(Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(dao==null) dao = new StockDao();
    	List<StockVo> list = dao.select(pageVo);
    	
    	String url = path + "stock_list.jsp";
    	RequestDispatcher rd = req.getRequestDispatcher(url);
    	req.setAttribute("list", list);
    	req.setAttribute("pageVo", pageVo);
    	rd.forward(req, resp);
    }

}
