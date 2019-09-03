angular
.module('run_proud')
.controller('LeaderboardController',['$rootScope', '$scope', '$state','$cookies','$http','$filter','Access','RunEventDataOp',
	function($rootScope, $scope, $state,$cookies,$http,$filter,Access,RunEventDataOp){
	
	    	
	$scope.user = undefined;
	$scope.error = undefined;
	
	$scope.currentRun = null;
	
	$scope.all_records ={
			
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
	
	$scope.blitz = angular.copy($scope.all_records);
	
	$scope.blitz.init = function(data){
		
		$scope.blitz.data = data;
		$scope.blitz.totalItems = data.length;
		$scope.blitz.filteredList = data;

        var begin = (($scope.blitz.currentPage - 1) * $scope.blitz.numPerPage);
        var end = begin + $scope.blitz.numPerPage;
        
        $scope.blitz.currentPageList = $scope.blitz.filteredList.slice(begin, end);
	}
	
	$scope.range1 = angular.copy($scope.all_records);
	
	$scope.blitz_range1 = angular.copy($scope.all_records);
	
	$scope.range2  = angular.copy($scope.all_records);
	
	$scope.blitz_range2 = angular.copy($scope.all_records);
	
	$scope.range3 = angular.copy($scope.all_records);
	
	$scope.full_metal = angular.copy($scope.all_records);
	
	
	$scope.getCurrentRun = function(callback){
		
		 RunEventDataOp
		   .getCurrentRun()
		   .then(function(r){
			   callback(r.data);
			   
		   })
		   .catch(function(e){
			   console.log(e);
		   })
	}
	
	$scope.getAllProgressByEvent = function(id){
		RunEventDataOp
		 .getAllProgressByEvent(id)
		 .then(function(r){
			 $scope.all_records.init(r.data);
		 })
		 .catch(function(e){
			 console.log(e);
		 });
		
	}
	
	$scope.getProgressByEventByCategory = function(event,category,callback){
		
		RunEventDataOp
		 .getProgressByEventByCategory(event,category)
		 .then(function(r){
			 callback(r.data);
		 })
		 .catch(function(e){
			 console.log(e)
		 })
		
	}
	
	$scope.initData = function(){
		
		 $scope.getCurrentRun(function(data){
			 
			 if(data.runStart){
				 $scope.currentRun = data;
				 
				 $scope.getAllProgressByEvent(data.id);
				
				 $scope.getProgressByEventByCategory(data.id,1,
						 function(d){
					 $scope.blitz.init(d);
				 });
				 
				 $scope.getProgressByEventByCategory(data.id,2,
						 function(d){
					 $scope.range1.init(d);
				 });
				 
				 $scope.getProgressByEventByCategory(data.id,3,
						 function(d){
					 $scope.blitz_range1.init(d);
				 });
				 
				 $scope.getProgressByEventByCategory(data.id,4,
						 function(d){
					 $scope.range2.init(d);
				 });
				 
				 $scope.getProgressByEventByCategory(data.id,5,
						 function(d){
					 $scope.blitz_range2.init(d);
				 });
				 
				 $scope.getProgressByEventByCategory(data.id,6,
						 function(d){
					 $scope.range3.init(d);
				 });
				 
				 $scope.getProgressByEventByCategory(data.id,7,
						 function(d){
					 $scope.full_metal.init(d);
				 });
				
			 }
			 
		 });
	}	 
	$scope.viewProgress = function(rec){
		$state.go('user-progress',{eventId: rec.event.id, userId: rec.user.id});
	}
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
	

	  
	
}]); 
