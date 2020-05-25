function openTab(evt, tabName) {
    var i, tabcontent, tablinks;

    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

window.onload = function () {
    document.getElementById("facultySelector").onchange = (e) => {
        const mark1 = document.getElementById("mark1");
        const mark2 = document.getElementById("mark2");
        const mark3 = document.getElementById("mark3");

        const mark1_subId = document.getElementById("mark_1_subId");
        const mark2_subId = document.getElementById("mark_2_subId");
        const mark3_subId = document.getElementById("mark_3_subId");

        const facultyId = e.target.value;
        mark1.innerHTML = fac[facultyId].subjects[0].name;
        mark1_subId.value = fac[facultyId].subjects[0].id;

        mark2.innerHTML = fac[facultyId].subjects[1].name;
        mark2_subId.value = fac[facultyId].subjects[1].id;

        mark3.innerHTML = fac[facultyId].subjects[2].name;
        mark3_subId.value = fac[facultyId].subjects[2].id;
    }
};
