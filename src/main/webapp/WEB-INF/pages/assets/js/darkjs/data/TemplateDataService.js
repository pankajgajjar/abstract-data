var TemplateDataService=function()
{
    this.getMetaData=function(callback){
        var dataElements=[{'templateUrl':'/home','containerId':''},
                          {'templateUrl':'/login','containerId':''},
                          {'templateUrl':'/directory','containerId':''},
                          {'templateUrl':'/file','containerId':''},
                          {'templateUrl':'/directorySingleDepth','containerId':''},
                          {'templateUrl':'/file/delete','containerId':''}];
        return dataElements;
    }
}


