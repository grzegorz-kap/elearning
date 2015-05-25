var app = angular.module("elearning");

app.service('UserService',['$http','$rootScope','Session',
                           function($http,$rootScope,Session){
	this.login = function(user){ 
		$http
			.post($rootScope.prefix+'/login',user)
			.error(function(res){
				alert(res);
			})
	}
	
	this.isAuthenticated = function (){
		return !!Session.userId;
	}
	
	this.register = function(user){
		$http.post($rootScope.prefix+'/register',user);
	}
	
	this.logout = function(){
		$http.post($rootScope.prefix+'/logout');
	}
}]);

app.service('Session',function(){
	this.create = function(sessionId, userId, userRole){
		this.id = sessionId;
		this.userId = userId;
		this.userRole = userRole;
	};
	
	this.destroy = function(){
		this.id=null;
		this.userId=null;
		this.userRole=null;
	}
});