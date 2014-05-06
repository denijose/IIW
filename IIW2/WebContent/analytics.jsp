
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">
	<style type="text/css"> 
     @font-face {   font-family: 'Glyphicons Halflings';   
     src: url('../fonts/glyphicons-halflings-regular.eot');   
     src: url('../fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'), 
     url('../fonts/glyphicons-halflings-regular.woff') format('woff'),  
     url('../fonts/glyphicons-halflings-regular.ttf') format('truetype'), 
     url('../fonts/glyphicons-halflings-regular.svg#glyphicons-halflingsregular') format('svg'); } 
	</style>
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
    

    
  </head>

 
<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->


  <body role="document">
      <script>
  window.fbAsyncInit = function() {
  FB.init({
    appId      : '631162070309479',
    status     : true, // check login status
    cookie     : true, // enable cookies to allow the server to access the session
    xfbml      : true  // parse XFBML
  });
  FB.Event.subscribe('auth.authResponseChange', function(response) {
    if (response.status === 'connected') {    	
    	//document.location.href = '/IIW2/Form';       	
  	  var id;
	  var params = {};
	  FB.api('/me', function(response) {	    
		  
	    	  params['userName'] = response.name; 
		  	  params['email'] = response.email;
		  	  id = response.id;
		  	  var user = 'https://graph.facebook.com/' + id;
		  	  params['userURI'] = user; 
		  	  FB.api(user, function(response2) {				  
			  	  params['firstName'] = response2.first_name; 
			  	  params['lastName']= response2.last_name;		;
			  	post_to_url('/IIW2/Form',params,'Post');
		      });	
	        }	  
	   );	    	
    	//post_to_url('/IIW2/Form',params,'Post');
    } else if (response.status === 'not_authorized') {
      FB.login();
    } else {
      FB.login();
      document.location.href = '/IIW/Form';
    }
  });
  };

  // Load the SDK asynchronously
     (function(d, s, id){
         var js, fjs = d.getElementsByTagName(s)[0];
         if (d.getElementById(id)) {return;}
         js = d.createElement(s); js.id = id;
         js.src = "//connect.facebook.net/en_US/all.js";
         fjs.parentNode.insertBefore(js, fjs);
       }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is successful. 
  // This testAPI() function is only called in those cases. 
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Good to see you, ' + response.name + '.');
    });
  }
  
  function post_to_url(path, params, method) {
	    method = method || "post"; // Set method to post by default if not specified.
	    var form = document.createElement("form");
	    form.setAttribute("method", method);
	    form.setAttribute("action", path);

	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);
	            form.appendChild(hiddenField);
	         }
	    }
	    document.body.appendChild(form);
	    form.submit();
	}
</script>


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
        <h1>Analytics</h1>
        <p>Please select the type of analysis.</p>
        <p><fb:login-button show-faces="true" width="2000" max-rows="1"></fb:login-button></p>
      </div>
      </div> <!-- /container -->
      <div class="container">
      <h4><a href="/IIW2/"><span class="label label-success label-lg">< Back</span></a></h4>
      </div><br>
		<div class="container">
		<div class="row">
		<div class="col-md-6" style="text-align:center">
		
		<button class="btn btn-primary btn-lg btn-block" onClick="window.location='/IIW2/shootout.jsp'">University Shoot Out</button>
		</div>
		<div class="col-md-6" style="text-align:center">
		<button class="btn btn-primary btn-lg btn-block" onClick="window.location='/IIW2/aggregate.jsp'">Aggregate Data</button>
		</div>
		</div>
		</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="./dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
  </body>
</html>
