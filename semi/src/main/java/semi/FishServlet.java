package semi;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/fish.do")
public class FishServlet extends HttpServlet{
	String path="index.jsp?inc=semi/";
	InoutModifyDAO modifyDAO;
	RequestDispatcher rd;
	FishDao dao;
	FishVo fVo;
	Page pageVo;
	String idx = "";
	
	boolean isStockUpdated = false;
	boolean stock_result = false;
	
	public FishServlet() {
		dao= new FishDao();
		modifyDAO = new InoutModifyDAO();
		System.out.println("servlet");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao==null) dao = new FishDao();

		String job = req.getParameter("job");
		String io_code = req.getParameter("io_code");
		System.out.println("get"+job);
		
		Page pageVo = new Page();
		fVo = new FishVo();

	      if(req.getParameter("nowPage")==null) {
	    	  pageVo.setNowPage(pageVo.getNowPage());  
	      }else {
	    	  pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
	      }
	      req.setAttribute("pageVo", pageVo); 
	      
			//if(io_code!=null)
		System.out.println(pageVo.getNowPage());

		switch(job) {
			case "io_select":			
				System.out.println("get"+io_code);
				fVo.setIo_code(io_code);    
				inout_select(pageVo,fVo,req,resp);
				break;
		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String job = req.getParameter("job");
		System.out.println("post"+job);
		fVo = new FishVo();

	      Page pageVo = new Page();	  
	      if(req.getParameter("nowPage")=="") {
	    	  pageVo.setNowPage(pageVo.getNowPage());  
	      }else {
	    	  pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
	      }
	      if(req.getParameter("findStr")!=null)
	    	  pageVo.setFindStr(req.getParameter("findStr"));
	      req.setAttribute("pageVo", pageVo); 
	      
	      if(req.getAttribute("fVo")!=null) {
	    	  fVo = (FishVo)req.getAttribute("fVo");
	    	  req.setAttribute("fVo", fVo);
	      }
		switch(job) {
		case "stats_select":
			fVo.setIo_code(req.getParameter("io_code"));
			if(req.getParameter("io_combiName")!=null)
				fVo.setIo_combiName(req.getParameter("io_combiName"));
			if(req.getParameter("io_contName")!=null)
				fVo.setIo_contName(req.getParameter("io_contName"));
			if(req.getParameter("io_combiName")!=null)
				fVo.setIo_combiName(req.getParameter("io_combiName"));
			if(req.getParameter("io_fishName")!=null)
				fVo.setIo_fishName(req.getParameter("io_fishName"));
			if(req.getParameter("io_type")!=null)
				fVo.setIo_type(req.getParameter("io_type"));
			if(req.getParameter("io_date1")!=null)
				fVo.setIo_date1(req.getParameter("io_date1"));
			if(req.getParameter("io_date2")!=null)
				fVo.setIo_date2(req.getParameter("io_date2"));
			req.setAttribute("fVo", fVo);
			
			stats_select(pageVo, fVo, req, resp);
			break;
		
		case "ioSelect":
			if(modifyDAO == null) modifyDAO = new InoutModifyDAO();
			
			idx = req.getParameter("idx");
			
			String url = path + "inout_modify.jsp";
			fVo = modifyDAO.selectOne(idx);	
			
			req.setAttribute("fVo", fVo);
			req.setAttribute("pageVo", pageVo);
			
			rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
			
			break;
			
		case "ioUpdateR":
			if(modifyDAO == null) modifyDAO = new InoutModifyDAO();
			
			int preAmt = Integer.parseInt(req.getParameter("inout_modify_preAmt"));
			String Amt = req.getParameter("inout_modify_amt");
			String date = req.getParameter("inout_modify_date");
			String combi = req.getParameter("inout_modify_combiList");
			String cont = req.getParameter("inout_modify_contList");
			String fish = req.getParameter("inout_modify_fishList");
			
			
			fVo = modifyDAO.selectOne(idx);
			fVo.setIo_date1(date);
			fVo.setIo_combiName(combi.split("-")[0]);
			fVo.setIo_combiCode(combi.split("-")[1]);
			fVo.setIo_contName(cont.split("-")[0]);
			fVo.setIo_contCode(cont.split("-")[1]);
			fVo.setIo_fishName(fish.split("-")[0]);
			fVo.setIo_fishCode(fish.split("-")[1]);
			fVo.setIo_amt(Amt);
			
			
			boolean isUpdated = modifyDAO.update(fVo);
			
			if(isUpdated) {
				isStockUpdated  = modifyDAO.updateStock(fVo, preAmt);
				if(isStockUpdated ) {
					System.out.println("수정 완료!");
					inout_select(pageVo,fVo,req,resp);
				}
			} else {
				System.out.println("수정 실패!");
			}
			
			
			break;
			
		case "ioDelete":			
			if(modifyDAO == null) modifyDAO = new InoutModifyDAO();
			
			idx = req.getParameter("inout_modify_index");
			
			fVo = modifyDAO.selectOne(idx);
			boolean isDeleted = modifyDAO.delete(fVo);
		
			if(isDeleted){
				isStockUpdated  = modifyDAO.deleteStock(fVo);
				if(isStockUpdated) {
					System.out.println("삭제 성공!");
					inout_select(pageVo,fVo,req,resp);
				}
			} else {
				System.out.println("삭제 실패!");
			}
			
			break;
			
		case "io_select":
			System.out.println("post case"+(String)req.getParameter("findDate"));
	    	if(req.getParameter("findDate")!=null);
				pageVo.setDate((String)req.getParameter("findDate"));
			System.out.println("post case"+req.getParameter("io_code"));
			fVo.setIo_code(req.getParameter("io_code"));
			req.setAttribute("fVo", fVo);
		    inout_select(pageVo,fVo,req,resp);
			break;

		}
		
	}

	


	   public void inout_select(Page pageVo, FishVo fvo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      if(dao==null) dao=new FishDao();
	      List<FishVo> list = dao.inout_select(pageVo, fVo);
	      System.out.println(fVo.getIo_code());
	      String url = path + "inout_list.jsp&io_job="+fVo.io_code;
	      System.out.println(url);
	      RequestDispatcher rd=req.getRequestDispatcher(url); 

	      req.setAttribute("list", list);

	      rd.forward(req, resp);
	   }

	   
	   public void stats_select(Page pageVo, FishVo fVo,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      if(dao==null) dao=new FishDao();
	      List<FishVo> list = dao.stats_select(pageVo, fVo);
	      System.out.println(pageVo.getNowPage());

	      String url = path + "inout_stats2.jsp&io_job="+fVo.io_code;
	      System.out.println(url);
	      RequestDispatcher rd=req.getRequestDispatcher(url); 

	      System.out.println(pageVo.getEndPage());
	      System.out.println(pageVo.getNowPage());

	      req.setAttribute("fVo",fVo);
	      req.setAttribute("pageVo", pageVo);
	      req.setAttribute("list", list);
	      rd.forward(req, resp);
	   }
}
