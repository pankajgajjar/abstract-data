var DOMManipulator = function(){
    this.addEvents = function(id,event,func,flag){
        var htmlElement = this.getElementBy(id);
        htmlElement.addEventListener(event,func,flag);
    }

    this.getElementBy = function(id){
        return  document.getElementById(id);
    }
}