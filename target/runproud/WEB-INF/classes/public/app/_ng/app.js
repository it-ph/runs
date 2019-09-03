angular.module('run_proud', ['ui.router', 'ui.bootstrap', 'ngCookies', 'AxelSoft','ngFileSaver']);

angular
  .module('run_proud')
  .config(['$stateProvider', '$urlRouterProvider', '$locationProvider',
    function($stateProvider, $urlRouterProvider, $locationProvider) {
      $urlRouterProvider.otherwise('/');
      $stateProvider

        .state("landing", {
          url: '/',
          templateUrl: '/app/partials/landing.html',
          controller: 'LandingPageController',
        })
      
        .state("home", {
          url: '/home',
          templateUrl: '/app/partials/home.html',
          controller: 'DashboardController'
        })
       
        .state("login", {
          url: '/login',
          templateUrl: '/app/partials/login.html',
          controller: 'LoginController'
        })
        
        .state("registrations", {
          url: '/registrations',
          templateUrl: '/app/partials/registrations.html',
          controller: 'RegistrationController'
        })
        .state("run_registrations", {
          url: '/run_registrations',
          templateUrl: '/app/partials/run-registrations.html',
          controller: 'RunRegistrationController'
        })
        
        .state("records", {
          url: '/records',
          templateUrl: '/app/partials/record.html',
          controller: 'RecordController',
        }) 
        .state("leaderboards", {
            url: '/leaderboards',
            templateUrl: '/app/partials/leaderboard.html',
            controller: 'LeaderboardController',
         })
        .state("users", {
          url: '/users',
          templateUrl: '/app/partials/users.html',
          controller: 'UserController',
        })
        
        .state("register", {
	        url: '/register',
	        templateUrl: '/app/partials/register.html',
	        controller: 'RegisterControllers'
         })
        .state("user-progress", {
	        url: '/user-progress/:eventId/:userId',
	        templateUrl: '/app/partials/user-progress.html',
	        controller: 'UserProgressController'
        })
        .state("reports", {
	        url: '/reports',
	        templateUrl: '/app/partials/reports.html',
	        controller: 'ReportController'
        })
        .state("events", {
	        url: '/events',
	        templateUrl: '/app/partials/events.html',
	        controller: 'EventController'
        })
        .state("event_participants", {
	        url: '/event_participants/event/:eventId',
	        templateUrl: '/app/partials/event_participants.html',
	        controller: 'ParticipantController'
        })
        .state("new_record", {
            url: '/new_record/:id',
            templateUrl: '/app/partials/new_record.html',
            controller: 'NewRecordController'
         })
       
    }
  ]);
  
  angular
  .module('run_proud')
  .directive("fileInput", function() {
    return {
        require: "ngModel",
        link: function postLink(scope,elem,attrs,ngModel) {
          elem.on("change", function(e) {
            var files = elem[0].files[0];
           
            console.log(elem[0].files[0]);
            ngModel.$setViewValue(files);
           
          })
        }
      }
  });
  
  


angular
.module('run_proud')
.filter('categFilter', function() {
    return function(values, key) {
    	
    	var result =[];
    	
    	if(key === 'ALL') return values;
    	
    	values.forEach(function(currentValue){
    		if (currentValue.eventCategory.category === key) result.push(currentValue);
    	});
        return result;
    };
});
angular
.module('run_proud')
.filter('genderFilter', function() {
    return function(values, key) {
    	
    	var result =[];
    	
    	if(key === 'ALL') return values;
    	
    	values.forEach(function(currentValue){
    		if (currentValue.eventCategory.category === key) result.push(currentValue);
    	});
        return result;
    };
});

