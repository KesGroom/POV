var actGrado = document.getElementById("actAddGrado");
var actArea = document.getElementById("actAddArea");
var actMat = document.getElementById("actAddMat");
var formGrado = document.getElementById("formAddGrado");
var formArea = document.getElementById("formAddArea");
var formMat = document.getElementById("formAddMat");
var iconGrado = document.getElementById("iconGrado");
var iconArea = document.getElementById("iconArea");
var iconMat = document.getElementById("iconMat");

actGrado.addEventListener('click', function () {
    actGrado.classList.toggle("active");
    formGrado.classList.toggle("active");
    actArea.classList.remove("active");
    formArea.classList.remove("active");
    actMat.classList.remove("active");
    formMat.classList.remove("active");

    var nameIcon = iconGrado.getAttribute("name");
    if (nameIcon === 'people') {
        iconGrado.setAttribute('name', 'close-circle');
    } else {
        iconGrado.setAttribute('name', 'people');
    }

    iconArea.setAttribute('name', 'book');
    iconMat.setAttribute('name', 'folder-open');

});

actMat.addEventListener('click', function () {
    actMat.classList.toggle("active");
    formMat.classList.toggle("active");
    actArea.classList.remove("active");
    formArea.classList.remove("active");
    actGrado.classList.remove("active");
    formGrado.classList.remove("active");

    var nameIcon2 = iconMat.getAttribute("name");
    if (nameIcon2 === 'folder-open') {
        iconMat.setAttribute('name', 'close-circle');
    } else {
        iconMat.setAttribute('name', 'folder-open');
    }

    iconArea.setAttribute('name', 'book');
    iconGrado.setAttribute('name', 'people');

});

actArea.addEventListener('click', function () {
    actArea.classList.toggle("active");
    formArea.classList.toggle("active");
    actGrado.classList.remove("active");
    formGrado.classList.remove("active");
    actMat.classList.remove("active");
    formMat.classList.remove("active");

    var nameIcon3 = iconArea.getAttribute("name");
    if (nameIcon3 === 'book') {
        iconArea.setAttribute('name', 'close-circle');
    } else {
        iconArea.setAttribute('name', 'book');
    }

    iconGrado.setAttribute('name', 'people');
    iconMat.setAttribute('name', 'folder-open');

});

