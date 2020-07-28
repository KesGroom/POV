
function AlertaPopUp(title, text, icon) {
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        showConfirmButton: false,
        timer: 6500,
        timerProgressBar: true
    });
}
;
function AlertaPopUpBtn(title, text, icon, btnText) {
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        confirmButtonText: btnText
    });
}
;

function AlertaToast(title, icon) {
    Swal.fire({
        title: title,
        icon: icon,
        toast: true,
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        position: 'bottom-end',
        onOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    });
}
;


