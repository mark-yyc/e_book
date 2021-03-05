import React from 'react';
import {Button, StyleSheet, Text, View} from 'react-native';

export class Order extends React.Component {
    navigateToDetail(info) {
        //console.log(info);
        this.props.navigation.push("OrderDetail", {detail: info});
    }

    render() {
        const {info} = this.props;
        return (
            <View>
                <Text>订单号：{info.orderId}</Text>
                <View>
                    <View>
                        <Text>用户号：{info.userId}</Text>
                        <Text>日期：{info.orderDate}</Text>
                    </View>
                    <View>
                        <Button title="了解详情" onPress={() => {
                            this.navigateToDetail(info)
                        }}/>
                    </View>
                </View>
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
    amount: {
        fontSize: 20,
        textAlign: 'center',
    },
    rightContainer: {
        flex: 1,
        paddingRight: 10,
    },
});