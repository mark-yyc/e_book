import React from 'react';
import {AsyncStorage, Button, Dimensions, ImageBackground, StyleSheet, Text, TextInput, View} from 'react-native';
import {checkUser} from "../service/userService";

let {width, height} = Dimensions.get('window');

export class LoginScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            user: null
        }
    }

    toLogin() {
        // console.log(this.state.username);
        checkUser(this.state.username, this.state.password, (data) => {
            this.setState({user: data});
        });
    }

    render() {
        if (this.state.user == null) {
            //console.log("denied");
        } else {
            AsyncStorage.setItem("userId", this.state.user.userId.toString(), function (error) {
                if (error) {
                    console.log('存储失败');
                } else {
                    console.log('存储完成');
                }
            });
            this.props.navigation.navigate("Home", {item: this.state.user.userId});
        }
        return (
            <ImageBackground style={{flex: 1}} source={require('../resources/typewriter-801921_1920.jpg')}>
                {/*<View style={{ flex: 1}}>*/}
                <View style={styles.container}>
                    {/*<Text style={styles.titleStyle}>Login</Text>*/}
                    <TextInput
                        style={styles.textInputStyle}
                        onChangeText={text => this.setState({username: text})}
                        value={this.state.username}
                        placeholder={'请输入用户名'}/>

                    <TextInput
                        style={styles.textInputStyle}
                        placeholder='请输入密码'
                        onChangeText={text => this.setState({password: text})}
                        value={this.state.password}
                        password={true}/>

                    <Button color="grey" title="      登录     " onPress={() => {
                        this.toLogin();
                    }}>
                        {/*<Text color='black'>登录</Text>*/}
                    </Button>
                    <View style={styles.settingStyle}>
                        <Text>忘记密码</Text>
                        <Text>注册</Text>
                    </View>
                </View>
                {/*</View>*/}
            </ImageBackground>


        )
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: 120,
        // 侧轴的对齐方式
        justifyContent: "center",
        alignItems: 'center',
        // backgroundColor: '#add8e6'
    },
    textInputStyle: {
        width: width * 0.9,
        height: 40,
        backgroundColor: 'white',
        textAlign: 'center',
        marginTop: 10
        // marginBottom:5
    },
    // loginBtnStyle: {
    //     width: width*0.9,
    //     height: 40,
    //     color:'white',
    //     // backgroundColor:'white',
    //     marginTop:30,
    //     marginBottom: 20,
    //     borderRadius:10
    // },
    settingStyle: {
        fontSize: 20,
        width: width * 0.85,
        height: 40,
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
    },
    titleStyle: {
        fontSize: 40,
        color: 'white',
        alignItems: 'center',
        paddingBottom: 10
    },
});
