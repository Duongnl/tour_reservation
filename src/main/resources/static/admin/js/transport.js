import { validateText,validateTextDetail } from './validation.js';
import {errorNotify, successNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {
    const transportName = document.getElementById("transport-name");
    const transportNameError = document.getElementById("transport-name-error");

    const departureLocation = document.getElementById("departure-location");
    const departureLocationError = document.getElementById("departure-location-error");

    const destinationLocation = document.getElementById("destination-location");
    const destinationLocationError = document.getElementById("destination-location-error");

    const transportDetail = document.getElementById("transport-detail");
    const transportDetailError = document.getElementById("transport-detail-error");

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

    departureLocation.addEventListener("input", function () {
        const successText = "Địa điểm xuất hợp lệ"
        const errorText = "Địa điểm xuất phát chỉ cho phép chứa chữ, số và , . -"
        validation[1] =validateText(departureLocation, departureLocationError,successText,errorText);
    });

    destinationLocation.addEventListener("input", function () {
        const successText = "Địa điểm đến hợp lệ"
        const errorText = "Địa điểm đến chỉ cho phép chứa chữ, số và , . -"
        validation[2] =validateText(destinationLocation, destinationLocationError,successText,errorText);
    });

    transportDetail.addEventListener("input", function () {
        const successText = "Chi tiết hợp lệ"
        const errorText = "Chi tiết chỉ cho phép chứa chữ, số và , . -"
        validation[3] =validateTextDetail(transportDetail, transportDetailError,successText,errorText);
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