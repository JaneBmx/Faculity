(function () {
    var body = document.body;
    var buttonToggle = document.getElementsByClassName('container')[0];
    var buttonReg = document.getElementsByClassName('button-reg')[0];

    buttonReg.addEventListener('click', function toggleClasses() {
        [body, buttonReg].forEach(function (el) {
            el.classList.toggle('button');
        });
    });
})();