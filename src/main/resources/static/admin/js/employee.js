import { validateText,validateTextDetail, validateEmail, validatePhoneNumber, validateUsername } from './validation.js';
import {errorNotify, successNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {
    const employeeName = document.getElementById("employee-name");
    const employeeNameError = document.getElementById("employee-name-error");

    const phoneNumber = document.getElementById("employeePhone");
    const phoneNumberError = document.getElementById("employeePhone-error");

    const employeeUsername = document.getElementById("employee-username");
    const employeeUsernameError = document.getElementById("employee-username-error");

    const employeePassword = document.getElementById("employee-password");
    const employeePasswordError = document.getElementById("employee-password-error");

    const employeeEmail = document.getElementById("employee-email");
    const employeeEmailError = document.getElementById("employee-emai-error");

    const btnemployeeAdd = document.getElementById("btn-employee-add");

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
    employeeName.addEventListener("input", function () {
        const successText = "Tên nhân viên hợp lệ"
        const errorText = "Tên nhân viên chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(employeeName, employeeNameError,successText,errorText);
    });


    // Số điện thoại
    phoneNumber.addEventListener("input", function () {
        const successText = "Số điện thoại hợp lệ"
        const errorText = "Số điện thoại chỉ chứa số và không quá 10 kí tự"
        validation[1] =validatePhoneNumber(phoneNumber, phoneNumberError,successText,errorText);
    });

    employeeUsername.addEventListener("input", function () {
        const successText = "Tên đăng nhập hợp lệ"
        const errorText = "Tên đăng nhập không hợp lệ"
        validation[2] = validateUsername(employeeUsername, employeeUsernameError,successText,errorText);
    });

    employeePassword.addEventListener("input", function () {
        const successText = "Chi tiết hợp lệ"
        const errorText = "Chi tiết chỉ cho phép chứa chữ, số và , . -"
        validation[3] = validateTextDetail(employeePassword, employeePasswordError,successText,errorText);
    });

    employeeEmail.addEventListener("input", function () {
        const successText = "Email hợp lệ"
        const errorText = "Email không hợp lệ (VD: abc@gmail.com)"
        validation[4] = validateEmail(employeeEmail, employeeEmailError,successText,errorText);
    });

    btnemployeeAdd.addEventListener("click", function (event){

        console.log("Validation",validation)
        if (!validation.some(v => v === false)) {

        } else {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }
    });



});