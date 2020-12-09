document.getElementById("edit-user-form").addEventListener("submit", editUser)

function editUser(e){
    e.preventDefault();

    let id = document.getElementById("eid").value;
    let username = document.getElementById("eusername").value;
    let name = document.getElementById("ename").value;
    let email = document.getElementById("eemail").value;
    let password = document.getElementById("epassword").value;
    let roles = setRoles(Array.from(document.getElementById("eroles").selectedOptions)
        .map(option => option.value));

    fetch("http://localhost:8080/editUser", {
        method:"PUT",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type":"application/json"
        },
        body:JSON.stringify({
            id:id,
            firstName:username,
            lastName:name,
            email:email,
            password:password,
            roles:roles
        })
    }).finally(() => {
        $('#edit').modal("hide")
        getUsers();
    })
}