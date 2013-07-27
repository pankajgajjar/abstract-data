var TemplateRegistrar=function(dataService)
{
    var dataService = dataService;

    this.register=function(){
        var data = dataService.getMetaData();
        if(data!=null){
            setConfigTemplateArray(data);
        }
    }
}
