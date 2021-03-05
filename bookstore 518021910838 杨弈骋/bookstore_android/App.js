import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {HomeScreen} from "./view/homeScreen";
import {LoginScreen} from "./view/loginScreen";
import {BookScreen} from "./view/detailScreen";
import {OrderDetailScreen} from "./view/orderDetailScreen";


const Stack = createStackNavigator();

export default function App() {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Login">
                <Stack.Screen name="Login" component={LoginScreen}/>
                <Stack.Screen name="Home" component={HomeScreen}/>
                <Stack.Screen name="Detail" component={BookScreen}/>
                <Stack.Screen name="OrderDetail" component={OrderDetailScreen}/>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

