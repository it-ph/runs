angular
.module('run_proud')
.controller('UserProgressController',['$scope', '$state','$stateParams','$cookies','RunEventDataOp','Access',function ($scope, $state,$stateParams,$cookies,RunEventDataOp,Access){
	    
	    
	      $scope.runEventReg = null;
	      $scope.userProgress = null;
	  	  $scope.submitions ={
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
	  				searchDB: function(){
	  					
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
	  	  
	  	  	$scope.userProgress ={};
	  	  	
	  	  	$scope.getUserProgressById = function(eventId, userId){
	  	  		RunEventDataOp
	  	  			.getUserProgressByid(eventId, userId)
	  	  			.then(function(r){
	  	  				
		  	  			if (Object.getOwnPropertyNames(r.data).length > 0){
							 $scope.userProgress = r.data;
							 
							 distance = r.data.distanceTraveled;
							 totalDistance = r.data.eventCategory.distance;
							 
							 
							 percent = (distance/totalDistance) * 100;
							 $scope.userProgress.percent = percent;
							 
							
						 }
	  	  			
	  	  			})
	  	  			.catch(function(e){
	  	  				console.log(e);
	  	  			})
	  	  	}
	  	  	
	  	  	$scope.getRunRecordsByEventByUser = function(eventId, userId){
	  	  		RunEventDataOp
	  	  			.getRunRecordsByEventByUser(eventId, userId)
	  	  			.then(function(r){
	  	  				$scope.submitions.init(r.data);
	  	  			})
	  	  			.catch(function(e){
	  	  				console.log(e);
	  	  			})
	  	  	}
	  	  	
	  	  	$scope.initData = function(){
		  	  	
	  	  		if(($stateParams.eventId  && $stateParams.eventId > 0 )&&($stateParams.userId  && $stateParams.userId > 0 )){
		    
		    		
		    		$scope.getUserProgressById($stateParams.eventId, $stateParams.userId);
		    		$scope.getRunRecordsByEventByUser($stateParams.eventId, $stateParams.userId);
		    	}else{
		    		$state.go('home');
		    	}
	  	  	}
	  	  	
	  	  	$scope.showAttachment = function(run){
				$scope.selectedRun  = run;
				console.log($scope.selectedRun);
				$('#img-attach').modal('show');
			}
			$scope.showAttachment = function(run){
				$scope.selectedRun  = run;
				console.log($scope.selectedRun);
				$('#img-attachs').modal('show');
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
	