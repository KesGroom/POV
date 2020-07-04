
(function () {
    var actualizarHora = function () {
        var fecha = new Date(),
                horas = fecha.getHours(),
                ampm,
                minutos = fecha.getMinutes(),
                segundos = fecha.getSeconds(),
                diaSemana = fecha.getDay(),
                dayWeek = fecha.getDay(),
                dia = fecha.getDate(),
                mes = fecha.getMonth(),
                year = fecha.getFullYear();


        var pHoras = document.getElementById('horas'),
                pAMPM = document.getElementById('ampm'),
                pMinutos = document.getElementById('minutos'),
                pSegundos = document.getElementById('segundos'),
                pDiaSemana = document.getElementById('diaSemana'),
                pDaySem = document.getElementById('diaSem'),
                pDia = document.getElementById('dia'),
                pMes = document.getElementById('mes'),
                pMonth = document.getElementById('month'),
                pYear = document.getElementById('year');

        var semana = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];
        pDiaSemana.textContent = semana[diaSemana];

        var semanaEn = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
        pDaySem.textContent = semanaEn[dayWeek];

        pDia.textContent = dia;

        var meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

        pMes.textContent = meses[mes];

        var mesesEn = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
        pMonth.textContent = mesesEn[mes];

        pYear.textContent = year;

        if (horas >= 12) {
            horas = horas - 12;
            ampm = 'PM';
        } else {
            ampm = 'AM';
        }

        if (horas === 0) {
            horas = 12;
        }
        ;

        pHoras.textContent = horas;
        pAMPM.textContent = ampm;

        if (minutos < 10) {
            minutos = "0" + minutos;
        }
        ;
        if (segundos < 10) {
            segundos = "0" + segundos;
        }
        ;
        pMinutos.textContent = minutos;
        pSegundos.textContent = segundos;

    };
    actualizarHora();
    var intervalo = setInterval(actualizarHora, 1000);
}());







