import React from 'react';
import "../css/Book.css"
import {Card} from 'antd';

import {Link} from 'react-router-dom'

const {Meta} = Card;

export class Book extends React.Component {


    render() {
        // console.log(this.props.info);
        return (
            // <p>Book</p>
            <Link to={{
                pathname: this.props.Link.toString(),
                search: '?id=' + this.props.info.bookId
            }}
                  target="_blank"
            >
                <Card
                    hoverable
                    style={{width: 181}}
                    cover={<img alt="image" src={this.props.info.image} className={"bookImg"}/>}
                    //onClick={this.showBookDetails.bind(this, info.bookId)}
                >
                    <Meta title={this.props.info.name} description={'¥' + this.props.info.price}/>
                </Card>
            </Link>
        );
    }

}