
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">

    <title>Higher Education Leverage Program</title>

    <!-- Bootstrap core CSS -->
    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="http://getbootstrap.com/dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/theme/theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="./assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type= "text/javascript" src = "countries.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<script>
		
			function search()
			{
			var xmlhttp;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    //alert(xmlhttp.responseText);
				  var json = JSON.parse(xmlhttp.responseText);
				  //alert(json.Details[0].Rank);
				  showResults(json);
			    }
			  }
			var country = document.getElementById("country").value;
			var select = document.getElementById("category");
			var category = select.options[select.selectedIndex].value;
			var fees = document.getElementById("feesRange");
			var gre = document.getElementById("GREScore");
			var URL = "SearchServlet?country=" + country +"&category=" +category + "&feesRange=" + fees + "&GREScoreRange=" + gre;
			xmlhttp.open("GET",URL,true);
			xmlhttp.send();
			}
	
			function showResults(json){
			     document.getElementById("tableDiv").innerHTML = "";
				 var table = document.createElement('table');
				 table.className = "table table-striped table-hover";
				 table.innerHTML = "<tr><td>Rank</td><td>University</td><td>Details</td></tr>";
				for(var i=0;i<json.Details.length;i++){		
					 var rank =  json.Details[i].Rank;
					 var University = json.Details[i].University;
					 var hiddenDetails = document.createElement("INPUT");
					 hiddenDetails.setAttribute("type","hidden");
					 var details = University.name+","+University.state+","+University.city+","+University.numOfWaiting+","+University.numOfAdmits+","+University.numOfRejects+","+University.URI;
					 hiddenDetails.setAttribute("value",details);
					 hiddenDetails.setAttribute("id",University.URI);
					 document.body.appendChild(hiddenDetails);
					 table.innerHTML += "<tr><td>"+rank+"</td><td>"+University.name+"</td><td  onClick='showDetails("+University.URI+");'><img src='adsds'></td></tr>";				     
				}
				//table.innerHTML += 
				document.getElementById("tableDiv").appendChild(table);
			}
			
			//window.onload = fillCategories(){
			//		alert('muhahah');
			//}
			
			function addCategories()
          {          
				var json = '<%= request.getAttribute("categoryJSON")%>';
				var categories = JSON.parse(json);
				var newOption = document.createElement("option");
	            document.getElementById("category").options.add(newOption);
	             newOption.text = "";
	             newOption.value = "empty";	
				for(var i=0;i<categories.length;i++){
					var newOption = document.createElement("option");
	              document.getElementById("category").options.add(newOption);
	        			newOption.text = categories[i];
	        		newOption.value = categories[i];	
				}
	 		}
				
           function showDetails(University){           	
           	alert(document.getElementById("u").value);
           }
		</script>

    
  </head>

 

<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->


  <body role="document" onload="addCategories();">

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">

        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
      Welcome <%=request.getAttribute("name")%>
      <p> Start Searching <p>
      <form id="searchForm" action="/" >
   		 	Select Country:   <select id="country" name ="country"></select>
					<script language="javascript">
					populateCountries("country");
					 </script>
					 <br>
			GRE Score Range <select id="GREScore"> 
							  <option value="1">290-300</option>
							  <option value="2">290-300</option>
							  <option value="3">290-300</option>
							  <option value="4">290-300</option>
					          </select><br>
			Category <select id="category"></select><br>		
			Fees Range 	<input id="feesRange" type="text" name="fees"><br>	<br>			 
			<button type="button" onclick="search()">Searcho</button>		 
			<input type="submit" value="Search">		 
</form>  
<br><br>

<div id="tableDiv"></div>
       
      </div>
      </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="./dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
  </body>
</html>
