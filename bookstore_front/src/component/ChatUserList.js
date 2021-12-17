import React from 'react';
import {List} from "antd";

export class ChatUserList extends React.Component{
    render(){
        // console.log("userList:"+this.props.userList)
        return (
            <div>
                <p>在线用户列表</p>
                <List
                    itemLayout={"horizontal"}
                    dataSource={this.props.userList}
                    renderItem={item=>(
                        <List.Item style={{margin:5}}>
                            <p>{item}</p>
                        </List.Item>
                    )}/>
            </div>

        )
    }
}