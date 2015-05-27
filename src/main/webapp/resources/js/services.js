var app = angular.module("elearning");

app.service('SessionService',['$http','Session',
                           function($http,Session){
	this.login = function(user,callback){ 
		$http
			.post('login',user)
			.success(function(res){
				Session.create(res.id,res.authorities[0].authority,res.username);
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

app.service('RegistrationService',['$http','Session',
function($http,Session){	
	this.register = function(user,callback){
		$http
			.post('register',user)
			.success(function(data){
				Session.create(data.id,data.authorities[0].authority,data.username);
				if(callback)
					callback(data,true);
			})
			.error(function(data){
				if(callback)
					callback(data,false);
			});
	}
}]);

app.factory('Session',['$cookies',function($cookies){
	
	var self = this;
	var obj = {};
	obj.userId = null;
	obj.userRole = null;
	obj.userName = null;
	
	var _createCookies = function(){
		$cookies.put('userId',obj.userId);
		$cookies.put('userRole',obj.userRole);
		$cookies.put('userName',obj.userName);
		$cookies.put('old',$cookies.get('XSRF-TOKEN'));
	}
	
	var _destroyCookies = function(){
		$cookies.remove('userId');
		$cookies.remove('userRole');
		$cookies.remove('userName');
		$cookies.remove('old');
	}
	
	var _getUserInfo = function(){
		if($cookies.get('XSRF-TOKEN')===$cookies.get('old')){
			obj.userId = $cookies.get('userId');
			obj.userRole = $cookies.get('userRole');
			obj.userName = $cookies.get('userName');
		}
	}
	
	_getUserInfo();
	
	obj.destroy = function(){
		obj.userId=null;
		obj.userRole=null;
		obj.userName = null;
		_destroyCookies();
	}
	
	obj.create = function( userId, userRole, userName){
		obj.userId = userId;
		obj.userRole = userRole;
		obj.userName = userName;
		_createCookies();
	};
	
	return obj;
		
}]);