/**
 * 
 * Javascript for joke.html
 */
document.getElementById("joke").innerHTML = "Guess where the jokes go?";
document.getElementById("message").innerHTML = "&nbsp;";
let random = document.getElementById("random");
let categories = document.getElementById("categories");
let searchBtn = document.getElementById("searchBtn");
let url = "https://api.chucknorris.io/jokes/";


let newcaturl = url + "categories";

// fetch all categories for selectbox
fetch(newcaturl)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {

            var randombycat = "<option selected disabled>Random joke from category</option>";
            for (var i = 0; i < data.length; i++) {
                randombycat += "<option value=" + data[i] + ">" + data[i] + "</option>";
            }

            document.getElementById("categories").innerHTML = randombycat;

            //console.log("data", data);

        });


// fetch a random joke
random.onclick = function (e) {
    e.preventDefault();

    let newurl = url + "random";
    document.getElementById("categories").selectedIndex = 0; // reset selectbox
    document.getElementById("joke").innerHTML = ""; // reset search result div
    document.getElementById("message").innerHTML = "&nbsp;"; // reset message
    document.getElementById("searchTxt").value = ""; // clear textfield

    fetch(newurl)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {

                document.getElementById("joke").innerHTML = "<div><img src=" + data.icon_url + " alt=\"chuck norris avatar\"> Random joke</div><p>" +
                        data.value + "</p>";

                //console.log("data", data);

            });
};


// fetch random joke from category
categories.onchange = function (e) {
    e.preventDefault();

    let catvalue = document.getElementById("categories").value;
    let newurl = url + "random?category=" + catvalue;

    if (catvalue !== "Random joke from category") {
        document.getElementById("joke").innerHTML = ""; // reset search result div
        document.getElementById("message").innerHTML = "&nbsp;"; // reset message
        document.getElementById("searchTxt").value = ""; // clear textfield

        fetch(newurl)
                .then(res => res.json()) //in flow1, just do it
                .then(data => {

                    document.getElementById("joke").innerHTML = "<div><img src=" + data.icon_url +
                            " alt=\"chuck norris avatar\"> Random joke from category " + data.categories + "</div><p>" +
                            data.value + "</p>";

                    //console.log("data", data);

                });

        console.log(catvalue);
    }
};


// fetch random joke by keypress 'r' on keybord
document.body.onkeydown = function (evt) {
    evt = evt || window.event;
    var target = evt.target || evt.srcElement;
    var targetTagName = (target.nodeType === 1) ? target.nodeName.toUpperCase() : "";
    if (!/INPUT|TEXTAREA/.test(targetTagName)) {
        if (evt.keyCode === 82) {
            document.getElementById("joke").innerHTML = ""; // reset search result div
            document.getElementById("message").innerHTML = "&nbsp;"; // reset message
            document.getElementById("searchTxt").value = ""; // clear textfield

            let newurl = url + "random";

            fetch(newurl)
                    .then(res => res.json()) //in flow1, just do it
                    .then(data => {

                        document.getElementById("joke").innerHTML = "<div><img src=" + data.icon_url + " alt=\"chuck norris avatar\"> Random joke</div><p>" +
                                data.value + "</p>";

                        document.getElementById("categories").selectedIndex = 0; // reset selectbox

                    });
            //console.log("data", data);
            //console.log(String.fromCharCode(e.keyCode) + " --> " + e.keyCode);
        }
    }
};

// fetch joke by search
searchBtn.onclick = function (e) {
    e.preventDefault();

    let searchTxt = document.getElementById("searchTxt").value;

    if (searchTxt.length < 3 || searchTxt.legth > 120) {
        document.getElementById("message").innerHTML = "Has to be between 3 and 120 letters";
    } else if (!isNaN(searchTxt) && searchTxt.length > 1) {
        document.getElementById("message").innerHTML = "You need more than numbers";
    } else {
        let newurl = url + "search?query=" + searchTxt;
        document.getElementById("categories").selectedIndex = 0; // reset selectbox     
        document.getElementById("message").innerHTML = "&nbsp;"; // reset message
       
        fetch(newurl)
                .then(res => res.json()) //in flow1, just do it
                .then(data => {

                    var searchresult = "<table class=\"table table-striped table-dark\"><thead><tr><th><div><img src=" +
                            data.result[0].icon_url + " alt=\"chuck norris avatar\"></th><th>" + data.total +
                            " jokes containing the word " + searchTxt + "</th></thead><tbody>";
                    for (var i = 0; i < data.result.length; i++) {
                        var count = i + 1;
                        searchresult += "<tr><td>" + count + "</td><td>" + data.result[i].value + "</td></tr>";
                    }
                    searchresult += "</tbody></table>";

                    document.getElementById("joke").innerHTML = searchresult;


                    //console.log("data", data);

                });
    }
};
