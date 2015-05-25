var app = angular.module('elearning',[
      'ngRoute',
      'appControllers',
      'pascalprecht.translate'
])

app.constant('AUTH_EVENTS',{
	 loginSuccess: 'auth-login-success',
	  loginFailed: 'auth-login-failed',
	  logoutSuccess: 'auth-logout-success',
	  sessionTimeout: 'auth-session-timeout',
	  notAuthenticated: 'auth-not-authenticated',
	  notAuthorized: 'auth-not-authorized'
})

app.constant('USER_ROLES',{
	all: '*',
	admin: 'ADMIN',
	user: 'USER',
	guest: 'GUEST'
})

app.config(['$routeProvider',function($routeProvider){
	$routeProvider.
		when('/login',{
			templateUrl: 'resources/partials/login.html',
			controller: 'LoginController'
		}).
		when('/register',{
			templateUrl: 'resources/partials/register.html',
			controller: 'RegistrationsController'
		})
}]);

app.run(function($rootScope){
	$rootScope.prefix="/elearning";
})