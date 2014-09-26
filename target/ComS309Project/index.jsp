<html ng-app>
<head>
<script type="text/javascript" src="angular.min.js"></script>
<script type="text/javascript" src="createUser.js"></script>
</head>


<body>
<h1>Hello World!</h1>

We are going to be setting up our REST calls so that they can display important information on a webpage like this.

<div ng-controller="createUser">
	<p>Was the user created? {{greeting.content}}</p>

</div>




</body>
</html>
