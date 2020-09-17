/**
 * 
 * Javascript for joke2.html
 */

document.getElementById("joke").innerHTML = "Guess where the jokes go?";
document.getElementById("message").innerHTML = "&nbsp;";
let findjokeBtn = document.getElementById("findjokeBtn");
let randomjokeBtn = document.getElementById("randomjokeBtn");
let alljokesBtn = document.getElementById("alljokesBtn");
let url = "api/joke/";




// fetch joke by Id
findjokeBtn.onclick = function (e) {
    e.preventDefault();

    let findjoke = document.getElementById("findjoke").value; // get value from inputfield

    if (findjoke < 1 || findjoke > 10 || isNaN(findjoke)) { // validate inputfield so only numbers between 1 and 10 is allowed
        document.getElementById("message").innerHTML = "Has to be a number between 1 and 10";
    } else {

        let newurl = url + findjoke;

        document.getElementById("message").innerHTML = "&nbsp;"; // reset message

        fetch(newurl)
                .then(res => res.json()) //in flow1, just do it
                .then(data => {

                    document.getElementById("joke").innerHTML = "<div><img src=\"img/ChuckNorrisGif.gif\" alt=\"chuck norris gif\"> Joke by Id</div><p>" +
                            data.joke + "</p>";

                    //console.log("data", data);

                });
    }
};

// fetch a random joke
randomjokeBtn.onclick = function (e) {
    e.preventDefault();

    let newurl = url + "random";
    document.getElementById("message").innerHTML = "&nbsp;"; // reset message
    document.getElementById("findjoke").value = ""; // clear inputfield

    fetch(newurl)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {

                document.getElementById("joke").innerHTML = "<div><img src=\"img/ChuckNorrisGif.gif\" alt=\"chuck norris gif\"> Random joke</div><p>" +
                        data.joke + "</p>";

                //console.log("data", data);

            });
};

// fetch all jokes
alljokesBtn.onclick = function (e) {
    e.preventDefault();

    let newurl = url + "all";
    document.getElementById("message").innerHTML = "&nbsp;"; // reset message
    document.getElementById("findjoke").value = ""; // clear inputfield

    fetch(newurl)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {

                var allJokes = "<table class=\"table table-striped table-dark\"><thead><tr><th><div><img src=\"img/ChuckNorrisGif.gif\" alt=\"chuck norris gif\"></th><th>All jokes</th></thead><tbody>";
                for (var i = 0; i < data.length; i++) {
                    allJokes += "<tr><td>" + data[i].id + "</td><td>" + data[i].joke + "</td></tr>";
                }
                allJokes += "</tbody></table>";

                document.getElementById("joke").innerHTML = allJokes;

                //console.log("data", data);

            });
};