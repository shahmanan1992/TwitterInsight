<%@page import="java.util.*"%>
<%@page import="toneAnalyzer.*"%>
<%@page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
	
</script>
<title>Twitter Insight</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/dialog.css" />
<link rel="stylesheet" type="text/css" href="css/stars.css" />
</head>
<body>
			<%
				HttpSession output = request.getSession(true);
			%>

	<script type="text/javascript">
		function displayTone(tweet) {

			$.ajax({
				type : "POST",
				url : 'GetUserTweet',
				data : {
					user_tweet : tweet
				},
				success : function(data) {
					var array_data = String(data).split(",");
					$("#Anger_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[0])*80)+'px"></span>');
					$("#Disgust_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[1])*80)+'px"></span>');
					$("#Fear_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[2])*80)+'px"></span>');
					$("#Joy_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[3])*80)+'px"></span>');
					$("#Sadness_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[4])*80)+'px"></span>');
					$("#Openness_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[5])*80)+'px"></span>');
					$("#Conscientiousness_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[6])*80)+'px"></span>');
					$("#Extraversion_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[7])*80)+'px"></span>');
					$("#Agreeableness_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[8])*80)+'px"></span>');
					$("#Emotional_body").html('<span class="stars_tone" style="background-image:url(\'image/stars.png\');width:'+(parseFloat(array_data[9])*80)+'px"></span>');
				}
			});

		
			var winW = window.innerWidth;
			var winH = window.innerHeight;
			var dialogoverlay = document.getElementById('dialogoverlay');
			var dialogbox = document.getElementById('dialogbox');

			dialogbox.style.left = (winW / 2) - (700 * 0.5) + "px";
			dialogbox.style.top = "65px";
			$("#dialogoverlay").fadeIn("slow");
			$("#dialogbox").fadeIn("slow");
			
			

		}

		function dialogDisappear() {
			$("#dialogoverlay").fadeOut("slow");
			$("#dialogbox").fadeOut("slow");
		}
		
		
		
		
	</script>


	<!-- Custom dialog box alert -->
	<div id="dialogoverlay">
		<div id="ajaxGetUserServletResponse">
		</div>
	</div>
	<div id="dialogbox">
		<div id="dialogheader">
			<strong>Tone Analyzer</strong> <span class="closebtn" onclick="dialogDisappear()">&times;</span>
		</div>
		<div id="dialogbody">
			<div id="bodyleft">
				<p><span class="toneheader">Emotion</span></p>
				<ul>	
					<li>
						<span class="tone_heading_left">Anger</span>
						<span id="Anger_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_left">Disgust</span>
						<span id="Disgust_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_left">Fear</span>
						<span id="Fear_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_left">Joy</span>
						<span id="Joy_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_left">Sadness</span>
						<span id="Sadness_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
		
				</ul>
			</div>

			<div id="bodyright">
				<p><span class="toneheader">Social Tendencies</span></p>
				<ul>
					<li>
						<span class="tone_heading_right">Openness</span>
						<span id="Openness_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_right">Conscientiousness</span>
						<span id="Conscientiousness_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_right">Extraversion</span>
						<span id="Extraversion_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_right">Agreeableness</span>
						<span id="Agreeableness_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
					<li>
						<span class="tone_heading_right">Emotional Range</span>
						<span id="Emotional_body"><span class="stars_tone" style="background-image:url('image/stars.png')"></span></span>
					</li>
				
				</ul>
			</div>
		</div>
		<div id="dialogfoot">
		<ul>
			<li><span id="foot_toneheader">Message Tone</span></li>
			<li> &lt; 2 star = not likely present </li>
			<li> &gt; 2 star = likely present </li>
			<li> &gt; 3.5 star = very likely present </li>
		</ul>
		</div>
	</div>


	<!-- Homepage -->
	<div id="page">

		<div id="heading">
			<div>
				<img src="image/twitter.png" height="50" width="50">
			</div>
			<div>
				Twitter Insight
			</div>
		</div>

		<div id="searchbar">
			<div>
				<span id="fix">See what people are talking about...</span>
			</div>
			<form id="searchform" action="${pageContext.request.contextPath}/Interface"
				method="POST">
				<input type="text" name="search"
					placeholder="Search here" /> <button type="submit" id="submit">Search</button>
			</form>
		</div>

		<div id="results">
			<%
				if (output.getAttribute("searchResult") != null) {
			%>
			<span id="twt">Tweets Found<hr></span>
			<%
				ArrayList<String> result = (ArrayList<String>) output.getAttribute("searchResult");
					for (int i = 0; i < result.size(); i++) {
			%>
			
			<span id="tweet">
				<a href='' onclick="javacsript:displayTone('<%=result.get(i)%>');return false"><%=result.get(i)%></a>
			</span>
			<%
				}
			%>
			<%
				} else if (output.getAttribute("searched") != null) {
			%>
			<span id="nothing">
				<p>No results found</p>
			</span>
			<%
				}
			%>
		</div>

	</div>
</body>
</html>