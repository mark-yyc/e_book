import React from 'react';
import {List} from 'antd'
import {Book} from './Book'
import {getBooks} from "../services/bookService";


export class BookContent extends React.Component {

    constructor(props) {
        super(props);
        //console.log(this.props.products);
        this.state = {Books: []};
    }

    componentDidMount() {

        const callback = (data) => {
            this.setState({books: data});
        };

        getBooks({"search": null}, callback);

    }

    render() {
        // for( let key in this.state.books ){
        //     console.log( key+' : '+this.state.books[key].name );
        // }
        //console.log(this.state.books);
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
        //console.log(this.state.books);
        //console.log(dataSource);
        //dataSource.toJSON();
        // for (i  = 0; i < this.state.books.length; i++) {
        //     if(this.state.books[i])
        // }
        //console.log(this.state.dataSource);
        //  this.state.books.forEach((product) => {
        //         if (product.name.indexOf(filterText) != -1)
        //             dataSource.push(product);
        //     }
        // );
        //console.log(dataSource);
        //console.log(this.state.books[1]);
        return (
            <List
                grid={{gutter: 10, column: 4}}
                //dataSource={this.state.books}
                dataSource={dataSource}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book info={item}/>
                    </List.Item>
                )}
            />
        );
    }

}