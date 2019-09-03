angular.module("run_proud").controller("UserController",["$rootScope","$scope","$state","$cookies","$filter","Access","UserDataOp",function(e,s,n,i,o,r,t){s.users={data:[],currentPageList:[],filteredList:[],totalItems:0,numPages:void 0,maxSize:5,currentPage:1,boundary_links:!0,numPerPage:10,numPerPageSelections:[5,10,20,50,100],pageChange:function(){var e=(this.currentPage-1)*this.numPerPage,t=e+this.numPerPage;this.currentPageList=this.filteredList.slice(e,t)},searchKeyword:"",search:function(){this.filteredList=o("filter")(this.data,this.searchKeyword),this.totalItems=this.filteredList.length;var e=(this.currentPage-1)*this.numPerPage,t=e+this.numPerPage;this.currentPageList=this.filteredList.slice(e,t)},numPageChange:function(){var e=(this.currentPage-1)*this.numPerPage,t=e+this.numPerPage;this.currentPageList=this.filteredList.slice(e,t)},init:function(e){this.data=e,this.totalItems=e.length,this.filteredList=e;var t=(this.currentPage-1)*this.numPerPage,s=t+this.numPerPage;this.currentPageList=this.filteredList.slice(t,s)}},s.getUsers=function(){t.getUsers().then(function(e){s.users.init(e.data)}).catch(function(e){console.log(e)})},s.resetPass=function(e){$("#loading").modal("show"),t.resetPass(e).then(function(e){console.log(e),s.getUsers(),$("#loading").modal("hide")}).catch(function(e){console.log(e),$("#loading").modal("hide")})},s.disableUser=function(e){$("#loading").modal("show"),t.disableUser(e).then(function(e){s.getUsers(),$("#loading").modal("hide")}).catch(function(e){console.log(e),$("#loading").modal("hide")})},s.confirm_delete=!1,s.user_to_delete=null,s.showDeleteConfirm=function(e){s.user_to_delete=e,$("#confirm-delete").modal("show")},s.deleteUser=function(){s.error=null,t.deleteUser(s.user_to_delete).then(function(e){console.log(e),s.getUsers(),$("#confirm-delete").modal("hide")}).catch(function(e){console.log(e),s.error=e.data.message})},s.enableUser=function(e){$("#loading").modal("show"),t.enableUser(e).then(function(e){s.getUsers(),$("#loading").modal("hide")}).catch(function(e){console.log(e),$("#loading").modal("hide")})},s.start=function(){s.getUsers(),s.$emit("childEmit",s.user)},s.checkUser=function(e){if(r.user)s.user=r.user,s.start();else{var t=i.get("run_proud_token");t?r.getClaimsFromToken(t,function(e){s.user=e.data.principal.user,s.start()},function(e){console.log(e),r.clearData(),i.remove("run_proud_token"),n.go("login")}):n.go("login")}},s.checkUser(s.start)}]);