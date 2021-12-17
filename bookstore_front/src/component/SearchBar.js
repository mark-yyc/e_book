import React from 'react';
import {Input, Layout} from 'antd';

const {Content} = Layout;

class SearchBar extends React.Component {
    constructor(props) {
        super(props);
        // this.handleFilterTextChange = this.handleFilterTextChange.bind(this);
    }

    handleFilterTextChange(e) {
        this.props.onFilterTextChange(e.target.value);
    }

    render() {
        return (
            <Content style={{margin: '12px 16px 0', overflow: 'initial'}}>
                <div className="site-layout-background" style={{padding: 20}}>
                    <Input className='searchbar'
                           type="text"
                           placeholder="Search..."
                           value={this.props.filterText}
                           onChange={this.handleFilterTextChange}
                    />
                </div>
            </Content>
        )
    }
}

export default SearchBar;