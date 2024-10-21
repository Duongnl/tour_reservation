import {validateDate, validateNumber, validateText, validateTextDetail, validateTypeahead} from './validation.js';
import {errorNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {

    const transport = document.getElementById('transport')
    const transportError = document.getElementById('transport-error')
    const selectValue = document.querySelector('.tt-dataset')

    const departureTime = document.getElementById("departure-time")
    const departureTimeError = document.getElementById("departure-time-error")

    const arrivalTime = document.getElementById("arrival-time")
    const arrivalTimeError = document.getElementById("arrival-time-error")

    const btnTransportDetailAdd = document.getElementById("btn-transport-detail-add")

    const btnAdd = document.querySelectorAll('.btn-add')




    let validation = [...Array(3).fill(false)]

    transport.addEventListener("input", function () {
        const successText = "Phương tiện hợp lệ"
        const errorText = "Phương tiện không tồn tại"
        validation[0] = validateTypeahead(transport, transportError, successText, errorText);
    })

    selectValue.addEventListener("click", function () {
        validation[0] = true
        transport.classList.remove("is-invalid");
        transport.classList.add("is-valid");
        transportError.textContent = "Phương tiện hợp lệ";
        transportError.classList.remove("text-danger");
        transportError.classList.add("text-success");
    })

    departureTime.addEventListener("input", function () {
        const successText = "Thời gian đi hợp lệ"
        const errorText = "Vui lòng nhập thời gian đi"
        validation[1] = validateDate(departureTime, departureTimeError, successText, errorText);
    });

    arrivalTime.addEventListener("input", function () {
        const successText = "Thời gian đến hợp lệ"
        const errorText = "Vui lòng nhập thời gian đến"
        validation[2] = validateDate(arrivalTime, arrivalTimeError, successText, errorText);
    });

    if (btnAdd.length > 0) {
        btnAdd.forEach(btn => {
            btn.addEventListener("click", function () {

                selected = null
                var $input = $('.typeahead');
                $input.typeahead('val',selected)

                transport.value = ""
                transportError.value = "Chọn phương tiện di chuyển."
                transport.classList.remove("is-valid");
                transport.classList.remove("is-invalid");
                transportError.classList.remove("text-danger");
                transportError.classList.remove("text-success");

                departureTime.value = ""
                departureTimeError.value = "Nhập thời gian xuất phát của phương tiện."
                departureTime.classList.remove("is-valid");
                departureTime.classList.remove("is-invalid");
                departureTimeError.classList.remove("text-danger");
                departureTimeError.classList.remove("text-success");

                arrivalTime.value = ""
                arrivalTimeError.value = "Nhập thời gian đến của phương tiện."
                arrivalTime.classList.remove("is-valid");
                arrivalTime.classList.remove("is-invalid");
                arrivalTimeError.classList.remove("text-danger");
                arrivalTimeError.classList.remove("text-success");

                validation = [...Array(3).fill(false)]


            });
        });
    }

    btnTransportDetailAdd.addEventListener("click", function (event) {

        if (validation.some(v => v === false)) {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
            console.log("Sai")
        }
    });




})