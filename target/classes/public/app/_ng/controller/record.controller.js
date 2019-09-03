angular
.module('run_proud')
.controller('RecordController',[ 
	    '$rootScope', '$scope', '$state','$cookies','$timeout','$filter','Access','RunEventDataOp',
		function($rootScope, $scope, $state,$cookies,$timeout,$filter,Access,RunEventDataOp){
	
	
	  $scope.user = {};
	  $scope.submit ={};
	  $scope.isAdmin = function(){
			return $scope.user.role =='ADMIN';
	  }
	  
	  $scope.selectedRecord ={};
	  $scope.records ={
				data: [],
				currentPageList: [],
				filteredList: [],
				totalItems: 0,
				numPages: 5,
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
	 
	  $scope.initData = function(){
		  RunEventDataOp
		   .getAllRecords()
		   .then(function(r){
			   
			  $scope.records.init(r.data);
		   })
		   .catch(function(e){
			  console.log(e);
		   });
	  }
	  
	  $scope.showAttachment = function(record){
		  $scope.selectedRecord = record;
		  $('#img-attach').modal('show');
	  }
	  
	  $scope.showAttachments = function(record){
		$scope.selectedRecord = record;
		$('#img-attachs').modal('show');
	}
	  $scope.approve = function(record){
		  
		  $scope.submit ={
				  error: false,
				  message: null
		  };
		  
		 RunEventDataOp
		  .approveRecord(record)
		  .then(function(r){
			  
			  $scope.submit ={
					  error: false,
					  message: 'Record approved'
			  };
			  
			  $('#submit-status').modal('show');
			  
			  $timeout(function(){
				  $('#submit-status').modal('hide');
			  }
			  ,2000);
			  
			  $scope.initData();
		  })
		  .catch(function(e){
			  
			  $scope.submit ={
					  error: false,
					  message: e.data.message
			  };
			  
			  $('#submit-status').modal('show');
			  
			  $timeout(function(){ $('#submit-status').modal('hide'); }, 2000);
			  
			  
		  })
		 
	  };
	  
	  $scope.dissaprove = function(record){
			 RunEventDataOp
			  .disapproveRecord(record)
			 .then(function(r){
			  
			  $scope.submit ={
					  error: false,
					  message: 'Record disapproved'
			  };
			  
			  $('#submit-status').modal('show');
			  
			  $timeout(function(){ $('#submit-status').modal('hide'); } ,2000);
			  $scope.initData();
		  })
		  .catch(function(e){
			  
			  $scope.submit ={
					  error: false,
					  message: e.data.message
			  };
			  
			  $('#submit-status').modal('show');
			  
			  $timeout(function(){
				  $('#submit-status').modal('hide');
			  }
			  ,2000);
			  
			  
		  })
			 
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
						 	$scope.$emit('childEmit', $scope.user);
						 	
						 	$scope.initData();
						 	
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
				$scope.initData();
				$scope.user = Access.getUser();
				$scope.$emit('childEmit', $scope.user);
			}
			
			
		};
		
		$scope.checkUser();
		
		$scope.logout = function(){
			$cookies.remove('run_proud_token');
			Access.logout();
			$state.go('login');
		}
	
}]); 