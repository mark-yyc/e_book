import React from 'react';
import '../App.css'
import '../css/home.css'
import {Button, Layout} from 'antd';
import {Link} from 'react-router-dom';
import HeaderInfo from "../component/Header";

import FilterableTable from "../component/FilterableTable";
import SideBarAdmin from "../component/SideBarAdmin";

const {Footer} = Layout;

class HomeViewAdmin extends React.Component {
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
                        <Link to={{
                            pathname: '/addBookPage'
                        }}>
                            <Button>
                                添加新书
                            </Button>
                        </Link>
                        <FilterableTable Link='/bookDetailAdmin'/>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default HomeViewAdmin;