import React from 'react';
import {List} from 'antd'
import {Book} from './Book'
import {getBooks} from "../services/bookService";


export class BookContent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {Books: []};
    }

    componentDidMount() {

        const callback = (data) => {
            this.setState({books: data});
        };

        getBooks({"search": null}, callback);

    }

    render() {
        // console.log(this.props.Link);
        const filterText = this.props.filterText;
        let dataSource = [];
        for (let key in this.state.books) {
            //console.log( key+' : '+this.state.books[key].name );
            if (this.state.books[key].name.indexOf(filterText) === -1) {
                continue;
            } else {
                dataSource.push(this.state.books[key]);
            }
        }
        return (
            <List
                grid={{gutter: 10, column: 4}}
                dataSource={dataSource}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book Link={this.props.Link} info={item}/>
                    </List.Item>
                )}
            />
        );
    }

}