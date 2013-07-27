function RegistrarFactory(){

}

RegistrarFactory.getRegistrar = function(type){

    var registrar;
    switch(type){
        case "event":
            registrar = new EventRegistrar(DataServiceFactory.getDataService("event"));
            break;
        case "element":
            registrar = new ElementRegistrar(DataServiceFactory.getDataService("element"));
            break;
        case "template":
            registrar = new TemplateRegistrar(DataServiceFactory.getDataService("template"));
            break;
    }
    return registrar;
}