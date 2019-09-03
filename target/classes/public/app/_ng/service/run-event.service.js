angular
	.module('run_proud')
	.factory('RunEventDataOp',['$http',function($http){
		
		var RunEventDataOp = {
				
				getRunEvents : function(){
					return $http({
						method: 'GET',
						dataType: 'json',
						url: '/run_event',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getRunEventParticipants : function(event){
					return $http({
						method: 'GET',
						dataType: 'json',
						url: '/run_event/registry_status/'+event,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				changeRunCategory: function(run_reg){
					return $http({
						method: 'PUT',
						data: run_reg,
						dataType: 'json',
						url: '/run_event/registration/change_category',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getCurrentRun : function(){
					return $http({
						method: 'GET',
						dataType: 'json',
						url: '/run_event/current',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getActiveRunReg : function(){
					
					return $http({
						method: 'POST',
						dataType: 'json',
						url: '/run_event/active_reg',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getEntitlements : function(){
					
					return $http({
						method: 'POST',
						dataType: 'json',
						url: '/run_event/entitlements',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				
				
				getRunCategories : function(){
					return $http({
						method: 'GET',
						dataType: 'json',
						url: '/run_event/categories',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getRunParticipants : function(){
					return $http({
						method: 'GET',
						url: '/run_event/participants',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getRunRecords: function(){
					return $http({
						method: 'GET',
						url: '/run_event/run_records',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getAllRecords: function(){
					return $http({
						method: 'GET',
						url: '/run_event/records',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getAllProgressByEvent: function(id){
					return $http({
						method: 'GET',
						url: '/run_event/event_progress/'+id,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getProgressByEventByCategory: function(event,id){
					return $http({
						method: 'GET',
						url: '/run_event/event_category_progress/'+event+'/'+id,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				getProgressByGender: function(event,id){
					return $http({
						method: 'GET',
						url: '/run_event/event_gender_progress/'+event+'/'+id,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getAllProgressByEventyByFacility: function(event,facility){
					return $http({
						method: 'GET',
						url: '/run_event/event_progress_by_facility/'+event+'/'+facility,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				getUnverifiedRecords : function(){
					return $http({
						method: 'GET',
						url: '/run_event/unverified_records/',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				
				approveRecord : function(record){
					return $http({
						method: 'POST',
						data: record,
						dataType: 'json',
						url: '/run_event/approve_record/',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				disapproveRecord : function(record){
					return $http({
						method: 'POST',
						data: record,
						dataType: 'json',
						url: '/run_event/disapprove_record/',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getRunCategories: function(){
					return $http({
						method: 'GET',
						url: '/run_event/categories',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getCurrentDate: function(){
					return $http({
						method: 'GET',
						url: '/current_time',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getActiveRun : function(){
					return $http({
						method: 'POST',
						url: '/run_event/active_run',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				registerRun : function(reg_detail){
					return $http({
						method: 'POST',
						data: reg_detail,
						dataType: 'json',
						url: '/run_event/register',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				approveRegistration : function(reg_detail){
					return $http({
						method: 'PUT',
						data: reg_detail,
						dataType: 'json',
						url: '/run_event/register',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getUserProgress: function(event){
					return $http({
						method: 'POST',
						data: event,
						dataType: 'json',
						url: '/run_event/user_progress',
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				getUserProgressByid: function(eventId, userId){
					return $http({
						method: 'POST',
						dataType: 'json',
						url: '/run_event/user_progress_by_id/'+eventId+'/'+userId,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				getRunRecordsByEventByUser: function(eventId, userId){
					return $http({
						method: 'POST',
						dataType: 'json',
						url: '/run_event/run_records_by_events_by_user/'+eventId+'/'+userId,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				submitRecord: function(record){
					
					return $http({
						method: 'POST',
						data: record,
						url: '/run_event/addRecord',
						headers: { 'Content-Type': undefined }
					});
					
				},
			
				
				getFacilityProgressPercent: function(eventId,facility){
					return $http({
						method: 'GET',
						dataType: 'json',
						url: '/run_event/run_percent/'+eventId+'/'+facility,
						headers: { 'Content-Type': 'application/json; charset=UTF-8' }
					});
				},
				
				
	
			
		        
		};
		
		return RunEventDataOp;
	}]);