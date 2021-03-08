import React from 'react';
import '../App.css'
import '../css/home.css'
import {Button, Layout, message} from 'antd';
import HeaderInfo from "../component/Header";
import {OrderInfo} from "../component/OrderInfo";
import {addOrder} from "../services/orderService";
import {Link} from "react-router-dom";


const {Footer} = Layout;

class OrderPageView extends React.Component {
    constructor(props) {
        super(props);

    }

    toggleOrder() {
        message.success("order success");
        const userId = localStorage.getItem("userId");
        console.log("userId: " + userId.toString());
        addOrder(userId);
        this.setState({bookInCart: [], submit: true});
    }

    render() {
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