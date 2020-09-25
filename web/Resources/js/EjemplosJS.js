//Se busca usar las graficas de Chart.js de manera más dinamica instanciandola a
// través de funciones y otorgando la información necesaria a través de parametros

/* 
 * Tabla de contenido
 * 1. Gráfica tipo Dougnout(Dona)
 * 
 * */

/* Tipo: Dougnout(Dona)
 * Parametros: 
 * -Id del canvas(String o cadena de texto)
 * -labels(Nombres de los elementos[List<String>])
 * -data(Información númerica[List<Integer>)
 * -colores(Colores generados aleatoriamente[List<String>)
 * */

function graphicDougnout(id, labels, data, colores) {

    var ctx = document.getElementById(id).getContext("2d");
    var graf = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{
                    data: data,
                    backgroundColor: colores
                }]
        }

    });
};


