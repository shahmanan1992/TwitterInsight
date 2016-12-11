package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import toneAnalyzer.ToneAnalyzer;

	/**
	 * Servlet implementation class GetUserTweet
	 */
	@WebServlet("/GetUserTweet")
	public class GetUserTweet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static ToneAnalyzer ts;
		private String anger="",disgust="",fear="",joy="",sadness="";
		private String openness="",conscientiousness="",extraversion="",agreeableness="",emotional="";

		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public GetUserTweet() {
			super();
			if(ts==null)
				ts=new ToneAnalyzer();

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
			String search=request.getParameter("user_tweet");
			
			HashMap<String,Double> results=ts.toneAnalyze(search);
//			if(results.size()>0) {
//				session_name.setAttribute("Anger", results.get("Anger"));
//				System.out.println("Setting session value: "+session_name.getAttribute("Anger"));
//			}
//			else {
//				session_name.setAttribute("Anger", null);
//				System.out.println("Bye");
//			}
			
			setValues(results);
			response.getWriter().write(anger+","+disgust+","+fear+","+joy+","+sadness+","+openness+","+conscientiousness+","+extraversion+","+agreeableness+","+emotional);
		}
		
		private void setValues(HashMap<String, Double> results)
		{
			anger=String.valueOf(results.get("Anger"));
			disgust=String.valueOf(results.get("Disgust"));
			fear=String.valueOf(results.get("Fear"));
			joy=String.valueOf(results.get("Joy"));
			sadness=String.valueOf(results.get("Sadness"));
			openness=String.valueOf(results.get("Openness"));
			conscientiousness=String.valueOf(results.get("Conscientiousness"));
			extraversion=String.valueOf(results.get("Extraversion"));
			agreeableness=String.valueOf(results.get("Agreeableness"));
			emotional=String.valueOf(results.get("Emotional Range"));
		}

}
