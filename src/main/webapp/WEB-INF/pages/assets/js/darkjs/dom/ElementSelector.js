var ElementSelector=function(){

    var dom = DomFactory.getDom();

    this.select=function(element){

        switch (element.type){
            case 'tree':{
                $(document).bind("treeDataLoaded", function onTreeDataLoadedHandler(e){
                    var data = e.treeData;
                    var treeObj = dom.getElementBy(element.id);
                    var darkTree = ElementFactory.getTree(data);
                    darkTree.createTree(treeObj);
                });
                getServerData(element.type);
                break;
            }
        }
    }
}