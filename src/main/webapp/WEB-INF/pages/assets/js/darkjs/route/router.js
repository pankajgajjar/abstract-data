function loadTemplate(templateUrl,containerElementID){
    //This sets the default value for the containerElementID
    containerElementID = typeof containerElementID !== 'undefined' ? containerElementID : "templateLoaderIndex";
    //Gets the div in which template needs to be loaded
    var placeHolderElement = document.getElementById(containerElementID);

    //Loads the template
    readTemplateHTML(templateUrl, function(text){
        //Loads the template coming from server
        if(text.html){
            placeHolderElement.innerHTML = text.html;
        }
        //Loads the elements string object into array object
        if(text.elements){
            var jsonelElements=eval('(' + text.elements + ')');
            registerElements(jsonelElements);
        }
        //Loads the events string object into array object
        if(text.events){
            var jsonEvents=eval('(' + text.events + ')');
            registerEvents(jsonEvents);
        }
    });
}

function readTemplateHTML(templateUrl, callback) {
    APIFactory.callToServer(templateUrl,function(data){
        callback(data);
    });
}

function registerElements(elementArray){
    var domManipulator=DomFactory.getDom();
    var elementRegistrar=RegistrarFactory.getRegistrar("element");
    var registerContextForElements=ContextFactory.getContext();
    registerContextForElements.register(elementRegistrar,elementArray);
}

function registerEvents(eventArray){
    var domManipulator=DomFactory.getDom();
    var eventRegistrar=RegistrarFactory.getRegistrar("event");
    var registrarContextForEvents=ContextFactory.getContext();
    registrarContextForEvents.register(eventRegistrar,eventArray,domManipulator);
}

window.onload = function(){
    var templateRegistrar= RegistrarFactory.getRegistrar("template");
    var registrarContextForTemplates=ContextFactory.getContext();
    registrarContextForTemplates.register(templateRegistrar) ;
    loadTemplate(configTempArray[0].templateUrl);
}
