import React from 'react';
import {Button, Form, Input, message} from 'antd';
import {Link, Redirect} from 'react-router-dom';
import {checkUser} from "../services/userService"

const layout = {
    labelCol: {span: 8},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};


export class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: null,
            firstLogin: true,
        };
    }

    onFinish = values => {
        const callback = (data) => {
            this.setState({user: data, firstLogin: false});
        };
        // console.log('Success:', values);
        //   checkUser(values,(data) => {
        //      this.setState({user:data});
        //  });
        checkUser(values, callback);
    };

    onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };

    render() {
        console.log('Success:', this.state.user);
        if (this.state.user == null && this.state.firstLogin == false) {
            message.error("请输入正确的密码")
        }
        if (this.state.user != null) {
            if (this.state.user.state == 0) {
                message.error("该用户已被禁用")
            } else {
                localStorage.clear();
                console.log(this.state.user.userId);
                localStorage.setItem("userId", this.state.user.userId);
                localStorage.setItem("username",this.state.user.username);
                if (this.state.user.userType == 0) {
                    return <Redirect to={{pathname: "/home"}}/>;
                } else {
                    return <Redirect to={{pathname: "/homeAdmin"}}/>;
                }
            }
        }
        return (
            <Form
                {...layout}
                name="basic"
                initialValues={{remember: true}}
                onFinish={this.onFinish}
                onFinishFailed={this.onFinishFailed}
            >
                <Form.Item
                    label="Username"
                    name="username"
                    rules={[{required: true, message: 'Please input your username!'}]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    label="Password"
                    name="password"
                    rules={[{required: true, message: 'Please input your password!'}]}
                >
                    <Input.Password/>
                </Form.Item>

                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">
                        登录
                    </Button>
                    <Link to={{pathname: "/register"}}>
                        <Button>
                            注册
                        </Button>
                    </Link>
                </Form.Item>
            </Form>
        );
    }
}


