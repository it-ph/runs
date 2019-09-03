angular
.module('run_proud')
.controller('RunEventController',[ 
	    '$rootScope', '$scope', '$state','$cookies','Access',
		function($rootScope, $scope, $state,$cookies,Access){
	

	  
	

	  $scope.checkUser = function(){
			
			if(!Access.user){ //if user is not defined (opened directly or upon refresh)
				
				var token = $cookies.get('run_proud_token');
				
				if(!token){//if there are no token redirect to login
					
					$state.go('login');
				
				}else{
					
					Access
					 .getClaimsFromToken(token,
						function(r){
						 	$scope.user = r.data.principal.user;
						 	$scope.$emit('childEmit', $scope.user);
					 	},
					 	function(e){
					 	  console.log(e);
					 	  
					 	  Access.clearData();
					 	  $cookies.remove('run_proud_token');
					 	  $state.go('login');
					 	}
					 );
				}
			}else{
				$scope.user = Access.getUser();
				$scope.$emit('childEmit', $scope.user);
			}
			
			
		};
		
		$scope.checkUser();
		
	
		
	
	
}]); 