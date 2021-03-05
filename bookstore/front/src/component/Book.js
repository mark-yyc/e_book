import React from 'react';
import "../css/Book.css"
import {Card} from 'antd';

import {Link} from 'react-router-dom'

const {Meta} = Card;

export class Book extends React.Component {


    render() {

        const {info} = this.props;

        return (
            //<p>{info.name}</p>
            <Link to={{
                pathname: '/bookDetails',
                search: '?id=' + info.bookId
            }}
                  target="_blank"
            >
                <Card
                    hoverable
                    style={{width: 181}}
                    cover={<img alt="image" src={info.image} className={"bookImg"}/>}
                    //onClick={this.showBookDetails.bind(this, info.bookId)}
                >
                    <Meta title={info.name} description={'¥' + info.price}/>
                </Card>
                {/*<Meta title={info.name} description={'¥' + info.price}/>*/}
            </Link>
        );
    }

}