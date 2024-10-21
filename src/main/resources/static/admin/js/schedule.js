import {validateDate, validateNumber, validateText, validateTextDetail, validateTypeahead} from './validation.js';
import {errorNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {
    const scheduleName = document.getElementById("schedule-name")
    const scheduleNameError = document.getElementById("schedule-name-error")

    const departureDate = document.getElementById("departure-date")
    const departureDateError = document.getElementById("departure-date-error")

    const returnDate = document.getElementById("return-date")
    const returnDateError = document.getElementById("return-date-error")

    const quantity = document.getElementById("quantity")
    const quantityError = document.getElementById("quantity-error")

    const priceAdult = document.getElementById("price-adult")
    const priceAdultError = document.getElementById("price-adult-error")

    const priceChild = document.getElementById("price-child")
    const priceChildError = document.getElementById("price-child-error")

    const btnScheduleAdd = document.getElementById("btn-schedule-add")

    const scheduleId = document.getElementById("schedule-id")
    let validation;
    if (scheduleId) {
        validation = [...Array(6).fill(true)]
    } else {
        validation = [...Array(3).fill(false),true,true,true]
    }


    scheduleName.addEventListener("input", function () {
        const successText = "Tên lịch trình hợp lệ"
        const errorText = "Tên lịch trình chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(scheduleName, scheduleNameError, successText, errorText);
    });

    departureDate.addEventListener("input", function () {
        const successText = "Ngày đi hợp lệ"
        const errorText = "Vui lòng nhập ngày đi"
        validation[1] = validateDate(departureDate, departureDateError, successText, errorText);
    });

    returnDate.addEventListener("input", function () {
        const successText = "Ngày về hợp lệ"
        const errorText = "Vui lòng nhập ngày về"
        validation[2] = validateDate(returnDate, returnDateError, successText, errorText);
    });

    quantity.addEventListener("input", function () {
        const successText = "Số lượng hợp lệ"
        const errorText = "Số lượng chỉ chứa số"
        validation[3] = validateNumber(quantity, quantityError, successText, errorText);
    });

    priceAdult.addEventListener("input", function () {
        const successText = "Giá vé người lớn hợp lệ"
        const errorText = "Giá vé người lớn chỉ chứa số"
        validation[4] = validateNumber(priceAdult, priceAdultError, successText, errorText);
    });

    priceChild.addEventListener("input", function () {
        const successText = "Giá vé trẻ em hợp lệ"
        const errorText = "Giá vé trẻ em chỉ chứa số"
        validation[5] = validateNumber(priceChild, priceChildError, successText, errorText);
    });


    btnScheduleAdd.addEventListener("click", function (event) {

        if (validation.some(v => v === false)) {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }
    });





})