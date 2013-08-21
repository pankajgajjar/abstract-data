var Router = function(){

}

Router.forward = function(url,async,callback){
    $.ajax({
        url:url,
        async:async,
        success:function(result){
            callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });
}

Router.forwardWithParams = function(url,path,type,callback){
    $.ajax({
        url:url,
        data:{path:path},
        dataType:'json',
       /* beforeSend: function(xhr){
            xhr.setRequestHeader('myName','rohan')
        },*/
        type: type,
        success:function(result){
        callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });

}

Router.loadTemplate = function(key,containerID){
    //This sets the default value for the containerElementID
    containerID = typeof containerID !== ('undefined'||"") ? containerID : "mainContainer";
    Router.forward(EngineDataStore.getScreenMappingObject()[key].screenName,true,function(data){
        Router.designScreen(data,containerID);
    });

}


Router.loadRequest = function(key,async,callBack,params){
    if(params){
        Router.forward(EngineDataStore.getApiMappingObject()[key]+params,async,function(data){
            callBack(data);
        });
    }
    else{
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }

}

Router.designScreen = function(data,containerID){
    //data=eval('(' + data + ')');
    var placeHolderElement = document.getElementById(containerID);
    placeHolderElement.innerHTML = data.html;
 
    if(data.events){
        Router.attachEvents(data.events);
    }
    if(data.elements){
        Router.createElements(data.elements);
    }

}

Router.attachEvents = function(events){
events=eval('(' + events + ')');
    for (var binding in events){
        HtmlEventDesigner.addEvents(events[binding].id,events[binding].event,events[binding].func,false);
    }
}

Router.createElements = function(elements){
elements=eval('(' + elements + ')');
    // var elements= [{"id":"leftTree","type":"tree","scriptName":"TreeSelector","screenName":"home"}];
    for (var element in elements){
        HtmlElementDesigner.design(elements[element].id,elements[element].scriptName,elements[element].screenName);
    }

}


