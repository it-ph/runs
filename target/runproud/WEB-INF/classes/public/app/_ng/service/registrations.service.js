angular
	.module('run_proud')
	.factory('RegistrationDataOp',['$http',function($http){
		
		var RegistrationDataOp = {
				
				getRegistrations : function(){
					return $http({
						method: 'GET',
						dataType: 'json',
						url: '/registrations',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				register: function(registration){
					return $http({
						method: 'POST',
						data: registration,
						dataType: 'json',
						url: '/register',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				approve: function(registration){
					return $http({
						method: 'POST',
						data: registration,
						dataType: 'json',
						url: '/registrations/approve',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				deleteReg: function(registration){
					return $http({
						method: 'POST',
						data: registration,
						dataType: 'json',
						url: '/registrations/delete',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				deleteUserReg: function(registration){
					
					return $http({
						method: 'POST',
						data: registration,
						dataType: 'json',
						url: '/users/registrations/delete',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				}
				
			
		        
		};
		
		return RegistrationDataOp;
	}]);