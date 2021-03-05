import React from 'react';
import {Button, Form, Input, message} from 'antd';
import {Redirect} from 'react-router-dom';
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
            user: null
        };
    }

    onFinish = values => {
        console.log('Success:', values);
        checkUser(values, (data) => {
            this.setState({user: data});
        });

    };

    onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };

    render() {
        console.log('Success:', this.state.user);
        if (this.state.user == null) {
            message.error("no such user")
        } else {
            localStorage.clear();
            console.log(this.state.user.userId);
            localStorage.setItem("userId", this.state.user.userId);
            return <Redirect to={{pathname: "/home"}}/>;
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
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}


