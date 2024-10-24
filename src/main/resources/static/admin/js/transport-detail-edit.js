import {validateDate, validateNumber, validateText, validateTextDetail, validateTypeahead} from './validation.js';
import {errorNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {

    const transportEdit = document.getElementById('transport-edit')
    const transportErrorEdit = document.getElementById('transport-error-edit')
    const selectValueEdit = document.querySelector('.tt-dataset')

    const departureTimeEdit = document.getElementById("departure-time-edit")
    const departureTimeErrorEdit = document.getElementById("departure-time-error-edit")

    const arrivalTimeEdit = document.getElementById("arrival-time-edit")
    const arrivalTimeErrorEdit = document.getElementById("arrival-time-error-edit")

    const btnTransportDetailEdit = document.getElementById("btn-transport-detail-edit")

    const btnEdit = document.querySelectorAll('.btn-edit')


    let validationEdit = [...Array(3).fill(true)]

    transportEdit.addEventListener("input", function () {
        const successText = "Phương tiện hợp lệ"
        const errorText = "Phương tiện không tồn tại"
        validationEdit[0] = validateTypeahead(transportEdit, transportErrorEdit, successText, errorText);
    })

    selectValueEdit.addEventListener("click", function () {
        validationEdit[0] = true
        transportEdit.classList.remove("is-invalid");
        transportEdit.classList.add("is-valid");
        transportErrorEdit.textContent = "Phương tiện hợp lệ";
        transportErrorEdit.classList.remove("text-danger");
        transportErrorEdit.classList.add("text-success");
    })

    departureTimeEdit.addEventListener("input", function () {
        const successText = "Thời gian đi hợp lệ"
        const errorText = "Vui lòng nhập thời gian đi"
        validationEdit[1] = validateDate(departureTimeEdit, departureTimeErrorEdit, successText, errorText);
    });

    arrivalTimeEdit.addEventListener("input", function () {
        const successText = "Thời gian đến hợp lệ"
        const errorText = "Vui lòng nhập thời gian đến"
        validationEdit[2] = validateDate(arrivalTimeEdit, arrivalTimeErrorEdit, successText, errorText);
    });

    if (btnEdit.length > 0) {
        btnEdit.forEach(btn => {
            btn.addEventListener("click", function () {

                transportErrorEdit.value = "Chọn phương tiện di chuyển."
                transportEdit.classList.remove("is-valid");
                transportEdit.classList.remove("is-invalid");
                transportErrorEdit.classList.remove("text-danger");
                transportErrorEdit.classList.remove("text-success");

                departureTimeErrorEdit.value = "Nhập thời gian xuất phát của phương tiện."
                departureTimeEdit.classList.remove("is-valid");
                departureTimeEdit.classList.remove("is-invalid");
                departureTimeErrorEdit.classList.remove("text-danger");
                departureTimeErrorEdit.classList.remove("text-success");

                arrivalTimeErrorEdit.value = "Nhập thời gian đến của phương tiện."
                arrivalTimeEdit.classList.remove("is-valid");
                arrivalTimeEdit.classList.remove("is-invalid");
                arrivalTimeErrorEdit.classList.remove("text-danger");
                arrivalTimeErrorEdit.classList.remove("text-success");

                validationEdit = [...Array(3).fill(true)]


            });
        });
    }


    btnTransportDetailEdit.addEventListener("click", function (event) {

        if (validationEdit.some(v => v === false)) {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
            console.log("Sai")
        }
    });




})