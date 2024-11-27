import { validateText,validateTextDetail } from './validation.js';
import {errorNotify, successNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {

    const transportName = document.getElementById("categoryName");
    const transportNameError = document.getElementById("category-name-error");

    const transportDetail = document.getElementById("categoryDetail");
    const transportDetailError = document.getElementById("category-detail-error");

    const btnTransportAdd = document.getElementById("btn-transport-add");

    const txtId = document.getElementById('txt-id')
    let validation;
    if(txtId) {
        // đang ở form chỉnh sửa
         validation = [...Array(4).fill(true)]
    } else {
        // đang ở form thêm mới
         validation = [...Array(3).fill(false),true]
    }


    // Lắng nghe sự kiện 'input' của trường nhập liệu
    transportName.addEventListener("input", function () {
        const successText = "Tên phương tiện hợp lệ"
        const errorText = "Tên phương tiện chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(transportName, transportNameError,successText,errorText);
    });

    transportDetail.addEventListener("input", function () {
        const successText = "Chi tiết hợp lệ"
        const errorText = "Chi tiết chỉ cho phép chứa chữ, số và , . -"
        validation[1] =validateTextDetail(transportDetail, transportDetailError,successText,errorText);
    });

    btnTransportAdd.addEventListener("click", function (event){

        console.log("Validation",validation)
        if (!validation.some(v => v === false)) {

        } else {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }
    });



});