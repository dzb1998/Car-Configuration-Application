package servlet;

import client.DefaultSocketClient;
import exception.AutoException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapter.BuildAuto;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ModelList", urlPatterns = "/ModelList")
public class ModelList extends HttpServlet {

	private static final long serialVersionUID = 1L;
		
    public ModelList() {
        super();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();
    	DefaultSocketClient clientSocket = new DefaultSocketClient("localhost", 8899);
        String[] modelArrays = clientSocket.getModelList().split("\n");
        String docType =
      	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      	      "Transitional//EN\">\n";
        out.println(docType + "<html>\n<head>");
        out.println("<meta http-equiv=\"Content-Type\" "
        		+ "content=\"text/html; charset=UTF-8\">");
        
        out.println("<title>Car Model List</title></head>");
        out.println("<body><h1>Car Configuration</h1>");
        
        out.println("<label>Please select a model from the list:</label>");
        out.println("<form action=\"ModelOptions\">");
        out.println("<select name=modelSelect>");
        
        for (String model : modelArrays) {
            out.println("<option value=\"" + model + "\"/>" + model + "</option>");
        }
        
        out.println("</select>");
        out.println("<input type=\"submit\" value=\"Submit\"/>");
        out.println("</form>\n</body>\n</html>");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
