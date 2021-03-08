import React from 'react';
import {View} from 'react-native';
import {SearchBar} from "./searchBar";
import {BookList} from "./bookList";

export class FilterableTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            filterText: '',
        };
        this.handleFilterTextChange = this.handleFilterTextChange.bind(this);
    }


    handleFilterTextChange(filterText) {
        this.setState({
            filterText: filterText
        });
    }

    render() {
        return (
            <View>
                <SearchBar
                    filterText={this.state.filterText}
                    onFilterTextChange={this.handleFilterTextChange}
                />
                <BookList navigation={this.props.navigation} filterText={this.state.filterText}/>
            </View>
        )
    }
}