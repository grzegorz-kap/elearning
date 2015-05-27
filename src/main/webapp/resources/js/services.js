var app = angular.module("elearning");

app.service('SessionService',['$http','Session',
                           function($http,Session){
	this.login = function(user,callback){ 
		$http
			.post('login',user)
			.success(function(res){
				Session.create(0,res.id,res.authorities[0].authority,res.username);
				if(callback!=null)
					callback(res);
			})
			.error(function(res){
				if(callback!=null)
					callback({error:true,data:res});
			});
	}
	
	this.isAuthenticated = function (){
		return !!Session.userId;
	}
	
	this.register = function(user){
		$http.post('register',user);
	}
	
	this.logout = function(callback){
		$http
			.post('logout',{})
			.then(function(res){
				Session.destroy();
				if(callback)
					callback();
			});
	}
}]);

app.service('Session',function(){
	this.create = function(sessionId, userId, userRole, userName){
		this.id = sessionId;
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
	};
	
	this.destroy = function(){
		this.id=null;
		this.userId=null;
		this.userRole=null;
		this.userName = null;
	}
});