var TemplateDataService=function()
{
    this.getMetaData=function(callback){

        var dataElements=[{'templateUrl':'/pub2.0/client/login','containerId':''},
                          {'templateUrl':'/pub2.0/client/home','containerId':''},
                          {'templateUrl':'/pub2.0/all','containerId':''},
                          {'templateUrl':'/pub2.0/dimension/get/','containerId':''},
                          {'templateUrl':'/pub2.0/','containerId':''}];
        return dataElements;

       /* var dataElements=[
            {'templateUrl':'/pub2.0/client/login','containerId':''},
            {'templateUrl':'/pub2.0/client/home','containerId':''},
            {'templateUrl':'assets/data/allSchema.json','containerId':''},
            {'templateUrl':'assets/data/schema2.json','containerId':''},
            {'templateUrl':'assets/data/treeData2.json','containerId':''},
            {'templateUrl':'assets/data/treeData1.json','containerId':''},
            {'templateUrl':'assets/data/schema1.json','containerId':''}];*/

        return dataElements;
    }
}


