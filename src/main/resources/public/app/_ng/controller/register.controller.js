angular
.module('run_proud')
.controller('RegisterControllers',[ 
	    '$rootScope', '$scope', '$state','RegistrationDataOp',function($rootScope, $scope, $state,RegistrationDataOp){
	
	    
	   $scope.reg = {};
	   $scope.error ='';
	   $scope.register = function(){
		  $scope.error =undefined;
		   
		  if(!$scope.isValid($scope.reg.email)){
			  $scope.error ='Invalid email format';
		  }
		  
		  if($scope.reg.password.length <7){
			  $scope.error = 'Password must not be less than 7 characters';
		  }
		  
		  if($scope.reg.password != $scope.reg.confirm_password){
			  $scope.error ='Passwords mismatched';
		  }
		  
		  if(!$scope.reg.facility){
			  $scope.error ='Please select a facility';
		  }
		  
		  if(!$scope.error){
			  
			 
			  RegistrationDataOp
			  	.register($scope.reg)
			  	.then(function(r){
			  		console.log(r);
			  		$scope.reg ={};
			  		
			  		$scope.msg = "Your registration was sent successfully. An email will be sent to you once your registration has been approved";
			  		
			  	})
			  	.catch(function(e){
			  		console.log(e);
			  		$scope.error = e.data.message;
			  	})
		  }
		  
	   }
	   
	   $scope.isValid = function(email) {
			  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			  return re.test(email);
	   };
		
	   $scope.registerDisableds = function(){
			
		return !($scope.ent.selected &&  $scope.reg.email && $scope.regDetail.auth);
		
	};
	$scope.regDetails ={
		auth: false,
		event_id: null,
		entitlement: null,
		shirt: null
  };
}]); 