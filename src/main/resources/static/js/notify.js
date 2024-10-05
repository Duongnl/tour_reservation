export function successNotify (message) {
    console.log("Đã vào")
    $.notify({
        // Nội dung thông báo
        message: `${message}`
    }, {
        // Tùy chọn thông báo
        type: 'success', // Loại thông báo (success, info, warning, danger)
        delay: 2000, // Thời gian hiển thị (ms)
        placement: {
            from: "top", // Từ vị trí nào
            align: "center" // Căn chỉnh
        }
    });
}

export function errorNotify (message) {
    console.log("Đã vào")
    $.notify({
        // Nội dung thông báo
        message: `${message}`
    }, {
        // Tùy chọn thông báo
        type: 'danger', // Loại thông báo (success, info, warning, danger)
        delay: 2000, // Thời gian hiển thị (ms)
        placement: {
            from: "top", // Từ vị trí nào
            align: "center" // Căn chỉnh
        }
    });
}

document.addEventListener("DOMContentLoaded", function () {
    const successMessage = document.getElementById("success-message")

    if (successMessage) {

        successNotify(successMessage.textContent); // Gọi hàm thông báo
    }
});