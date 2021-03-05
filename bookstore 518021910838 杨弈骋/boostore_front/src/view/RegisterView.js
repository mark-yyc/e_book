import React from 'react';
import '../App.css'
import '../css/home.css'
import {Alert, Button, Input, Result} from 'antd';
import {UserOutlined} from '@ant-design/icons';
import {addUser, findUser} from "../services/userService";
import {Link} from 'react-router-dom';


export class RegisterView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            address: null,
            usernameErrorVisible: false,
            passwordErrorVisible: false,
            addressErrorVisible: false,
            registered: false,
        };
        this.handleUsername = this.handleUsername.bind(this);
        this.handlePassword = this.handlePassword.bind(this);
        this.checkPassword = this.checkPassword.bind(this);
        this.handleAddress = this.handleAddress.bind(this);
        // this.handleSubmit=this.handleSubmit.bind(this);
    }

    handleUsername(e) {
        const value = e.target.value;
        const callback1 = () => {
            this.setState({usernameErrorVisible: true});
        };
        const callback2 = () => {
            this.setState({username: value, usernameErrorVisible: false})
        };
        findUser(value, callback1, callback2);
    }

    handlePassword(e) {
        this.setState({password: e.target.value})
    }

    checkPassword(e) {
        const tmp = e.target.value;
        if (tmp != this.state.password) {
            this.setState({passwordErrorVisible: true})
        } else {
            this.setState({passwordErrorVisible: false})
        }
    }

    handleAddress(e) {
        // if(e.target.value===null){
        //     this.setState({addressErrorVisible: false});
        // }
        // else {
        let value = e.target.value;
        if (!(/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test(value))) {
            console.log(value);
            this.setState({addressErrorVisible: true});
        } else {
            this.setState({address: value, addressErrorVisible: false});
        }
        // }
    }

    handleSubmit() {
        const callback = () => {
            // console.log("trigger callback");
            this.setState({registered: true});
        };
        if (this.state.username != null && this.state.password != null && this.state.address != null) {
            if (!this.state.usernameErrorVisible && !this.state.passwordErrorVisible && !this.state.addressErrorVisible) {
                addUser(this.state.username, this.state.password, this.state.address, callback);
            }
        }
    }

    render() {
        console.log(this.state.registered);
        if (this.state.registered) {
            return (
                <Result
                    status="success"
                    title="注册成功"
                    subTitle="欢迎来到电子书店"
                    extra={[
                        <Link to={{
                            pathname: '/',
                        }}
                        >
                            <Button type="primary" key="console">
                                去登陆
                            </Button>
                        </Link>
                    ]}
                />
            )
        }
        return (
            <div>
                <p>请输入用户名:</p>
                <Input
                    placeholder="Enter your username"
                    prefix={<UserOutlined className="site-form-item-icon"/>}
                    onBlur={this.handleUsername}
                >
                </Input>
                <div>
                    {this.state.usernameErrorVisible ? (
                        <Alert message="该用户名已被注册过" type="error"/>
                    ) : null}
                </div>
                <p>请输入密码:</p>
                <Input.Password
                    placeholder="Enter your password"
                    prefix={<UserOutlined className="site-form-item-icon"/>}
                    onBlur={this.handlePassword}
                >
                </Input.Password>
                <p>请再次输入密码:</p>
                <Input.Password
                    placeholder="Enter your password again"
                    prefix={<UserOutlined className="site-form-item-icon"/>}
                    onBlur={this.checkPassword}
                >
                </Input.Password>
                <div>
                    {this.state.passwordErrorVisible ? (
                        <Alert message="请输入相同的密码" type="error"/>
                    ) : null}
                </div>
                <p>请输入邮箱:</p>
                <Input
                    placeholder="Enter your address"
                    prefix={<UserOutlined className="site-form-item-icon"/>}
                    onBlur={this.handleAddress}
                >
                </Input>
                {this.state.addressErrorVisible ? (
                    <Alert message="请输入正确的Email" type="error"/>
                ) : null}
                <Button onClick={this.handleSubmit.bind(this)}>
                    提交
                </Button>
            </div>

        )
    }
}