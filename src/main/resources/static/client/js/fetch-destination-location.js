document.addEventListener('DOMContentLoaded', function() {
// In your Javascript (external .js resource or <script> tag)
    let destinationLocations = [];
    // Fetch dữ liệu từ API
    fetch('/api/client/destination-location')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(destinationLocationRes => {
            destinationLocations = destinationLocationRes;
            console.log("departureLocations >>> ",destinationLocations)

            const destinationLocationDiv = document.getElementById("destination-location");
            destinationLocationDiv.innerHTML="";

            const optionSelected = document.createElement('option');
            optionSelected.value = "all";
            optionSelected.textContent = "Tất cả điểm đến";
            destinationLocationDiv.appendChild(optionSelected);

            for (let i =0; i<destinationLocations.length; i++) {
                const option = document.createElement('option');
                option.value = destinationLocations[i];
                option.textContent = destinationLocations[i];
                console.log('fhduiash',destinationLocations[i])
                destinationLocationDiv.appendChild(option);
            }

        })
        .catch(error => {
            console.error('Có lỗi khi fetch API:', error);
        });



    $(document).ready(function() {
        $('.js-example-basic-single').select2();
    });

});
