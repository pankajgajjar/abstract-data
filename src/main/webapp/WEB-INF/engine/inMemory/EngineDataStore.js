var EngineDataStore = function(){
    var screenMappingObject;
    var apiMappingObject;
}

EngineDataStore.setScreenMappingObject = function(obj){
    this.screenMappingObject = obj;
}

EngineDataStore.getScreenMappingObject = function(){
    return this.screenMappingObject;
}

EngineDataStore.setApiMappingObject = function(obj){
    this.apiMappingObject = obj;
}

EngineDataStore.getApiMappingObject = function(){
    return this.apiMappingObject;
}
