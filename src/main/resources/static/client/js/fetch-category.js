document.addEventListener('DOMContentLoaded', function() {
// In your Javascript (external .js resource or <script> tag)
    let categories = [];
    // Fetch dữ liệu từ API
    fetch('/api/client/category')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(categoryRes => {
            categories = categoryRes;
            console.log("Category >>> ",categoryRes)

            const categoryDiv = document.getElementById("category");
            categoryDiv.innerHTML="";

            const optionSelected = document.createElement('option');
            optionSelected.value = "0";
            optionSelected.textContent = "Tất cả danh mục";
            categoryDiv.appendChild(optionSelected);

            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.categoryId;
                option.textContent = category.categoryName;
                categoryDiv.appendChild(option);
            });

        })
        .catch(error => {
            console.error('Có lỗi khi fetch API:', error);
        });



    $(document).ready(function() {
        $('.js-example-basic-single').select2();
    });

});
