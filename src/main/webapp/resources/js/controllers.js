var appControllers = angular.module('appControllers',[]);

appControllers.controller('MainController' ,['$scope','UserService','USER_ROLES',
                                             function($scope,UserService,USER_ROLES){
	
	$scope.currentUser = null;
	$scope.userRoles= USER_ROLES;
	$scope.isAuthorized = UserService.isAuthorized;
	
	$scope.setCurrentUser = function(user){
		$scope.currentUser = user;
	}
	
	$scope.logout = function(){
		UserService.logout();
	};
}]);


appControllers.controller('LoginController',['$scope','UserService','AUTH_EVENTS',
                                             function($scope,UserService,AUTH_EVENTS){
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