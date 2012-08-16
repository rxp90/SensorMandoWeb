window.onload = function() {

    showMsgConnected();

    var btnPuerto = document.getElementById("puerto");
    var btnHost = document.getElementById("host");
    
    btnPuerto.onclick = function() {
        var puerto = document.getElementById("puertoInput").value;
        if(validatePort(puerto)){
            document.getElementById("divPuerto").setAttribute("class", "control-group");
            document.getElementById("mensajePuerto").innerHTML = "";
            juego.setPuerto(puerto);
        }else{
            document.getElementById("divPuerto").setAttribute("class", "control-group error");
            document.getElementById("mensajePuerto").innerHTML = "Puerto no válido. Debe estar entre 1025 y 65535";
        }
        showMsgConnected();
        return false;
    }
    btnHost.onclick = function() {
        var host = document.getElementById("hostInput").value;
        if(validateIP(host)){
            document.getElementById("divHost").setAttribute("class", "control-group");
            document.getElementById("mensajeHost").innerHTML = "";
            juego.setHost(host);
        }else{
            document.getElementById("divHost").setAttribute("class", "control-group error");
            document.getElementById("mensajeHost").innerHTML = "Dirección de IP no válida.";
        }
        showMsgConnected();
        return false;
    }
    
    
}
function validateIP(ip) { 
    var regex  = new RegExp("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    return regex.test(ip);
} 
function validatePort(puerto){
    var minPort = 1025;
    var maxPort = 65535;
    return puerto.valueOf() > minPort && maxPort > puerto.valueOf();
}
function showMsgConnected(){
    if(juego.isConnected()){
        document.getElementById("divConectado").style.visibility='visible';
        document.getElementById("divNoConectado").style.visibility='hidden';
    }else{
        document.getElementById("divConectado").style.visibility='hidden';
        document.getElementById("divNoConectado").style.visibility='visible';
    }
}