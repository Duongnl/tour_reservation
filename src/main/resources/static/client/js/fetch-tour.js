document.addEventListener('DOMContentLoaded', function () {
    // const loader = document.querySelector('.loader');
    // const overlayer = document.getElementById('overlayer');
    let tours = [];
    const category = document.getElementById("category")
    const departureLocation = document.getElementById("departure-location")
    const destinationLocation = document.getElementById("destination-location")
    const departureDate = document.getElementById("departure-date")
    const price = document.getElementById("price")
    const tourSize = document.getElementById("tour-size")
    const btnReset = document.getElementById("btn-reset")


    async function postData(url = '', data = {}) {
        try {
            const response = await fetch(url, {
                method: 'POST', // Chọn phương thức POST
                headers: {
                    'Content-Type': 'application/json', // Định dạng dữ liệu là JSON
                },
                body: JSON.stringify(data), // Chuyển đổi đối tượng data thành chuỗi JSON
            });

            // Kiểm tra xem phản hồi có thành công hay không
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }

            const responseData = await response.json(); // Chuyển đổi phản hồi sang định dạng JSON
            return responseData; // Trả về dữ liệu phản hồi
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    }




    const fetchTours = (dataToSend) => {
        postData(apiUrl, dataToSend)
            .then(tourData => {
                console.log("Data  >>> ", tourData)
                tours = tourData.tourCardResponses;
                // Ẩn loader và overlay
                // loader.style.display = 'none';
                // overlayer.style.display = 'none';
                tourSize.innerHTML = tourData.cardTotal;
                // Chuyển đổi số sang định dạng tiền tệ
                const formatPrice = (price) => {
                    if (price == null) {
                        return ""
                    }
                    return price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                };

                // Tạo HTML bằng JavaScript
                const createTourHtml = (tourData) => {
                    const tourContainer = document.createElement('div');
                    tourContainer.classList.add('col-12', 'col-sm-12', 'col-md-6', 'col-lg-6', 'mb-4', 'mb-lg-0');

                    tourContainer.innerHTML = `
                                <div class="media-1">
                                    <a href="/tour/tour_detail/${tourData.tourId}" class="d-block mb-3">
                                        <img src="${tourData.imageMain}" alt="Image" class="img-fluid">
                                    </a>
                                    <div class="div-tour-content">
                                        <span class="d-flex align-items-center loc mb-2 span-country">
                                            <span class="icon-room mr-3"></span>
                                            <span>${tourData.country}</span>
                                        </span>
                                        <div class="d-flex align-items-center loc mb-2">
                                            <div>
                                                <h3 class="h3-tour-name">
                                                    <a href="#" class="a-tour-name">${tourData.tourName}</a>
                                                </h3>

                                                <div class="div-price">
                                                    <div class="price">
                                                        <span class="span-price">${tourData.priceSale != null ? formatPrice(tourData.priceSale) : (tourData.price != null ? formatPrice(tourData.price) : "Đang cập nhật")}</span>
                                                    </div>
                                                    <div class="price">
                                                        <span class="span-price-sale">${tourData.priceSale != null ? formatPrice(tourData.price) : ""}</span>
                                                    </div>
                                                </div>

                                                <div class="price ml-auto">
                                                    <span class="departure-location">Khởi hành: ${(tourData.departureLocation != null && tourData.departureLocation !== "") ? tourData.departureLocation : "Đang cập nhật"}</span>
                                                </div>

                                                <div class="scrollContainer">
                                                    ${tourData.departureDates.length > 0
                        ? tourData.departureDates.map(date => `<div class="list-item">${date}</div>`).join('')
                        : '<div class="list-item">Đang cập nhật</div>'}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            `;

                    return tourContainer;
                };


                const createPagination = (number, active) => {
                    const liContainer = document.createElement('li');
                    if (active === "active") {
                        liContainer.classList.add("page-item", "active" )
                    } else {
                        liContainer.classList.add("page-item" )
                    }

                    const button = document.createElement('button');
                    button.className = 'page-link';
                    button.textContent = number;
                    liContainer.appendChild(button)

                    return liContainer;
                }

                // Tìm phần tử cha để chèn HTML vào
                const divTour = document.getElementById('div-row-tour'); // Thay đổi ID theo phần tử trên trang của bạn
                divTour.innerHTML = "";  // Xóa nội dung cũ

                for (let i = 0; i < tours.length; i++) {
                    divTour.appendChild(createTourHtml(tours[i]));
                }

                const ul = document.getElementById("pagination");
                ul.innerHTML = ""
                for (let i = 1; i <=tourData.page; i++) {
                    if (i === tourData.pageCurrent) {
                        ul.appendChild(createPagination(i, "active"));
                    } else {
                        ul.appendChild(createPagination(i,""));
                    }

                }
                attachPaginationEvents()





            })
            .catch(error => {
                console.error('Error:', error); // Xử lý lỗi
            });
    };


    const btnFilter = document.getElementById("btn-filter")
    const apiUrl = '/api/client/filter-tour'; // Thay thế bằng URL API của bạn
    let dataToSend = {
        pageCurrent:1,
        category: 0,
        departureLocation: "all",
        destinationLocation: "all",
        departureDate: null,
        price: "all"
    }

// Gọi API ban đầu
     fetchTours(dataToSend);

    btnFilter.addEventListener("click", function () {
        let dataToSend = {
            pageCurrent:1,
            category: category.value,
            departureLocation: departureLocation.value,
            destinationLocation: destinationLocation.value,
            departureDate: departureDate.value === '' ? null : departureDate.value,
            price: price.value
        }
        $('html, body').animate({
            scrollTop: 0
        }, 500); // 400 milliseconds (0.4 seconds)
        console.log("Data filter >>> ", dataToSend)
        fetchTours(dataToSend)
    })

    function attachPaginationEvents() {
        const pageLinks = document.querySelectorAll('.page-link');
        pageLinks.forEach(link => {
            link.addEventListener('click', (event) => {
                event.preventDefault();

                // Lấy giá trị của trang
                const pageValue = event.target.textContent;
                console.log('Page clicked:', pageValue);

                let dataToSend = {
                    pageCurrent:pageValue,
                    category: category.value,
                    departureLocation: departureLocation.value,
                    destinationLocation: destinationLocation.value,
                    departureDate: departureDate.value === '' ? null : departureDate.value,
                    price: price.value
                }
                $('html, body').animate({
                    scrollTop: 0
                }, 500); // 400 milliseconds (0.4 seconds)
                console.log("Data filter >>> ", dataToSend)
                fetchTours(dataToSend)

            });
        });
    }




});
