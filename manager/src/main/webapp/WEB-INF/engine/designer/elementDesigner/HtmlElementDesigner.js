/**
 * Created with JetBrains WebStorm.
 * User: CS13
 * Date: 11/8/13
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */

function HtmlElementDesigner(){

}

HtmlElementDesigner.design = function(id,scriptName,screenName){
    var htmlElement = this.getElementBy(id);
    var className = scriptName;
    var scriptName = this.getScriptName(scriptName,screenName);
    $.getScript(scriptName,function(){
        var ref = eval(className+".getInstance()");
        ref.design(id);
    })
}

HtmlElementDesigner.getElementBy = function(id){
    return  document.getElementById(id);
}

HtmlElementDesigner.getScriptName = function(scriptName,screenName){
    var name = "/pub-controller/graphics/screens/"+screenName+"/scripts/inHouseScripts/js/"+scriptName+".js";
    return name;
}