var appControllers = angular.module('appControllers',[]);

appControllers.controller('MainController' ,
function($scope,SessionService,Session,$location,$window){
	
	$scope.currentUser = {};
	$scope.currentUser.name = Session.userName;
	$scope.currentUser.role= Session.userRole;
	
	$scope.logout = function(){
		SessionService.logout(function(){
			$scope.currentUser.refresh();
			$location.path("");
			$window.location.reload();
		});
	};
	
	$scope.currentUser.refresh = function(){
		$scope.currentUser.name = Session.userName;
		$scope.currentUser.role = Session.userRole;
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
				$scope.currentUser.refresh();
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