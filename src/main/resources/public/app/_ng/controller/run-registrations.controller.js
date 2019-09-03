angular
.module('run_proud')
.controller('RunRegistrationController',[ 
	    '$rootScope', '$scope', '$state','$cookies','$http','$filter','Access','RunEventDataOp','RegistrationDataOp',
		function($rootScope, $scope, $state,$cookies,$http,$filter,Access,RunEventDataOp,RegistrationDataOp){
	
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
		  
		  RunEventDataOp
		   .approveRegistration(reg)
		   .then(function(r){
			   console.log(r);
			   $scope.getRunRegistrations();
			   $('#loading').modal('hide');
			   
		   })
		   .catch(function(e){
			   console.log(e);
			   $('#loading').modal('hide');
		   })
	  }
	
	  
	  $scope.getRunRegistrations = function(){
		  
		  RunEventDataOp
			 .getRunParticipants()
			 .then(function(r){
				console.log(r); 
				 $scope.registrations.init(r.data);
			 })
			 .catch(function(e){
				 console.log(e);
			 });
			
	 }
	 
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
	  
		 
	  $scope.reg_to_delete = null;
	  $scope.reg_to_change = null;
	  $scope.battles = [];
	  
	  $scope.showConfirmDelete = function(reg){
		$scope.reg_to_delete = reg;
		$('#confirm-delete').modal('show');
	  }
	  
	  $scope.showCategoryChange = function(reg){
		  $scope.reg_to_change = reg;
		  $('#edit-category').modal('show');
	  }
	  
	  $scope.changeCategory = function(){
		  RunEventDataOp
		   .changeRunCategory($scope.reg_to_change)
		   .then(function(r){
			   $('#edit-category').modal('hide');
		   })
		   .catch(function(e){
			  console.log(e);
		   });
	  }
	  $scope.changeShirt = function(){
		RunEventDataOp
		 .changeRunCategory($scope.reg_to_change)
		 .then(function(r){
			 $('#edit-shirt').modal('hide');
		 })
		 .catch(function(e){
			console.log(e);
		 });
	}
	  
	  $scope.deleteReg = function(){ 
		  RegistrationDataOp
		 .deleteReg($scope.reg_to_delete)
		 .then(function(r){
			 console.log(r);
			 
			 $scope.getRunRegistrations();
			 $('#confirm-delete').modal('hide');
		 })
		 .catch(function(e){
			 console.log(e);
		 })
	  }
	  
	  $scope.getCategories = function(){
    	  RunEventDataOp
    	  	.getRunCategories()
    	  	.then(function(r){
    	  		$scope.battles = r.data;
    	  	})
    	  	.catch(function(e){
    	  		console.log(e);
    	  	})
      }
	  
	  $scope.start = function(){
		  
		  $scope.$emit('childEmit', $scope.user);
		  $scope.getRunRegistrations();
		  $scope.getCategories();
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