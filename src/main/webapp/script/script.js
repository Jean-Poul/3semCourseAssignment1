
// Global variables
var url = "api/groupmembers/";
var getAllMembers = document.getElementById("getAllMembers");


// All members function (building table)
getAllMembers.onclick = function () {

    let newUrl = url + "all";

    fetch(newUrl)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {

                let result = " <thead> <th> Student ID </th> <th> Name </th> <th> Favorite Shows </th> </thead> <tbody>";
                for (var i in data) {
                    result += "<tr> <td>" + data[i].studentId + "</td> <td>" + data[i].name + "</td> <td>" + data[i].favoriteShows + "</td> </tr>";
                }
                result += "</tbody>";
                document.getElementById("userTable").innerHTML = result;

                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/

            });

};
