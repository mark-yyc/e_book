import React from 'react';
import {List} from 'antd';
import ChatBubble from "./ChatBubble"

export class ChatList extends React.Component{
    constructor(props) {
        super(props);
        // this.handleReply=this.handleReply.bind(this);
    }
    handleReply(username){
        this.props.handleReply(username);
    }
    handleSecretReply(username){
        this.props.handleSecretReply(username)
    }
    render(){
        return(
            <List
                style={{backgroundColor:"white"}}
                itemLayout={"horizontal"}
                dataSource={this.props.chatList}
                renderItem={item=>(
                    <List.Item>
                        <ChatBubble info={item}
                                    onReply={(username)=>this.handleReply(username)}
                                    onSecretReply={(username)=>this.handleSecretReply(username)}
                        />
                    </List.Item>
                )
                }
            />
        )
    }
}