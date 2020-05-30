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
        tabcontent1[i].style.display = "none";    }

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

//json load

window.onload = function () {
    let content = "";
    fetch('http://localhost:8081/controller?command=GET_ALL_USERS_AJAX&type=users')
        .then((response) => {
            //console.log((response.json()));
            return response.json();
        })
        .then((data) => {
            showUsers(data);
        });

    let contenf = "";
    fetch('http://localhost:8081/controller?command=GET_ALL_FACULTIES_AJAX&type=faculty')
        .then((response) => {
            //console.log((response.json()));
            return response.json();
        })
        .then((data) => {
            showFaculties(data);
        });

    let conteng;
    fetch('http://localhost:8081/controller?command=GET_ALL_GRADE_REPORTS_AJAX&type=grade_report')
        .then((response) => {
            //console.log((response.json()));
            return response.json();
        })
        .then((data) => {
            showGrades(data);
        });
}

let dataGlobal;

function showUsers(data) {
    dataGlobal = data;
    let content ="<table width=\"100%\">";
    console.log(data);
    content = content + "<tr>";
    content = content + "<td>ID</td>";
    content = content + "<td>Role</td>";
    content = content + "<td>Name</td>";
    content = content + "<td>Surname</td>";
    content = content + "<td>Email</td>";
    content = content + "<td>Login</td>";
    content = content + "<td></td></tr>";
    for (let i = 0; i < data.length; i++) {
        content = content + "<tr>";
        content = content + "<td>";
        content = content + data[i].id;
        content = content + "</td>";
        content = content + "<td>";
        content = content + data[i].role;
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

function deleteUser(index) {
    fetch('http://localhost:8081/controller?command=delete_user&user_id=' + dataGlobal[index].id)
        .then((response) => {
            //console.log((response.json()));
            //return response.json();
        })
        .then((data) => {

        });
    alert("User " + dataGlobal[index].name + " " + dataGlobal[index].surname + " has been deleted!");
    dataGlobal.splice(index, 1);
    showUsers(dataGlobal);

}

let dataGlobalF;
function showFaculties(data) {
    let contentf = "<table width=\"100%\">";
    dataGlobalF = data;
    console.log(data);
    contentf = contentf + "<tr><th>ID</th>" +
        "<th>Name</th> " +
        "<th colspan=\"3\">Subjects</th>" +
        "<th>Paid accept plan</th>" +
        "<th>Free accept plan</th>" +
        "<th>Delete</th></tr>";
    for (let i = 0; i < data.length; i++) {
        contentf = contentf + "<tr>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].id;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].name;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].sub1;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].sub2;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].sub3;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].free;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + data[i].paid;
        contentf = contentf + "</td>";
        contentf = contentf + "<td>";
        contentf = contentf + "<a href='javascript:deleteFaculty(" + i + ")'>Delete</a>";
        contentf = contentf + "</td>";
        contentf = contentf + "</tr>";
    }
    contentf = contentf + "</table>";
    document.getElementById("experemental").innerHTML = contentf;
}

//grades
function showGrades(data) {
    let contentg = "<table width=\"100%\">";
    console.log(data);
    contentg = contentg + "<tr>";
    contentg = contentg + "<td>User</td>";
    contentg = contentg + "<td>Faculty</td>";
    contentg = contentg + "<td>Is accepted</td>";
    contentg = contentg + "<td>Is free paid</td>";
    contentg = contentg + "<td>Privilege</td>";
    contentg = contentg + "<td>Middle school mark</td>";
    contentg = contentg + "<td>Average mark(of certificates)</td>";
    contentg = contentg + "<td></td>";
    contentg = contentg + "</tr>";
    for (let i = 0; i < data.length; i++) {
        contentg = contentg + "<tr>";
        contentg = contentg + "<td>";
        contentg = contentg + data[i].id;
        contentg = contentg + "</td>";
        contentg = contentg + "<td>";
        contentg = contentg + data[i].faculty;
        contentg = contentg + "</td>";

        contentg = contentg + "<td>";
        contentg = contentg + data[i].isAccept;
        contentg = contentg + "</td>";

        contentg = contentg + "<td>";
        contentg = contentg + data[i].isFree;
        contentg = contentg + "</td>";

        contentg = contentg + "<td>";
        contentg = contentg + data[i].privilege;
        contentg = contentg + "</td>";

        contentg = contentg + "<td>";
        contentg = contentg + data[i].attestat;
        contentg = contentg + "</td>";

        contentg = contentg + "<td>";
        contentg = contentg + data[i].average;
        contentg = contentg + "</td>";

        contentg = contentg + "<td>";
        contentg = contentg + "<a href=''>Delete</a>";
        contentg = contentg + "</td>";

        contentg = contentg + "</tr>";
    }
    contentg = contentg + "</table>";
    document.getElementById("all_grades").innerHTML = contentg;
}


