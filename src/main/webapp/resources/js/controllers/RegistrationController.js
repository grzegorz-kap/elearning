
angular.module("elearning.Controllers")
.controller('RegistrationsController', ['$scope','RegistrationService','$location',
                                                      function($scope,RegistrationService,$location){
	$scope.user = {};
	$scope.user.username="grzk695";
	$scope.user.email="grzk@outlook.com";
	$scope.user.password="ciapa";
	$scope.user.passwordConfirm="ciapa";
	
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