angular
	.module('run_proud')
	.factory('Access',['$http',function($http){
		
		var user = null;
		
		var Access = {

			
			getRunParticipants : function(){
				return $http({
					method: 'GET',
					url: '/run_event/participants',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
			},
	
				getClaims : function(){
					return $http({
						method: 'POST',
						dataType: 'json',
						url: '/user-claims',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				getClaimsFromToken: function(token,success,error){
		        	
					$http({
		    			method: 'POST',
		    			url: '/user-claims',
		    		    headers: { 'Authorization': 'Bearer '+token }
		    		})
		    		.then(function(r){
		    			
		    			$http.defaults.headers.common.Authorization= 'Bearer ' + token;	
		    			this.user = r.data.principal.user;
		    			success(r);
		    		})
		    		.catch(function(e){
		    			
		    			$http.defaults.headers.common.Authorization = null;	
		    			error(e);
		    		});
		        },
		        
		        clearData: function(){
		        	this.user = null;
		        	$http.defaults.headers.common.Authorization = null;
		        },
		        
		        logout: function(){
		        	this.user = null;
		        	$http.defaults.headers.common.Authorization = null;
		        },
		        
		        changePassword: function(user){
		        	
		        	
		        	return $http({
						method: 'POST',
						data: user,
						dataType: 'json',
						url: '/users/changePassword',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
		        },
				
		       
		        getUser : function(){
					return this.user;
				},
				
				setUser : function(user){
					this.user = user;
				}
		        
				
			
		        
		};
		
		return Access;
	}]);