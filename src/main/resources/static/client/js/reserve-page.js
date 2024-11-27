import { validateText, validateEmail, validatePhoneNumber, validateUsername, validatePassword,
    validateConfirmPassword } from './validation.js';

document.addEventListener('DOMContentLoaded', function () {
    const minusAdult = document.getElementById("minus-adult")
    let plusAdult = document.getElementById("plus-adult")
    const numberAdult = document.getElementById("number-adult")

    const minusChild = document.getElementById("minus-child")
    let plusChild = document.getElementById("plus-child")
    const numberChild = document.getElementById("number-child")

    const countCustomerDiv = document.getElementById("count-customer")
    const priceAdultDiv = document.getElementById("price-adult")
    const priceChildDiv = document.getElementById("price-child")
    const priceTotalDiv = document.getElementById("total-price")


    const btnReserve = document.getElementById("reserve")

    let ctnCustomerAdult = document.getElementById("ctn-customer-adult")
    let ctnCustomerChild = document.getElementById("ctn-customer-child")


    const priceAdult = document.getElementById("inp-price-adult").value;
    const priceChild = document.getElementById("inp-price-child").value;

    const quantityLeft = document.getElementById("quantity-left")

    numberAdult.value = 1
    numberChild.value = 0

    let validation = [...Array(4).fill(false)]

    const nameInf = document.getElementById("name-inf")
    const email = document.getElementById("email")
    const phoneNumber = document.getElementById("phone-number")
    const address = document.getElementById("address")

    const nameInfError = document.getElementById("name-inf-error")
    const emailError = document.getElementById("email-error")
    const phoneNumberError = document.getElementById("phone-number-error")
    const addressError = document.getElementById("address-error")

    const detail = document.getElementById("detail")

    const tienMat = document.getElementById("tien-mat")
    const chuyenKhoan = document.getElementById("chuyen-khoan")


    let customers = []





    // xu ly so luong khach hang x so tien nguoi lon
    const item = document.createElement('div');
    item.innerHTML = `
            <p class="text-price"> ${numberAdult.value} x ${priceAdult}</p>
        `
    priceAdultDiv.appendChild(item)
    const handelPriceAdult = () => {
        priceAdultDiv.innerHTML=``

        const item = document.createElement('div');
        item.innerHTML = `
            <p class="text-price"> ${numberAdult.value} x ${priceAdult}</p>`
        priceAdultDiv.appendChild(item)
    }
    //

    const item1 = document.createElement('div');
    item1.innerHTML = `
            <p class="text-price-total">${priceAdult}</p>
        `
    priceTotalDiv.appendChild(item1)
    const handleTotalPrice = () => {
        let priceTotal = ((parseInt(priceAdult) * parseInt(numberAdult.value)) + (parseInt(priceChild) * parseInt(numberChild.value)))
        priceTotalDiv.innerHTML=``

        const item = document.createElement('div');
        item.innerHTML = `
            <p class="text-price-total">${priceTotal}</p>`
        priceTotalDiv.appendChild(item)
    }

    // xu ly so luong khach hang x so tien tre em
    const handelPriceChild = () => {
        priceChildDiv.innerHTML=``
        const item = document.createElement('div');
        item.classList.add('child')
        item.innerHTML = `
            <p class="text-color">Trẻ em</p>
            <p class="text-price"> ${numberChild.value} x ${priceChild}</p>
        `
        priceChildDiv.appendChild(item)
        if (parseInt(numberChild.value) === 0) {
            priceChildDiv.innerHTML=``
        }
    }

    const countCustomer = () => {
        countCustomerDiv.innerHTML = ``
        let total = parseInt(numberAdult.value) + parseInt(numberChild.value)
        const item = document.createElement('div');
        item.innerHTML = `
           <span class="tour-title"><i class="fa fa-user"></i> ${total} người</span>
        `
        countCustomerDiv.appendChild(item)
    }



    const addAdultCustomer = () => {
        const itemCustomer = document.createElement('div');
        itemCustomer.innerHTML = `
                <div class="inf-customer cus-adult">
                    <div class="form-group pr-4 w-50">
                        <label class="text-bold-color">Họ tên</label>
                        <input type="text" class="form-control name-cus" aria-describedby="emailHelp">
                        <small  class="form-text text-muted name-error">Vui lòng điền tên</small>
                    </div>
                    <div class="form-group pr-4 w-25">
                        <label   class="text-bold-color">Ngày sinh</label>
                        <input  type="date" class="form-control birthday-cus" aria-describedby="emailHelp">
                        <small  class="form-text text-muted birthday-error">Vui lòng chọn ngày sinh</small>
                    </div>
                    <div class="form-group pr-4 w-25">
                        <label  class="text-bold-color" >Giới tính</label>
                        <select class="form-control sex">
                            <option value=1 >Nam</option>
                            <option value=2 >Nữ</option>
                            <option value=3 >Khác</option>
                        </select>
                    </div>
                </div>
        `
        ctnCustomerAdult.appendChild(itemCustomer);
    }

    const addChildCustomer = (first) => {
        const itemCustomer = document.createElement('div');
        itemCustomer.innerHTML = `
                ${ first ? `
                <p class="text-bold-color " >
                    <svg xmlns="http://www.w3.org/2000/svg"
                         width="24"
                        height="24"
                        fill="none">
                    <path fill="#000" d="M13.443 19.85a.34.34 0 0 0-.445.195.4.4 0 0 1-.372.252h-1.252a.4.4 0 0 1-.372-.252.34.34 0 0 0-.445-.195.355.355 0 0 0-.19.457c.164.42.56.693 1.007.693h1.252c.448 0 .843-.272 1.008-.693a.354.354 0 0 0-.19-.457"></path><path fill="#000" d="M17.469 18.039a1.73 1.73 0 0 0-.812-1.17 1.78 1.78 0 0 0 .61-1.617 4.5 4.5 0 0 0-.985-2.216 4.35 4.35 0 0 0-1.412-1.118 4.08 4.08 0 0 0 1.064-2.757c0-1.844-1.211-3.403-2.86-3.883.224-.275.346-.619.346-.982 0-.714-.567-1.296-1.264-1.296a1.13 1.13 0 0 0-.742.264.97.97 0 0 0-.338.745c0 .194.154.352.343.352s.343-.158.343-.352q0-.126.094-.207a.46.46 0 0 1 .3-.099c.32 0 .579.266.579.593 0 .222-.084.43-.237.587a.8.8 0 0 1-.572.243h-.025C9.777 5.182 8.066 6.97 8.066 9.16c0 1.066.405 2.036 1.064 2.758-.539.266-1.02.646-1.412 1.118a4.5 4.5 0 0 0-.985 2.215 1.78 1.78 0 0 0 .61 1.617 1.73 1.73 0 0 0-.812 1.17 1.77 1.77 0 0 0 .329 1.4l.734.962c.296.389.747.598 1.204.598.26 0 .52-.067.757-.207a1.59 1.59 0 0 0 .652-1.938L9.76 17.72a2 2 0 0 0-.104-.218h2.514c.19 0 .343-.157.343-.351a.347.347 0 0 0-.343-.352H9.005v-.741c0-.096.076-.173.17-.173h5.65c.094 0 .17.077.17.173v.79a1.72 1.72 0 0 0-.756.872l-.446 1.133a1.59 1.59 0 0 0 .652 1.938c.236.14.498.207.757.207.457 0 .908-.209 1.204-.598l.734-.962c.303-.396.422-.906.329-1.4M8.751 9.16c0-1.838 1.458-3.332 3.249-3.332s3.249 1.494 3.249 3.332S13.79 12.492 12 12.492s-3.249-1.494-3.249-3.331m.375 8.822.445 1.133a.875.875 0 0 1-.358 1.066.83.83 0 0 1-1.08-.215l-.734-.962a1.05 1.05 0 0 1-.195-.833c.056-.293.232-.547.485-.697a.98.98 0 0 1 .831-.082c.276.098.496.313.606.59m5.702-3.504a.347.347 0 0 0-.343.351v.352H9.517v-.352a.347.347 0 0 0-.343-.351.347.347 0 0 0-.343.351v.425a.88.88 0 0 0-.511.803v.496c-.26-.026-.5-.154-.673-.359a1.07 1.07 0 0 1-.236-.84c.197-1.337 1.066-2.442 2.287-2.924a3.85 3.85 0 0 0 4.604 0c1.22.482 2.09 1.587 2.287 2.925.044.302-.042.608-.236.84a1.01 1.01 0 0 1-.673.358v-.496a.88.88 0 0 0-.51-.801v-.427a.347.347 0 0 0-.342-.351m1.773 4.526-.734.962a.83.83 0 0 1-1.08.215.874.874 0 0 1-.358-1.066l.445-1.133c.11-.277.33-.492.606-.59a.98.98 0 0 1 .831.082c.253.15.43.404.485.697.056.294-.016.597-.195.833"></path><path fill="#000" d="M13.775 16.902a.34.34 0 0 0-.242-.103.34.34 0 0 0-.242.103.36.36 0 0 0 0 .497.34.34 0 0 0 .242.103.34.34 0 0 0 .242-.103.36.36 0 0 0 0-.497M12.945 10.361a.337.337 0 0 0-.482.052.588.588 0 0 1-.926 0 .337.337 0 0 0-.482-.052.36.36 0 0 0-.05.495c.244.31.607.487.995.487s.75-.178.995-.487a.36.36 0 0 0-.05-.495M11.134 8.912a.784.784 0 0 0-1.128 0 .36.36 0 0 0 0 .497c.134.137.35.137.485 0a.11.11 0 0 1 .158 0 .34.34 0 0 0 .485 0 .36.36 0 0 0 0-.497M13.994 8.912a.785.785 0 0 0-1.128 0 .36.36 0 0 0 0 .497.337.337 0 0 0 .485 0 .11.11 0 0 1 .158 0c.134.137.351.137.485 0a.36.36 0 0 0 0-.497">
                    </path></svg>
                    Trẻ em
                </p>
                ` : ``}

                <div class="inf-customer cus-child">
                    <div class="form-group pr-4 w-50">
                        <label class="text-bold-color">Họ tên</label>
                        <input type="text" class="form-control name-cus" aria-describedby="emailHelp">
                        <small  class="form-text text-muted name-error">Vui lòng điền tên.</small>
                    </div>
                    <div class="form-group pr-4 w-25">
                        <label  class="text-bold-color">Ngày sinh</label>
                        <input  type="date" class="form-control birthday-cus" aria-describedby="emailHelp">
                        <small  class="form-text text-muted birthday-error">Vui lòng chọn ngày sinh.</small>
                    </div>
                    <div class="form-group pr-4 w-25">
                        <label class="text-bold-color" >Giới tính</label>
                        <select class="form-control sex">
                            <option value=1 >Nam</option>
                            <option  value=2 >Nữ</option>
                            <option value= 3 >Khác</option>
                        </select>
                    </div>
                </div>
        `
        ctnCustomerChild.appendChild(itemCustomer);
    }

    const handleValidateReserve = () => {
        if (!validation.some(v => v === false) && (tienMat.checked || chuyenKhoan.checked)) {
            btnReserve.disabled = false
        } else {
            btnReserve.disabled = true
        }

        if (parseInt(quantityLeft.innerText) === 0) {
            btnReserve.disabled = true
        }
        console.log("quantityLeft >>> ", quantityLeft.innerText)
    }

    const validateAutoFiled = () => {
        if (nameInf.value !== "") {
            validation[0] = true
        }
        if (phoneNumber.value !== "") {
            validation[1] = true
        }
        if (email.value !== "") {
            validation[2] = true
        }

        if (address.value !== "") {
            validation[3] = true
        }
        handleValidateReserve()
    }

    validateAutoFiled()


    minusAdult.addEventListener("click", function () {
        if (parseInt(numberAdult.value) >1 ) {
            numberAdult.value = parseInt(numberAdult.value) - 1
            ctnCustomerAdult.removeChild(ctnCustomerAdult.lastChild);
            countCustomer()
            handelPriceAdult()
            handleTotalPrice()
        }
    });

    plusAdult.addEventListener("click", function () {
        numberAdult.value = parseInt(numberAdult.value) + 1
        addAdultCustomer()
        countCustomer()
        handelPriceAdult()
        handleTotalPrice()
    });

    minusChild.addEventListener("click", function () {
        if (parseInt(numberChild.value) > 0 ) {
            numberChild.value = parseInt(numberChild.value) - 1
            ctnCustomerChild.removeChild(ctnCustomerChild.lastChild);
            countCustomer()
            handelPriceChild()
            handleTotalPrice()
        }
    });

    plusChild.addEventListener("click", function () {
        numberChild.value = parseInt(numberChild.value) + 1
        if ( parseInt(numberChild.value) === 1) {
        addChildCustomer(true)
        } else {
            addChildCustomer(false)
        }
        countCustomer()
        handelPriceChild()
        handleTotalPrice()
    });

    nameInf.addEventListener("input", function () {
        const successText = "    "
        const errorText = "Họ tên không hợp lệ, không được để trống"
        validation[0] = validateText(nameInf, nameInfError, successText, errorText);
        handleValidateReserve()
    });

    phoneNumber.addEventListener("input", function () {
        const successText = "   "
        const errorText = "Số điện thoại không hợp lệ, không được để trống"
        validation[1] = validatePhoneNumber(phoneNumber, phoneNumberError, successText, errorText);
        handleValidateReserve()
    });

    email.addEventListener("input", function () {
        const successText = "     "
        const errorText = "Email không đúng định dạng, không để trống."
        validation[2] = validateEmail(email, emailError, successText, errorText);
        handleValidateReserve()
    });

    address.addEventListener("input", function () {
        const successText = "    "
        const errorText = "Địa chỉ không đúng định dạng, không để trống."
        validation[3] = validateText(address, addressError, successText, errorText);
        handleValidateReserve()
    });

    tienMat.addEventListener('change', function () {
       console.log("tien mat >>> ", tienMat.checked)
        if (tienMat.checked) {
            chuyenKhoan.checked = false
        }
        handleValidateReserve()
    })

    chuyenKhoan.addEventListener('change', function () {
        console.log("chuyen khoan >>> ", chuyenKhoan.checked)
        if (chuyenKhoan.checked) {
            tienMat.checked = false
        }
        handleValidateReserve()
    })


    const nameCusElements = document.querySelectorAll(".name-cus");
    const birthdayCusElements = document.querySelectorAll(".birthday-cus");

    const handleTourScheduleId = () => {
        const urlPath = window.location.pathname; // Lấy phần đường dẫn, ví dụ: "/reserve/tour-hang-chau-1" hoặc "/reserve/1"
        const match = urlPath.match(/(\d+)$/); // Tìm số cuối cùng trong đường dẫn
        const tourScheduleId = match ? parseInt(match[1], 10) : null; // Nếu có số, chuyển sang số nguyên
        console.log("Tour schedule id >>>", tourScheduleId);
        return tourScheduleId;
    };


     function errorNotify (message) {
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
            },
            z_index: 9999 // Đảm bảo thông báo nằm trên các phần tử khác
        });
    }



    btnReserve.addEventListener("click", async function (){

        let listCus = []

        const customerAdultElements = document.querySelectorAll(".cus-adult");
        const customerChildElements = document.querySelectorAll(".cus-child");
        // Lặp qua từng phần tử .inf-customer và lấy thông tin
        customerAdultElements.forEach((customerItem, index) => {
            const name = customerItem.querySelector(".name-cus").value;
            const birthday = customerItem.querySelector(".birthday-cus").value;
            const sex = customerItem.querySelector(".sex").value;

            const customer = {
                customerName:name,
                birthday:birthday,
                sex:sex,
                customerType: "adult"
            }
            listCus.push(customer)
        });

        customerChildElements.forEach((customerItem, index) => {
            const name = customerItem.querySelector(".name-cus").value;
            const birthday = customerItem.querySelector(".birthday-cus").value;
            const sex = customerItem.querySelector(".sex").value;

            const customer = {
                customerName:name,
                birthday:birthday,
                sex:sex,
                customerType: "child"
            }
            listCus.push(customer)
        });




        const data = {
            tourScheduleId: handleTourScheduleId(),
            reserveDetail:detail.value,
            customerName:nameInf.value,
            phoneNumber : phoneNumber.value,
            email : email.value,
            address: address.value,
            customers:listCus
        }

        const res = await  fetch("/api/client/reserve", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'

            },
            body: JSON.stringify(data)
        })
        const dataRes = await res.json();
        console.log("dataRes", dataRes)
        if(dataRes.code === 200 ) {

            if (chuyenKhoan.checked) {
                const data = {
                    tourScheduleId: handleTourScheduleId(),
                    reserveId:dataRes.reserveId,
                    reserveDetail:detail.value,
                    customerName:nameInf.value,
                    phoneNumber : phoneNumber.value,
                    email : email.value,
                    address: address.value,
                    customers:listCus
                }

                const res = await  fetch("/api/payment/create_payment", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                const dataRes1 = await res.json();
                window.location.href = dataRes1.url

            }

        } else if (dataRes.code === 201) {

            errorNotify("Chuyến đi đã hết chổ")


        }


    })







})

