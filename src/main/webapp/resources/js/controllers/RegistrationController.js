
angular.module("elearning.Controllers")
.controller('RegistrationsController', ['$scope','RegistrationService','$location',
                                                      function($scope,RegistrationService,$location){
	$scope.user = {};
	$scope.user.username="";
	$scope.user.email="";
	$scope.user.password="";
	$scope.user.passwordConfirm="";
	
	$scope.register = function(){
		RegistrationService.register($scope.user,function(response,correct){
			if(!correct)
				alert("Bledy w formularzu!");
			else{
				$scope.currentUser.refresh();
				$location.path("");
			}
		});
	}
}])