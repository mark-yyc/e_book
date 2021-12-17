import {postRequest, postRequest_v2} from "../utils/ajax";

export const getUsers = (data, callback) => {
    const url = `http://localhost:8080/getUsers`;
    postRequest(url, data, callback);
}

export const checkUser = (data, callback) => {
    const url = `http://localhost:8080/checkUser`;
    postRequest_v2(url, data, callback);
};

export const findUser = (username, callback1, callback2) => {
    const url = `http://localhost:8080/findUser`;
    let formData = new FormData();
    formData.append('username', username);
    let opts = {
        method: "POST",
        body: formData,
        // credentials: "include"
    };
    fetch(url, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback1();
        })
        .catch((error) => {
            callback2();
            // console.log("no such user");
        });
};

export const addUser = (username, password, address, callback) => {
    const url = `http://localhost:8080/addUser`;
    let formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);
    formData.append('address', address);
    let opts = {
        method: "POST",
        body: formData,
        // credentials: "include"
    };
    fetch(url, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback();
        })
        .catch((error) => {
        });
};

export const modifyState = (userId, state, callback) => {
    const url = `http://localhost:8080/modifyState`;
    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('state', state);

    let opts = {
        method: "POST",
        body: formData,
        // credentials: "include"
    };

    fetch(url, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
        });
}
