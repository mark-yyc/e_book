import React from 'react';
import '../App.css'
import '../css/home.css'
import {Layout} from 'antd';
import SideBar from "../component/SideBar";
import HeaderInfo from "../component/Header";
import FilterableTable from "../component/FilterableTable";

const {Footer} = Layout;

class HomeView extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <Layout>
                <SideBar/>
                <Layout className="site-layout" style={{marginLeft: 200}}>
                    <HeaderInfo/>
                    {/*<SearchBar/>*/}
                    {/*<FilterableTable products={PRODUCTS}/>*/}
                    {/*<FilterableTable/>*/}
                    <div className="bookList">
                        <FilterableTable/>
                        {/*<BookList/>*/}
                    </div>

                    <Footer style={{textAlign: 'center'}}>Copyright ©2018 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default HomeView;