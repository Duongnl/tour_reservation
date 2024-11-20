import { validateText, validateEmail, validatePhoneNumber, validateDate } from './validation.js';
import { errorNotify, successNotify } from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {


    const customerName = document.getElementById("customer-name");
    const customerNameError = document.getElementById("customer-name-error");

    const phoneNumber = document.getElementById("customer-phoneNumber");
    const phoneNumberError = document.getElementById("customer-phoneNumber-error");

    const relationshipName = document.getElementById("relationshipName");
    const relationshipNameError = document.getElementById("relationshipName-error");

    const customerEmail = document.getElementById("customer-email");
    const customerEmailError = document.getElementById("customer-email-error");

    const customerAddress = document.getElementById("customer-address");
    const customerAddressError = document.getElementById("customer-address-error");

    const customerBirthday = document.getElementById("customer-birtday");
    const customerBirthdayError = document.getElementById("customer-birtday-error");

    const customerVisa = document.getElementById("customer-visaExpire");
    const customerVisaError = document.getElementById("customer-visaExpire-error");

    const btnCustomerAdd = document.getElementById("btn-customer-add");

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
    customerName.addEventListener("input", function () {
        const successText = "Tên khách hàng hợp lệ"
        const errorText = "Tên khách hàng chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(customerName, customerNameError, successText, errorText);
    });

    //mối quan hệ
    relationshipName.addEventListener("input", function () {
        const successText = "Mối quan hệ hợp lệ"
        const errorText = "Mối quan hệ không hợp lệ"
        validation[1] = validateText(relationshipName, relationshipNameError, successText, errorText);
    });

    // Số điện thoại
    phoneNumber.addEventListener("input", function () {
        const successText = "Số điện thoại hợp lệ"
        const errorText = "Số điện thoại chỉ chứa số và không quá 10 kí tự"
        validation[2] = validatePhoneNumber(phoneNumber, phoneNumberError, successText, errorText);
    });


    customerEmail.addEventListener("input", function () {
        const successText = "Email hợp lệ"
        const errorText = "Email không hợp lệ (VD: abc@gmail.com)"
        validation[3] = validateEmail(customerEmail, customerEmailError, successText, errorText);
    });

      // Lắng nghe sự kiện 'input' của trường nhập liệu
      customerAddress.addEventListener("input", function () {
        const successText = "Địa chỉ hợp lệ"
        const errorText = "Địa chỉ không hợp lệ"
        validation[4] = validateText(customerAddress, customerAddressError, successText, errorText);
    });

    customerBirthday.addEventListener("input", function () {
        const successText = "Ngày hợp lệ"
        const errorText = "Ngày không hợp lệ"
        validation[5] = validateDate(customerBirthday, customerBirthdayError, successText, errorText);
    });

    customerVisa.addEventListener("input", function () {
        const successText = "Ngày hợp lệ"
        const errorText = "Ngày không hợp lệ"
        validation[5] = validateDate(customerVisa, customerVisaError, successText, errorText);
    });

    btnCustomerAdd.addEventListener("click", function (event) {

        console.log("Validation", validation)
        if (!validation.some(v => v === false)) {

        } else {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }
    });



});