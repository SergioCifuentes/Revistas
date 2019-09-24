/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var texto;
contenido_textarea = "";
num_caracteres_permitidos = 45;
function valida_longitud(){
   num_caracteres = texto.value.length;

   if (num_caracteres > num_caracteres_permitidos){
      texto.value = contenido_textarea;
   }else{
      contenido_textarea = texto.value;
   }
      if (num_caracteres >= num_caracteres_permitidos){
      texto.style.color="#ff0000";
   }else{
      texto.style.color="#000000";
   } 
}


function valida_longitud_Tema(){
   texto= document.getElementById("Temas");
    valida_longitud();
}
function valida_longitud_Descripcion(){
   texto= document.getElementById("Descripcion");
    valida_longitud();
}
function valida_longitud_Hobbies(){
   texto= document.getElementById("Hobbies");
    valida_longitud();
}
function valida_longitud_Gustos(){
   texto= document.getElementById("Gustos");
    valida_longitud();
}



