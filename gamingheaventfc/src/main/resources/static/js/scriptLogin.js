const form = document.getElementById("loginForm");
const inEmailLogin = document.getElementById("username");
const inPasswordLogin = document.getElementById("password");

form.addEventListener("submit", tryToSubmit);

function tryToSubmit() {
    event.preventDefault();
    let allValidFields = true;

    if(inEmailLogin.nextSibling != null)
        inEmailLogin.nextSibling.remove();

    if(!isValidEmail(inEmailLogin.value)) {
        allValidFields = false;
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The email must be in the usual email format and have a maximum length of 320 characters.";
        span.appendChild(p);
        inEmailLogin.insertAdjacentElement("afterend", span);
    }

    if(inPasswordLogin.nextSibling != null)
        inPasswordLogin.nextSibling.remove();

    if(!isValidPassword(inPasswordLogin.value)) {
        allValidFields = false;        
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The password must contain a capital letter, a number and be between 8 and 12 characters long.";
        span.appendChild(p);
        inPasswordLogin.insertAdjacentElement("afterend", span);
    }

    if(allValidFields)
        form.submit();
}

function isValidEmail(value) {
    let validEmail = true;
    let pattern = /^[a-zA-Z0-9.!¡#$%&'*+\/=¿?^_`{|}~-]{1,64}@([a-zA-Z0-9-]{1,63}\.){1,}[a-zA-Z]{2,63}$/;

    if(!pattern.test(value))
        validEmail = false;

    return validEmail;
}

function isValidPassword(value) {
    let validPassword = true;
    let pattern = /^(?=.*[A-Z])(?=.*\d)[\w@\.,\/!?#$%^&*()-+=]{8,12}$/;

    if(!pattern.test(value))
        validPassword = false;

    return validPassword;
}