import React from 'react';
import {Layout, Table} from 'antd'
import '../App.css'
import '../css/home.css'
import {getShoppingCart} from "../services/orderService";
import {Redirect} from 'react-router-dom';


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

export class OrderInfo extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            // bookList:"",
            bookInCart: [],
            submit: false
        };
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({bookInCart: data});
        };
        const userId = localStorage.getItem("userId");
        getShoppingCart(userId, callback);

    }

    // toggleOrder(){
    //     message.success("order success");
    //     const userId=localStorage.getItem("userId");
    //     console.log("userId: "+userId.toString());
    //     addOrder(userId);
    //     this.setState({bookInCart:[],submit:true});
    // }

    render() {
        let order = this.state.bookInCart;
        let sum = 0;
        let tmp = 0;
        for (let i = 0; i < order.length; i++) {
            tmp = order[i].amount * order[i].price;
            sum += tmp;
        }
        sum = sum.toFixed(2);
        if (this.state.submit == true) {
            return <Redirect to={{pathname: "/home"}}/>;
        }
        return (
            <div>
                <Table dataSource={this.state.bookInCart} columns={columns}/>
                <div>
                    <p>共计： </p>
                    <p style={{color: 'red'}}>¥ {sum}</p>
                </div>
                {/*<Button id="submit_order" size={"large"} onClick={()=>this.toggleOrder() }>*/}
                {/*    下订单*/}
                {/*</Button>*/}
                {/*<Link to="/cart">*/}
                {/*    <Button id="submit_order" size={"large"}>*/}
                {/*        返回*/}
                {/*    </Button>*/}
                {/*</Link>*/}
            </div>


        );
    }

}