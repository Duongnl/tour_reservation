document.addEventListener('DOMContentLoaded', function() {
// In your Javascript (external .js resource or <script> tag)
    let departureLocations = [];
    // Fetch dữ liệu từ API
    fetch('/api/client/departure-location')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(departureLocationRes => {
            departureLocations = departureLocationRes;
            console.log("departureLocations >>> ",departureLocations)

            const departureLocationDiv = document.getElementById("departure-location");
            departureLocationDiv.innerHTML="";

            const optionSelected = document.createElement('option');
            optionSelected.value = "all";
            optionSelected.textContent = "Tất cả điểm khởi hành";
            departureLocationDiv.appendChild(optionSelected);

            for (let i =0; i<departureLocations.length; i++) {
                const option = document.createElement('option');
                option.value = departureLocations[i];
                option.textContent = departureLocations[i];
                console.log('fhduiash',departureLocations[i])
                departureLocationDiv.appendChild(option);
            }

        })
        .catch(error => {
            console.error('Có lỗi khi fetch API:', error);
        });



    $(document).ready(function() {
        $('.js-example-basic-single').select2();
    });

});
