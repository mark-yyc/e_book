import React from 'react';
import '../App.css'
import '../css/home.css'
import {Layout} from 'antd';
import HeaderInfo from "../component/Header";
import SideBarAdmin from "../component/SideBarAdmin";

import {UserList} from "../component/UserList";

const {Footer} = Layout;

class UsersView extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <Layout>
                <SideBarAdmin/>
                <Layout className="site-layout" style={{marginLeft: 200}}>
                    <HeaderInfo/>
                    <div className="bookList">
                        <UserList/>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default UsersView;