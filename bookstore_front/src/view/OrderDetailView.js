import React from 'react';
import '../App.css'
import '../css/home.css'
import {Layout} from 'antd';
import HeaderInfo from "../component/Header";
import {OrderDetail} from "../component/OrderDetail";
import {getOrderItem} from "../services/orderService";

const {Footer} = Layout;

class OrderDetailView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            bookInOrder: [],
        };
    }


    componentDidMount() {
        const callback = (data) => {
            this.setState({bookInOrder: data});
        };
        const query1 = this.props.location.search;
        const arr = query1.split('&');
        const orderId = arr[0].substr(4);
        getOrderItem(orderId, callback);
    }

    render() {
        const query2 = this.props.location.search;
        const arr = query2.split('&');
        const orderDate = arr[1].substr(5);
        // console.log(orderDate);
        // console.log(this.state.bookInOrder);
        return (
            <Layout>
                <Layout className="site-layout">
                    <HeaderInfo/>
                    <div className="bookList">
                        <p>下单时间：{orderDate}</p>
                        <OrderDetail info={this.state.bookInOrder}/>
                        {/*<Link to="/orders">*/}
                        {/*    <Button id="submit_order" size={"large"}>*/}
                        {/*        返回*/}
                        {/*    </Button>*/}
                        {/*</Link>*/}
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default OrderDetailView;