import { validateText, validatePercentage, validateDate } from './validation.js';
import { errorNotify, successNotify } from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {
    const promotionName = document.getElementById("promotionName");
    const promotionNameError = document.getElementById("promotion-error");

    const percentage = document.getElementById("percentage");
    const percentageError = document.getElementById("percentage-error");

    const startdate = document.getElementById("startTime");
    const startdateError = document.getElementById("startTime-error");

    const enddate = document.getElementById("endTime");
    const enddateError = document.getElementById("endTime-error");

    const btnPromotionAdd = document.getElementById("btn-promotion-add");

    const txtId = document.getElementById('txt-id')
    let validation;
    if (txtId) {
        // đang ở form chỉnh sửa
        validation = [...Array(4).fill(true)]
    } else {
        // đang ở form thêm mới
        validation = [...Array(3).fill(false), true]
    }


    // Lắng nghe sự kiện 'input' của trường nhập liệu
    promotionName.addEventListener("input", function () {
        const successText = "Tên khuyến mãi hợp lệ"
        const errorText = "Tên khuyến mãi chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(promotionName, promotionNameError, successText, errorText);
    });


    // phần trăm khuyễn mãi
    percentage.addEventListener("input", function () {
        const successText = "Phần trăm khuyến mãi hợp lệ"
        const errorText = "Phần trăm khuyến mãi không hợp lệ !!"
        validation[1] = validatePercentage(percentage, percentageError, successText, errorText);
    });

    startdate.addEventListener("input", function () {
        const successText = "Ngày hợp lệ"
        const errorText = "Ngày không hợp lệ"
        validation[2] = validateDate(startdate, startdateError, successText, errorText);
    });

    enddate.addEventListener("input", function () {
        const successText = "Ngày hợp lệ"
        const errorText = "Ngày không hợp lệ"
        validation[3] = validateDate(enddate, enddateError, successText, errorText);
    });



    btnPromotionAdd.addEventListener("click", function (event) {

        console.log("Validation", validation)
        if (!validation.some(v => v === false)) {

        } else {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }
    });
});