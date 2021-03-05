import moment from 'moment';
import {apiUrl} from "../urlConfig";
import {Alert} from "react-native";

export const addCartItem = (userId, bookId) => {
    const url = apiUrl + "/addCartItem";
    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('bookId', bookId);
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
            Alert.alert('success', '购买成功');
        })
        .catch((error) => {
            console.log("error");
        });
};

export const getShoppingCart = (userId, callback) => {
    const url = apiUrl + "/getShoppingCart";
    let formData = new FormData();
    formData.append('userId', userId);

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
            callback(data);
        })
        .catch((error) => {
            // console.log("no such user");
        });
};

export const addOrder = (userId, callback) => {
    const url = apiUrl + "/addOrder";
    let formData = new FormData();
    formData.append('userId', userId);
    let day = moment().format('YYYY-MM-DD');
    console.log(day);
    formData.append('orderDate', day);
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
            Alert.alert("sucess", "下单成功");
            callback(data);
        })
        .catch((error) => {
            //console.log("no such user");
            //console.log(error);
        });
};

export const getUserOrder = (userId, callback) => {
    const url = apiUrl + "/getUserOrder";
    let formData = new FormData();
    formData.append('userId', userId);

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
            callback(data);
        })
        .catch((error) => {
            //console.log("no such user");
            //console.log(error);
        });
};
export const getOrderItem = (orderId, callback) => {
    const url = apiUrl + "/getOrderItem";
    let formData = new FormData();
    formData.append('orderId', orderId);

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
            callback(data);
        })
        .catch((error) => {
            //console.log("no such user");
            //console.log(error);
        });
};
