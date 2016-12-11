package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;

import twittersearch.twitterSearch;

/**
 * Servlet implementation class Interface
 */
@WebServlet("/Interface")
public class Interface extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static twitterSearch ts;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Interface() {
		super();
		if(ts==null)
			ts=new twitterSearch();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session_name=request.getSession();
		String search=request.getParameter("search");
		ArrayList<String> results=ts.tweetSearch(search);
		ArrayList<String> refined_result=new ArrayList<String>();
		for(String s:results)
		{
			refined_result.add(s.replaceAll("[^a-zA-Z0-9:@.]"," "));
			System.out.println(s.replaceAll("[^a-zA-Z0-9]"," "));
		}
		if(results.size()>0) {
			session_name.setAttribute("searchResult", results);
			session_name.setAttribute("refinedResult", refined_result);
		}
		else {
			session_name.setAttribute("searchResult", null);
		}
		session_name.setAttribute("searched", "true");
		response.sendRedirect("index.jsp");
	}

}
