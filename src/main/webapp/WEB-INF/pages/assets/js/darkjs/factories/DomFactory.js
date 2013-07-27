function DomFactory(){

}
DomFactory.getDom = function(){
    return new DOMManipulator();
}

DomFactory.getSelector = function(){
    return new ElementSelector();
}
