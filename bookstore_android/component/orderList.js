import {AsyncStorage, FlatList, StyleSheet, View} from 'react-native';
import React from 'react';
import {getUserOrder} from "../service/orderService";
import {Order} from "./order";

export class OrderList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            Orders: [],
        };
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({Orders: data});
        };
        AsyncStorage.getItem("userId", function (error, result) {
            if (error) {
                console.log('读取失败');
            } else {
                const userId = parseInt(result);
                getUserOrder(userId, callback);
            }
        });
    }

    render() {
        return (
            <View>
                <FlatList
                    data={this.state.Orders}
                    renderItem={
                        ({item}) => <Order info={item} navigation={this.props.navigation}/>
                    }
                    style={styles.list}
                />
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