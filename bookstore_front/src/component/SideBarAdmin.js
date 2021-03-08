import React from 'react';
import {Layout, Menu} from 'antd';
import {CopyOutlined, ReadOutlined, UserOutlined,} from '@ant-design/icons';
import {Link} from 'react-router-dom'

const {Sider} = Layout;

class SideBarAdmin extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Sider className='sider'
                   style={{
                       overflow: 'auto',
                       height: '100vh',
                       position: 'fixed',
                       left: 0,
                   }}
            >
                <Menu class='home-menu' theme='light' mode="inline" defaultSelectedKeys={['4']}>
                    <Menu.Item key="1">
                        <ReadOutlined/>
                        <Link to={{pathname: "/homeAdmin"}}>
                            <span className="nav-text">Books</span>
                        </Link>
                    </Menu.Item>
                    {/*<Menu.Item key="2">*/}
                    {/*    <ShoppingCartOutlined/>*/}
                    {/*    <Link to={{pathname:"/cart"}} >*/}
                    {/*        <span className="nav-text">My Cart</span>*/}
                    {/*    </Link>*/}
                    {/*</Menu.Item>*/}
                    <Menu.Item key="2">
                        <UserOutlined/>
                        <Link to={{pathname: "/users"}}>
                            <span className="nav-text">Users</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="3">
                        <CopyOutlined/>
                        <Link to={{pathname: "/ordersAdmin"}}>
                            <span className="nav-text">Orders</span>
                        </Link>
                    </Menu.Item>
                </Menu>
            </Sider>
        )
    }

}

export default SideBarAdmin;