$(document).ready(function() {
    getScreenMappingObject();
});

function getScreenMappingObject(){
    Router.forward("/pub/graphics/tacks/ScreenMapping.json",true,function(json){
        parseScreenMappingObject(json);
    });
}

function parseScreenMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setScreenMappingObject(json);
    $.each(json, function (key, item) {
        if(item.loadOnStartup == "true"){
            Router.loadTemplate(key,item.containerId);
        }
    });
    getApiMappingObject();
}

function getApiMappingObject(){
    Router.forward("/pub/graphics/tacks/RequestMapping.json",true,function(json){
        parseApiMappingObject(json);
    });
}

function parseApiMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setApiMappingObject(json);
}

