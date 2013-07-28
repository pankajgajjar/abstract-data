var currentOpenedNodePath;
var currentOpenedNodeName;


function getLogin(){
    loadTemplate(configTempArray[1].templateUrl);
}

function createNode(){
	
}

function getAllSchema(){
	APIFactory.callToServer(configTempArray[2].templateUrl,onSchemaSuccess);
}

function onSchemaSuccess(data){
  	data=eval('(' + data + ')');
    var structure = getNodesFromSchema(data);
    structure = structure.substring(0, structure.length - 1)
    setCurrentSchema(structure);
    setDimensionArray(keyArray);
    $(document).trigger({
        type: "schemaLoaded",
        schemaData: data,
        schema: structure
    });
}

function getCreatedTree(){
   APIFactory.callToServer("/abstract-data/get",onTreeSuccess);
}

function onTreeSuccess(data){
    $(document).trigger({
        type: "treeDataLoaded",
        treeData: data
    });
}

var str="";
var keyArray = [];

function getNodesFromSchema(schema){
    var i=0
    for (var key in schema){
        //alert(key + "key")
        str += key+"-";
        keyArray.push(key);
        if(schema[key] != 'leaf') {
            getNodesFromSchema(schema[key]);
        }
    }
    return str;
}
