import React from 'react';
import {Layout, List,} from 'antd'
import "../css/Cart.css";
import '../App.css'
import '../css/home.css'


const {Footer} = Layout;

const columns = [
    {
        title: '书名',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: '价格',
        dataIndex: 'price',
        key: 'price',
    },
    {
        title: '购买数量',
        dataIndex: 'amount',
        key: 'amount',
    },
];

export class OrderDetail extends React.Component {

    constructor(props) {
        super(props);
    }


    render() {
        const {info} = this.props;
        return (
            <List
                itemLayout="horizontal"
                bordered
                dataSource={info}

                renderItem={item => (
                    <List.Item>
                        <img alt="image" src={item.image} className={"itemImg"}/>
                        <div className={"itemInfo"}>
                            <p>{item.name}</p>
                        </div>
                        <div className={"itemInfo"}>
                            <p style={{color: 'red'}}>¥ {item.price}</p>
                        </div>
                        <div className={"itemInfo"}>
                            <p>数量：{item.amount}</p>
                        </div>
                    </List.Item>
                )}
            />
        );
    }

}