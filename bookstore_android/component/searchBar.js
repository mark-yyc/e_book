import React from 'react';
import {Dimensions, StyleSheet, TextInput, View} from 'react-native';

let {width, height} = Dimensions.get('window');

export class SearchBar extends React.Component {
    constructor(props) {
        super(props);
        this.handleFilterTextChange = this.handleFilterTextChange.bind(this);
    }

    handleFilterTextChange(text) {
        //console.log(text);
        this.props.onFilterTextChange(text);
    }

    render() {
        return (
            <View style={styles.container}>
                <View style={[styles.flexDirection, styles.inputHeight]}>
                    <View style={styles.flex}>
                        <TextInput
                            style={styles.input}
                            // returnKeyType="search"
                            placeholder="请输入关键字"
                            value={this.props.filterText}
                            onChangeText={text => this.handleFilterTextChange(text)}/>
                    </View>
                    {/*<View style={styles.btn}>*/}
                    {/*    <Text style={styles.search}*/}
                    {/*          onPress={() => this.search()}>{this.state.isCancel ? '取消' : '搜索'}</Text>*/}
                    {/*</View>*/}
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        height: 46,
        width: width
    },
    flex: {
        flex: 1
    },
    flexDirection: {
        flexDirection: 'row'
    },
    inputHeight: {
        height: 45,
    },
    input: {
        height: 45,
        borderWidth: 1,
        marginLeft: 1,
        paddingLeft: 5,
        borderColor: '#ccc',
        borderRadius: 4
    },
    btn: {
        width: 55,
        marginLeft: -5,
        marginRight: 5,
        backgroundColor: 'transparent',
        height: 45,
        justifyContent: 'center',
        alignItems: 'center'
    },
    search: {
        color: '#bbb',
        fontSize: 15,
        fontWeight: 'bold'
    }
});