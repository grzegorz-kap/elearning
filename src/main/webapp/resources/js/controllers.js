var appControllers = angular.module('appControllers',[]);

appControllers.controller('MainController' ,
function($scope,SessionService,Session,$location,$window){
	
	$scope.user = {};
	$scope.user.name = null;
	$scope.user.role= null;
	
	$scope.logout = function(){
		SessionService.logout(function(){
			$scope.user.refresh();
			$location.path("");
			$window.location.reload();
		});
	};
	
	$scope.user.refresh = function(){
		$scope.user.name = Session.userName;
		$scope.user.role = Session.userRole;
	}
	
});


appControllers.controller('LoginController',['$scope','SessionService','$location',
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
				$scope.user.refresh();
				$location.path("");
			}	
		});
	};
}]);

appControllers.controller('RegistrationsController', ['$scope','UserService',
                                                      function($scope,UserService){
	$scope.user = {};
	$scope.user.username="grzk695";
	$scope.user.email="grzk@outlook.com";
	$scope.user.password="ciapa";
	$scope.user.passwordConfirm="ciapa";
	
	$scope.register = function(){
		UserService.register($scope.user);
	}
}])