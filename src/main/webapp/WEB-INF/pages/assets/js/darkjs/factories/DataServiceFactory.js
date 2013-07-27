function DataServiceFactory(){

}

DataServiceFactory.getDataService = function(type){
    var dataService;
    switch(type){
        case "event":
            dataService = new EventDataService();
            break;
        case "element":
            dataService = new ElementDataService();
            break;
        case "template":
            dataService = new TemplateDataService();
            break;
    }
    return dataService;
}
