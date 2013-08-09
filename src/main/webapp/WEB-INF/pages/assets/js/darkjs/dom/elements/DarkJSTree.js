var newNodeId;
var newNode;

var DarkJSTree = function(data){
    var currentPath;

    this.customMenu = function(node) {
        var possibleDim = getPossibleChild(node.attr("type"))
        if(possibleDim != null){
             var icontype;
             switch(possibleDim){
                 case 'Campaign' : icontype="assets/css/images/dimension_campaign.png";
                 break;
                 case 'PublicationGroup' : icontype="assets/css/images/error_small.png"
                 break;
                 case 'MasterPublication' :  icontype="assets/css/images/eraser-small.png";
                 break;
                 case 'Publication' : icontype="assets/css/images/dimension_publication.png";
                 break;
             }

            var items;
            items ={
                "Create" : {
                    "label" : "Create "+ possibleDim,
                    "icon": icontype,
                    "action" : function (obj){

                        var name=openPopUp(possibleDim);
                        
                        if(name != null){
                        	if(node.attr("type")!="root"){
                            	currentPath =  node.attr("path")+","+ name;
	                        }
	                        else{
	                            currentPath =  "";
	                            currentPath += name;
	                        }
	                        APIFactory.callToServerWithPost("/pub2.0/create/"+possibleDim+"/name/"+name+"/path/"+currentPath,function(data){
	                        	newNodeId = data.id;
	                        	newNode = createNodeJSON(newNodeId,possibleDim,node.attr("id"),name,data.path);
	                        	name = newNode;
	                        });
	                        return {createItem: this.create(node,-1,name,false,true)};
	                    }
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
            "themes" : {
                    "theme" : "apple",
                    "dots" : false,
                    "icons" : true
            },
            "state" : "open",
            "json_data" : {
                "data" : data
            },
            "contextmenu" : {
                "items": this.customMenu
            }

        }).bind("select_node.jstree", function(evt, data){
                //selected node object: data.inst.get_json()[0];
                //selected node text: data.inst.get_json()[0].data
            })
    }

}

function createNodeJSON(id,type,parentId,displayName,path){
	  var data = {
                    "attr" : { "id" : id ,"type": type,"parentId": parentId,"path": path},
                    "data" : displayName
                 };
      return data;
}