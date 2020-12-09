fetch("http://localhost:8080/api").then(
    res => {
        res.json().then(
            data => {
                let user = "";
                data.forEach((u) => {

                    let rol = "";
                    u.roles.forEach((r) => {
                        rol += r.role + "  ";
                    })

                        user += `<tr>`;
                        user += `<th>` + u.id + `</th>`;
                        user += `<th>` + u.username + `</th>`;
                        user += `<th>` + u.name + `</th>`;
                        user += `<th>` + u.email + `</th>`;
                        user += `<th>` + rol + `</th>`;
                        user += `<th scope="row">
                            <button type="button" class="btn btn-info" data-toggle="modal"
                            data-target="#edit">Edit</button></th>`;
                        user += `<th scope="row">
                            <button type="button" class="btn btn-danger" data-toggle="modal"
                            ata-target="#delete">Delete</button></td></tr>`;
                    }
                )
                    document.getElementById("listUsers").innerHTML = user;
            }
        )
    }
)