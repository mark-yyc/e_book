import React from 'react';
import '../App.css'
import '../css/home.css'
import {Layout} from 'antd';
import SideBar from "../component/SideBar";
import HeaderInfo from "../component/Header";
import {getBook} from "../services/bookService";
import {BookDetail} from "../component/BookDetail";

const {Footer} = Layout;

class BookDetailView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {books: null};
    }

    componentDidMount() {
        const query = this.props.location.search;
        console.log(query);
        const arr = query.split('&');
        const bookId = arr[0].substr(4);
        getBook(bookId, (data) => {
            this.setState({bookInfo: data})
        })
    }

    render() {
        return (
            <Layout>
                <SideBar/>
                <Layout className="site-layout" style={{marginLeft: 200}}>
                    <HeaderInfo/>
                    <div className="bookList">
                        <BookDetail info={this.state.bookInfo}/>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ©2020 Created by Mark</Footer>
                </Layout>
            </Layout>
        )
    }


}

export default BookDetailView;