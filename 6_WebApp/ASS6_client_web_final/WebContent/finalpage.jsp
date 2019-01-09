<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Receipt</title>
	</head>
	<body>
		<% Automobile auto = (Automobile) request.getSession().getAttribute("automobile"); %>
		Here is what you selected: <br/>
	    <form>
	      <table>
	        <tbody>
	        <tr>
	          <td><%= auto.getName() %></td><td>base price</td>
	          <td class="price"><%= auto.getBasePrice() %></td>
	        </tr>
	          <%
	          	for (int i = 0; i < auto.getOptionSetSize(); i++) {
	          		String optSet = auto.getOptionSetName(i);
	          		String choice = request.getParameter(optSet);
	          		out.println("<tr><td>" + optSet + "</td><td>"
	          					+ choice + "</td><td class=\"price\">"
	          					+ auto.getOptionPrice(choice, optSet)
	          					+ "</td></tr>");
	          		auto.setOptionChoice(optSet, choice);
	          	}
	          %>
	        <tr>
	          <td><b>Total Cost</b></td>
	          <td></td><td class="price"><b>
	          <%= "$ " + auto.getTotalPrice() %></b></td>
	        </tr>
	        </tbody>
	      </table>
	    </form>
	</body>
</html>