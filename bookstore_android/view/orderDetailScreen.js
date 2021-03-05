import {FlatList, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import {getOrderItem} from "../service/orderService";
import {CartItem} from "../component/cartItem";

export class OrderDetailScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userId: null,
            orderId: null,
            orderDate: null,
            orderItem: []
        }
    }

    componentDidMount() {
        let detail = this.props.route.params.detail;
        let userId = detail.userId;
        let orderId = detail.orderId;
        let orderDate = detail.orderDate;
        const callback = (data) => {
            this.setState({userId: userId, orderId: orderId, orderDate: orderDate, orderItem: data});
        };
        getOrderItem(orderId, callback);
    }

    render() {
        return (
            <View>
                <Text>订单号：{this.state.orderId}</Text>
                <Text>用户号：{this.state.userId}</Text>
                <Text>时间：{this.state.orderDate}</Text>
                <FlatList
                    data={this.state.orderItem}
                    renderItem={
                        ({item}) => <CartItem info={item}/>
                    }
                    style={styles.list}
                />
            </View>
        )

    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#F5FCFF"
    },
    name: {
        fontSize: 18,
        marginBottom: 8,
        textAlign: 'center',
    },
    author: {
        fontSize: 10,
        textAlign: 'center',
    },
    rightContainer: {
        flex: 1,
        paddingRight: 10,
    },
    image: {
        width: 53,
        height: 81
    },
    list: {
        paddingLeft: 10,
        paddingRight: 5,
        backgroundColor: '#F5FCFF',
    },
});
