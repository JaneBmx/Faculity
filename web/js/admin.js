/*MAIN TABS */
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;

    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

/*1.FACULTY TAB*/
function openCity1(evt, cityName) {
    var i, tabcontent1, tablinks1;

    tabcontent1 = document.getElementsByClassName("tabcontent1");
    for (i = 0; i < tabcontent1.length; i++) {
        tabcontent1[i].style.display = "none";
    }

    tablinks1 = document.getElementsByClassName("tablinks1");
    for (i = 0; i < tablinks1.length; i++) {
        tablinks1[i].className = tablinks1[i].className.replace(" active", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

/*2. USERS TAB*/
function openCity2(evt, cityName) {
    var i, tabcontent2, tablinks2;

    tabcontent2 = document.getElementsByClassName("tabcontent2");
    for (i = 0; i < tabcontent2.length; i++) {
        tabcontent2[i].style.display = "none";
    }

    tablinks2 = document.getElementsByClassName("tablinks2");
    for (i = 0; i < tablinks2.length; i++) {
        tablinks2[i].className = tablinks2[i].className.replace(" active", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

/*GRADEREPORT TABS*/
function openCity3(evt, cityName) {
    var i, tabcontent3, tablinks3;

    tabcontent3 = document.getElementsByClassName("tabcontent3");
    for (i = 0; i < tabcontent3.length; i++) {
        tabcontent3[i].style.display = "none";
    }

    tablinks3 = document.getElementsByClassName("tablinks3");
    for (i = 0; i < tablinks3.length; i++) {
        tablinks3[i].className = tablinks3[i].className.replace(" active", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

window.onload = function () {
    let content = "";
    fetch('http://localhost:8081/controller?command=GET_ALL_USERS_AJAX')
        .then((response) => {
            //console.log((response.json()));
            return response.json();
        })
        .then((data) => {
            showUsers(data);
        });
}

let dataGlobal;
function showUsers(data){
    let content;
    dataGlobal=data;
    console.log(data);
    content = content + "<table>";
    for (let i = 0; i < data.length; i++) {

        content = content + "<tr>";
        content = content + "<td>";
        content = content + data[i].id;
        content = content + "</td>";
        content = content + "<td>";
        content = content + data[i].name;
        content = content + "</td>";
        content = content + "<td>";
        content = content + data[i].surname;
        content = content + "</td>";
        content = content + "<td>";
        content = content + data[i].email;
        content = content + "</td>";
        content = content + "<td>";
        content = content + data[i].login;
        content = content + "</td>";
        content = content + "<td>";
        content = content + "<a href='javascript:deleteUser(" + i + ")'>Delete</a>";
        content = content + "</td>";
        content = content + "</tr>";
    }
    content = content + "</table>";
    document.getElementById("Search_users").innerHTML = content;
}

function deleteUser(index){

    fetch('http://localhost:8081/controller?command=delete_user&user_id='+dataGlobal[index].id)
        .then((response) => {
            //console.log((response.json()));
            //return response.json();
        })
        .then((data) => {

        });
    alert("User "+dataGlobal[index].name+" "+dataGlobal[index].surname+ " has been deleted!");
    dataGlobal.splice(index,1);
    showUsers(dataGlobal);

}


