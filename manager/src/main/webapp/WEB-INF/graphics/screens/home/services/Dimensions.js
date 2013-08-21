function Dimensions(){

}

Dimensions.createDim = function(prefix,action,name,currentPath,flag,callMe){
    Router.forward(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,function(data){
        callMe(data);
    });
}
