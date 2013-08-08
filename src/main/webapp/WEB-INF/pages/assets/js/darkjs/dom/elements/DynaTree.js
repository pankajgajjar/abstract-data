var newNodeId;
var newNode;
var temp;

var DynaTree = function(data){
    var currentPath;

    // --- Contextmenu --------------------------------------------------
    function bindContextMenu(span,type) {
        console.log(type)
        if(type=="dim9"){
            console.log(123)
        }
        // Add context menu to this node:
        $(span).contextMenu({menu: "myMenu"}, function(action, el, pos) {
            // The event was bound to the <span> tag, but the node object
            // is stored in the parent <li> tag
            var node = $.ui.dynatree.getNode(el);

            switch( action ) {
                case "like":
                    alert("Thank for liking '" + node + "' !");
                    break;
                case "unlike":
                    alert("Sorry you din't like '" + node + "' !");
                    break;

            }
        });
    }

    this.createTree = function(treeObj){
        if(temp != null){
            temp.removeChildren();
            temp.addChild(data);
        }else{
            $(treeObj).dynatree({
                "children": data,


                /*Bind context menu for every node when it's DOM element is created.
                 We do it here, so we can also bind to lazy nodes, which do not
                 exist at load-time. (abeautifulsite.net menu control does not
                 support event delegation)*/

                onCreate: function(node, span){
                    bindContextMenu(span,node.data.type);
                }

            });
            temp = $(treeObj).dynatree("getRoot");
        }
    }
}
