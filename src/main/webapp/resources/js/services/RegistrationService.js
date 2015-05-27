angular.module('elearning.Services').service('RegistrationService',['$http','Session',
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

