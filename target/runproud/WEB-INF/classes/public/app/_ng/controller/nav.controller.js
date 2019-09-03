angular
.module('run_proud')
.controller('NavController',['$rootScope', '$scope', '$state','$cookies','$http','Access','NotifDataOp',
	function($rootScope, $scope, $state,$cookies,$http,Access,NotifDataOp){
	
	    	
	$scope.user = undefined;
	$scope.newPassword ={};
	$scope.error = undefined;
	$scope.notif =[];
	
	$scope.showChangePassModal = function(){
		$scope.error =undefined;
		$scope.newPassword ={};
		$('#update-password').modal('show');
	}
	
	$scope.changePassword = function(){
		
	  if($scope.newPassword.password.length <7){
		  $scope.error = 'Password must not be less than 7 characters';
	  }
	  
	  if($scope.newPassword.password != $scope.newPassword.confirm_password){
		  $scope.error ='Passwords mismatched';
	  }
	  
	  
	  if(!$scope.error){
		 
		  
		  user_data ={
			password: $scope.newPassword.password
		  }
		  Access
		   .changePassword(user_data)
		   .then(function(r){
			   $('#update-password').modal('hide');
		   })
		   .catch(function(e){
			   $scope.error = 'Oops, something went wrong!';
		   })
		
	  }
	
		  
	}
	
	$scope.getNotifications  = function(){
		NotifDataOp
		 .getNotification()
		 .then(function(r){
			 $scope.notif = r.data;
		 })
		 .catch(function(e){
			 console.log(e);
		 })
	};

	$scope.viewNotif = function(notif){
		
		NotifDataOp
		 .viewNotif(notif)
		 .then(function(r){
			 $scope.getNotifications();
			 $state.go(notif.click_action);
			 
		 })
		 .catch(function(e){
			 console.log(e);
		 })
		
		
	}
	$scope.viewAll = function(){
		
		NotifDataOp
		 .viewAll($scope.notif)
		 .then(function(r){
			 $scope.getNotifications();
		 })
		 .catch(function(e){
			 console.log(e);
		 })
	}
	
	$scope.logout = function(){
		$cookies.remove('run_proud_token');
		Access.logout();
		$state.go('login');
	}
	
	
	$scope.init = function(){
		$scope.getNotifications();
	};
	
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
					  
					 	$scope.init();
				 	},
				 	function(e){
				 	  console.log(e);
				 	}
				 );
				
				
			}
		}else{
			$scope.user = Access.user;
			$scope.init();
		}
		
	
	};
	
	$scope.checkUser();
	

	  
	
}]); 
