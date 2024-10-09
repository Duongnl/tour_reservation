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




