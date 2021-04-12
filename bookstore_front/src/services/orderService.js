import moment from 'moment';

export const addOrder = (userId) => {
    const url = `http://localhost:8080/addOrder`;
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
        })
        .catch((error) => {
            //console.log("no such user");
            //console.log(error);
        });
};

export const addCartItem = (userId, bookId) => {
    const url = `http://localhost:8080/addCartItem`;
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
        })
        .catch((error) => {
            //console.log("no such user");
            //console.log(error);
        });
};

export const getShoppingCart = (userId, callback) => {
    const url = `http://localhost:8080/getShoppingCart`;
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
            console.log(data)
            callback(data);
        })
        .catch((error) => {
            // console.log("no such user");
        });
};

export const getUserOrder = (userId, callback) => {
    const url = `http://localhost:8080/getUserOrder`;
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
    const url = `http://localhost:8080/getOrderItem`;
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

export const getOrders = (callback) => {
    const url = `http://localhost:8080/getOrders`;
    let formData = new FormData();
    formData.append('search', null);
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

