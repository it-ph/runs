angular
.module('run_proud')
.controller('DashboardController',[ 
	    '$rootScope', '$scope', '$state','$cookies','Access','RunEventDataOp',
		function($rootScope, $scope, $state,$cookies,Access,RunEventDataOp){

		
			
	  $scope.user ={};
	  
	  $scope.activeRun = null;
	  $scope.newRecord = {};
	  $scope.error = null;
	  $scope.runEventReg = null;
	  $scope.battles = {
			  selected: null,
			  list: []
	  };
	  
	  $scope.enable_add = false;
	  
	  $scope.selectedRun ={};
	  $scope.userProgress ={};
	  $scope.selectedBattle = "";
	  
	 
	  $scope.entitlements = [
        {name:'- SELECT YOUR ENTITLEMENT -',cost: 0},
	  ];
	  
	  
	  $scope.selectedEntitlement =  $scope.entitlements[0];
	  
	  $scope.ent ={
			  selected: null,
			  list: [
				{name:'- SELECT YOUR ENTITLEMENT -',cost: 0},
			  ],
			  hasSelected : function(){
				  return this.selected.cost >0
			  },
			  
			  isDisabled: function(){
				  return this.list.length == 1;
			  }
	  }
	  
	  $scope.run_reg = {};
	  
	  $scope.ent.selected = $scope.ent.list[0];
	  
	
	
	
	
	  $scope.shirtSize ={
			  list:[
				  {name: 'XS', desc: 'EXTRA SMALL'},
				  {name: 'S', desc: 'SMALL'},
				  {name: 'M', desc: 'MEDIUM'},
				  {name: 'L', desc: 'LARGE'},
				  {name: 'XL', desc: 'EXTRA LARGE'},
				  {name: 'XXL', desc: 'EXTRA EXTRA LARGE'}
			  ],
			  selected: null
	  } 
	  $scope.shirtSize.selected = $scope.shirtSize.list[0];
	  
	  $scope.isAdmin = function(){
		  return $scope.user.role =='ADMIN';
	  }
	  

	  $scope.currentRun = null;
	
	  $scope.auth_pay = false;
	 
	  $scope.regDetail ={
			auth: false,
			auths:false,
			event_id: null,
			entitlement: null,
			shirt: null
	  };
	
	  $scope.time_change = function(){
		  console.log( 'time chanaged ');
	  }
	 
	
	  
	  $scope.submitions ={
				data: [],
				currentPageList: [],
				currentPageLists: [],
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
				},
				inits : function(data){
					
					this.data = data;
			        this.totalItems = data.length;
			        this.filteredList = data;

		            var begin = ((this.currentPage - 1) * this.numPerPage);
		            var end = 1;
		            this.currentPageLists = this.filteredList.slice(begin, end);
				}
		 };
	  
	    $scope.canAddRecord = function(){
			
	    	return $scope.enable_add;
	    }
	    
		$scope.getActiveRunReg = function(){
				
			RunEventDataOp
			 .getActiveRunReg()
			 .then(function(r){
				 
				 if (Object.getOwnPropertyNames(r.data).length > 0){
					var currentDate = new Date();
					var exactDate= new Date('2019-08-20');

					$scope.runEventReg = r.data;
					
					
					//var currentTime = moment("2018-05-15T00:00:00");
					var currentTime = moment(new Date());
		
					
					var runStart = moment(r.data.runEvent.runStart);
					 
					var timeRem = moment.duration(runStart.diff(currentTime));

					$scope.enable_add = timeRem._milliseconds <= 0 && r.data.approved;$scope.enable_add = timeRem._milliseconds <= 0 && r.data.approved || r.data.forhim==1 && currentDate >= exactDate;
					

					
				 }
			 })
			 .catch(function(e){
				 console.log(e);
				 console.log(e);
			 });
		};
		
		$scope.getUserProgress = function(run){
			 
			RunEventDataOp
			 .getUserProgress($scope.currentRun)
			 .then(function(r){
				 
				 if (Object.getOwnPropertyNames(r.data).length > 0){
					 $scope.userProgress = r.data;
					 
					 distance = r.data.distanceTraveled;
					 totalDistance = $scope.runEventReg.eventCateg.distance;
					 iduser=r.data.userId;
			
					 percent = (distance/totalDistance) * 100;
					 $scope.userProgress.percent = percent;
					 
					
				 }
				 
			 })
			 .catch(function(e){
				 console.log(e);
			 })
		};
		
		$scope.getCurrentRun = function(){

			
			RunEventDataOp
			 .getCurrentRun()
			 .then(function(r){
				 if (Object.getOwnPropertyNames(r.data).length > 0){
					 $scope.currentRun = r.data;
					 $scope.getUserProgress(r.data);
				}
			 })//
			 .catch(function(e){
				 console.log(e);
			 });
		};
		
		$scope.getEntitlements = function(){

			RunEventDataOp
			 .getEntitlements()
			 .then(function(r){
				 
				 data = r.data;
			
				 $scope.ent.list = r.data;
				 $scope.ent.selected = $scope.ent.list[0];
				 
				 
			 })
			 .catch(function(e){
				 console.log(e);
			 })
			
		};
		
		$scope.getRunCategories = function(){

			RunEventDataOp
			 .getRunCategories()
			 .then(function(r){
				// console.log(r);
				 $scope.battles.list = r.data;
				 $scope.battles.selected = $scope.battles.list[0];
			 })
			 .catch(function(e){
				 console.log(e);
			 });
		}
		$scope.getRunRecords = function(){
			
			RunEventDataOp
			 .getRunRecords()
			 .then(function(r){
				 	
				$scope.fac = ["RUN","RIDE"];
				$scope.ffac = [];
				$scope.ffac[0]=  r.data.facility;
		   
				 $scope.submitions.init(r.data);
				 $scope.submitions.inits(r.data);
			
				 
				 
			 })
			 .catch(function(e){
				 console.log(e);
			 })
		};
		
		$scope.registerRun = function(){
				
				reg ={
						    user: $scope.user,
						runEvent: $scope.currentRun,
					  eventCateg: $scope.battles.selected,
					 entitlement: $scope.ent.selected,
					   shirtSize: $scope.shirtSize.selected.name
				};
				
				console.log(reg);
				
				RunEventDataOp
				 .registerRun(reg)
				 .then(function(r){
					 console.log(r);
					 $scope.getActiveRunReg();
					 
				 })
				 .catch(function(e){
					 console.log(e);
				 })
		}
		
		$scope.addNewRecord = function(){
		  
		  $scope.error = null;
		  var newData = {};
		  
		
          newData.user = $scope.runEventReg.user;
          newData.event = $scope.runEventReg.runEvent;
          newData.eventCategory = $scope.runEventReg.eventCateg;
		  newData.distanceTraveled = $scope.newRecord.distance;
		  newData.categ = $scope.newRecord.categ;
		  if($scope.newRecord.gallery==null)
		  {
		  newData.gallery=1;
		  }
		  else{
		  newData.gallery = $scope.newRecord.gallery;
		  }

          newData.totalElapseTime = moment($scope.newRecord.time).format("HH:mm:ss");
         
          console.log($scope.newRecord.categ);
          
		  var formData = new FormData();
		  
		  if(newData.totalElapseTime =='00:00:00' || $scope.newRecord.time == null){
			  
			  $scope.error ='Invalid time';
			  
		  }
		
		  
		  if(!$scope.error){
			  
			
			formData.append('file',$scope.newRecord.file);
			  formData.append('files',$scope.newRecord.files);
			  
			  formData.append('record',angular.toJson(newData));
			  formData.append('records',angular.toJson(newData));
			  
			  
			  
			 
			  
			console.log($scope.error);
			  
			  RunEventDataOp
			   .submitRecord(formData)
			   .then(function(r){
				  console.log(r); 
				  
				  $scope.getUserProgress($scope.currentRun);
				  $scope.getRunRecords();
				  $('#add-record').modal('hide');
				  
			   })
			   .catch(function(e){
				   console.log(e);
			   });
		  }

		  
	  }
	  $scope.records ={
		 numPerPage: 5,
		 numPerPageSelections: [5,10,20,50,100],
		 numPageChange: function(){
			 console.log('num page change');
		 }
	  };
	  
	  $scope.showUploadModal = function(){
		  
		  $scope.newRecord ={};
		  $scope.newRecord.time= null;
		  $scope.error =null;
		  $('#add-record').modal('show');
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
		
		
		
		//return if there is a current run and the user is not registered yet
		$scope.canRegister = function(){
			
			return $scope.currentRun && !$scope.runEventReg;
		}
		
		$scope.registerDisabled = function(){
			
			return !($scope.ent.selected &&  $scope.shirtSize.selected && $scope.regDetail.auth);
			
		}
		
		$scope.registerDisableds = function(){
			
			return !($scope.regDetail.auths);
			
		}
		

		$scope.showAttachment = function(run){
			$scope.selectedRun  = run;
			console.log($scope.selectedRun);
			$('#img-attach').modal('show');
		}
		
		$scope.showAttachments = function(run){
			$scope.selectedRun  = run;
			console.log($scope.selectedRun);
			$('#img-attachs').modal('show');
		}
		
		$scope.initData = function(){
		
			$scope.getActiveRunReg();
			$scope.getCurrentRun();
			$scope.getEntitlements();
		    $scope.getRunCategories();
		    $scope.getRunRecords();
		};
		$scope.checkUser();
		
	

		
		
	
}]); 