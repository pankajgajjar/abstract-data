function ElementFactory(){

}

ElementFactory.getSelector = function(){
    return new ElementSelector();
}

ElementFactory.getTree = function(data){
    return new DynaTree(data);
}

ElementFactory.getTabs = function(tabObj){
    return new DarkTabs(tabObj);
}

ElementFactory.getDropDown = function(data){
    return new DropDownJS(data);
}