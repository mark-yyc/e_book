import React from 'react';
//import { Router, Route, Switch, Redirect} from 'react-router-dom';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import HomeView from "./view/HomeView"
import BookDetailView from "./view/BookDetailView";
import LoginView from "./view/LoginView"
import CartView from "./view/CartView";
import OrderPageView from "./view/OrderPageView";
import OrdersView from "./view/OrdersView";
import OrderDetailView from "./view/OrderDetailView";
import {RegisterView} from "./view/RegisterView";
import HomeViewAdmin from "./view/HomeViewAdmin";
import UsersView from "./view/UsersView";
import BookDetailAdminView from "./view/BookDetailAdminView";
import {AddBookView} from "./view/AddBookView";
import OrdersAdminView from "./view/OrdersAdminView";
import ChatRoomView from "./view/ChatRoomView";


class BasicRoute extends React.Component {


    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/homeAdmin" component={HomeViewAdmin}/>
                    <Route path="/users" component={UsersView}/>
                    <Route path="/bookDetailAdmin" component={BookDetailAdminView}/>
                    <Route path="/addBookPage" component={AddBookView}/>
                    <Route path="/ordersAdmin" component={OrdersAdminView}/>
                    <Route path="/orderPage" component={OrderPageView}/>
                    <Route path="/orders" component={OrdersView}/>
                    <Route path="/register" component={RegisterView}/>
                    <Route path="/home" component={HomeView}/>
                    <Route path="/bookDetails" component={BookDetailView}/>
                    <Route path="/cart" component={CartView}/>
                    <Route path="/orderDetail" component={OrderDetailView}/>
                    <Route path="/chatRoom" component={ChatRoomView}/>
                    <Route path="/" component={LoginView}/>

                </Switch>

            </Router>
        )
    }


}

export default BasicRoute;