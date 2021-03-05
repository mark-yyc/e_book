import React from "react";
import SearchBar from "./SearchBar";
import '../css/home.css'
import {BookContent} from "./bookContent";

class FilterableTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            filterText: '',
        };

        this.handleFilterTextChange = this.handleFilterTextChange.bind(this);

    }

    // componentDidMount() {
    //
    //     const callback =  (data) => {
    //         this.setState({books:data});
    //     };
    //
    //     getBooks({"search":null}, callback);
    //
    // }

    handleFilterTextChange(filterText) {
        this.setState({
            filterText: filterText
        });
    }

    render() {
        //console.log(this.state.books);
        return (
            <div>
                <SearchBar
                    filterText={this.state.filterText}
                    onFilterTextChange={this.handleFilterTextChange}
                />
                <BookContent
                    //products={this.state.books}
                    filterText={this.state.filterText}/>
                {/*<EditableTable*/}
                {/*    products={this.props.products}*/}
                {/*    filterText={this.state.filterText}*/}
                {/*/>*/}
            </div>
        );
    }
}

export default FilterableTable;