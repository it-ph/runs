angular
.module('run_proud')
.controller('LandingPageController',['$rootScope', '$scope', '$state','$timeout','$filter','RunEventDataOp',function($rootScope, $scope, $state,$timeout,$filter,RunEventDataOp){
	$scope.selectedRuns ={};

	$scope.records ={
		data: [],
		currentPageList: [],
		filteredList: [],
		numPages: 40,
		maxSize: 40,
		currentPage:1,
		boundary_links: true,
		numPerPage: 40,
		numPerPageSelections: [40,80,120,160,200],
		totalItems:0,
		currentPageList:1,
		
				pageChange: function(){
			
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

		            var begin = 0;
		            var end = begin + this.numPerPage;
		            this.currentPageList = this.filteredList.slice(begin, end);
				}
		 };
	
		 $scope.min = 0;
		 $scope.max = 0;
		  $scope.loadNum = function(max) {
			$scope.min = 1;
			$scope.max = max;
			return 	$scope.randomNumber = Math.floor(Math.random()*($scope.max-$scope.min+1)+$scope.min);
		  }
		
		  $scope.generateNumber = function() {
		
		  }
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

		$scope.all_run_progress ={ 
			
			data: [],
			currentPageList: [],
			filteredList: [],
			totalItems: 0,
			numPages: 5,
			maxSize: 5,
			currentPage:1,
			boundary_links: true,
			numPerPage: 5,
			numPerPageSelections: [5,10,20,50,100],
			genders: ['ALL','MALE','FEMALE'],
			gender: 'ALL',
			categories: ['ALL','RUN','DUATHLON','RIDE'],
			category: 'ALL',
			facilityProgressPercent: {},
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
		         
			},
			filterByCategory: function(){
				
				this.filteredList = $filter('categFilter')(this.data,this.category);
			    this.totalItems = this.filteredList.length;				
				var begin = ((this.currentPage - 1) * this.numPerPage);
				var end = begin + this.numPerPage;				
				this.currentPageList = this.filteredList.slice(begin, end);	
			},
			filterByGender: function(){
				
				this.filteredList = $filter('genderFilter')(this.data,this.gender);
			    this.totalItems = this.filteredList.length;				
				var begin = ((this.currentPage - 1) * this.numPerPage);
				var end = begin + this.numPerPage;				
				this.currentPageList = this.filteredList.slice(begin, end);	
			}
			
	  };

	  $scope.austin_run_progress =  angular.copy($scope.all_run_progress);
	  $scope.coimbatore_run_progress =  angular.copy($scope.all_run_progress);
	  $scope.gurugram_run_progress = angular.copy($scope.all_run_progress);
	  $scope.manila_run_progress = angular.copy($scope.all_run_progress);
	  $scope.facilities =['MANILA','GURUGRAM','COIMBATORE','AUSTIN'];
	  
	  $scope.countRemaining ={
			  days: 0,
			  hours: 0,
			  minutes: 0,
			  seconds: 0
	  }
	  $scope.runEvent ={};
	  
	  $scope.event_start_tick = function(){
		  
		  eventStart = moment(runEvent.regStart);
		  currentTime = moment(new Date());
		  
		  eventRem = moment.duration(eventStart.diff(currentTime));	
		  
		  if(eventRem._milliseconds > 0){ //registration not yet started
			  
		  }
		  
	  }
	  $scope.countRemaining = {};
	  
	  $scope.tickEnd = function(){
		 // console.log('end ticking');
		  var end = moment($scope.runEvent.runEnd);
		  var currentTime = moment(new Date());
		  var eventRem = moment.duration(end.diff(currentTime));

		  
		  
		  if(eventRem._milliseconds > 0){ //run event not finished
				 
			  $scope.countRemaining ={
				  days: eventRem.days(),
				  hours: eventRem.hours(),
				  minutes: eventRem.minutes(),
				  seconds: eventRem.seconds(),
				  event_msg: 'Event Ends'
			  }
			  
			  $timeout($scope.tickEnd,1000); //recursive call
			  
		  }
	  }
	  $scope.showAttachmentss = function(run){
		$scope.selectedRuns  = run;
		console.log($scope.selectedRuns);
		$('#img-attachss').modal('show');
	}
	$scope.getRandomSpan = function(max){
		return Math.floor(Math.random() * Math.floor(max));
	  }
	$scope.showAttachmentsss = function(run){
		$scope.selectedRuns  = run;
		console.log($scope.selectedRuns);
		$('#img-attachsss').modal('show');
	}
	  $scope.tickRun =function(){
		 // console.log('  run ticking');
		  var eventStart = moment($scope.runEvent.runStart);
		  var currentTime = moment(new Date());
		  
		  var eventRem = moment.duration(eventStart.diff(currentTime));	
		  
		  if(eventRem._milliseconds > 0){ //registration not yet started
			 
			  $scope.countRemaining ={
				  days: eventRem.days(),
				  hours: eventRem.hours(),
				  minutes: eventRem.minutes(),
				  seconds: eventRem.seconds(),
				  event_msg: 'Event Starts'
			  }
			  
			  $timeout($scope.tickRun,1000); //recursive call
			  
		  }else{
			  $scope.tickEnd();
		  }
	  }
	  
	  $scope.tickReg = function(){
		//  console.log('reg ticking');
		  var eventStart = moment($scope.runEvent.regStart);
		  var currentTime = moment(new Date());
		  
		  var eventRem = moment.duration(eventStart.diff(currentTime));	
		  
		  if(eventRem._milliseconds > 0){ //registration not yet started
			 
			  $scope.countRemaining ={
				  days: eventRem.days(),
				  hours: eventRem.hours(),
				  minutes: eventRem.minutes(),
				  seconds: eventRem.seconds(),
				  event_msg: 'Event Starts'
			  }
			  
			  $timeout($scope.tickReg,1000); //recursive call
			  
		  }else{
			  
			  $scope.tickRun(); //
		  }
		  
	  }
	  
	 
	  $scope.getRunRecords = function(event, facility, callback){
		  RunEventDataOp
		   .getAllProgressByEventyByFacility(event,facility)
		   .then(function(r){
			 callback(r.data);  
		   })
		   .catch(function(e){
			   console.log(e);
		   });
	  }
	  
	  $scope.getAllRunRecords = function(event,callback){
		  RunEventDataOp
		   .getAllProgressByEvent(event)
		   .then(function(r){
			r.data.bike=(r.data.bike)+(r.data.duacountbike);
			r.data.obike=(r.data.bikes*(300)+r.data.dua*(300));
			r.data.totalbike=(r.data.bike/r.data.obike)*.50
			r.data.runs=r.data.runapproved;
			r.data.orun=(r.data.frun*(160)+r.data.dua*(160));
			r.data.totalrun=((r.data.runs/(r.data.orun))*.50);
			r.data.dua=r.data.totalCategoryDistance-(r.data.dua*(300));
			r.data.percentFac=Math.round((r.data.totalbike + r.data.totalrun ) * 100);
			   callback(r.data);
		   })
		   .catch(function(e){
			   console.log(e);
		   });
	  }
	  $scope.getFacilityProgressPercent = function(eventId,facility,callback){
		  RunEventDataOp
		  	.getFacilityProgressPercent(eventId,facility)
		  	.then(function(r){
				  r.data.percent = Math.round((r.data.totalUserDistance / r.data.totalCategoryDistance ) * 100);
				 
				  r.data.bike=r.data.bikeapproved;
				  r.data.obike=(r.data.bikes*(300)+r.data.dua*(300));
				  r.data.totalbike=(r.data.bike)*.50
				  r.data.runs=r.data.runapproved;
				  r.data.orun=(r.data.frun*(160)+r.data.dua*(160));
				  r.data.totalrun=((r.data.runs))*.50;
				  r.data.dua=r.data.totalCategoryDistance-(r.data.dua*(300));
				  if(isNaN(r.data.totalbike))
				  {
					r.data.percentFac=r.data.totalrun;
				  }
				  else if(isNaN(r.data.totalrun))
				  {
					r.data.percentFac=r.data.totalbike;
				  }
				  else if(isNaN(r.data.totalrun) && isNaN(r.data.totalbike))
				  {
					r.data.percentFac=0;
				  }
				  else{
					r.data.percentFac=r.data.totalbike + r.data.totalrun;
				  }
				 
		  		callback(r.data);
		  	})
		  	.catch(function(e){
		  		console.log(e);
		  	})
	  }
	  $scope.start = function(data){
		
		  $scope.runEvent = data;
		  
		  if(data.runStart){
			
			  $scope.getAllRunRecords(data.id, function(r){
					 if(r.length >0){
						 $scope.all_run_progress.init(r);
						 
					 }
			  });
			  
			  $scope.getRunRecords(data.id,'AUSTIN',function(r){
					 if(r.length >0){
						 $scope.austin_run_progress.init(r);
					 }
			  });
			  $scope.getRunRecords(data.id,'COIMBATORE',function(r){
					 if(r.length >0)
						 $scope.coimbatore_run_progress.init(r);
			  });
			  $scope.getRunRecords(data.id,'GURUGRAM',function(r){
					
					 if(r.length >0)
						 $scope.gurugram_run_progress.init(r);
			  });
			  $scope.getRunRecords(data.id,'MANILA',function(r){
				
					 if(r.length >0)
						 $scope.manila_run_progress.init(r);
			  });
			  
			  $scope.getFacilityProgressPercent(data.id,'AUSTIN',function(data){
				  $scope.austin_run_progress.facilityProgressPercent = data;
			  }); 
			  
			  $scope.getFacilityProgressPercent(data.id,'COIMBATORE',function(data){
				  $scope.coimbatore_run_progress.facilityProgressPercent = data;
			  });
			  
			  $scope.getFacilityProgressPercent(data.id,'GURUGRAM',function(data){
				  $scope.gurugram_run_progress.facilityProgressPercent = data;
			  });
			  
			  $scope.getFacilityProgressPercent(data.id,'MANILA',function(data){
				  $scope.manila_run_progress.facilityProgressPercent = data;
			  });
			  
			  $scope.tickReg();
			  
		  }
		
		  
	  }
	  
	
	  $scope.getAllProgressByEventyByFacility = function(){
		  
	  }
	  $scope.init = function(){
		  RunEventDataOp
		   .getCurrentRun()
		   .then(function(r){
			   
			  $scope.start(r.data);
		   })
		   .catch(function(e){
			   console.log(e);
		   })
		   
	  }
	  
	 $scope.init();
	 $scope.initData();
	  
//	  $scope.count = 1;
//	  $scope.tick = function(){
//		  eventTime = moment("2018-04-15T00:10:00");
//		  currentTime = moment(new Date());
//		  duration = moment.duration(eventTime.diff(currentTime));	
//		  
//		  if(duration._milliseconds > 0){
//			  $scope.countRemaining ={
//					  days: duration.days(),
//					  hours: duration.hours(),
//					  minutes: duration.minutes(),
//					  seconds: duration.seconds()
//			  }
//		   
//			  $scope.count = $scope.count + 1;
//			  $timeout($scope.tick, 1000);
//		  }
//		 
//		 
//	  }
//	  $timeout($scope.tick,1000);
	  
	  $scope.scroll = function(loc){
		  
		  if(loc){
			  $('html, body').animate({
				  scrollTop: $(loc).offset().top - 49
			  },600);
		  }else{
			  $('html, body').animate({
				  scrollTop: 0
			  },600);
		  }
		
		  
	}
	  
}]); 