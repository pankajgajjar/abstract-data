var DarkJSTree = function(data){

    var data = data;

    this.createTree = function(treeObj){

        treeObj.innerHTML="";
        $(treeObj).jstree({
            "plugins" : ["themes", "json_data", "ui", "crrm", "contextmenu"],
            "json_data" : {
                "data" : [
                    {
                        "data" : "A node",
                        "metadata" : {
                            "path":"./TestDir/js",
                            "name":"js",
                            "type":"folder"
                        },
                        "children" : [ {
                                            "data" : "A1 node",
                                            "metadata" : {
                                                "path":"./TestDir/js",
                                                "name":"js",
                                                "type":"folder"
                                            },
                                            "children" :[]
                                        },
                                        {
                                            "data" : "A2 node",
                                            "metadata" : {
                                                "path":"./TestDir/js",
                                                "name":"js",
                                                "type":"folder"
                                            },
                                            "children" :[]
                                        }
                        ]
                    }
                ]
            },
            "contextmenu" : {
                "items": function(node,data){
                    alert(data)
                }
            }

        }).bind("select_node.jstree", function(evt, data){
                alert(JSON.stringify(data.inst.get_json()));
                //selected node object: data.inst.get_json()[0];
                //selected node text: data.inst.get_json()[0].data
            })
    }



}
