var ElementRegistrar=function(dataService)
{
      var dataService = dataService;
      var selector= ElementFactory.getSelector();

      this.register=function(elementArray){
          var data = dataService.getMetaData(elementArray);
          setElementsArray(data);
          for (var element in configElementsArray){
               selector.select(configElementsArray[element]);
          }
      }
}

