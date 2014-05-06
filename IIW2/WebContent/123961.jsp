
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

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Status');
        data.addColumn('number', 'Number of Students');
        data.addRows([
          ['Admits', 1189],
          ['Rejects', 318]
        ]);

        // Set chart options
        var options = {'title':'Percentage of Admits and Rejects',
                       'width':600,
                       'height':600};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>

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
        <h1>University of Southern California</h1>
        <p>Detailed Information</p>
        <p><fb:login-button show-faces="true" width="2000" max-rows="1"></fb:login-button></p>
        <h4><a href="/IIW2/"><span class="label label-success label-lg">< Back</span></a></h4>
      </div>
      </div> <!-- /container -->
      <div class="container">
		<div class="row">
		<div class="col-md-6" style="text-align:center; text-align:justify">
		The University of Southern California (known as USC or SC) is a private, not-for-profit, nonsectarian, research university founded in 1880 with its main campus in Los Angeles, California. As California's oldest private research university, USC has historically educated a large number of the region's business leaders and professionals. In recent decades, the university has also leveraged its location in Los Angeles to establish relationships with research institutions throughout Asia and the Pacific Rim. Reflecting the status of Los Angeles as a global city, USC has the largest number of international students of any university in the United States. In 2011, USC was named among the Top 10 Dream Colleges in the nation. As of 2011, USC enrolls 17,414 students in its four-year undergraduate program. USC is also home to 20,596 graduate and professional students in a number of different programs, including business, law, social work, and medicine. The university has a "very high" level of research activity and received $560.9 million in sponsored research from 2009 to 2010. USC sponsors a variety of intercollegiate sports and competes in the NCAA Pacific-12 Conference. Members of the sports teams, the Trojans, have won 96 NCAA team championships, ranking them third in the nation, and 361 NCAA individual championships, ranking them second in the nation. Trojan athletes have won 287 medals at the Olympic games (135 golds, 87 silvers and 65 bronzes), more than any other U.S. university. If USC were a country, it would rank 12th in most Olympic gold medals.
		</div>
		<div class="col-md-6" style="text-align:center">
		<img src="http://upload.wikimedia.org/wikipedia/en/f/f5/USC_Shield.svg"><br>
		Mascot: Traveller<br>
		School Colour: USC Cardinal & USC Gold<br>
		Location: Los Angeles, California<br>
		</div>
		</div>
		
		<div class="row">
		
		<div class="col-md-6">
		<h3>Departments</h3>
		University of Southern California - Roski School of Fine Arts<br>
University of Southern California - Department of Earth Sciences<br>	
University of Southern California - Department of Mathematics	<br>
University of Southern California - Department of Physics and Astronomy	<br>
University of Southern California - Department of Economics	<br>
University of Southern California - Department of English	<br>
University of Southern California - Department of History	<br>
University of Southern California - Department of Political Science	<br>
University of Southern California - Department of Psychology	<br>
University of Southern California - Department of Sociology	<br>
University of Southern California - Computer Science Department	<br>
University of Southern California (Price) - USC Sol Price School of Public Policy	<br>
University of Southern California - School of Policy, Planning and Development	<br>
University of Southern California - Program of Nurse Anesthesia	<br>
University of Southern California - Division of Occupational Science & Occupational Therapy	<br>
University of Southern California - School of Pharmacy	<br>
University of Southern California - Department of Biokinesiology and Physical Therapy	<br>
University of Southern California (Keck) - Department of Family Medicine	<br>
University of Southern California - School of Social Work	<br>
University of Southern California - Department of Biological Sciences	<br>
University of Southern California - Department of Chemistry	<br>
		
		</div>
		<div class="col-md-6" style="text-align:center">
		<div id="chart_div"></div>
  		
		</div>
		
		</div>
		</div>
<div class="chart_div"></div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="./dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
  </body>
</html>
