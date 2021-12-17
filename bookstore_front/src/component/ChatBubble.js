import React from 'react';
import {Comment,Avatar,Layout} from 'antd';


class ChatBubble extends React.Component{
    constructor(props) {
        super(props);
        // this.handleClick=this.handleClick.bind(this);
    }
    handleReply(){
        this.props.onReply(this.props.info.username)
    }
    handleSecretReply(){
        this.props.onSecretReply(this.props.info.username)
    }
    render(){
        return(
                this.props.info.type==="chat"?(
                    <Comment
                        style={{backgroundColor:"white"}}
                        actions={[
                            <span key="comment-basic-reply-to" onClick={()=>this.handleReply()}>回答</span>,
                            <span key="comment-basic-secret-reply-to" onClick={()=>this.handleSecretReply()}>私聊</span>
                        ]}
                        author={<a>{this.props.info.username}</a>}
                        avatar={
                            <Avatar
                                src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                            />
                        }
                        content={<p>{this.props.info.message}</p>}
                    />
                ):
                    (
                        <div
                            style={{backgroundColor:"white"}}
                        >
                            <p style={{verticalAlign:"middle"}} >{this.props.info.username+" 加入了聊天"}</p>
                        </div>
                    )

        )
    }
}

export default ChatBubble;