var appControllers = angular.module('appControllers',[]);


appControllers.controller('LoginController',['$scope','UserService',function($scope,UserService){
	$scope.user = {};
	$scope.user.username = "";
	$scope.user.password = "";
	$scope.user.rememberMe = false;
	
	$scope.login = function(){
		UserService.login($scope.user);
	};
}]);