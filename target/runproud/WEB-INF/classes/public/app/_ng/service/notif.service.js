angular
	.module('run_proud')
	.factory('NotifDataOp',['$http',function($http){
		
		var NotifDataOp ={
				
			getNotification: function(){
				
				return 	$http({
	    			method: 'GET',
	    			url: '/notifications'
	    		})
			},
			
			viewNotif: function(notif){
				
				return $http({
					method: 'POST',
					data: notif,
					dataType: 'json',
					url: '/notifications/view',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
			},
			
			viewAll: function(notif){
				
				return $http({
					method: 'POST',
					data: notif,
					dataType: 'json',
					url: '/notifications/viewAll',
					headers: { 'Content-Type': 'application/json; charset=UTF-8' }
				});
			},
				
		};
		
		return NotifDataOp;
	}]);