angular
	.module('run_proud')
	.factory('LoginDataOp',['$http',function($http){
		
		var LoginDataOp = {
			getUsers : function(){
				return $http({
					method: 'GET',
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
				login : function(user,success,error){
					return $http({
						method: 'POST',
						data: user,
						dataType: 'json',
						url: '/authenticate',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					})
					.then(function(r){
						token = r.data.access_token;
						$http.defaults.headers.common.Authorization= 'Bearer ' + token;	
						success(r);
					})
					.catch(function(e){
						error(e);
					})
				},
				
				getClaimsFromToken: function(token){
		        	return $http({
		    			method: 'POST',
		    			url: '/user-claims',
		    		    headers: { 'Authorization': 'Bearer '+token }
		    		})
		        },
		        
		};
		
		return LoginDataOp;
	}]);