import React, {useContext, useEffect, useRef, useState} from 'react';
import {Form, Input, Layout, Table} from 'antd';

const {Content} = Layout;

const EditableContext = React.createContext();

const EditableRow = ({index, ...props}) => {
    const [form] = Form.useForm();
    return (
        <Form form={form} component={false}>
            <EditableContext.Provider value={form}>
                <tr {...props} />
            </EditableContext.Provider>
        </Form>
    );
};

const EditableCell = ({
                          title,
                          editable,
                          children,
                          dataIndex,
                          record,
                          handleSave,
                          ...restProps
                      }) => {
    const [editing, setEditing] = useState(false);
    const inputRef = useRef();
    const form = useContext(EditableContext);
    useEffect(() => {
        if (editing) {
            inputRef.current.focus();
        }
    }, [editing]);

    const toggleEdit = () => {
        setEditing(!editing);
        form.setFieldsValue({
            [dataIndex]: record[dataIndex],
        });
    };

    const save = async e => {
        try {
            const values = await form.validateFields();
            toggleEdit();
            handleSave({...record, ...values});
        } catch (errInfo) {
            console.log('Save failed:', errInfo);
        }
    };

    let childNode = children;

    if (editable) {
        childNode = editing ? (
            <Form.Item
                style={{
                    margin: 0,
                }}
                name={dataIndex}
                rules={[
                    {
                        required: true,
                        message: `${title} is required.`,
                    },
                ]}
            >
                <Input ref={inputRef} onPressEnter={save} onBlur={save}/>
            </Form.Item>
        ) : (
            <div
                className="editable-cell-value-wrap"
                style={{
                    paddingRight: 24,
                }}
                onClick={toggleEdit}
            >
                {children}
            </div>
        );
    }

    return <td {...restProps}>{childNode}</td>;
};

class EditableTable extends React.Component {
    constructor(props) {
        super(props);
        this.columns = [
            {
                title: 'Book',
                dataIndex: 'book',
                editable: true,
            },
            {
                title: 'Author',
                dataIndex: 'author',
                editable: true,
            },
            {
                title: 'Language',
                dataIndex: 'language',
                editable: true,
            },
            {
                title: 'Published',
                dataIndex: 'published',
                editable: true,
            },
            {
                title: 'Sales',
                dataIndex: 'sales',
                editable: true,
            },
            // {
            //     title: 'operation',
            //     dataIndex: 'operation',
            //     render: (text, record) =>
            //         this.state.dataSource.length >= 1 ? (
            //             <Popconfirm title="Sure to delete?" onConfirm={() => this.handleDelete(record.key)}>
            //                 <a>Delete</a>
            //             </Popconfirm>
            //         ) : null,
            // },
        ];
        this.state = {
            dataSource: this.props.products,

        };
    }

    // handleDelete = key => {
    //     const dataSource = [...this.state.dataSource];
    //     this.setState({
    //         dataSource: dataSource.filter(item => item.key !== key),
    //     });
    // };

    // handleAdd = () => {
    //     const { count, dataSource } = this.state;
    //     const newData = {
    //         key: 'n',
    //         book: 'edit',
    //         author: 'edit',
    //         language: 'edit',
    //         published:'edit',
    //         sales:'edit',
    //     };
    //     this.setState({
    //         dataSource: [...dataSource, newData],
    //         count: count + 1,
    //     });
    // };

    handleSave = row => {
        const newData = [...this.state.dataSource];
        const index = newData.findIndex(item => row.key === item.key);
        const item = newData[index];
        newData.splice(index, 1, {...item, ...row});
        this.setState({
            dataSource: newData,
        });
    };

    render() {
        const filterText = this.props.filterText;
        // const { dataSource } = this.state;
        const dataSource = [];
        // this.props.products.forEach((product)=>{
        //     if (product.book.indexOf(filterText) === -1) {
        //         return;
        //     }
        //     else{
        //         dataSource.push(product);
        //
        //     }
        // });
        this.state.dataSource.forEach((product) => {
                if (product.book.indexOf(filterText) === -1) {
                    return;
                } else {
                    dataSource.push(product);

                }
            }
        )

        const components = {
            body: {
                row: EditableRow,
                cell: EditableCell,
            },
        };
        const columns = this.columns.map(col => {
            if (!col.editable) {
                return col;
            }

            return {
                ...col,
                onCell: record => ({
                    record,
                    editable: col.editable,
                    dataIndex: col.dataIndex,
                    title: col.title,
                    handleSave: this.handleSave,
                }),
            };
        });
        return (
            <Content style={{margin: '10px 16px 0', overflow: 'initial'}}>
                <div className="site-layout-background" style={{padding: 24}}>

                    {/*<Button*/}
                    {/*    onClick={this.handleAdd}*/}
                    {/*    type="primary"*/}
                    {/*    style={{*/}
                    {/*        marginBottom: 16,*/}
                    {/*        marginLeft:20,*/}
                    {/*    }}*/}
                    {/*>*/}
                    {/*    Add a row*/}
                    {/*</Button>*/}

                    <Table
                        components={components}
                        rowClassName={() => 'editable-row'}
                        bordered
                        dataSource={dataSource}
                        columns={columns}
                    />
                </div>
            </Content>
        );
    }
}

// class ContentInfo extends React.Component{
//     render() {
//         return (
//             <Content style={{ margin: '10px 16px 0', overflow: 'initial' }}>
//                 <div className="site-layout-background" style={{ padding: 24 }}>
//                     <EditableTable  />
//                 </div>
//             </Content>
//         )
//     }
// }

export default EditableTable;