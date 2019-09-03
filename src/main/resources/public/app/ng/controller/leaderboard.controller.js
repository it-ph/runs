angular.module("run_proud").controller("LeaderboardController",["$rootScope","$scope","$state","$cookies","$http","$filter","Access","RunEventDataOp",function(t,n,e,r,i,a,s,o){n.user=void 0,n.error=void 0,n.currentRun=null,n.all_records={data:[],currentPageList:[],filteredList:[],totalItems:0,numPages:5,maxSize:5,currentPage:1,boundary_links:!0,numPerPage:10,numPerPageSelections:[5,10,20,50,100],pageChange:function(){var t=(this.currentPage-1)*this.numPerPage,e=t+this.numPerPage;this.currentPageList=this.filteredList.slice(t,e)},searchKeyword:"",search:function(){this.filteredList=a("filter")(this.data,this.searchKeyword),this.totalItems=this.filteredList.length;var t=(this.currentPage-1)*this.numPerPage,e=t+this.numPerPage;this.currentPageList=this.filteredList.slice(t,e)},numPageChange:function(){var t=(this.currentPage-1)*this.numPerPage,e=t+this.numPerPage;this.currentPageList=this.filteredList.slice(t,e)},init:function(t){this.data=t,this.totalItems=t.length,this.filteredList=t,this.filteredList=a("filter")(this.data,this.searchKeyword);var e=(this.currentPage-1)*this.numPerPage,r=e+this.numPerPage;this.currentPageList=this.filteredList.slice(e,r)}},n.blitz=angular.copy(n.all_records),n.blitz.init=function(t){n.blitz.data=t,n.blitz.totalItems=t.length,n.blitz.filteredList=t;var e=(n.blitz.currentPage-1)*n.blitz.numPerPage,r=e+n.blitz.numPerPage;n.blitz.currentPageList=n.blitz.filteredList.slice(e,r)},n.range1=angular.copy(n.all_records),n.blitz_range1=angular.copy(n.all_records),n.range2=angular.copy(n.all_records),n.blitz_range2=angular.copy(n.all_records),n.range3=angular.copy(n.all_records),n.full_metal=angular.copy(n.all_records),n.getCurrentRun=function(e){o.getCurrentRun().then(function(t){e(t.data)}).catch(function(t){console.log(t)})},n.getAllProgressByEvent=function(t){o.getAllProgressByEvent(t).then(function(t){n.all_records.init(t.data)}).catch(function(t){console.log(t)})},n.getProgressByEventByCategory=function(t,e,r){o.getProgressByEventByCategory(t,e).then(function(t){r(t.data)}).catch(function(t){console.log(t)})},n.initData=function(){n.getCurrentRun(function(t){t.runStart&&(n.currentRun=t,n.getAllProgressByEvent(t.id),n.getProgressByEventByCategory(t.id,1,function(t){n.blitz.init(t)}),n.getProgressByEventByCategory(t.id,2,function(t){n.range1.init(t)}),n.getProgressByEventByCategory(t.id,3,function(t){n.blitz_range1.init(t)}),n.getProgressByEventByCategory(t.id,4,function(t){n.range2.init(t)}),n.getProgressByEventByCategory(t.id,5,function(t){n.blitz_range2.init(t)}),n.getProgressByEventByCategory(t.id,6,function(t){n.range3.init(t)}),n.getProgressByEventByCategory(t.id,7,function(t){n.full_metal.init(t)}))})},n.viewProgress=function(t){e.go("user-progress",{eventId:t.event.id,userId:t.user.id})},n.checkUser=function(){if(s.user)n.initData(),n.user=s.getUser(),n.$emit("childEmit",n.user);else{var t=r.get("run_proud_token");t?s.getClaimsFromToken(t,function(t){n.user=t.data.principal.user,n.$emit("childEmit",n.user),n.initData()},function(t){console.log(t),s.clearData(),r.remove("run_proud_token"),e.go("login")}):e.go("login")}},n.checkUser()}]);