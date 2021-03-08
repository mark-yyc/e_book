import React from 'react';
import {Image, StyleSheet, Text, TouchableHighlight, View} from 'react-native';

export class Book extends React.Component {

    navigateToDetail(info) {
        //console.log(info);
        this.props.navigation.push("Detail", {detail: info});
    }

    render() {
        const {info} = this.props;
        return (
            <TouchableHighlight onPress={() => {
                this.navigateToDetail(info)
            }}>
                <View style={styles.container}>
                    <Image
                        source={{uri: info.image}}
                        style={styles.image}
                    />
                    <View style={styles.rightContainer}>
                        <Text style={styles.name}>{info.name}</Text>
                        <Text style={styles.author}>{info.author}</Text>
                    </View>
                    <View>
                        <Text>¥{info.price}</Text>
                    </View>
                </View>
            </TouchableHighlight>
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