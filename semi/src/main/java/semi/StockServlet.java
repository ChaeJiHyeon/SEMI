package semi;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import semi.StockDao;
import semi.StockVo;


@WebServlet(urlPatterns = "/stock.do")
public class StockServlet extends HttpServlet{
   String path = "index.jsp?inc=semi/";
   StockDao dao;
   StockVo sVo = new StockVo();

   public StockServlet() {
      dao = new StockDao();
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao==null) dao = new StockDao();
      String job = req.getParameter("job");
      System.out.println("job : " + job);
      
      switch(job){
      case "change_main":
         change_select_all(req, resp);
         break;
      }
   }   
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String job = req.getParameter("job");
      System.out.println("job : " + job);
      
      sVo.setStock_fishName(req.getParameter("stock_fishName"));
      sVo.setStock_date1(req.getParameter("stock_date1"));
      sVo.setStock_date2(req.getParameter("stock_date2"));
      sVo.setStock_type(req.getParameter("stock_type"));
      req.setAttribute("sVo", sVo);
      
      switch(job) {
      case "change_select":
         change_select(sVo, req, resp);
         break;
      }
   }

   private void change_select_all(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao == null) dao = new StockDao();
      List<StockVo> list = dao.change_select_all();
      
      String url = path + "stock_change.jsp";
      RequestDispatcher rd = req.getRequestDispatcher(url);
      req.setAttribute("list", list);
      req.setAttribute("sVo", sVo);
      
      rd.forward(req, resp);
   }
   
   private void change_select(StockVo sVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao == null) dao = new StockDao();
      List<StockVo> list = dao.change_select(sVo);
      
      String url = path + "stock_change.jsp";
      RequestDispatcher rd = req.getRequestDispatcher(url);
      req.setAttribute("list", list);
      req.setAttribute("sVo", sVo);
      
      rd.forward(req, resp);
   }
}