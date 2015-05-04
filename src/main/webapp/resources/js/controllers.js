var appControllers = angular.module('appControllers',[]);

appControllers.controller('MainController' ,['$scope','UserService',function($scope,UserService){
	
	$scope.logout = function(){
		UserService.logout();
	};
}]);


appControllers.controller('LoginController',['$scope','UserService',function($scope,UserService){
	$scope.user = {};
	$scope.user.username = "";
	$scope.user.password = "";
	$scope.user.rememberMe = false;
	
	$scope.login = function(){
		UserService.login($scope.user);
	};
}]);

appControllers.controller('RegistrationsController', ['$scope','UserService', function($scope,UserService){
	$scope.user = {};
	$scope.user.username="grzk695";
	$scope.user.email="grzk@outlook.com";
	$scope.user.password="ciapa";
	$scope.user.passwordConfirm="ciapa";
	
	$scope.register = function(){
		UserService.register($scope.user);
	}
}])