var currentOpenedNodePath;
var currentOpenedNodeName;


function getLogin(){
    loadTemplate(configTempArray[1].templateUrl);
}

function getTree(){
    APIFactory.callToServer(configTempArray[2].templateUrl,onTreeSuccess);
}
function onTreeSuccess(data){
    $(document).trigger({
        type: "treeDataLoaded",
        treeData: data
    });
    if(currentOpenedNodePath && currentOpenedNodeName) {
        getFolderContent(currentOpenedNodeName,currentOpenedNodePath,true);
    }

}


function getFileContent(name,path){
    APIFactory.callToServerWithParams(configTempArray[3].templateUrl,path,"get", function(data){
        $(document).trigger({
            type: "fileContentLoaded",
            fileData: data,
            currentNodePath: path,
            currentNodeName: name
        });
    });
}

function getFolderContent(name,path,afterDelete){
    afterDelete = typeof afterDelete !== 'undefined' ? afterDelete : false;
    currentOpenedNodePath = path;
    currentOpenedNodeName = name;
    APIFactory.callToServerWithParams(configTempArray[4].templateUrl,path,"get", function(result){
        var renderer = eval('(' + result.renderer + ')');
        var obj = new Object();
        obj.folderData= result.data;
        obj.elementType = renderer[0].type;
        obj.rendererInfo= renderer[0].columns;
        obj.currentNodePath= path ;
        obj.currentNodeName= name;
        obj.deleted= afterDelete

        $(document).trigger({
            type: "folderContentLoaded",
            obj : obj
        });
    });
}

function deleteSelectedFile(pathToDelete){
    APIFactory.callToServerWithParams(configTempArray[5].templateUrl,pathToDelete,"get", function(data){
        if(data[0].msg == "deleted"){
            getTree();
        }
    });
}
