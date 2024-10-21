import { validateText, validateTextDetail, validateTypeahead} from './validation.js';
import {errorNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {
    const tourName = document.getElementById('tour-name')
    const tourCity = document.getElementById('city')
    const tourCountry = document.getElementById('country')
    const tourDetail = document.getElementById('tour-detail')
    const category = document.getElementById('category')

    const tourNameError = document.getElementById('tour-name-error')
    const tourCityError = document.getElementById('city-error')
    const tourCountryError = document.getElementById('country-error')
    const tourDetailError = document.getElementById('tour-detail-error')
    const categoryError = document.getElementById('category-error')
    const btnTourAdd = document.getElementById('btn-tour-add')
    const selectValue = document.querySelector('.tt-dataset')

    const inputMainImg = document.getElementById('input-main-img')
    const imgMain = document.getElementById('img-main')
    const divMainImg = document.querySelector('.div-main-img')
    const inputMainImgError = document.getElementById('input-main-img-error')
    const btnDeleteMainImg = document.querySelector('.btn-delete-main-img')

    const input1Img = document.getElementById('input-1-img')
    const img1 = document.getElementById('img-1')
    const div1Img = document.querySelector('.div-1-img')
    const input1ImgError = document.getElementById('input-1-img-error')
    const btnDelete1Img = document.querySelector('.btn-delete-1-img')

    const input2Img = document.getElementById('input-2-img')
    const img2 = document.getElementById('img-2')
    const div2Img = document.querySelector('.div-2-img')
    const input2ImgError = document.getElementById('input-2-img-error')
    const btnDelete2Img = document.querySelector('.btn-delete-2-img')

    const ipImg1 = document.getElementById("ipImg1");
    const ipImg2 = document.getElementById("ipImg2");


    const tourId = document.getElementById('tour-id');
    // let validation= [...Array(5).fill(false),true];
    let validation;

    if (tourId) {
         validation = [...Array(6).fill(true)]
    } else {

     validation = [...Array(5).fill(false),true]
    }

    tourName.addEventListener("input", function () {
        const successText = "Tên tour hợp lệ"
        const errorText = "Tên tour chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(tourName, tourNameError, successText, errorText);
    });

    tourCity.addEventListener("input", function () {
        const successText = "Tên thành phố/tỉnh hợp lệ"
        const errorText = "Tên thành phố/tỉnh chỉ cho phép chứa chữ, số và , . -"
        validation[1] = validateText(tourCity, tourCityError, successText, errorText);
    });

    tourCountry.addEventListener("input", function () {
        const successText = "Tên nước hợp lệ"
        const errorText = "Tên nước chỉ cho phép chứa chữ, số và , . -"
        validation[2] = validateText(tourCountry, tourCountryError, successText, errorText);
    });

    category.addEventListener("input", function () {
        const successText = "Tên danh mục hợp lệ"
        const errorText = "Tên danh mục không tồn tại"
        validation[3] = validateTypeahead(category, categoryError, successText, errorText);
    })

    selectValue.addEventListener("click", function () {
        validation[3] = true
        category.classList.remove("is-invalid");
        category.classList.add("is-valid");
        categoryError.textContent = "Tên danh mục hợp lệ";
        categoryError.classList.remove("text-danger");
        categoryError.classList.add("text-success");
    })

    category.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
            const successText = "Tên danh mục hợp lệ"
            const errorText = "Tên danh mục không tồn tại"
            validation[3] = validateTypeahead(category, categoryError, successText, errorText);
        }
    });




    tourDetail.addEventListener("input", function () {
        const successText = "Thông tin chi tiết hợp lệ"
        const errorText = "Thông tin chi tiết chỉ cho phép chứa chữ, số và , . -"
        validation[5] = validateTextDetail(tourDetail, tourDetailError, successText, errorText);
    });

    function loadImg (img,divImg,inputImg,inputImgError,event, hasImg) {
        const file = event.target.files[0]; // Lấy tệp hình ảnh đầu tiên
        const reader = new FileReader(); // Tạo một đối tượng FileReader

        // Khi tệp đã được đọc xong
        reader.onload = function(event) {
            img.src = event.target.result; // Đặt nguồn của thẻ img bằng dữ liệu đã đọc
            img.style.display = 'block'; // Hiển thị thẻ img
        };

        // Đọc tệp dưới dạng URL
        if (file) {
            reader.readAsDataURL(file);
            divImg.classList.add(hasImg)

            inputImg.classList.remove("is-invalid");
            inputImg.classList.add("is-valid");
            inputImgError.textContent = "Ảnh hợp lệ";
            inputImgError.classList.remove("text-danger");
            inputImgError.classList.add("text-success");
        }
    }

    function deleteImg (img,inputImg, inputImgError,  divImg, hasImg) {
        // Reset the img src to remove the image
        img.src = '';
        img.style.display = 'none'; // Hide the image

        // Clear the file input value
        inputImg.value = '';

        if (hasImg === 'has-main-img') {
            // Reset the validation styles and message
            inputImg.classList.remove("is-valid");
            inputImg.classList.add("is-invalid");

            inputImgError.textContent = "Chưa chọn ảnh";
            inputImgError.classList.remove("text-success");
            inputImgError.classList.add("text-danger");
        }


        // Optionally, remove the 'has-main-img' class if needed
        divImg.classList.remove(hasImg);

    }


    inputMainImg.addEventListener('change', function(event) {
        loadImg(imgMain, divMainImg, inputMainImg, inputMainImgError,event,'has-main-img')
        validation[4] =true
    });

    input1Img.addEventListener('change', function(event) {
        loadImg(img1, div1Img, input1Img, input1ImgError,event,'has-1-img')
    });

    input2Img.addEventListener('change', function(event) {
        loadImg(img2, div2Img, input2Img, input2ImgError,event,'has-2-img')
    });


    btnDeleteMainImg.addEventListener('click', function (){
        deleteImg(imgMain, inputMainImg, inputMainImgError, divMainImg, 'has-main-img')
        validation[4] =false
    });

    btnDelete1Img.addEventListener('click', function (){
        deleteImg(img1, input1Img, input1ImgError, div1Img, 'has-1-img')
        ipImg1.value = "";
    });

    btnDelete2Img.addEventListener('click', function (){
        deleteImg(img2, input2Img, input2ImgError, div2Img, 'has-2-img')
        ipImg2.value=''
    });





    btnTourAdd.addEventListener("click", function (event) {

        if (validation.some(v => v === false)) {
            event.preventDefault();
            errorNotify("Vui lòng điền đầy đủ thông tin hợp lệ")
        }
    });


});