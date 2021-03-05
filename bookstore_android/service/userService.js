import {apiUrl} from "../urlConfig";
import {Alert} from 'react-native';

const LOGIN_URL = apiUrl + "/checkUser";

export function checkUser(username, password, callback) {
    let formData = new FormData();

    formData.append("username", username);
    formData.append("password", password);

    let opts = {
        method: "POST",
        body: formData,
        credentials: "include"
    };

    fetch(LOGIN_URL, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            console.log(data);
            callback(data);
        })
        .catch((error) => {
            Alert.alert("用户名或秘密错误！");
            callback(null);
        });
}