var DropDownJS = function(data){
    var data = data;
    this.createDropDown = function(dropDownObj){

        var jsonlist = [
            {
                "name": "name1",
                "id": "1",
                "data": "ViewStructure1"
            },
            {
                "name": "name2",
                "id": "2",
                "data": "ViewStructure2"
            }
        ];
        //alert(jsonlist.length+"length")
        for(var i=0; i< jsonlist.length; i ++){
            var opt = document.createElement('option');
            opt.value=jsonlist[i].id;
            opt.innerHTML=jsonlist[i].data;
            //alert(jsonlist[i].data+"data");
            dropDownObj.appendChild(opt);
        }

        $(dropDownObj).change(function(){
            changeSchema($(this).val());
            $('#schemaDropDown').hide();
            $('#mainAnimationContainer').animate({height: actualHeight}, 500);
            $('#selectLbl').text("Select View");
            flag = true;
        });
    }
}
