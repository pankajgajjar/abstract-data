function GetTree(){

}

GetTree.get = function(){
    Router.loadRequest("getTree",true,onTreeSuccess,GraphicDataStore.getCurrentSchema().name);
    /*
     * Comment the below code to stop data mockup, and work with actual server data
     * Section starts here
     */
    /*
     if(GraphicDataStore.getCurrentSchema().id == "1"){
     Router.loadRequest("getTree1",true,onTreeSuccess);
     }   else if(GraphicDataStore.getCurrentSchema().id == "2"){
     Router.loadRequest("getTree2",true,onTreeSuccess);
     }*/
    /*
     * Section Ends here
     */
}

GetTree.createDim = function(prefix,action,name,currentPath,flag,callMe){
    Router.forward(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,function(data){
             callMe(data);
    });
}

this.onTreeSuccess = function(data){

    $(document).trigger({
        type: "treeDataLoaded",
        treeData: data
    });
}