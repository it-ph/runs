angular
.module('run_proud')
.controller('LoginController',['$rootScope', '$scope', '$state','$cookies','LoginDataOp','Access','UserDataOp',function($rootScope, $scope, $state,$cookies,LoginDataOp,Access,UserDataOp){
	
	var log = [];
	$scope.customer = {};     
   
	$scope.reset = {};
	$scope.error ='';
	$scope.msg ='';
	$scope.errorForgot ='';
	$scope.msgForgot='';
	
	$scope.resetPasswords = function(){
	   $scope.error =undefined;
		
	   if(!$scope.isValid($scope.reset.email)){
		   $scope.errorForgot ='Invalid email format';
		   $scope.msgForgot ='';
	   }
	   
	  if(!$scope.errorForgot){
		$scope.errorForgot ='';
		$scope.getuser();
		
	
	   }
	   
	}
	$scope.istrue=false;

	$scope.resetPass = function(user){
		  

		
		UserDataOp
		 .resetPass(user)
		 .then(function(r){
			 console.log(r);
			
			
		 })
		 .catch(function(e){
			 console.log(e);
		
		 })
	};


	$scope.getuser = function(){

		LoginDataOp
	   .getUsers()
	   .then(function(r){
		console.log(r);

		angular.forEach(r.data, function (value, key) {
		
			if(value.email==$scope.reset.email)
			{
				$scope.istrue=true;
				$scope.customer = {
					"id" : value.id,
					"fullaname" : value.fullname,
					"email" : value.email
					
				};
				$scope.resetPass($scope.customer);

	
			
				
			}
			
			
		},log);

		if($scope.istrue)
		{
			$scope.msgForgot ='Your temporary password was send to your email';
		
	 
		}
		  
		if(!$scope.istrue)
		{
			$scope.msgForgot ='No Email Found';
			
		}
		

	   })
	   .catch(function(e){
		   console.log(e);
		   $scope.errorForgot = 'Invalid Email';
	   });
	   console.log($scope.reset.email);
	   console.log();
	 	
  
	}
	
	$scope.isValid = function(email) {
		   var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		   return re.test(email);
	};
	$scope.user ={};
	
	
	$scope.checkToken = function(){
		var token = $cookies.get('run_proud_token');
		
		if(token){
			Access
			 .getClaimsFromToken(token,
			    function(r){
				$state.go('home');
				
			 }, function(e){
				 console.log(e);
			 });
		
		}
		
	};
	$scope.resetPassword = function(){
	
		$('#resetpassword').modal('show');
	}
	$scope.checkToken();
	
	$scope.logUser = function(){
		LoginDataOp
		 .login($scope.user,
			function(r){
			 $cookies.put('run_proud_token',r.data.access_token);	
			 
			 Access.setUser(r.data.user);
			 $state.go('home');
			 
		 },
		 function(e){
			 $scope.error = 'Invalid Username or Password';
		 });
		
	}
	
		
	
	    
}]); 