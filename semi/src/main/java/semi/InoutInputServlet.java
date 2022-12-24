package semi;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inoutInput.do")
public class InoutInputServlet extends HttpServlet{
	String path = "index.jsp?inc=semi/";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String url = "";
		
		 	String ioCode = req.getParameter("inout_input_ioCode");
			String Date = req.getParameter("inout_input_date");
			String combi = req.getParameter("inout_input_combiList");
			String cont= req.getParameter("inout_input_contList");
			String fish = req.getParameter("inout_input_fishList");
			String amt = req.getParameter("inout_input_amt");
			
			String combiName = combi.split("-")[0];
			String combiCode = combi.split("-")[1];
			String contName = cont.split("-")[0];
			String contCode = cont.split("-")[1];
			String fishName = fish.split("-")[0];
			String fishCode = fish.split("-")[1];
			
			
			FishVo fishVo = new FishVo();
			fishVo.setIo_code(ioCode);
			fishVo.setIo_date1(Date);
			fishVo.setIo_combiName(combiName);
			fishVo.setIo_combiCode(combiCode);
			fishVo.setIo_contCode(contCode);
			fishVo.setIo_contName(contName);
			fishVo.setIo_fishName(fishName);
			fishVo.setIo_fishCode(fishCode);
			fishVo.setIo_amt(amt);
			fishVo.setIo_name((ioCode.equals("1") ? "입고" : "출고"));
			
			
			InoutInputDAO inputDAO = new InoutInputDAO();
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			
			
			boolean isInserted =inputDAO.inout_insert(fishVo);
			if(isInserted) {
				inputDAO.input_updateStock(fishVo);
				
				out.print("<script>");
				out.print("		alert('입력 완료!');");
				out.print(" 	history.back();");
				out.print("</script>");
				out.flush();
				url = path + "inout_input.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
				
			} else {
				out.print("<script>");
				out.print("		alert('입력 중 오류 발생!');");
				out.print(" 	history.back();");
				out.print("</script>");
				out.flush();
			}
			
			out.close();
			
			
	}
	
}
