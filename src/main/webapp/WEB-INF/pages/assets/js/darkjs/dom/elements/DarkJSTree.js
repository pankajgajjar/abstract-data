var newNodeId;

var DarkJSTree = function(data){
    this.customMenu = function(node) {
    var self = this;
        var possibleDim = getPossibleChild(node.attr("type"))
        if(possibleDim != null){
            var items;
            items ={
                "Create" : {
                    "label" : "Create "+ possibleDim,
                    "action" : function (obj){
                        var name=openPopUp(possibleDim);
                        newNode = createNodeJSON(possibleDim,node.attr("id"),name);
                        
                        APIFactory.callToServers(configTempArray[2].templateUrl,function(data){
                        	newNodeId = data;
                        	alert(1)
                        });
                        alert(2)
                         return {createItem: this.create(node,-1,name,false,true)};
                        //Now create a json and send to the backend api
                    }
                }
            }
            return items;
        }
    }

    this.createTree = function(treeObj){

        //treeObj.innerHTML="";
        $(treeObj).jstree({
            "plugins" : ["themes", "json_data", "ui", "crrm", "contextmenu"],
            "json_data" : {
                "data" : data
            },
            "contextmenu" : {
                "items": this.customMenu
            }

        }).bind("select_node.jstree", function(evt, data){
                alert(JSON.stringify(data.inst.get_json()));
                //selected node object: data.inst.get_json()[0];
                //selected node text: data.inst.get_json()[0].data
            })
    }

}

function createNodeJSON(type,parentId,displayName){
	  var data = {
                    "attr" : { "id" : "" ,"type": type,"parentId": parentId},
                    "data" : displayName
                 };		
      return data;
	}