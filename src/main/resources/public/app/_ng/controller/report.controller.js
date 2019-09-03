angular
.module('run_proud')
.controller('ReportController',[ 
	    '$rootScope', '$scope', '$state','$cookies','$timeout','$filter','Access','RunEventDataOp',
		function($rootScope, $scope, $state,$cookies,$timeout,$filter,Access,RunEventDataOp){
	    
	      $scope.user = null;
	   	  $scope.isAdmin = function(){
	   		  if($scope.user){
	   			  return $scope.user.role =='ADMIN';
	   		  }else{
	   			  return false;
	   		  }
	   	  }
	   	  
	      $scope.start = function(){
	   		  
	   		  $scope.$emit('childEmit', $scope.user);
   		  };
	      
	  	  $scope.checkUser = function(callback){
				if(!Access.user){ //if user is not defined (opened directly or upon refresh)
					var token = $cookies.get('run_proud_token');
					if(!token){//if there are no token redirect to login
						$state.go('login');
					}else{
						Access
						 .getClaimsFromToken(token,
							function(r){
							 $scope.user = r.data.principal.user;
							 $scope.start();
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
					
					$scope.user = Access.user;
					$scope.start();
					
				}
			
		};
		
		$scope.checkUser($scope.start);
}]); 