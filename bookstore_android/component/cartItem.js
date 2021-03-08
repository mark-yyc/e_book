import React from 'react';
import {Image, StyleSheet, Text, View} from 'react-native';

export class CartItem extends React.Component {
    render() {
        const {info} = this.props;
        return (
            <View style={styles.container}>
                <Image
                    source={{uri: info.image}}
                    style={styles.image}
                />
                <View style={styles.rightContainer}>
                    <Text style={styles.name}>{info.name}</Text>
                    <Text style={styles.amount}>数量：{info.amount}</Text>
                </View>
                <View>
                    <Text>¥{info.price}</Text>
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