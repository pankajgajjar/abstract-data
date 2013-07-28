var TemplateDataService=function()
{
    this.getMetaData=function(callback){
        var dataElements=[{'templateUrl':'/abstract-data/client/login.html','containerId':''},
                          {'templateUrl':'/abstract-data/client/home.html','containerId':''},
                          {'templateUrl':'/abstract-data/treeviewstructure/default','containerId':''},
                          {'templateUrl':'/directorySingleDepth','containerId':''},
                          {'templateUrl':'/file/delete','containerId':''}];
        return dataElements;
    }
}


