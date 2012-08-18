window.onload = function() {

    showMsgConnected();

}
function showMsgConnected(){
    if(reproductor.isConnected()){
        document.getElementById("divConectado").style.visibility='visible';
        document.getElementById("divNoConectado").style.visibility='hidden';
    }else{
        document.getElementById("divConectado").style.visibility='hidden';
        document.getElementById("divNoConectado").style.visibility='visible';
    }
}