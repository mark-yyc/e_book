import {AsyncStorage, Button, DeviceEventEmitter, FlatList, StyleSheet, View} from 'react-native';
import React from 'react';
import {addOrder, getShoppingCart} from "../service/orderService";
import {CartItem} from "./cartItem";

export class ShoppingCart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            bookInCart: [],
            update: false
        };
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({bookInCart: data});
        };
        AsyncStorage.getItem("userId", function (error, result) {
            if (error) {
                console.log('读取失败');
            } else {
                const userId = parseInt(result);
                // console.log(userId);
                getShoppingCart(userId, callback);
            }
        });
    }

    // refreshData(){
    //     const callback =  (data) => {
    //         this.setState({bookInCart:data});
    //     };
    //     AsyncStorage.getItem("userId",function (error, result) {
    //         if (error) {
    //             console.log('读取失败');
    //         }else {
    //             const userId=parseInt(result);
    //             // console.log(userId);
    //             getShoppingCart(userId,callback);
    //         }
    //     });
    // }

    toggleOrder() {
        const callback = (data) => {
            this.setState({update: data});
        };
        AsyncStorage.getItem("userId", function (error, result) {
            if (error) {
                console.log('读取失败');
            } else {
                const userId = parseInt(result);
                addOrder(userId, callback);
                DeviceEventEmitter.emit('UPDATE_USER_DATA');
            }
        });
    }

    render() {
        return (
            <View>
                <FlatList
                    data={this.state.bookInCart}
                    renderItem={
                        ({item}) => <CartItem info={item}/>
                    }
                    style={styles.list}
                />
                <Button title="下订单" onPress={() => {
                    this.toggleOrder();
                }}/>
            </View>
        );
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