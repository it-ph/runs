angular
.module('run_proud')
.controller('MainController',[ 
	    '$rootScope', '$scope', '$state','$cookies','Access',
		function($rootScope, $scope, $state,$cookies,Access){
	
		//hide nav if it is login, landing, or register page
	    
	    	
	    $scope.showNav = function(){
	    	register = $state.current.name =='register'; 
	    	landing = $state.current.name =='landing'; 
	    	login =  $state.current.name =='login'; 
	    	
	    	show = !(register || landing || login);
	    	return show;
	    }
	    
		$scope.logout = function(){
			$cookies.remove('run_proud_token');
			Access.logout();
			$state.go('login');
		}
		
	
		
		$scope.user = {};
		
		$scope.$on('childEmit', function(event, obj) {
			$scope.user = obj; 
		});
	
		
//		$scope.checkUser = function(callback){
//			
//			console.log(Access.getUser());
//				
//				if(Access.getUser()){ 
//
//					$scope.user = Access.getUser();
//				
//				}else{//if user is not defined (opened directly or upon refresh)
//					
//					var token = $cookies.get('run_proud_token');
//					
//					console.log(token);
//					
//					if(token){
//						console.log('has token');
//						
//						Access
//						 .getClaimsFromToken(token,
//							function(r){
//							 	$scope.user = r.data.principal.user;
//								callback(); //call start once there is no error when checking user;
//						 	},
//						 	
//						 	function(e){
//						 	  console.log(e);
//						 	  
//						 	}
//						 );
//					}else{ //if there are no token redirect to login
//						
//						console.log('no token');
//						$state.go('login');
//						
//					}
//				}
//			
//			
//		};
		
		
		$scope.start = function(){
			
		};
		
		$scope.isAdmin = function(){
			
			
			return $scope.user.role =='ADMIN';
		}
		
		
		
		//$scope.checkUser($scope.start);
		
		
		
}]); 