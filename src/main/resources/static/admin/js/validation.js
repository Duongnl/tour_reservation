// hàm này chỉ cho phép chứa chữ, số và , . - và không cho phép chỉ chứa khoảng trắng
export function validateText(inputElement, errorElement, successText, errorText) {

    const regex = /^(?!\s*$)[\p{L}0-9 ,.\-]{0,255}$/u;

    const value = inputElement.value.trim();


    if (!regex.test(value)) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true
    }
}

//hàm kiểm tra định dạng email
export function validateEmail(inputElement, errorElement, successText, errorText) {
    // Biểu thức chính quy kiểm tra định dạng email
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // Lấy giá trị từ input và loại bỏ khoảng trắng ở đầu và cuối
    const value = inputElement.value.trim();

    // Kiểm tra định dạng email
    if (!regex.test(value)) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true;
    }
}


//hàm kiểm tra số điện thoại

export function validatePhoneNumber(inputElement, errorElement, successText, errorText) {
    // Biểu thức chính quy kiểm tra định dạng số điện thoại

    const regex = /^\+?\d{1,11}$/;
    // Lấy giá trị từ input và loại bỏ khoảng trắng ở đầu và cuối
    const value = inputElement.value.trim();

    // Kiểm tra định dạng số điện thoại
    if (!regex.test(value)) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true;
    }
}

//hàm kiểm tra phần trăm khuyến mãi
export function validatePercentage(inputElement, errorElement, successText, errorText) {
    // Lấy giá trị từ input và loại bỏ khoảng trắng ở đầu và cuối
    const value = inputElement.value.trim();

    // Chuyển giá trị sang dạng số
    const percentage = parseFloat(value);

    // Kiểm tra xem giá trị có phải là số và nằm trong khoảng từ 0 đến 100 hay không
    if (isNaN(percentage) || percentage < 0 || percentage > 100) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true;
    }
}


//hàm kiểm tra tên đăng nhập
export function validateUsername(inputElement, errorElement, successText, errorText) {

    // Tài khoản phải từ 5 đến 20 ký tự, chỉ chứa số, chữ, dấu . _ -
    const regex = /^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$/;


    // Lấy giá trị từ input và loại bỏ khoảng trắng ở đầu và cuối
    const value = inputElement.value.trim();

    // Kiểm tra định dạng tên đăng nhập
    if (!regex.test(value) || value.includes(" ")) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true;
    }
}

//hàm kiểm tra tên mật khẩu
export function validatePassword(inputElement, errorElement, successText, errorText) {
    // Mật khẩu từ 8 đến 20 ký tự, chứa ít nhất một số, chữ thường, hoa,ký tự đặc biệt
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@.#$!%*?&])[A-Za-z\d@.#$!%*?&]{8,20}$/;
    // Lấy giá trị từ input và loại bỏ khoảng trắng ở đầu và cuối
    const value = inputElement.value.trim();
    // Kiểm tra định dạng tên mật khẩu
    if (!regex.test(value) || value.includes(" ")) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true;
    }
}
//Hàm xác nhận mật khẩu
export function validateConfirmPassword(passwordElement, confirmPasswordElement, errorElement, successText, errorText) {
    // Lấy giá trị từ các input và loại bỏ khoảng trắng ở đầu và cuối
    const passwordValue = passwordElement.value.trim();
    const confirmPasswordValue = confirmPasswordElement.value.trim();

    // Kiểm tra xem mật khẩu và xác nhận mật khẩu có giống nhau không
    if (confirmPasswordValue !== passwordValue || confirmPasswordValue === "") {
        confirmPasswordElement.classList.remove("is-valid");
        confirmPasswordElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        confirmPasswordElement.classList.remove("is-invalid");
        confirmPasswordElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true;
    }
}


// hàm này chỉ cho phép chứa chữ, số và , . - và cho phép chỉ chứa khoảng trắng (để trống)
export function validateTextDetail(inputElement, errorElement, successText, errorText) {

    const regex = /^[\p{L}0-9 ,.\-]{0,255}$/u;

    const value = inputElement.value.trim();


    if (!regex.test(value)) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true
    }


}

export function validateTypeahead(inputElement, errorElement, successText, errorText) {
    let value = inputElement.value.trim();
    if (states?.includes(value)) {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true
    } else {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false
    }
}


export function validateDate(inputElement, errorElement, successText, errorText) {
    const value = inputElement.value.trim();
    if (value === "") {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false;
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true
    }
}

export function validateNumber(inputElement, errorElement, successText, errorText) {
    const regex = /^\d+$/;
    const value = inputElement.value.trim();
    if (!regex.test(value)) {
        inputElement.classList.remove("is-valid");
        inputElement.classList.add("is-invalid");
        errorElement.textContent = errorText;
        errorElement.classList.add("text-danger");
        return false
    } else {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
        errorElement.textContent = successText;
        errorElement.classList.remove("text-danger");
        errorElement.classList.add("text-success");
        return true
    }
}





