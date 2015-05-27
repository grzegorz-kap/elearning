angular.module('elearning.Controllers',[]);

angular.module('elearning.Controllers')
.controller('MainController',function($scope,SessionService,Session,$location,$window){
	
	$scope.currentUser = {};
	$scope.currentUser.name = Session.userName;
	$scope.currentUser.role= Session.userRole;
	
	$scope.logout = function(){
		SessionService.logout(function(){
			$scope.currentUser.refresh();
			$location.path("");
			$window.location.reload();
		});
	};
	
	$scope.currentUser.refresh = function(){
		$scope.currentUser.name = Session.userName;
		$scope.currentUser.role = Session.userRole;
	}	
});