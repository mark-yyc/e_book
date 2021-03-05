import React from 'react';
import './App.css';
import BasicRoute from "./Router";
// import './css/home.css'
// import {Layout} from 'antd';
// import SideBar from "./component/SideBar";
// import HeaderInfo from "./component/Header";
// import {BookList} from "./component/BookList";
//
// const {Footer} = Layout;


const App = () => (
    <BasicRoute/>
    // <Layout>
    //     <SideBar/>
    //     <Layout className="site-layout" style={{marginLeft: 200}}>
    //         <HeaderInfo/>
    //         {/*<FilterableTable products={PRODUCTS}/>*/}
    //         {/*<FilterableTable/>*/}
    //         <BookList/>
    //         <Footer style={{textAlign: 'center'}}>Copyright ©2018 Created by Mark</Footer>
    //     </Layout>
    // </Layout>
);

export default App;
