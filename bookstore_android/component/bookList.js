import React from 'react';
import {FlatList, StyleSheet} from 'react-native';
import {Book} from "./book";
import {getBooks} from "../service/bookService";

export class BookList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {books: []};
    }

    componentDidMount() {

        const callback = (data) => {
            this.setState({books: data});
        };

        getBooks({"search": null}, callback);

    }

    render() {
        //console.log(this.props.filterText);
        const filterText = this.props.filterText;
        let dataSource = [];
        for (let key in this.state.books) {
            if (this.state.books[key].name.indexOf(filterText) === -1) {
                continue;
            } else {
                dataSource.push(this.state.books[key]);
            }
        }
        return (
            <FlatList
                data={dataSource}
                renderItem={
                    ({item}) => <Book navigation={this.props.navigation} info={item}/>
                }
                style={styles.list}
            />
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