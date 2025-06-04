const form = document.getElementById("registerForm");
const inNicknameRegister = document.getElementById("inNicknameRegister");
const inEmailRegister = document.getElementById("inEmailRegister");
const inPasswordRegister = document.getElementById("inPasswordRegister");

form.addEventListener("submit", tryToSubmit);

function tryToSubmit() {
    event.preventDefault();
    let allValidFields = true;

    if(inNicknameRegister.nextSibling != null)
        inNicknameRegister.nextSibling.remove();

    if(!isValidNickname(inNicknameRegister.value)) {
        allValidFields = false;
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The nickname must start with a capital letter, have a maximum length of 20 characters, and must not contain spaces.";
        span.appendChild(p);
        inNicknameRegister.insertAdjacentElement("afterend", span);
    }

    if(inEmailRegister.nextSibling != null)
        inEmailRegister.nextSibling.remove();

    if(!isValidEmail(inEmailRegister.value)) {
        allValidFields = false;
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The email must be in the usual email format and have a maximum length of 320 characters.";
        span.appendChild(p);
        inEmailRegister.insertAdjacentElement("afterend", span);
    }

    if(inPasswordRegister.nextSibling != null)
        inPasswordRegister.nextSibling.remove();

    if(!isValidPassword(inPasswordRegister.value)) {
        allValidFields = false;        
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The password must contain a capital letter, a number and be between 8 and 12 characters long.";
        span.appendChild(p);
        inPasswordRegister.insertAdjacentElement("afterend", span);
    }

    if(allValidFields)
        form.submit();
}

function isValidNickname(value) {
    let validNickname = true;
    let pattern = /^[A-Z][a-zA-Z0-9_]{0,19}$/;

    if(!pattern.test(value))
        validNickname = false;

    return validNickname;
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
