import React from 'react';
import {LoginForm} from "../component/LoginForm";
import background from "../resources/typewriter-801921_1920.jpg"
import "../css/Login.css"
import {PageHeader} from 'antd';

let sectionStyle = {
    backgroundImage: `url(${background})`
};

class LoginView extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div id="container" style={sectionStyle}>
                <div id="login_form">
                    <PageHeader
                        className="site-page-header"
                        title="Login"
                        subTitle="welcome!"
                    />,
                    <LoginForm/>
                </div>

            </div>

        )
    }
}

export default LoginView;