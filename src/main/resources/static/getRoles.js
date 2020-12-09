function getRoles() {
    fetch("http://localhost:8080/api/allRoles")
        .then((res) => res.json())
        .then((data) => {
            let roles = "";
            data.forEach(function (role) {
                roles += `<option>${role.role}</option>`;
            });
            document.getElementById("nroles").innerHTML = roles;
            document.getElementById("eroles").innerHTML = roles;
            document.getElementById("droles").innerHTML = roles;
        })
}
getRoles()