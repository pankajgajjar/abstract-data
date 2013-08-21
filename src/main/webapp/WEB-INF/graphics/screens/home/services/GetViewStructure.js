function GetViewStructure(){

}

GetViewStructure.get = function(schemaId){
    Router.loadRequest("getSchema",true,onViewStructureSuccess,schemaId);
    /*
     * Comment the below code to stop data mockup, and work with actual server data
     * Section starts here
     */
    /*switch(schemaId)
     {
     case "1":
     {
     Router.loadRequest("getSchema1",true,onChangeSchemaSuccess);
     break;
     }
     case "2":
     {
     Router.loadRequest("getSchema2",true,onChangeSchemaSuccess);
     break;
     }
     }*/
    /*
     * Section Ends here
     */
}

GetViewStructure.getAll = function(){
    Router.loadRequest("getAllSchema",true,onViewStructuresLoaded);
}

this.onViewStructureSuccess = function(data){
    GraphicDataStore.setCurrentSchema(data);
    GraphicDataStore.setSchemaLabel();
    $(document).trigger({
        type: "viewStructureLoaded",
        schemaData: GraphicDataStore.getSchemaArray(),
        schemaChanged: true
    });
    $(document).trigger({
        type: "TREE_ITEM_CLICKED",
        uiData: ""
    });
}

this.onViewStructuresLoaded = function(data){
    data=eval('(' + data + ')');
    GraphicDataStore.setSchemaArray(data);
    GraphicDataStore.setDefaultSchema();
    GraphicDataStore.setSchemaLabel();
    $(document).trigger({
        type: "viewStructureLoaded",
        schemaData: GraphicDataStore.getSchemaArray(),
        schemaChanged: false
    });

}