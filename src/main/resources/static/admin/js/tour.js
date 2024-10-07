import { validateText,validateTextDetail,validateTypeahead } from './validation.js';
import {errorNotify, successNotify} from "./notify.js";

document.addEventListener("DOMContentLoaded", function () {
    const tourName = document.getElementById('tour-name')
    const  tourCity  = document.getElementById('city')
    const tourCountry = document.getElementById('country')
    const tourDetail = document.getElementById('tour-detail')
    const category = document.getElementById('category')

    const tourNameError = document.getElementById('tour-name-error')
    const tourCityError = document.getElementById('city-error')
    const tourCountryError = document.getElementById('country-error')
    const tourDetailError = document.getElementById('tour-detail-error')
    const categoryError = document.getElementById('category-error')



    let validation = [...Array(4).fill(true)]

    tourName.addEventListener("input", function () {
        const successText = "Tên tour hợp lệ"
        const errorText = "Tên tour chỉ cho phép chứa chữ, số và , . -"
        validation[0] = validateText(tourName, tourNameError,successText,errorText);
    });

    tourCity.addEventListener("input", function () {
        const successText = "Tên thành phố/tỉnh hợp lệ"
        const errorText = "Tên thành phố/tỉnh chỉ cho phép chứa chữ, số và , . -"
        validation[1] = validateText(tourCity, tourCityError,successText,errorText);
    });

    tourCountry.addEventListener("input", function () {
        const successText = "Tên nước hợp lệ"
        const errorText = "Tên nước chỉ cho phép chứa chữ, số và , . -"
        validation[2] = validateText(tourCountry, tourCountryError,successText,errorText);
    });

    category.addEventListener("input", function (){
        const successText = "Tên danh mục hợp lệ"
        const errorText = "Tên danh mục không tồn tại"
        validation[3] = validateTypeahead(category, categoryError,successText,errorText);
    })

    tourDetail.addEventListener("input", function () {
        const successText = "Thông tin chi tiết hợp lệ"
        const errorText = "Thông tin chi tiết chỉ cho phép chứa chữ, số và , . -"
        validation[4] = validateTextDetail(tourDetail, tourDetailError,successText,errorText);
    });

    console.log(validation)







});