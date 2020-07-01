window.onload = function () {
    // let content = "";
    // fetch('http://localhost:8081/controller?command=GET_COMMON_INFO&type=common_info')
    //     .then((response) => {
    //         console.log((response.json()));
    //         // return response.json();
    //     })
    //     .then((data) => {
    //         showData(data);
    //     });

    let contenf = "";
    fetch('http://localhost:8081/controller?command=GET_COMMON_INFO&type=common_info')
        .then((response) => {
            //console.log((response.json()));
            return response.json();
        })
        .then((data) => {
            showData(data);
        });
}

function showData(data) {
    console.log(data);

    let countFaculties = "" + data[0].countFaculties;
    let countPlaces = "" + data[0].countPlaces;
    let countFree = "" + data[0].countFree;
    let countEnroles = "" + data[0].countEnroles;

    document.getElementById("countFaculties").innerHTML = countFaculties;
    document.getElementById("countPlaces").innerHTML = countPlaces;
    document.getElementById("countFree").innerHTML = countFree;
    document.getElementById("countEnroles").innerHTML = countEnroles;
}