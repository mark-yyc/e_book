import {View} from 'react-native';
import React from 'react';
import {OrderList} from "../component/orderList";

export class OrderScreen extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <View>
                <OrderList navigation={this.props.navigation}/>
            </View>
        )
    }
}