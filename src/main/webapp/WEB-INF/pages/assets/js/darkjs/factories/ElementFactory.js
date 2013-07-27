function ElementFactory(){

}

ElementFactory.getSelector = function(){
    return new ElementSelector();
}

ElementFactory.getTree = function(data){
    return new DarkJSTree(data);
}

ElementFactory.getTabs = function(tabObj){
    return new DarkTabs(tabObj);
}

ElementFactory.getGrid = function(data,name){
    return new DarkPqGrid(data,name);
}

ElementFactory.getTable = function(data){
    return new DarkGrid(data);
}