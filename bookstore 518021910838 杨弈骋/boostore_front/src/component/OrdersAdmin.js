import React from 'react';
import {Button, List} from 'antd'
import {getOrders} from "../services/orderService";
import "../css/Cart.css";
import {Link} from "react-router-dom";


export class OrdersAdmin extends React.Component {

    constructor(props) {
        super(props);
        this.state = {Orders: []};
    }

    componentDidMount() {

        const callback = (data) => {
            this.setState({Orders: data});
        };
        const userId = localStorage.getItem("userId");
        getOrders(callback);
    }


    render() {
        let dataSource = [];
        console.log(this.props.beginTime);
        if (this.props.beginTime == null) {
            console.log(this.props.beginTime);
            console.log(this.props.endTime);
            dataSource = this.state.Orders;
        } else {
            for (let key in this.state.Orders) {
                let date = new Date(this.state.Orders[key].orderDate);
                // console.log(date);
                if (date >= this.props.beginTime && date <= this.props.endTime) {
                    dataSource.push(this.state.Orders[key]);
                } else {
                    continue;
                }
            }
        }
        return (
            <List
                itemLayout="horizontal"
                bordered
                dataSource={dataSource}

                renderItem={item => (
                    <List.Item>
                        <div className={"itemInfo"}>
                            <p>订单号：{item.orderId}</p>
                        </div>
                        <div className={"itemInfo"}>
                            <p>日期：{item.orderDate}</p>
                        </div>
                        <div className={"itemInfo"}>
                            <p>用户ID：{item.userId}</p>
                        </div>
                        <div className={"itemInfo"}>
                            <Link to={{
                                pathname: '/orderDetail',
                                search: '?id=' + item.orderId + '&date=' + item.orderDate
                            }}
                                  target="_blank">
                                <Button size={"large"}>
                                    了解详情
                                </Button>
                            </Link>
                        </div>
                    </List.Item>
                )}
            />
        );
    }

}