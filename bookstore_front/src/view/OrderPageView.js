import React from 'react';
import '../App.css'
import '../css/home.css'
import {Button, Layout, message} from 'antd';
import HeaderInfo from "../component/Header";
import {OrderInfo} from "../component/OrderInfo";
import {addOrder} from "../services/orderService";
import {Link,Redirect} from "react-router-dom";


const {Footer} = Layout;

class OrderPageView extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            submit:false
        }
    }

    toggleOrder() {
        const userId = localStorage.getItem("userId");
        console.log("userId: " + userId.toString());
        addOrder(userId);
        message.success("order success");
        this.setState({bookInCart: [], submit: true});
    }

    render() {
        if(this.state.submit){
            return <Redirect to={{pathname: "/home"}}/>
        }
        return (
            // <h>OrderPage</h>
            <Layout>
                {/*<SideBar/>*/}
                <Layout className="site-layout">
                    <HeaderInfo/>
                    <div className="bookList">
                        <OrderInfo/>
                        <Button id="submit_order" size={"large"} onClick={() => this.toggleOrder()}>
                            下订单
                        </Button>
                        <Link to="/cart">
                            <Button id="submit_order" size={"large"}>
                                返回
                            </Button>
                        </Link>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default OrderPageView;