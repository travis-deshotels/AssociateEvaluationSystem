var app = angular.module('myApp', []);
app.controller('Controller', function($scope, $http) {
	
	$http.get("http://localhost:8080/core/user")
	.then(function(response) {
		console.log("INSIDE RESPONSE TO /USER");
		console.log(response.data);
	})
});