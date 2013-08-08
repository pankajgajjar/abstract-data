var configBaseURL;

function setConfigBaseURL(url){
    configBaseURL = "http://192.168.135.234:3000/";
}

var configTempArray;
function setConfigTemplateArray(configArray){
    configTempArray = configArray;
}


var configEventsArray;
function setEventsArray(events){
    configEventsArray = events;
}


var configElementsArray;
function setElementsArray(elements){
    configElementsArray = elements;
}


var schemaArray=[];
function setSchemaArray(schemaData){
    schemaArray = schemaData;
}



var currentSchema;
function setDefaultSchema(){
    for(var i=0; i< schemaArray.length; i++){
        if(schemaArray[i].default == "true"){
            setCurrentSchema(schemaArray[i]);
        }
    }
}

function setCurrentSchema(schema){
    currentSchema = schema;
}

var dimensionArray;
function setDimensionArray(dimArray){
    dimensionArray = dimArray;
}

var newNode;

function getFirstDimension(){
    return currentSchema.structure[0].name+"s";
}

function getPossibleChild(dim){
    if(dim === 'root'){
        return currentSchema.structure[0].name;
    }
    else{
        for(var i=0; i< currentSchema.structure.length; i++){
            if(dim === currentSchema.structure[i].name){
                    return currentSchema.structure[i].possibleChild;
            }
        }
    }

}

function openPopUp(dim){
    var x;
    var name=prompt("Please enter name",dim);

    if (name!=null){
        x= name;
        return x;
    }
    else{
    	return null
    }
}
