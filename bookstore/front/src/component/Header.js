import React from 'react';
import {Layout} from 'antd';
import Ebooks from '../resources/E-books.png'

const {Header} = Layout;

class HeaderInfo extends React.Component {
    render() {
        return (
            <Header className="site-layout-background" style={{padding: 0}}>
                <div id="header-content">
                    <img alt="E-books" className="logo" src={Ebooks} style={{height: 100, margin: '-20px 20px 0'}}/>
                    {/*<Avatar icon={<UserOutlined />} style={{margin: '-20px 300px 0'}}/>*/}
                </div>
            </Header>
        )
    }
}

export default HeaderInfo;