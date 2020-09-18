
            // Global variables
            var url = "api/car/";
            var allCars = document.getElementById("allCars");
            var searchYear = document.getElementById("searchYear");
            var searchMake = document.getElementById("searchMake");
            var priceBelow = document.getElementById("priceBelow");
            document.getElementById("carTable").innerHTML = "<thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead><tbody></tbody>";

            //Show all cars in table
            allCars.onclick = function () {
                let newUrl = url + "all";

                fetch(newUrl)
                        .then(res => res.json())
                        .then(data => {
                            document.getElementById("errorMessage").innerHTML = "";
                            let result = " <thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead> <tbody>";
                            for (var i in data) {
                                result += "<tr> <td>" + data[i].id + "</td> <td>" + data[i].maker + "</td> <td>" + data[i].model + "</td> <td>" + data[i].year + "</td> <td>" + data[i].price + "</td> </tr>";
                            }
                            result += "</tbody>";
                            document.getElementById("carTable").innerHTML = result;
                            console.log("data", data);
                        });
            };

            //Search by year
            searchYear.onclick = function () {
                let carInputField = document.getElementById("carInputField").value;
                if (isNaN(carInputField) || carInputField.length !== 4) {
                    document.getElementById("errorMessage").innerHTML = "Please enter a valid year";
                    document.getElementById("carTable").innerHTML = "<thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead><tbody></tbody>";
                } else {
                    let newUrl = url + "year/" + carInputField;
                    fetch(newUrl)
                            .then(res => res.json())
                            .then(data => {
                                document.getElementById("errorMessage").innerHTML = "";

                                let result = " <thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead> <tbody>";
                                for (var i in data) {
                                    result += "<tr> <td>" + data[i].id + "</td> <td>" + data[i].maker + "</td> <td>" + data[i].model + "</td> <td>" + data[i].year + "</td> <td>" + data[i].price + "</td> </tr>";
                                    //Sets ifNull to id, to help with varying that data isn't empty
                                    var ifNull = data[i].id;
                                }
                                if (ifNull === null || ifNull === "" || ifNull === "undefined") {
                                    document.getElementById("errorMessage").innerHTML = "No results found for that year";
                                }
                                result += "</tbody>";
                                document.getElementById("carTable").innerHTML = result;
                                console.log("data", data);
                            });
                }
            };

            //Search by make
            searchMake.onclick = function () {

                let carInputField = document.getElementById("carInputField").value;
                if (!isNaN(carInputField)) {
                    document.getElementById("errorMessage").innerHTML = "Please enter a valid make";
                    document.getElementById("carTable").innerHTML = "<thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead><tbody></tbody>";
                } else {
                    let newUrl = url + carInputField;
                    fetch(newUrl)
                            .then(res => res.json())
                            .then(data => {
                                document.getElementById("errorMessage").innerHTML = "";
                                let result = " <thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead> <tbody>";
                                for (var i in data) {
                                    result += "<tr> <td>" + data[i].id + "</td> <td>" + data[i].maker + "</td> <td>" + data[i].model + "</td> <td>" + data[i].year + "</td> <td>" + data[i].price + "</td> </tr>";

                                    //Helps with validating if data is empty
                                    var ifNull = data[i].id;
                                }
                                if (ifNull === null || ifNull === "" || ifNull === "undefined") {
                                    document.getElementById("errorMessage").innerHTML = "No results found";
                                }
                                result += "</tbody>";
                                document.getElementById("carTable").innerHTML = result;
                                console.log("data", data);
                            });
                }
            };

            //show price below
            priceBelow.onclick = function () {

                let carInputField = document.getElementById("carInputField").value;
                if (isNaN(carInputField) || carInputField === "" || carInputField === null) {
                    document.getElementById("errorMessage").innerHTML = "Please enter a valid price";
                    document.getElementById("carTable").innerHTML = "<thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead><tbody></tbody>";
                } else {
                    let newUrl = url + "all";
                    fetch(newUrl)
                            .then(res => res.json())
                            .then(data => {
                                document.getElementById("errorMessage").innerHTML = "";
                                var carsFiltered = data.filter(data => data.price <= carInputField);
                                //checks if carsfiltered has a length
                                if (!carsFiltered.length) {
                                    document.getElementById("errorMessage").innerHTML = "No results found";
                                    document.getElementById("carTable").innerHTML = "<thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead><tbody></tbody>";
                                } else {
                                    carsToHtmlTable = "<thead> <tr><th>ID</th><th>Make</th> <th>Model</th> <th>Year</th><th>Price</th></thead><tbody>" + carsFiltered.map(function (car) {
                                        return "<tr><td>" + car.id + "</td><td>" + car.maker + "</td><td>" + car.model + "</td><td>" + car.year + "</td><td>" + car.price + "</td></tr>";
                                    }).join("") + "</tbody";
                                    document.getElementById("carTable").innerHTML = carsToHtmlTable;
                                }
                                console.log("data", data);
                            });
                }
            };