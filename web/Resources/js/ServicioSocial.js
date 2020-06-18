var actZona = document.getElementById("actAddZona");
var actBit = document.getElementById("actAddBit");
var formBit = document.getElementById("formAddBit");
var formZona = document.getElementById("formAddZona");
var iconZona = document.getElementById("iconZona");
var iconBit = document.getElementById("iconBit");

actZona.addEventListener('click', function() {
    actZona.classList.toggle("active");
    formZona.classList.toggle("active");
    actBit.classList.remove("active");
    formBit.classList.remove("active");
    
    var nameIcon = iconZona.getAttribute("name");
    if( nameIcon === 'golf'){
        iconZona.setAttribute('name' , 'close-circle');
    } else{
        iconZona.setAttribute('name' , 'golf');
    }
    
    iconBit.setAttribute('name','clipboard');
    
});

actBit.addEventListener('click', function() {
    actBit.classList.toggle("active");
    formBit.classList.toggle("active");
    actZona.classList.remove("active");
    formZona.classList.remove("active");
    
    var nameIcon = iconBit.getAttribute("name");
    if( nameIcon === 'clipboard'){
        iconBit.setAttribute('name' , 'close-circle');
    } else{
        iconBit.setAttribute('name' , 'clipboard');
    }
    
    iconZona.setAttribute('name','golf');
    
});