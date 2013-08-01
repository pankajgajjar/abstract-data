function getLogin(){
    loadTemplate(configTempArray[1].templateUrl);
}

function getAllSchema(){
	APIFactory.callToServer(configTempArray[2].templateUrl+"default",onSchemaSuccess);
}

function onSchemaSuccess(data){
  	data=eval('(' + data + ')');
    var structure = getNodesFromSchema(data,0);
    structure = structure.substring(0, structure.length - 1)
    setCurrentSchema(structure);
    setSchemaLabel();
    setDimensionArray(keyArray);
    $(document).trigger({
        type: "schemaLoaded",
        schemaData: data,
        schema: structure,
        schemaChanged: false
    });
}

function setSchemaLabel(){
    $("#txt").show();
    $("#txt").text(currentSchema);
}

function onChangeSchemaSuccess(data){
    data=eval('(' + data + ')');
    var structure = getNodesFromSchema(data,0);
    structure = structure.substring(0, structure.length - 1)
    setCurrentSchema(structure);
    setSchemaLabel();
    setDimensionArray(keyArray);
    $(document).trigger({
        type: "schemaLoaded",
        schemaData: data,
        schema: structure ,
        schemaChanged: true
    });
}

function getCreatedTree(){
   APIFactory.callToServer(configTempArray[3].templateUrl+currentSchema,onTreeSuccess);
}

function onTreeSuccess(data){
$(document).trigger({
        type: "treeDataLoaded",
        treeData: data
    });
}

function changeSchema(schemaId){
    APIFactory.callToServer(configTempArray[2].templateUrl+schemaId,onChangeSchemaSuccess);
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