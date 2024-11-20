import { validateText, validateDate, validateEmail, validatePhoneNumber, validateUsername, validatePassword } from './validation.js';
import { errorNotify, successNotify } from "./notify.js";

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
    const employeeEmailError = document.getElementById("employee-email-error");

    const employeeBirthday = document.getElementById("employeeDate");
    const employeeBirthdayError = document.getElementById("employeeDate-error");

    const btnemployeeAdd = document.getElementById("btn-employee-add");

    const employeeUserNameOld = document.getElementById("employee-user-name-old")

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
    employeeName.addEventListener("input", function () {
        const successText = "Tên nhân viên hợp lệ"
        const errorText = "Tên nhân viên chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(employeeName, employeeNameError, successText, errorText);
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
        validation[3] = validatePassword(employeePassword, employeePasswordError, successText, errorText);
    });

    employeeEmail.addEventListener("input", function () {
        const successText = "Email hợp lệ"
        const errorText = "Email không hợp lệ (VD: abc@gmail.com)"
        validation[4] = validateEmail(employeeEmail, employeeEmailError, successText, errorText);
    });

    employeeBirthday.addEventListener("input", function () {
        const successText = "Ngày hợp lệ"
        const errorText = "Ngày không hợp lệ"
        validation[5] = validateDate(employeeBirthday, employeeBirthdayError, successText, errorText);
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

    btnemployeeAdd.addEventListener("click", async function (event) {
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