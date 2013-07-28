var newNodeId;
var newNode;

var DarkJSTree = function(data){

    this.customMenu = function(node) {
        var possibleDim = getPossibleChild(node.attr("type"))
        if(possibleDim != null){
            var items;
            items ={
                "Create" : {
                    "label" : "Create "+ possibleDim,
                    "action" : function (obj){
                        var name=openPopUp(possibleDim);
                        APIFactory.callToServers("/abstract-data/create/"+possibleDim+"/name/"+name+"/parentId/"+node.attr("id"),function(data){
                        	newNodeId = data;
                        	newNode = createNodeJSON(newNodeId,possibleDim,node.attr("id"),name);
                        	name = newNode;
                        });
                        return {createItem: this.create(node,-1,name,false,true)};
                    }
                }
            }
            return items;
        }
    }

    this.createTree = function(treeObj){
		alert(JSON.stringify(data))
        //treeObj.innerHTML="";
        $(treeObj).jstree({
            "plugins" : ["themes", "json_data", "ui", "crrm", "contextmenu"],
            "state" : "open",
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

function createNodeJSON(id,type,parentId,displayName){
	  var data = {
                    "attr" : { "id" : id ,"type": type,"parentId": parentId},
                    "data" : displayName
                 };		
      return data;
	}