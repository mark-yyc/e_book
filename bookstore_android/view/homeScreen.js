import React from 'react';
import {Text} from 'react-native';
import {FilterableTable} from "../component/filterableTable";
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {createStackNavigator} from '@react-navigation/stack';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import {CartScreen} from "./cartScreen";
import {OrderScreen} from "./orderScreen";

const Tab = createBottomTabNavigator();
const innerStack = createStackNavigator();

function Book({navigation}) {
    return (
        <FilterableTable navigation={navigation}/>
    );
}

function Cart({navigation}) {
    console.log("cartPage");
    return (
        // <Text>Cart</Text>
        <CartScreen navigation={navigation}/>
    );
}

function Order({navigation}) {
    return (
        <OrderScreen navigation={navigation}/>
    )
}

function Profile() {
    return (
        <Text>Profile</Text>
    )
}

export class HomeScreen extends React.Component {
    render() {
        return (
            <Tab.Navigator
                screenOptions={({route}) => ({
                    tabBarIcon: ({focused, color, size}) => {
                        let iconName;

                        if (route.name === 'Books') {
                            iconName = 'book';
                        }
                        if (route.name === 'Cart') {
                            iconName = 'shopping-cart';
                        }
                        if (route.name === 'Order') {
                            iconName = 'bookmark';
                        }
                        if (route.name === 'Profile') {
                            iconName = 'user';
                        }
                        return <FontAwesome name={iconName} size={size} color={color}/>;
                    },
                })}
                tabBarOptions={{
                    activeTintColor: 'tomato',
                    inactiveTintColor: 'gray',
                }}>

                <Tab.Screen name="Books" component={Book}/>
                <Tab.Screen name="Cart" component={Cart}/>
                <Tab.Screen name="Order" component={Order}/>
                <Tab.Screen name="Profile" component={Profile}/>
            </Tab.Navigator>
        )
    }
}







