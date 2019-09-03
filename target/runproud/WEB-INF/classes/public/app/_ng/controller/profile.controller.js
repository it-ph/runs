angular
.module('run_proud')
.controller('ProfileController',[ 
	    '$rootScope', '$scope', '$state','$cookies','Access',
		function($rootScope, $scope, $state,$cookies,Access){
	
	  var vm = this;
	  
	  vm.user = null;
	  
	  vm.isAdmin = function(){
		  
		  
		  if(vm.user){
			  return vm.user.role =='ADMIN';
		  }else{
			  return false;
		  }
	  }
	  
	  vm.checkUser = function(){
			
			if(!Access.user){ //if user is not defined (opened directly or upon refresh)
				
				var token = $cookies.get('run_proud_token');
				
				if(!token){//if there are no token redirect to login
					
					$state.go('login');
				
				}else{
					
					Access
					 .getClaimsFromToken(token,
						function(r){
						 	vm.user = r.data.principal.user;
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
				vm.user = Access.user;
			}
			
		};
		
		vm.checkUser();
		
		vm.hasRace = true;
		vm.logout = function(){
			$cookies.remove('run_proud_token');
			Access.logout();
			$state.go('login');
		}
}]); 