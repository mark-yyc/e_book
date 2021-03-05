import {postRequest, postRequest_v2} from "../utils/ajax";
import {message} from "antd";

export const getBooks = (data, callback) => {
    const url = `http://localhost:8080/getBooks`;
    postRequest(url, data, callback);
};

export const getBook = (id, callback) => {
    const data = {id: id};
    const url = `http://localhost:8080/getBook`;
    postRequest_v2(url, data, callback);

};

export const modifyBookstate = (id, state) => {
    const url = `http://localhost:8080/modifyBookState`;
    let formData = new FormData();
    formData.append('id', id);
    formData.append('state', state);
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
        });
};

export const modifyBook = (id, name, isbn, type, author, price, description, inventory, image, callback) => {
    const url = `http://localhost:8080/modifyBook`;
    let formData = new FormData();
    formData.append('id', id);
    formData.append('name', name);
    formData.append('isbn', isbn);
    formData.append('type', type);
    formData.append('author', author);
    formData.append('price', price);
    formData.append('description', description);
    formData.append('inventory', inventory);
    formData.append('image', image);

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
            message.success("修改成功");
            callback(data);
        })
        .catch((error) => {
        });
};


export const addBook = (name, isbn, type, author, price, description, inventory, image, callback) => {
    const url = `http://localhost:8080/addBook`;
    let formData = new FormData();
    formData.append('name', name);
    formData.append('isbn', isbn);
    formData.append('type', type);
    formData.append('author', author);
    formData.append('price', price);
    formData.append('description', description);
    formData.append('inventory', inventory);
    formData.append('image', image);

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
            console.log("added");
            callback(data);
        })
        .catch((error) => {
        });
};

