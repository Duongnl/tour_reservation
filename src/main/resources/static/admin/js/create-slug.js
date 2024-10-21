
export function CreateSlug (string) {
    const a = 'àáäâãåăæąçćčđďèéěėëêęğǵḧìíïîįłḿǹńňñòóöôœøṕŕřßşśšșťțùúüûǘůűūųẃẍÿýźžż·/_,:;'
    const b = 'aaaaaaaaacccddeeeeeeegghiiiiilmnnnnooooooprrsssssttuuuuuuuuuwxyyzzz------'
    const p = new RegExp(a.split('').join('|'), 'g')
    return string.toString().toLowerCase()
        .replace(/á|à|ả|ạ|ã|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ/gi, 'a')
        .replace(/é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ/gi, 'e')
        .replace(/i|í|ì|ỉ|ĩ|ị/gi, 'i')
        .replace(/ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ/gi, 'o')
        .replace(/ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự/gi, 'u')
        .replace(/ý|ỳ|ỷ|ỹ|ỵ/gi, 'y')
        .replace(/đ/gi, 'd')
        .replace(/\s+/g, '-')
        .replace(p, c => b.charAt(a.indexOf(c)))
        .replace(/&/g, '-and-')
        .replace(/[^\w\-]+/g, '')
        .replace(/\-\-+/g, '-')
        .replace(/^-+/, '')
        .replace(/-+$/, '')
}

document.addEventListener("DOMContentLoaded", function () {
    // Lặp qua tất cả các hàng trong bảng
    const rows = document.querySelectorAll('.row-data');
    const pathname = window.location.pathname;
    if (rows) {
        rows.forEach(row => {
            const txtId = row.querySelector('.txt-id'); // Lấy phần tử txt-id trong hàng
            const txtName = row.querySelector('.txt-name'); // Lấy phần tử txt-name trong hàng
            const btnEdit = row.querySelector('.btn-edit'); // Lấy nút btn-edit trong hàng

            if (btnEdit && txtId && txtName) {
                const slug = CreateSlug(txtName.textContent); // Tạo slug từ tên
                btnEdit.href = `${pathname}/${slug}-${txtId.textContent}`; // Gán giá trị href mới

            }


        });
    }
});

