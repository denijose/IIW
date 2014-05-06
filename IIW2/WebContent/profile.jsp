
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
		function setUserURIAndName(){
			var uri = '<%=request.getAttribute("userURI")%>';
		    var name = '<%=request.getAttribute("name")%>';		    
		    document.getElementById('userURIHiddenFieldID').value = uri;
		    document.getElementById('userNameHiddenFieldID').value = name;
		}
</script>
    
  </head>

 
<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->


  <body role="document" onload="setUserURIAndName()">
      


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
        <h1>Hello <%=request.getAttribute("name") %>!</h1>
        <p>Fill your Profile Here YO!!.</p>
			        <form role="form" action="Profile">
							  <div class="form-group">
							    <label for="exampleInputEmail1">GRE Quantitative Score</label>
							    <input type="email" class="form-control" id="GREQScoreID" name="GREQScoreName" placeholder="Enter email">
							  </div>
							  <div class="form-group">
							    <label for="exampleInputEmail1">GRE Verbal Score</label>
							    <input type="email" class="form-control" id="GREVScore" name="GREVScoreName" placeholder="Enter email">
							  </div>
							  <div class="form-group">
							    <label for="exampleInputEmail1">GRE A Score</label>
							    <input type="email" class="form-control" id="GREAScore" name="GREAScoreName" placeholder="Enter email">
							  </div>
							  <div class="form-group">
							    <label for="exampleInputEmail1">Toefl Score</label>
							    <input type="email" class="form-control" id="ToeflScore" name="ToeflScoreName" placeholder="Enter email">
							  </div>
							  <div class="form-group">
							    <label for="exampleInputEmail1">Country of Your Choice</label>
							    <input type="email" class="form-control" id="country" name="countryName" placeholder="Enter email">
							  </div>
							 <div class="form-group">
							    <label for="exampleInputEmail1">Stream of your Choice</label>
							    <input type="email" class="form-control" id="stream" name="streamName" placeholder="Enter email">
							  </div>
							  <input type="hidden" id="userURIHiddenFieldID" name="userURIHiddenFieldName"/>
							   <input type="hidden" id="userNameHiddenFieldID" name="userNameHiddenFieldName"/>
							  <button type="submit" class="btn btn-default" >Submit</button>
			         </form>
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
