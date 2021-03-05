import React from 'react';
import {Button, Layout, message, Table} from 'antd'
import '../App.css'
import '../css/home.css'
import {addOrder} from "../services/orderService";


const {Footer} = Layout;

const columns = [
    {
        title: '书名',
        dataIndex: 'bookname',
        key: 'bookname',
    },
    {
        title: '价格',
        dataIndex: 'price',
        key: 'price',
    },
    {
        title: '购买数量',
        dataIndex: 'number',
        key: 'number',
    },
];

export class ShoppingCart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            bookList: "",
            bookInCart: []
        };
    }

    componentDidMount() {
        let information = null;
        let tmp = [], tmp_list = "";
        for (let i = 0; i < localStorage.length; i++) {
            let bookId = localStorage.key(i);
            if (!isNaN(parseInt(bookId))) {
                information = localStorage.getItem(bookId);
                const arr = information.split("&");
                let bookInfo = {
                    bookname: arr[0],
                    price: '¥' + arr[1],
                    number: arr[2]
                };
                tmp_list = tmp_list + bookId + "&" + arr[2] + " ";
                tmp = tmp.concat(bookInfo);
            }
        }
        this.setState({bookInCart: tmp, bookList: tmp_list});

    }

    toggleOrder() {
        let id = localStorage.getItem("userId").toString();
        message.success("order success");
        addOrder(id, this.state.bookList);
    }

    render() {
        //console.log(this.state.bookInCart);
        console.log(this.state.bookList);
        return (
            <div>
                <Table dataSource={this.state.bookInCart} columns={columns}/>
                <Button id="submit_order" size={"large"} onClick={() => this.toggleOrder()}>
                    下订单
                </Button>
            </div>


        );
    }

}