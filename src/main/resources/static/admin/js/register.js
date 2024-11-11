import { validateText, validateEmail, validatePhoneNumber, validateUsername, validatePassword, validateConfirmPassword } from './validation.js';
import { errorNotify, successNotify } from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {

    const customerName = document.getElementById("customer-name");
    const customerNameError = document.getElementById("customer-name-error");

    const employeeUsername = document.getElementById("customer-username");
    const employeeUsernameError = document.getElementById("employee-username-error");

    const employeePassword = document.getElementById("customer-password");
    const employeePasswordError = document.getElementById("customer-password-error");

    const confirmPassword = document.getElementById("customer-confirm_password");
    const confirmPasswordError = document.getElementById("customer-confirm_password-error");

    const phoneNumber = document.getElementById("customer-phoneNumber");
    const phoneNumberError = document.getElementById("customer-phoneNumber-error");

    const customerEmail = document.getElementById("customer-email");
    const customerEmailError = document.getElementById("customer-email-error");

    const customerAddress = document.getElementById("customer-address");
    const customerAddressError = document.getElementById("customer-address-error");

    const btnRegister = document.getElementById("btn-register");

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

    // Số điện thoại
    phoneNumber.addEventListener("input", function () {
        const successText = "Số điện thoại hợp lệ"
        const errorText = "Số điện thoại chỉ chứa số và không quá 10 kí tự"
        validation[1] = validatePhoneNumber(phoneNumber, phoneNumberError, successText, errorText);
    });
    //tên đăng nhập
    employeeUsername.addEventListener("input", function () {
        const successText = "Tên đăng nhập hợp lệ"
        const errorText = "Tên đăng nhập không hợp lệ"
        validation[2] = validateUsername(employeeUsername, employeeUsernameError, successText, errorText);
    });

    //Kiểm tra password
    employeePassword.addEventListener("input", function () {
        const successText = "mật khẩu hợp lệ"
        const errorText = " Mật khẩu từ 8 đến 20 ký tự, chứa ít nhất một số, chữ thường, hoa,ký tự đặc biệt"
        validation[4] = validatePassword(employeePassword, employeePasswordError, successText, errorText);
    });

    //Kiểm tra xác nhận password
    confirmPassword.addEventListener("input", function () {
        const successText = "mật khẩu hợp lệ"
        const errorText = " Mật khẩu không trùng khớp"
        validation[4] = validateConfirmPassword(employeePassword, confirmPassword, confirmPasswordError, successText, errorText);
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
        validation[5] = validateText(customerAddress, customerAddressError, successText, errorText);
    });

    const fetchApi = async () => {

        const res = await fetch("/api/check-username", {
            method: "POST",
            headers: {
                'Content-Type': 'text/plain'
            },
            body: employeeUsername.value
        })

        const data = res.json();
        return data

    }

    btnRegister.addEventListener("click", async function (event) {
        event.preventDefault();

        if (!validation.some(v => v === false)) {

            if (!txtId || employeeUserNameOld.value != employeeUsername.value) {
                const data = await fetchApi()
                if (data === false) {
                    errorNotify("Tài khoản đã tồn tại")
                    employeeUsername.classList.remove("is-valid");
                    employeeUsername.classList.add("is-invalid");
                    employeeUsernameError.textContent = "Tài khoản đã tồn tại";
                    employeeUsernameError.classList.add("text-danger");
                } else {
                    console.log("submit trong add")
                    document.querySelector("form").submit(); // Replace "form" with the actual form selector if needed
                }
            } else {
                document.querySelector("form").submit();
                console.log("submit")
            }
        } else {
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }



    });



});