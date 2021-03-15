import React from 'react';
import '../App.css'
import '../css/home.css'
import {Layout,Input,Button} from 'antd';
import {ChatList} from "../component/ChatList"
import {ChatUserList} from "../component/ChatUserList";
import SideBar from "../component/SideBar";
import HeaderInfo from "../component/Header";

const { TextArea } = Input;
const {Footer} = Layout;

class ChatRoomView extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            websocket:null,
            chatList:[],
            userList:[],
            inputValue:"",
            username:null
        };

        // this.handleReply=this.handleReply.bind(this)
    }

    onMessage(evt){
        let msg=JSON.parse(evt.data);
        console.log(msg)
        let tmp=this.state.chatList;
        switch (msg.type){
            case "chat":
                tmp.push(msg);
                this.setState({
                chatList:tmp
                });
                break;
            case "user":
                this.setState({
                    userList:msg.userList
                })
                break;
            case "join":
                tmp.push(msg);
                this.setState({
                    chatList:tmp
                });
                break;
        }
    }

    onOpen(){
        // console.log("connection open")
        let json={
            type:'join',
            username:this.state.username
        }
        this.state.websocket.send(JSON.stringify(json));
    }

    componentDidMount() {
        const query = this.props.location.search;
        // console.log(query);
        const arr = query.split('=');
        this.setState({
            username:arr[1]
        })
        let wsocket=new WebSocket("ws://localhost:8083/chatRoom");
        wsocket.onopen=()=>this.onOpen();
        wsocket.onmessage=(evt)=>this.onMessage(evt);
        // wsocket.onclose=()=>{console.log("connection closed")}
        this.setState({websocket:wsocket});
    }
    //
    // componentWillUnmount() {
    //     this.state.websocket.close();
    // }

    sendJson(){
        // console.log(this.state.websocket);
        let target=null;
        let secret="public";
        // let message=this.state.inputValue;
        if(this.state.inputValue==null||this.state.inputValue===""){
            return;
        }
        let str=this.state.inputValue.split(" ");
        if(str[0].startsWith("@")){
            target=str[0].split("@")[1];
        }
        if(str[0].startsWith("私")){
            secret="private";
            target=str[0].split("@")[1];
        }
        let json={
            type:'chat',
            secret:secret,
            username:this.state.username,
            target:target,
            message:this.state.inputValue
        }
        console.log(json)
        this.state.websocket.send(JSON.stringify(json));
        this.setState({
            inputValue:null
        })
    }

    handleInputChange(e){
        this.setState({inputValue:e.target.value});
    }

    handleReply(username){
        this.setState({
            inputValue:"@"+username+" "
        })
    }

    handleSecretReply(username){
        this.setState({
            inputValue:"私@"+username+" "
        })
    }

    render(){
        return (
            <Layout>
                <SideBar/>
                <layout className="site-layout" style={{marginLeft: 200}}>
                    {/*<HeaderInfo/>*/}
                    <div style={{margin:20,width:800,float:"left"}}>
                        <ChatList chatList={this.state.chatList}
                                  handleReply={(username)=>this.handleReply(username)}
                                  handleSecretReply={(username)=>this.handleSecretReply(username)}
                        />
                        <TextArea value={this.state.inputValue} rows={4} onChange={(e)=>this.handleInputChange(e)}/>
                        <Button style={{marginTop:10}} type="primary" onClick={()=>this.sendJson()}>发送</Button>
                    </div>
                    <div style={{margin:20,float:"left"}}>
                        <ChatUserList userList={this.state.userList}/>
                    </div>
                </layout>
            </Layout>
        )
    }
}

export default ChatRoomView;