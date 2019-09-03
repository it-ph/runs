angular
.module("run_proud")
.factory("LoginDataOp",["$http",function(a){
    return{
        
        
        
        getMail : function(){
            return a({
                method: 'GET',
                dataType: 'json',
                url: '/mail',
                headers: { 'Content-Type': 'application/json; charset=UTF-8' }
            })
        },
        login:function(t,e,n){
        return a({method:"POST",data:t,dataType:"json",url:"/authenticate",headers:{"Content-Type":"application/json; charset=UTF-8"}})
        .then(function(t){token=t.data.access_token,a.defaults.headers.common.Authorization="Bearer "+token,e(t)})
        .catch(function(t){n(t)})},
        getClaimsFromToken:function(t){return a({method:"POST",url:"/user-claims",headers:{Authorization:"Bearer "+t}})}}}]);