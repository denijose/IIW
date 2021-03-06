
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
    
    <script>
    
	function shootOut()
    {          
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
			
			var newOption2 = document.createElement("option");
	          document.getElementById("shootOut2").options.add(newOption2);
	           newOption.text = "";
	           newOption.value = "empty";	
				for(var i=0;i<universities.length;i++){
					var newOption2 = document.createElement("option");
	            document.getElementById("shootOut2").options.add(newOption2);
	      			newOption2.text = universities[i];
	      		newOption2.value = universities[i];	
				}
		}
    
    
    </script>

    
  </head>

 
<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->


<body role="document" onload="shootOut()"> 

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
        <h1>Analytics</h1>
        <p>University Shoot Out</p>
        
        <h4><a href="/IIW2/analytics.jsp"><span class="label label-success label-lg">< Back</span></a></h4>
        </div>
        
      <form id="searchForm" action="/IIW2/shootOut2.jsp" >	 
			<div class="row">  
			<div class="col-md-6" style="text-align:center"> <select id="shootOut1" style="width:80%"><option>Select University</option></select> </div>	
			 <div class="col-md-6" style="text-align:center"> <select id="shootOut2" style="width:80%"><option>Select University</option></select> </div>
			 </div><br><br>
			 <div class="row" style="text-align:center" >
			 <button class="btn btn-primary btn-lg" "onClick="window.location='/IIW2/shootOut2.jsp">Compare</button>
			 </div>
			 
	</form>  
<br><br>
        
        
      
       <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="./dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
  </body>
</html>
