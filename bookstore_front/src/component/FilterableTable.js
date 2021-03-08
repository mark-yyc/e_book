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


    handleFilterTextChange(filterText) {
        this.setState({
            filterText: filterText
        });
    }

    render() {
        // console.log(this.props.Link);
        return (
            <div>
                <SearchBar
                    filterText={this.state.filterText}
                    onFilterTextChange={this.handleFilterTextChange}
                />
                <BookContent
                    Link={this.props.Link}
                    filterText={this.state.filterText}/>
            </div>
        );
    }
}

export default FilterableTable;