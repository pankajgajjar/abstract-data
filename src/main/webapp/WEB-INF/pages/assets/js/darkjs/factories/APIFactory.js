function APIFactory(){

}

APIFactory.callToServer = function(url,callback){
    $.ajax({url:url,success:function(result){
        callback(result);
    },
     error: function (error) {
     
     	callback("error");
              }
    });
}

APIFactory.callToServerWithPost = function(url,callbacks){
    $.ajax({url:url,async:false,success:function(result){
         callbacks(result);
    }});
}
APIFactory.callToServerWithParams = function(url,path,type,callback){
    $.ajax({url:url,data:{path:path},dataType:'json',type: type, success:function(result){
        callback(result);
    }});

}