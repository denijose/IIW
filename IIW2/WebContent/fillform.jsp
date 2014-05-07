
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
				  //alert(json.table[0].U);
				  showResults(json);
			    }
			  }
			var country = document.getElementById("country").value;
			var state = document.getElementById("state").value;
			var select = document.getElementById("category");
			var category = select.options[select.selectedIndex].value;			
			var q = document.getElementById("Q").value;var v = document.getElementById("V").value;var a = document.getElementById("A").value;var t = document.getElementById("T").value;var b = document.getElementById("B").value;
			var f = document.getElementById("F").value;
			var URL = "SearchServlet?country=" + country + "&state=" + state + "&category=" +category + "&Q=" + q + "&V=" + v + "&A=" + a + "&T=" + t + "&B=" + b + "&F=" + f;
			xmlhttp.open("GET",URL,true);
			xmlhttp.send();
			}
	
			function showResults(json){
			     document.getElementById("tableDiv").innerHTML = "";
				 var table = document.createElement('table');
				 if(json.table.length==0)
				    return;
				 table.className = "table table-striped table-hover";
				 table.innerHTML = "<tr><td></td><td></td><td></td> <td><button id='saveResultBtn' onClick=changeSaveResultBtn() type=\"button\" class=\"btn btn-info\">Save Results</button></td> <td><button id='postToFBBtn' onClick=changepostToFBBtn() type=\"button\" class=\"btn btn-info\">Post To FaceBook</button></td> </tr>";
				 table.innerHTML += "<tr><td>Rank</td><td>University</td><td>Fees</td><td>Admittance Ratio</td> <td></td></tr>";
				for(var i=0;i<json.table.length;i++){		
					 var rank =  json.table[i].rank;
					 var name = json.table[i].name;
					 var University = json.table[i];
					 //var hiddenDetails = document.createElement("INPUT");
					 //hiddenDetails.setAttribute("type","hidden");
					 //var details = University.country+","+University.state+","+University.city+","+University.admits+","+University.U;
					 //hiddenDetails.setAttribute("value",details);
					 //hiddenDetails.setAttribute("id",i);
					 //document.body.appendChild(hiddenDetails);
					 table.innerHTML += "<tr id=\""+i+"_row\" ><td>"+rank+"</td><td>"+name+ "<table id=\""+i+"_table\" style=\"display:none\"  class=\"table table-striped table-hover\"><tr><td>Country: </td><td>"+ University.country +"</td></tr><tr><td>State: </td><td>"+ University.state +"</td></tr><tr><td>City: </td><td>"+ University.city +"</td></tr><tr><td>No. Of Admits: </td><td>"+ University.admits +"</td></tr><tr><td><a href='/IIW2/123961.jsp'>More Info ... </a></td><td></td></tr></table>" +"</td><td>500</td><td>0.45</td><td onClick='showDetails("+i+")'>+</td></tr>";				     
				}
				//table.innerHTML += 
				document.getElementById("tableDiv").appendChild(table);
			}
			
			//window.onload = fillCategories(){
			//		alert('muhahah');
			//}
			
			function addCategoriesAndShootOut()
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
				
				var json = '<%= request.getAttribute("universitiesJSON")%>';
				var universities = JSON.parse(json);
				var newOption = document.createElement("option");
	            document.getElementById("shootOut1").options.add(newOption);
	             newOption.text = "";
	             newOption.value = "empty";	
				for(var i=0;i<universities.length;i++){
					var newOption = document.createElement("option");
	              document.getElementById("shootOut1").options.add(newOption);
	        			newOption.text = universities[i];
	        		newOption.value = universities[i];	
				}
				
	 		}
				
           function showDetails(i){
           	  displayTable = document.getElementById(i+"_table");
           	  if(displayTable.style.display=='none')
           	      displayTable.style.display='block';
           	  else 
           	      displayTable.style.display='none';
           	     
           }
           
           function  changeSaveResultBtn(){
           document.getElementById('saveResultBtn').innerHTML = "Results Saved";
           }
           
           function changepostToFBBtn(){
           }
		</script>

    
  </head>

 

<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->


  <body role="document" onload="addCategoriesAndShootOut();">

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">

        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
                        <li class="active"><a href="/IIW2/">Home</a></li>
            <li><a href="/IIW2/profile.jsp">My Account</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
      Uni Search Page
      <p> Fill up the form below and start searching <p>
      <form id="searchForm" action="/" >
   		 	<div class="row"><div class="col-md-2">Select Country:  </div><div class="col-md-4"> <select id="country" name ="country"></select></div><div class="col-md-2">Select State:  </div><div class="col-md-4"><select name ="state" id ="state"></select>  </div></div> 
					<script language="javascript">
					populateCountries("country","state");
					 </script>
					 </br>
			<div class="row"><div class="col-md-2"> Category </div> <div class="col-md-2"> <select id="category"><option>Computer Science</option></select><br> </div></div>
			</br>
			<div class="row"><div class="col-md-2"> Scores </div>  <div class="col-md-2"> <input id="Q" type="number" class="form-control" placeholder="Gre Quant"> </div>  <div class="col-md-2"> <input id="V" type="number" class="form-control" placeholder="Gre Verbal"> </div> <div class="col-md-2"> <input id="A" type="number" class="form-control" placeholder="GRE Analytics"> </div>  <div class="col-md-2"> <input id="T" type="number" class="form-control" placeholder="Toefl"> </div> <div class="col-md-2"> <input id="B" type="number" class="form-control" placeholder="UnderGrad Score"> </div></div>
			</br>
			<div class="row"><div class="col-md-2"> Fees Range </div>  <div class="col-md-2"> <input id="F" type="number" class="form-control" placeholder="Fees Less Than"> </div> </div>		
			<br>			 
			<div class="row"> <div class="col-md-3"><button type="button" onclick="search()">Search</button></div> </div><br>	 
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
