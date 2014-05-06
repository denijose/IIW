
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <style type="text/css">

.item{
    background: #FFFFFF;    
    height: 100% !important;
    width:100%;
    text-align: center !important
}
.slidepic{
text-shadow: none !important;
text-align: center !important;
}
.carousel-control.left, .carousel-control.right {
    background-image: none !important;
}


</style>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>    
<script>
  $(document).ready(function(){
    $('.carousel').carousel();
  });
</script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
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
          ['Admits', 91068],
          ['Rejects', 78856]
        ]);

        // Set chart options
        var options = {'title':'Total Graduate Admits and Rejects in the United States',
                       'width':1200,
                       'height':600};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
        
        var query = new google.visualization.Query('https://docs.google.com/spreadsheets/d/1CxZO1MNZZMdTzrkGG8htzFayeQiXFfhqU4oIWhxqzug/edit?usp=sharing');
        query.send(handleQueryResponse);
        
        var query2 = new google.visualization.Query('https://docs.google.com/spreadsheets/d/1VLNmVDDMtH1JQlSLcornS46TqMgHKuyCXKnKDpIGCX4/edit?usp=sharing');
        query2.send(handleQueryResponse2);
        
      }
      
      function handleQueryResponse(response) {
    	  if (response.isError()) {
    	    alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
    	    return;
    	  }
    	  var options = {'title':'Costliest Courses in the United States',
                  'width':1200,
                  'height':600, colors: ['red'],
                  'chartArea': {'width': '75%', 'height': '50%'},
                   'vAxis':{'slantedText':'true','title':'Fees per Year in Dollars','baseline':0}, 'hAxis': {'slantedText':'true','textStyle':{'fontSize':9}}};

    	  var data = response.getDataTable();
    	  var chart = new google.visualization.ColumnChart(document.getElementById('columnchart'));
    	  chart.draw(data, options);
    	}
      
      function handleQueryResponse2(response) {
    	  if (response.isError()) {
    	    alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
    	    return;
    	  }
    	  var options = {'title':'Cheapest Graduate Courses for Foreign Students',
                  'width':1200,
                  'height':600, colors: ['blue'],
                  'chartArea': {'width': '75%', 'height': '50%'},
                   'vAxis':{'slantedText':'true','title':'Fees per Year in Dollars','baseline':0}, 'hAxis': {'slantedText':'true','textStyle':{'fontSize':9}}};

    	  var data = response.getDataTable();
    	  var chart = new google.visualization.ColumnChart(document.getElementById('cheapchart'));
    	  chart.draw(data, options);
    	}
    </script>
    
        <script type='text/javascript'>
     google.load('visualization', '1', {'packages': ['geochart']});
     google.setOnLoadCallback(drawRegionsMap);

      function drawRegionsMap() {
        var data3 = google.visualization.arrayToDataTable([
          ['State', 'Income'],
          ['California',	762251762],
          ['Michigan',	567882405],
          ['Ohio',	424040180],
          ['Texas',	387347141],
          ['Virginia',	375496337],
          ['Georgia',	371140942],
          ['Florida',	320792976],
          ['Arizona',	289925294],
          ['Illinois',	281964211],
          ['Indiana',	236969324],
          ['Pennsylvania',	229250150],
          ['North Carolina',	226468103],
          ['Colorado',	216190574],
          ['New Jersey',	215374258],
          ['New York',	211599764],
          ['Tennessee',	162211868],
          ['South Carolina',	156745241],
          ['Washington',	154867970],
          ['Minnesota',	153645934],
          ['Kentucky',	142927689],
          ['Iowa',	135269721],
          ['Maryland',	134849011],
          ['Alabama',	129289802],
          ['Wisconsin',	125242185],
          ['Utah',	122300991],
          ['West Virginia',	119830466],
          ['Louisiana',	101591590],
          ['Oregon',	95098266],
          ['Connecticut',	86614322],
          ['Missouri',	78044801],
          ['Oklahoma',	73879823],
          ['Kansas',	69661405],
          ['New Mexico',	55029371],
          ['Nebraska',	46557030],
          ['Arkansas',	45023722],
          ['Nevada',	37144465],
          ['Massachusetts',	31954434],
          ['Mississippi',	30645931],
          ['Vermont',	28114848],
          ['Hawaii',	27750504],
          ['South Dakota',	19001166],
          ['Idaho',	12512698],
          ['Montana',	11166218],
          ['Maine',	9961696],
          ['New Hampshire',	8313600],
          ['Delaware',	6522984],
          ['North Dakota',	6115338],
          ['District Of Columbia',	4022865],
          ['Rhode Island',	2927144],
          ['Wyoming',	1930632]
        ]);

        var options3 = {'region':'US','displayMode':'regions','resolution':'provinces','width':1200};

        var chart3 = new google.visualization.GeoChart(document.getElementById('mapChart'));
        chart3.draw(data3, options3);
    };
    </script>
    
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
        <h1>Analytics</h1>
        <p>Aggregate Data</p>
        <p><fb:login-button show-faces="true" width="2000" max-rows="1"></fb:login-button></p>
        <h4><a href="/IIW2/analytics.jsp"><span class="label label-success label-lg">< Back</span></a></h4>
      </div>
      </div> <!-- /container -->
    
		<div class="container">
		<div class="row">
		</div>
		
<div class="bs-example">
    <div id="myCarousel" class="carousel slide" data-interval="6000" data-ride="carousel">
    	<!-- Carousel indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>   
       <!-- Carousel items -->
        <div class="carousel-inner">
            <div class="active item">              
<div class="row">
		<div class="col-md-6 slidepic">
		<div id="chart_div"></div>
		</div>
		</div>
            </div>
            <div class="item">
        	<div class="col-md-6 slidepic">
			<div id="columnchart"></div>
			</div>
		
            </div>
            <div class="item">
            <div id="cheapchart"></div>
            </div>
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev"><  </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">> </a>
    </div>
</div><br>
<h2 style="text-align:center">Statewise Income from Foreign Engineering Graduate Students</h2>
<div id="row"><div id="mapChart"></div></div>
</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>
