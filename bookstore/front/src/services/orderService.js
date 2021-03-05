export const addOrder = (userId, bookList) => {
    const url = `http://localhost:8080/addOrder`;
    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('bookList', bookList);

    let opts = {
        method: "POST",
        body: formData,
        credentials: "include"
    };
    fetch(url, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
        })
        .catch((error) => {
            console.log("no such user");
            //console.log(error);
        });

};