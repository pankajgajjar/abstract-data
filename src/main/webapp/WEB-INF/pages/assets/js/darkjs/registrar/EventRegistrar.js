var EventRegistrar=function(dataService)
{
    var dataService = dataService;

    this.register=function(eventArray,domManipulator){
        var data = dataService.getMetaData(eventArray);
        setEventsArray(data)
        if(configEventsArray!=null){
            for (var binding in configEventsArray){
                var id=configEventsArray[binding].id;
                domManipulator.addEvents(id,configEventsArray[binding].event,configEventsArray[binding].func,false);
            }
        }
    }
}
