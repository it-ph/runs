angular.module("run_proud").controller("ReportController",["$rootScope","$scope","$state","$cookies","$timeout","$filter","Access","RunEventDataOp",function(e,o,t,n,r,u,s,c){o.user=null,o.isAdmin=function(){return!!o.user&&"ADMIN"==o.user.role},o.start=function(){o.$emit("childEmit",o.user)},o.checkUser=function(e){if(s.user)o.user=s.user,o.start();else{var r=n.get("run_proud_token");r?s.getClaimsFromToken(r,function(e){o.user=e.data.principal.user,o.start()},function(e){console.log(e),s.clearData(),n.remove("run_proud_token"),t.go("login")}):t.go("login")}},o.checkUser(o.start)}]);