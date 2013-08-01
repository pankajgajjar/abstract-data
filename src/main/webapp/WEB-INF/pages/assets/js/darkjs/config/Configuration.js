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


var currentSchema;
function setCurrentSchema(schema){
    currentSchema = schema;
}


var dimensionArray;
function setDimensionArray(dimArray){
    dimensionArray = dimArray;
}

var newNode;

function getFirstDimension(){
    return dimensionArray[0]+"s";
}

function getPossibleChild(dim){
    if(dim === 'root'){
        return dimensionArray[0];
    }
    else{
        for(var i=0; i< dimensionArray.length; i++){
            if(dim === dimensionArray[i]){
                if(i != dimensionArray.length){
                    return dimensionArray[i+1];
                }
                else{
                    return null;
                }
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
