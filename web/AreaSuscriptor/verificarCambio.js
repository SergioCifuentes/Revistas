const textoDescripcion=document.getElementById("Descripcion").value;
const textoHobbies=document.getElementById("Hobbies").value;
const textoTemas=document.getElementById("Temas").value;
const textoGustos=document.getElementById("Gustos").value;
var botton = document.getElementById("botton");
function confirmarCambio(){
    if (textoDescripcion===document.getElementById("Descripcion").value&&
            textoHobbies===document.getElementById("Hobbies").value &&
            textoTemas===document.getElementById("Temas").value&&
            textoGustos===document.getElementById("Gustos").value) {
        botton.disabled=true;
    }else{
        botton.disabled=false;
    }
}

