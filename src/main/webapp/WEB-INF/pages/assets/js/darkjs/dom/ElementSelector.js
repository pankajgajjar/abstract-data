var ElementSelector=function(){

    var dom = DomFactory.getDom();

    this.select=function(element){

        switch (element.type){
            case 'tree':{
                /*$(document).bind("treeDataLoaded", function onTreeDataLoadedHandler(e){
                    var data = e.treeData;
                    var treeObj = dom.getElementBy(element.id);
                    var darkTree = ElementFactory.getTree(data);
                    darkTree.createTree(treeObj);
                });*/
                var treeData;
                
                $(document).bind("schemaLoaded", function onSchemaLoadedHandler(e){
                    //This is to create folder as per first element of the current schema
                    treeData = {
	                        "attr" : { "id" : "-1" ,"type": 'root'},
	                        "data" : getFirstDimension(),
	                        children:[]
                	};
					
					$(document).bind("treeDataLoaded", function onSchemaLoadedHandler(e){
					
					if(e.treeData != "error"){
						treeData.children = eval('(' + e.treeData + ')');
					}
						
						var treeObj = dom.getElementBy(element.id);
	                	var darkTree = ElementFactory.getTree(treeData);
	                	darkTree.createTree(treeObj); 
					});
					getCreatedTree();
					
                    
            	});

                break;
            }

            case 'dropdown':{
                $(document).bind("schemaLoaded", function onSchemaLoadedHandler(e){
                    //This is to set the current schema display
                    var data = e.schema;
                    var dropdownObj = dom.getElementBy(element.id);
                    var dropdown = ElementFactory.getDropDown(data);
                    dropdown.createDropDown(dropdownObj);
                });
                getServerData(element.type);
                break;
            }
        }
    }
}