angular
.module('run_proud')
.controller('EventController',[ 
	    '$rootScope', '$scope', '$state','$cookies','$timeout','$filter','Access','RunEventDataOp',
		function($rootScope, $scope, $state,$cookies,$timeout,$filter,Access,RunEventDataOp){
	    
	      $scope.user = null;
	$scope.events ={
				
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
			        this.filteredList = $filter('filter')(this.data,this.searchKeyword);
		            var begin = ((this.currentPage - 1) * this.numPerPage);
		            var end = begin + this.numPerPage;
		            this.currentPageList = this.filteredList.slice(begin, end);
		            
				}
		  };
	      
		  $scope.viewParticipants = function(event){
			  $state.go('event_participants',{eventId: event.id});
		  }
		  
	      $scope.getRunEvents = function(){
	    	  RunEventDataOp
	    	   .getRunEvents()
	    	   .then(function(r){
	    		  $scope.events.init(r.data);
	    	   })
	    	   .catch(function(e){
	    		   console.log(e);
	    	   })
	      }
	      
	  
	  	
	   	  $scope.isAdmin = function(){
	   		  if($scope.user){
	   			  return $scope.user.role =='ADMIN';
	   		  }else{
	   			  return false;
	   		  }
	   	  }
	   	  
	      $scope.start = function(){
	   		  $scope.$emit('childEmit', $scope.user);
	   		  $scope.getRunEvents();
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