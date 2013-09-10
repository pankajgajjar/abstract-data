
var tempStoreSchemaId = 1;

function getLogin(){
    loadTemplate(configTempArray[1].templateUrl);
}

function getAllSchema(){
	//APIFactory.callToServer(configTempArray[2].templateUrl+"default",onSchemaSuccess);
    APIFactory.callToServer(configTempArray[2].templateUrl,onSchemaSuccess);
}

function onSchemaSuccess(data){
  	data=eval('(' + data + ')');
    setSchemaArray(data);
    setDefaultSchema();
    setSchemaLabel();
    $(document).trigger({
        type: "schemaLoaded",
        //schemaData: data,
        schemaData: schemaArray,
        schemaChanged: false
    });

   /* var structure = getNodesFromSchema(data,0);
    structure = structure.substring(0, structure.length - 1)
    setCurrentSchema(structure);
    setSchemaLabel();
    setDimensionArray(keyArray);
    $(document).trigger({
        type: "schemaLoaded",
        schemaData: data,
        schema: structure,
        schemaChanged: false
    });*/
}

function setSchemaLabel(){
    $("#txt").show();
    $("#txt").text(currentSchema.name);
}

function onChangeSchemaSuccess(data){
    //data=eval('(' + data + ')');
    setCurrentSchema(data);
    setSchemaLabel();
    $(document).trigger({
        type: "schemaLoaded",
        //schemaData: data,
        schemaData: schemaArray,
        schemaChanged: true
    });
}

function getCreatedTree(){

  APIFactory.callToServer(configTempArray[3].templateUrl+currentSchema.name,onTreeSuccess);

    /*
     * Comment the below code to stop data mockup, and work with actual server data
     * Section starts here
     */

    /*if(currentSchema.id == "1"){
        APIFactory.callToServer(configTempArray[5].templateUrl,onTreeSuccess);
    }   else if(currentSchema.id == "2"){
        APIFactory.callToServer(configTempArray[4].templateUrl,onTreeSuccess);
    }*/
    /*
     * Section Ends here
     */
}

function onTreeSuccess(data){
    $(document).trigger({
            type: "treeDataLoaded",
            treeData: data
    });
}

var dummyCounter;
function getDummyCounter(){
    dummyCounter += 1;
    return dummyCounter;
}

function changeSchema(schemaId){
    APIFactory.callToServer(configTempArray[4].templateUrl+schemaId,onChangeSchemaSuccess);

    /*
    * Comment the below code to stop data mockup, and work with actual server data
    * Section starts here
    */
    /*tempStoreSchemaId = schemaId;
    switch(schemaId)
    {
        case "1":
        {
            APIFactory.callToServer(configTempArray[6].templateUrl,onChangeSchemaSuccess);
            break;
        }
        case "2":
        {
            APIFactory.callToServer(configTempArray[3].templateUrl,onChangeSchemaSuccess);
            break;
        }
    }*/
    /*
     * Section Ends here
     */
}

var str;
var keyArray;

function getNodesFromSchema(schema, mode){
    if(mode == 0){
        str="";
        keyArray = [];
    }
    for (var key in schema){
        str += key+"-";
        keyArray.push(key);
        if(schema[key] != 'leaf') {
            getNodesFromSchema(schema[key],-1);
        }
    }
    return str;
}