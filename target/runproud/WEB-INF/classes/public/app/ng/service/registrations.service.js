angular.module("run_proud").factory("RegistrationDataOp",["$http",function(t){return{getRegistrations:function(){return t({method:"GET",dataType:"json",url:"/registrations",headers:{"Content-Type":"application/json; charset=UTF-8"}})},register:function(e){return t({method:"POST",data:e,dataType:"json",url:"/register",headers:{"Content-Type":"application/json; charset=UTF-8"}})},approve:function(e){return t({method:"POST",data:e,dataType:"json",url:"/registrations/approve",headers:{"Content-Type":"application/json; charset=UTF-8"}})},deleteReg:function(e){return t({method:"POST",data:e,dataType:"json",url:"/registrations/delete",headers:{"Content-Type":"application/json; charset=UTF-8"}})},deleteUserReg:function(e){return t({method:"POST",data:e,dataType:"json",url:"/users/registrations/delete",headers:{"Content-Type":"application/json; charset=UTF-8"}})}}}]);