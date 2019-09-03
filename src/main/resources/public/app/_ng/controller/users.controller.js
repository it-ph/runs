angular
.module('run_proud')
.controller('UserController',[ 
	    '$rootScope', '$scope', '$state','$cookies','$filter','Access','UserDataOp',
		function($rootScope, $scope, $state,$cookies,$filter,Access,UserDataOp){
	
		  $scope.users ={
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
		  
	  $scope.getUsers = function(){
		  UserDataOp
		   .getUsers()
		   .then(function(r){
			   $scope.users.init(r.data);
		   })
		   .catch(function(e){
			   console.log(e);
		   })
	  }
	  
	  $scope.resetPass = function(user){
		  
		  $('#loading').modal('show');
		  
		  UserDataOp
		   .resetPass(user)
		   .then(function(r){
			   console.log(r);
			   $scope.getUsers();
			   $('#loading').modal('hide');
		   })
		   .catch(function(e){
			   console.log(e);
			   $('#loading').modal('hide');
		   })
	  };
	  
	  $scope.disableUser = function(user){
		  
		  $('#loading').modal('show');
		  
		  UserDataOp
		   .disableUser(user)
		   .then(function(r){
			   $scope.getUsers();
			   $('#loading').modal('hide');
		   })
		   .catch(function(e){
			   console.log(e);
			   $('#loading').modal('hide');
		   })
	  };
	  
	  
	  $scope.confirm_delete = false;
	  $scope.user_to_delete = null;
	  
	  $scope.showDeleteConfirm = function(user){
		  $scope.user_to_delete = user;
		  $('#confirm-delete').modal('show');
		  
	  }
	  
	
	  
	  $scope.deleteUser = function(){
		  
		  $scope.error = null;
		  
		  UserDataOp
		   .deleteUser($scope.user_to_delete)
		   .then(function(r){
			   console.log(r);
			   $scope.getUsers();
			   $('#confirm-delete').modal('hide');
			   
		   })
		   .catch(function(e){
			   console.log(e);
			   $scope.error =e.data.message;
		   })
	  }
	  
	  
	  $scope.enableUser = function(user){
		  
		  $('#loading').modal('show');
		  
		  UserDataOp
		   .enableUser(user)
		   .then(function(r){
			   $scope.getUsers();
			   $('#loading').modal('hide');
		   })
		   .catch(function(e){
			   console.log(e);
			   $('#loading').modal('hide');
		   })
	  };
	  $scope.start = function(){
		  $scope.getUsers();
		  $scope.$emit('childEmit', $scope.user);
		 
		
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