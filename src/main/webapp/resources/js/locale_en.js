var app = angular.module('elearning');

app.config(['$translateProvider',function($translateProvider){
	$translateProvider.translations('en',{
		USERNAME: 'Username',
		PASSWORD: 'Password'
	})
}]);