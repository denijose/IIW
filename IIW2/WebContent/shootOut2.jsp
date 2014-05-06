
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
        var options = {'title':'Percentage of Admits and Rejects at USC',
                       'width':600,
                       'height':400};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div_usc'));
        chart.draw(data, options);
        
        var data2 = new google.visualization.DataTable();
        data2.addColumn('string','Status');
        data2.addColumn('number','Number of Students');
        data2.addRows([
                       ['Admits',123],
                       ['Rejects',331]
                       ]);
        
        var options2 = {'title':'Percentage of Admits and Rejects at UCLA',
                'width':600,
                'height':400};
        
        var chart = new google.visualization.PieChart(document.getElementById('chart_div_ucla'));
        chart.draw(data2, options2);
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


    
    
    
        <script type='text/javascript' src='https://www.google.com/jsapi'></script>
    <script type='text/javascript'>
      google.load('visualization', '1', {packages:['table']});
      google.setOnLoadCallback(drawTable);
      function drawTable() {
        var data3 = new google.visualization.DataTable();
        data3.addColumn('string', 'University');
        data3.addColumn('number', 'Avg Quant Score');
        data3.addColumn('number', 'Avg Verbal Score');
        data3.addColumn('number', 'Avg Analytical Score');
        data3.addColumn('number', 'Avg TOEFL Score');
        data3.addColumn('number', 'Avg Undergrad Score');
//        data3.addColumn('boolean', 'Full Time Employee');
        data3.addRows([
                       ['University of Southern California', 777.02, 557.02, 4.61, 117.7, 74.47],
                       ['University of California Los Angeles', 797.09, 605.7, 3.8, 101.94, 74.5]
        //  ['Mike',  {v: 10000, f: '$10,000'}, true],
          
        ]);

        var table = new google.visualization.Table(document.getElementById('table_div'));
        table.draw(data3, {showRowNumber: false});
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
        
      	 
			<div class="row">  
			<div class="col-md-6" style="text-align:center"> <select id="shootOut1" style="width:80%"><option>Select University</option></select> </div>	
			 <div class="col-md-6" style="text-align:center"> <select id="shootOut2" style="width:80%"><option>Select University</option></select> </div>
			 </div><br><br>
			 <div class="row" style="text-align:center">
			 <button class="btn btn-primary btn-lg" onClick="window.location='/IIW2/shootOut2.jsp'">Compare</button>
			 </div><br><br>
			 
			 <div class="row">
      	 <div class="col-md-6" style="text-align:center">
			 <h3>Rank 20</h3>
      	 </div>
      	 <div class="col-md-6" style="text-align:center">
			 <h3>Rank 13</h3>
      	 </div>
      	 </div>
			 <div class="row">
			 <div class="col-md-6" style="text-align:center">
			 <div id="chart_div_usc"></div>
			 </div>
			 <div class="col-md-6" style="text-align:center">
			 <div id="chart_div_ucla"></div>
			 </div>
			 </div>
			 <div class="row">
			 <h3>Average Scores of Admitted Students (Selectivity Measure)</h3><br>
			 <div id="table_div"></div></div>
	
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
