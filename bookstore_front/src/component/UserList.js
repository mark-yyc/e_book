import React from 'react';
import {Button, Layout, List} from 'antd'
import '../App.css'
import '../css/home.css'
import "../css/Cart.css";
import {getUsers, modifyState} from "../services/userService";


const {Footer} = Layout;

export class UserList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
        };
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({users: data});
        };
        getUsers({"search": null}, callback);
    }

    handleLock(userId) {
        // console.log("lock");
        const callback = (data) => {
            this.setState({users: data});
        };
        modifyState(userId, 0, callback);
    }

    handleUnlock(userId) {
        // console.log("unlock");
        const callback = (data) => {
            this.setState({users: data});
        };
        modifyState(userId, 1, callback);
    }

    render() {
        // console.log(this.state.users);
        return (
            <div>
                <List
                    itemLayout="horizontal"
                    bordered
                    dataSource={this.state.users}
                    renderItem={item => (
                        <List.Item>
                            <div className={"itemInfo"}>
                                <p>{item.userId}</p>
                            </div>
                            <div className={"itemInfo"}>
                                <p>{item.username}</p>
                            </div>
                            <div className={"itemInfo"}>
                                <p>{item.userType ? "管理员" : "用户"}</p>
                            </div>
                            <div>
                                {item.userType ? null : (
                                    <div>
                                        <div className={"itemInfo"}>
                                            <p>{item.state ? "可用" : "禁用"}</p>
                                        </div>
                                        <div className={"itemInfo"}>
                                            <Button onClick={this.handleLock.bind(this, item.userId)}>
                                                禁用
                                            </Button>
                                        </div>
                                        <div className={"itemInfo"}>
                                            <Button onClick={this.handleUnlock.bind(this, item.userId)}>
                                                解禁
                                            </Button>
                                        </div>
                                    </div>
                                )}
                            </div>

                        </List.Item>
                    )}
                />
            </div>
        );
    }

}