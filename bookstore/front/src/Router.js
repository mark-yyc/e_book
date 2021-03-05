import React from 'react';
//import { Router, Route, Switch, Redirect} from 'react-router-dom';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import HomeView from "./view/HomeView"
import BookDetailView from "./view/BookDetailView";
import LoginView from "./view/LoginView"
import CartView from "./view/CartView";


class BasicRoute extends React.Component {

    // constructor(props) {
    //     super(props);
    // }

    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/home"><HomeView/></Route>
                    <Route path="/bookDetails" component={BookDetailView}/>
                    <Route path="/cart" component={CartView}/>
                    <Route path="/" component={LoginView}/>

                </Switch>

            </Router>
        )
    }


}

export default BasicRoute;