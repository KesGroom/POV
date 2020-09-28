var nextItem = 0;
var place;
function AgregarElemento(place){
    nextItem++;
    lista = '<div class="col s12 input-field">\n\
<input type="text" class="itemListAdded" id="itemL'+nextItem+'"></input>\n\
<label for="itemL'+nextItem+'">'+place+' '+nextItem+'</label>\n\
</div>';
    
$("#contList").append(lista);
};
var idInput;
var itemList = document.getElementsByClassName("itemListAdded");
var idCant;
function CombinarLista(idInput, idCant){
    var contentFinal = "";
    var cantItems = 0;
    for(var i=0;i<itemList.length;i++){
        if(itemList[i].value!==""){
        cantItems++;
        contentFinal=contentFinal+"<"+itemList[i].value+">";
        }
    }
    var inputFinal= document.getElementById(idInput);
    var cantElements = document.getElementById(idCant);
    inputFinal.value = contentFinal;
    cantElements.value = cantItems;
};
