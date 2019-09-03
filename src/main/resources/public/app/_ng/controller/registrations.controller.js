angular
.module('run_proud')
.controller('RegistrationController',[ 
	    '$rootScope', '$scope', '$state','$cookies','$http','$filter','Access','RegistrationDataOp',
		function($rootScope, $scope, $state,$cookies,$http,$filter,Access,RegistrationDataOp){
	
	  $scope.user = null;
	  
	  $scope.isAdmin = function(){
		  
		  
		  if($scope.user){
			  return $scope.user.role =='ADMIN';
		  }else{
			  return false;
		  }
	  }
	  
	  $scope.approve = function(reg){
		  
		  $('#loading').modal('show');
		  RegistrationDataOp
		   .approve(reg)
		   .then(function(r){
			   $scope.getRegistrations();

			   $('#loading').modal('hide');
		   })
		   .catch(function(e){
			   $('#loading').modal('hide');
			   console.log(e);
		   })
	  }
	  
	  $scope.deleteUserReg = function(reg){
		  
		  $('#loading').modal('show');
		  RegistrationDataOp
		   .deleteUserReg(reg)
		   .then(function(r){
			   $scope.getRegistrations();

			   $('#loading').modal('hide');
		   })
		   .catch(function(e){
			   $('#loading').modal('hide');
			   console.log(e);
		   })
	  }
	  
	  $scope.getRegistrations = function(){
			 RegistrationDataOp
			  .getRegistrations()
			  .then(function(r){
				  
				 $scope.registrations.init(r.data);
				 
				 $scope.registrations.data;
				 
			  })
			  .catch(function(e){
				  console.log(e);
			  });
			 
			
	 }
	  

	 
	  $scope.start = function(){
		  
		  $scope.$emit('childEmit', $scope.user);
	
		
		  $scope.registrations ={
				data: [],
				currentPageList: [],
				filteredList: [],
				totalItems: 0,
				numPages: undefined,
				maxSize: 5,
				currentPage:1,
				boundary_links: true,
				numPerPage: 10,
				numPerPageSelections: [5,10,20,50,100],
				pageChange: function(){
					
					 var begin = ((this.currentPage - 1) * this.numPerPage);
					 var end = begin + this.numPerPage;
					 this.currentPageList = this.filteredList.slice(begin, end);
				},
				searchKeyword: '',
				search: function(){
					
					this.filteredList = $filter('filter')(this.data,this.searchKeyword);
				    this.totalItems = this.filteredList.length;				
					var begin = ((this.currentPage - 1) * this.numPerPage);
					var end = begin + this.numPerPage;				
					this.currentPageList = this.filteredList.slice(begin, end);	
				},
				
				numPageChange: function(){
					var begin = ((this.currentPage - 1) * this.numPerPage);
					 var end = begin + this.numPerPage;
					 this.currentPageList = this.filteredList.slice(begin, end);
				
				},
				
				init : function(data){
					
					this.data = data;
			        this.totalItems = data.length;
			        this.filteredList = data;

		            var begin = ((this.currentPage - 1) * this.numPerPage);
		            var end = begin + this.numPerPage;
		            this.currentPageList = this.filteredList.slice(begin, end);
				}
		 };
		 
		
		 
		 $scope.getRegistrations();
		
	  }
		
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