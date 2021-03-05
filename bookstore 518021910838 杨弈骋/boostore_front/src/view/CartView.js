import React from 'react';
import '../App.css'
import '../css/home.css'
import {Layout} from 'antd';
import SideBar from "../component/SideBar";
import HeaderInfo from "../component/Header";
import {ShoppingCart} from "../component/ShoppingCart";


const {Footer} = Layout;

class CartView extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <Layout>
                <SideBar/>
                <Layout className="site-layout" style={{marginLeft: 200}}>
                    <HeaderInfo/>
                    <div className="bookList">
                        <ShoppingCart/>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default CartView;