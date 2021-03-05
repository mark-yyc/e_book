import React from 'react';
import '../App.css'
import '../css/home.css'
import {DatePicker, Layout} from 'antd';
import HeaderInfo from "../component/Header";
import {OrdersAdmin} from "../component/OrdersAdmin";
import SideBarAdmin from "../component/SideBarAdmin";

const {RangePicker} = DatePicker;


const {Footer} = Layout;

class OrdersAdminView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            beginTime: null,
            endTime: null,
        }
    }

    handleTime(dateString) {
        if (dateString == null) {
            this.setState({beginTime: null, endTime: null});
        } else {
            this.setState({beginTime: dateString[0], endTime: dateString[1]});
        }
    }


    render() {
        return (
            <Layout>
                <SideBarAdmin/>
                <Layout className="site-layout" style={{marginLeft: 200}}>
                    <HeaderInfo/>
                    <div className="bookList">
                        <RangePicker
                            format="YYYY-MM-DD"
                            onChange={this.handleTime.bind(this)}/>
                        <OrdersAdmin beginTime={this.state.beginTime} endTime={this.state.endTime}/>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default OrdersAdminView;