var app = angular.module("elearning");

app.service('UserService',['$http','$rootScope',function($http,$rootScope){
	this.login = function(user){
		$http.post(
				$rootScope.prefix+'/login',
				user
		);}
}]);