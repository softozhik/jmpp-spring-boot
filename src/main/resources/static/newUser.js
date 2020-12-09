document.getElementById("newUser").addEventListener("submit", newUser);

function newUser(e){
    e.preventDefault();

    let username = document.getElementById("nusername").value;
    let name = document.getElementById("nname").value;
    let email = document.getElementById("nemail").value;
    let password = document.getElementById("passwordNew").value;
    let roles = setRoles(Array.from(document.getElementById("nroles").selectedOptions)
        .map(option => option.value));

    fetch("http://localhost:8080/api", {
        method: "POST",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            username: username,
            name: name,
            email: email,
            password: password,
            roles: roles
        })
    })
        .finally(() => {
            document.getElementById("listUsers").click();
            getUsers();
            document.getElementById("newUser").reset();
        })
}

function setRoles(someRoles) {
    let roles = [];
    if (someRoles.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({"id": 1, "name": "ROLE_ADMIN"});
    }
    if (someRoles.indexOf("ROLE_USER") >= 0) {
        roles.push({"id": 2, "name": "ROLE_USER"});
    }
    if (someRoles.indexOf("ROLE_OVER") >= 0) {
        roles.push({"id": 3, "name": "ROLE_OVER"});
    }
    return roles;
}