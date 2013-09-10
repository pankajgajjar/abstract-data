function loadTemplate(templateUrl,containerElementID){
    //This sets the default value for the containerElementID
    containerElementID = typeof containerElementID !== 'undefined' ? containerElementID : "templateLoaderIndex";
    //Gets the div in which template needs to be loaded
    var placeHolderElement = document.getElementById(containerElementID);

    //Loads the template
    readTemplateHTML(templateUrl, function(text){
        if(templateUrl != configTempArray[0].templateUrl){
               makeLogoutVisible();
        }

        //Loads the template coming from server
        if(text.html){
            placeHolderElement.innerHTML = text.html;

            /*Manual Logic Added Here by Ravi Sharma, starts here */
            if(templateUrl == "/pub2.0/client/home"){
                $('#schemaDropDown').hide();

                actualHeight = $('#mainAnimationContainer').height();

                $('#selectLbl').click(function(){
                    if(flag){
                        $('#mainAnimationContainer').animate({height: "70px"}, 500, function(){
                            $('#schemaDropDown').show();
                            $("#txt").show();
                        });
                        $(this).text("Hide View");
                        flag = false;
                    }else{
                        $('#schemaDropDown').hide();
                        $('#mainAnimationContainer').animate({height: actualHeight}, 500);
                        $(this).text("Select View");
                        flag = true;
                    }
                });
            }
            /*Manual Logic Added Here by Ravi Sharma, ends here */

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

function makeLogoutVisible(){
    $(".logout").css( "visibility", "visible" );
    $(".wrapper").css( "visibility", "visible" );
    $(".profileLogo").css( "visibility", "visible" );
    $(".logout").click(function(){
        location.reload();
    })
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
var flag = true;
var actualHeight;

window.onload = function(){
    var templateRegistrar= RegistrarFactory.getRegistrar("template");
    var registrarContextForTemplates=ContextFactory.getContext();
    registrarContextForTemplates.register(templateRegistrar) ;
    loadTemplate(configTempArray[0].templateUrl);
}
