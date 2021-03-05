import React from 'react';
import {Button, Layout, List} from 'antd'
import '../App.css'
import '../css/home.css'
import {getShoppingCart} from "../services/orderService";
import {Link} from 'react-router-dom';
import {CartItem} from "./CartItem";


const {Footer} = Layout;

export class ShoppingCart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            bookInCart: [],
        };
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({bookInCart: data});
        };
        const userId = localStorage.getItem("userId");
        getShoppingCart(userId, callback);

    }

    render() {

        return (
            <div>
                <List
                    itemLayout="horizontal"
                    bordered
                    dataSource={this.state.bookInCart}
                    renderItem={item => (
                        <List.Item>
                            <CartItem info={item}/>
                        </List.Item>
                    )}
                />
                <Link to="/orderPage">
                    <Button id="submit_order" size={"large"} style={{marginTop: 40}}>
                        结算
                    </Button>
                </Link>
            </div>
        );
    }

}