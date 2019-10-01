const  NuevaR = document.getElementById("NuevaR");
const  NuevaE = document.getElementById("NuevaE");
const  EspacioR = document.getElementById("EspacioRevista");
const  EspacioE = document.getElementById("EspacioEdicion");

const  Revista = document.getElementById("NuevaRevista");
const  Edicion = document.getElementById("NuevaEdicion");

const  Botton = document.getElementById("Siguiente");
const  LabelCodigo = document.getElementById("codigo");

function realizarNuevaRevista() {
    if (NuevaR.checked === true) {
        Revista.style = "background-color: lightgray; width: 95%";
        Edicion.style = "background-color: white; width: 95%";
        NuevaE.checked = false;
        EspacioR.hidden = false;
        EspacioE.hidden=true;
        Botton.disabled = false;
        Botton.name="NuevaR";
    } else {
        EspacioR.hidden = true;
        Revista.style = "background-color: white; width: 95%";
    }

}
function realizarNuevaEdicion() {
    if (NuevaE.checked === true) {
        Edicion.style = "background-color: lightgray; width: 95%";
        Revista.style = "background-color: white; width: 95%";
        NuevaR.checked = false;
        EspacioR.hidden = true;
        EspacioE.hidden=false;
        Botton.disabled = false;
        Botton.name="NuevaE";
    } else {
        EspacioE.hidden=true;
        Edicion.style = "background-color: white; width: 95%";
    }
}
