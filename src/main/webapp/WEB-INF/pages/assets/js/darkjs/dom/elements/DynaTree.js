var newNodeId;
var newNode;
var temp;

var DynaTree = function(data){
    var currentPath;

    function menuExists(type){
        var contextMenusHolder = document.getElementById('menus')
        uls = contextMenusHolder.getElementsByTagName('ul');
        for (i=0; i<uls.length; i++) {
            if (uls[i].id == type) {
               //alert("exists")
                return true;
            }
        }
        return false;
    }

    function createList(type,menuOptions){
        var contextMenusHolder = document.getElementById('menus');
        var options = menuOptions;
        var list = document.createElement("ul");
        list.id = type;
        list.setAttribute("class", "contextMenu");
        //list.addClass("contextMenu");
        for (var i in options) {
            var anchor = document.createElement("a");
            anchor.href = "#"+options[i];
            anchor.innerText = "Create"+options[i];

            var elem = document.createElement("li");
            elem.appendChild(anchor);
            list.appendChild(elem);
        }
        contextMenusHolder.appendChild(list);
    }

    // --- Contextmenu --------------------------------------------------
    function bindContextMenu(span,type) {
        var possibleDim=[];
        possibleDim  = getPossibleChild(type);
        if(possibleDim != ""){
            //Check as per type of node if menu exists later
            var alreadyExists = menuExists(type)
            if(!alreadyExists){
                createList(type,possibleDim);
            }
            $(span).contextMenu({menu: type}, function(action, el, pos) {
                var name=prompt("Please enter "+action+" name","");
                if(name != null){
                    if(name != ""){
                        var parentNode = $.ui.dynatree.getNode(el);
                        if(parentNode.data.type == "root"){
                            currentPath = "-1";
                        }
                        else{
                            currentPath = parentNode.data.path+","+ parentNode.data.title;
                            if(currentPath.indexOf("-1")!=-1)
                            currentPath = currentPath.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder
                        }
                        var flag = isFolder(action);
                        APIFactory.callToServerWithPost("/pub2.0/dimension/create/"+possibleDim+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,function(data){
                            alert(data)
                            var newNode = createNode(name,action,currentPath,flag);
                            parentNode.addChild(newNode);
                        });

                    }
                }
            });

        }
    }

    function createNode(name,type,path,flag){

        var newNode = {
                        "id": "P01",
                        "title": name,
                        "type": type,
                        "path": path,
                        "isFolder": flag,
                        "children": []
                     }
        return newNode;
    }

    function isFolder(dim){
        var flag =true;
        if(dim == "Page"){
            flag = false;
        }
        return flag;
    }

    this.createTree = function(treeObj){
        $(document).bind("schemaLoaded", function onSchemaLoadedHandler(e){
           $('#menus').empty();
        });

        if(temp != null){
            temp.removeChildren();
            temp.addChild(data);
        }else{
            $(treeObj).dynatree({
                children: data,
                onCreate: function(node, span){
                    bindContextMenu(span,node.data.type);
                },
                onActivate: function(node) {
                    var nodeDetails = getChildrenForSelectedNode(node)
                     //alert(JSON.stringify(nodeDetails));
                }
            });
            temp = $(treeObj).dynatree("getRoot");
        }
    }
}
