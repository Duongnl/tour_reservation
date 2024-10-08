
// hàm này chỉ cho phép chứa chữ, số và , . - và không cho phép chỉ chứa khoảng trắng
export function validateText(inputElement, errorElement,successText,errorText ) {

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
    const regex = /^\+?\d{0,11}$/;

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

//hàm kiểm tra tên đăng nhập
export function validateUsername(inputElement, errorElement, successText, errorText) {
    // Biểu thức chính quy kiểm tra định dạng tên đăng nhập
    const regex = /^[\p{L}0-9]+$/u;

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



// hàm này chỉ cho phép chứa chữ, số và , . - và cho phép chỉ chứa khoảng trắng (để trống)
export function validateTextDetail(inputElement, errorElement,successText,errorText ) {

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

export function validateTypeahead (inputElement, errorElement,successText,errorText ) {
    let value = inputElement.value.trim();
    const selectValue = document.querySelector('.tt-open')
    let flag = false;
    selectValue.addEventListener("click", function () {
        console.log("Selected")
        flag = true;
        value = inputElement.value.trim();
    })


        if (states?.includes(value)) {
            flag = true
        }else {
            flag =  false
        }
    return flag;

}


