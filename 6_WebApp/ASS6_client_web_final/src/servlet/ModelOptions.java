package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.DefaultSocketClient;
import exception.AutoException;
import model.Automobile;

@WebServlet(name = "ModelOptions", urlPatterns = "/ModelOptions")
public class ModelOptions extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    public ModelOptions() {
        super();
    }
    
    @Override
	public void init(){
    	
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String modelSelect = request.getParameter("modelSelect");
        PrintWriter out = response.getWriter();
        DefaultSocketClient clientSocket = new DefaultSocketClient("localhost", 8899);
        
        Automobile auto = clientSocket.getModelInfo(modelSelect);
        String docType =
        	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
        	      "Transitional//EN\">\n";
        out.println(docType + "<html>\n<head>");
        out.println("<meta http-equiv=\"Content-Type\" "
        		+ "content=\"text/html; charset=UTF-8\">");
        out.println("<title>Basic Car Choice</title></head>");
        out.println("<body><div>");
        
        out.println("<h1>Basic Car Choice</h1>");
        
        out.println("<form action=\"finalpage.jsp\">"
        		+ "<table action=\"ConfigModel\">");
        out.println("<tr><td class=\"midbold\">Make/Model: </td><td>"
            					+ auto.getName() + "</td></tr>");
        for (int i = 0; i < auto.getOptionSetSize(); i++) {
        	String optSet = auto.getOptionSetName(i);
            out.println("<tr><td class=\"midbold\"><b>" 
            		+ optSet + ": " +"</b></td>");
            out.println("<td><select name=\"" + optSet + "\">");
            
            for(int j = 0; j < auto.getOptionSize(i); j++) {
            	String optionName;
				try {
					optionName = auto.getOptionName(optSet, j);
					out.println("<option value=\"" + optionName + "\">"
										+ optionName + "</option>");
				} catch (AutoException e) {
					e.fix(e.getErrorNo());
				}
            }
            out.println("</select></td>\n</tr>");	
        }
        out.println("<tr><td class=\"input\" colspan=2>"
            		+ "<input type=\"submit\" "
            		+ "value=\"Done\"/></td></tr>");
        out.println("</table>\n</form>\n</div>\n</body>\n</html>");
        HttpSession session = request.getSession(true);
        session.setAttribute("automobile", auto);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}