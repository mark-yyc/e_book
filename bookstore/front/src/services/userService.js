import {postRequest_v2} from "../utils/ajax";


export const checkUser = (data, callback) => {
    const url = `http://localhost:8080/checkUser`;
    //const data={username:values.username,password:values.password}
    // const callback = (data) => {
    //     if(data.status >= 0) {
    //         localStorage.setItem('user', JSON.stringify(data.data));
    //         message.success(data.msg);
    //     }
    //     else{
    //         message.error(data.msg);
    //     }
    // };


    postRequest_v2(url, data, callback);
};