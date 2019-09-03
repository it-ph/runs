angular
	.module('run_proud')
	.factory('UserDataOp',['$http',function($http){
		
		var UserDataOp = {
				
			getUsers : function(){
				return $http({
					method: 'GET',
					dataType: 'json',
					url: '/users',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
			},
			
			resetPass: function(user){
				
				return $http({
					method: 'POST',
					data: user,
					dataType: 'json',
					url: '/users/resetPassword',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
				
			},
			
			disableUser : function(user){
		        	return $http({
						method: 'POST',
						data: user,
						dataType: 'json',
						url: '/users/disable',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
		    },
		    
		    enableUser : function(user){
		    	return $http({
					method: 'POST',
					data: user,
					dataType: 'json',
					url: '/users/enable',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
		    },
		        
		    deleteUser : function(user){
		    	return $http({
					method: 'POST',
					data: user,
					dataType: 'json',
					url: '/users/delete',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
		    },
		        
		};
		
		return UserDataOp;
	}]);