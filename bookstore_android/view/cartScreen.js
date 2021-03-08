import {View} from 'react-native';
import React from 'react';
import {ShoppingCart} from "../component/shoppingCart";

export class CartScreen extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <View>
                <ShoppingCart navigation={this.props.navigation}/>
            </View>
        )
    }
}