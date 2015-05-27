
angular.module('elearning.Controllers')
.controller('LoginController',['$scope','SessionService','$location',
                                             function($scope,SessionService,$location){
	$scope.credentials = {};
	$scope.credentials.username = "grzk695";
	$scope.credentials.password = "ciapa";
	$scope.credentials.rememberMe = false;
	
	$scope.login = function(){
		SessionService.login($scope.credentials,function(response){
			if(response.error)
				alert("Niepoprawny login lub hasło.");
			else{
				$scope.currentUser.refresh();
				$location.path("");
			}	
		});
	};
}]);