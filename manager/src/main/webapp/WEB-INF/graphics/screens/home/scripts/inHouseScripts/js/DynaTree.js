var temp;

var DynaTree = function(){
    var currentPath;
    var parentNode;
    var newNode

    function menuExists(type){
        var contextMenusHolder = document.getElementById('menus')
        uls = contextMenusHolder.getElementsByTagName('ul');
        for (i=0; i<uls.length; i++) {
            if (uls[i].id == type) {
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
        possibleDim  = GraphicDataStore.getPossibleChild(type);
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
                        parentNode = $.ui.dynatree.getNode(el);
                        if(parentNode.data.type == "root"){
                            currentPath = "-1";
                        }
                        else{
                            currentPath = parentNode.data.path+","+ parentNode.data.title;
                            if(currentPath.indexOf("-1")!=-1)
                                currentPath = currentPath.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder
                        }

                        var flag = isFolder(action);
                        var prefix=getUrlPrefix(action,"create");
                        newNode = createNode(name,action,currentPath,flag);
                        Dimensions.createDim(prefix,action,name,currentPath,flag,addNode);
                    }
                }
            });

        }
    }

    function addNode(data){
        parentNode.addChild(newNode).activate();
        var node_expand = parentNode.isExpanded();
        if(node_expand == false)
            parentNode.expand();
        parentNode.data.children.push(newNode);
    }

    function createNode(name,type,path,flag){
        var flag = isFolder(type);
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

    var getUrlPrefix=function(type,action){
        switch(type){
            case "Chapter":
                return  "/pub/chapter/"+action+"/";
            case "Page":
               return  "/pub/page/"+action+"/";

        }
        return "/pub/dimension/"+action+"/";
    }

    this.createTree = function(treeObj,data){
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
                    var data = getChildrenForSelectedNode(node)
                    $(document).trigger({
                        type: "TREE_ITEM_CLICKED",
                        uiData: data
                    });
                },
                dnd: {
                    preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
                    onDragStart: function(node) {
                        if(node.data.type == "Chapter"||node.data.type == "Page" ) {
                            return true;
                        }
                        else{
                            return false;
                        }

                    },
                    onDragEnter: function(node, sourceNode) {
                        if(node.data.type == "Publication" || node.data.type == "Chapter"){
                            return ["over"];
                        }
                        else{
                            return false;
                        }

                    },
                    onDrop: function(sourceNode, node, hitMode, ui, draggable) {
                        console.log(node.data.title+"node");
                        console.log(sourceNode.data.title+"sourceNode");
                        var parentNode = sourceNode;
                        var newChildNode = node;
                        var oldPathForChild = node.data.path;
                        
                        newChildNode.data.path = parentNode.data.path +","+parentNode.data.title;
                        var newPathForChild   = newChildNode.data.path;
                        //API call will go here
                        //alert(JSON.stringify(newChildNode.data.children));
                        var flag=isFolder(node.data.type);
                        var prefix=getUrlPrefix(node.data.type,"move");
                        Router.forward(prefix+node.data.type+"/name/"+node.data.title+"/path/"+oldPathForChild+"/folder/"+flag+"/newpath/"+newPathForChild,true,function(data){

                        });

                        node.move(sourceNode, hitMode);
                    }
                }
            });
            temp = $(treeObj).dynatree("getRoot");
        }
    }

}

